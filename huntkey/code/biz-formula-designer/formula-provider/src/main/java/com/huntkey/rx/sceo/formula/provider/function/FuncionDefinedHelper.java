package com.huntkey.rx.sceo.formula.provider.function;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiledExample;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.engine.function.Function;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.config.TempClassFileConfig;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.dao.TfmFunctionCompiledMapper;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.impl.DefineFunctionServiceImpl;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.provider.utils.ClassLoaderUtils;
import com.huntkey.rx.sceo.formula.provider.utils.HdfsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author chenfei on 2017/8/7.
 */
@Component
public class FuncionDefinedHelper {

    private Logger logger = LoggerFactory.getLogger(DefineFunctionServiceImpl.class);

    private Map<String, byte[]> classifyCache = new HashMap<String, byte[]>();

    @Autowired
    private TempClassFileConfig tempClassFileConfig;

    @Autowired
    private FunctionClassifyService functionClassifyService;

    @Autowired
    private FunctionHandlerBuildIn functionHandlerBuildIn;

    @Autowired
    private TfmFunctionCompiledMapper tfmFunctionCompiledMapper;

    @Autowired
    private DefineFunctionService defineFunctionService;

    @Value("${jarHdfsDownloadPath}")
    private String jarHdfsDownloadPath;

    @Value("${hdfsJarPath}")
    private String hdfsJarPath;

    //为了让HdfsUtil的postConstruct方法在此类的postConstruct方法之前执行
    @Autowired
    private HdfsUtil temp;

    @PostConstruct
    public void init() {

        logger.info("FuncionDefinedHelper init.");
        List<FtmFunctionClassify> funcClassifyList = functionClassifyService.getFtmFunctionClassifyList();
        logger.info("classify size: {}", funcClassifyList == null ? 0 : funcClassifyList.size());

        for (FtmFunctionClassify classify : funcClassifyList) {
            String classifyCode = classify.getFnccClassifyCode();
            byte[] classBytes = classify.getFuccClassifyBinary();

            if (classBytes != null) {
                classifyCache.put(classifyCode, classBytes);
                File classTempFile = createTmpClassFile(classifyCode);
                logger.info("init -> classifyCode: {}", classifyCode);
                ClassLoaderUtils.loadClass(classBytes, classifyCode);
            }
        }

        TfmFunctionCompiledExample example = new TfmFunctionCompiledExample();
        example.createCriteria().andIsenableEqualTo((byte) 1);
        List<TfmFunctionCompiled> compileds = tfmFunctionCompiledMapper.selectByExampleWithBLOBs(example);

        for (TfmFunctionCompiled compiled : compileds) {
            String fundId = compiled.getFuncFunId();
            TfmFunctionDefinition functionDefinition = defineFunctionService.getDefineFunByDefineId(fundId);
            String state = functionDefinition.getFundState();

            if ("prohibit".equals(state)) {
                continue;
            }

            String classFullName = compiled.getFuncClassFullName();
            byte[] classBinary = compiled.getFuncClassBinary();

            logger.info("classFullName: {}, classBinary is {}.", classFullName, (classBinary == null ? "null" : "not null"));

            Class<Function> clazz = null;
            // 判断是否有依赖jar，有的话从hdfs中下载，并加载
            if (compiled.getHasjar() == Constant.HASJAR_YSE) {
//                String basePath = this.getClass().getResource("/").getPath() + jarHdfsDownloadPath;
                String basePath = System.getProperty("user.dir") + File.separator + jarHdfsDownloadPath;
                String hdfsJarDownloadPath = basePath + functionDefinition.getFundId();
                File file = new File(hdfsJarDownloadPath);
                if (file.exists() && file.list().length > 0) {
                    for (File f : file.listFiles()) {
                        f.delete();
                    }
                    file.delete();
                }
                // 从hdfs下载jar
                HdfsUtil.copyFileToLocal(hdfsJarPath + functionDefinition.getFundId() + "/", basePath);
                clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName, basePath, functionDefinition.getFundId());
            } else {
                clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName);
            }

            logger.info("Class Object: {}", clazz);
            functionHandlerBuildIn.loadCustomizedFunction2FormulaEngine(clazz);
        }

    }

    public byte[] getClassifyBytes(String classifyCode) {

        return classifyCache.get(classifyCode);
    }

    public void cache(String classifyCode, byte[] classifyBytes) {

        classifyCache.put(classifyCode, classifyBytes);
    }

    /**
     * 创建函数分类的.class文件
     *
     * @param classifyCode
     * @return
     */
    public File createTmpClassFile(String classifyCode) {

        FileOutputStream fos = null;

        try {
            int dot = classifyCode.lastIndexOf(".");
            String pack = classifyCode.substring(0, dot).replace(".", "/");
            String tmpPath = tempClassFileConfig.getTmpPath();

            new File(tmpPath + pack).mkdirs();
            File tmpClassFile = new File(tmpPath + pack + File.separator + classifyCode.substring(dot + 1) + ".class");
            fos = new FileOutputStream(tmpClassFile);
            fos.write(classifyCache.get(classifyCode));
            fos.flush();
            logger.info("Tmp class file path: {}", tmpClassFile.getAbsoluteFile());

            return tmpClassFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String getClassifyDesc(String funcName) {
        logger.info("keyset: {}", FunctionHandlerBuildIn.STATIC_MAPPINGS.keySet());
        logger.info("funcName:{}", funcName.substring(funcName.lastIndexOf(".") + 1));

        try {
            Class<Function> clazz = FunctionHandlerBuildIn.STATIC_MAPPINGS.get(funcName.substring(funcName.lastIndexOf(".") + 1));
            if (clazz == null) {
                return null;
            }
            return clazz.newInstance().buildFunctionDescriber().getClassifyDesc();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public FunctionDescriber getFunctionDescriber(String classFullName, byte[] classBytes) {
        try {
            Class<Function> clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBytes, classFullName);
            return clazz.newInstance().buildFunctionDescriber();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断该自定义函数是否与自定义函数重名
     *
     * @param classFullName
     * @return
     */
    public boolean isExistsInCustomizeFunction(String classFullName) {

        TfmFunctionCompiledExample example = new TfmFunctionCompiledExample();
        example.createCriteria().andIsenableEqualTo((byte) 1)
                .andFuncClassFullNameEqualTo(classFullName);

        int count = tfmFunctionCompiledMapper.countByExample(example);

        return count == 0 ? true : false;
    }

    /**
     * 判断自定义函数名是否与系统函数名重复
     *
     * @param classFullName
     * @return
     */
    public boolean isExistsInSystemFunction(String classFullName) {

        // 获取系统函数的函数名
        Set<String> keys = FunctionHandlerBuildIn.STATIC_MAPPINGS.keySet();

        int flag = 0;
        for (String key : keys) {
            if (key.equals(classFullName.substring(classFullName.lastIndexOf(".") + 1))) {
                flag = 1;
                break;
            }
        }

        return flag == 0 ? true : false;
    }

}
