package com.huntkey.rx.sceo.formula.provider.function.definefunc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.file.FileUtils;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.common.util.StringUtils;
import com.huntkey.rx.sceo.formula.provider.config.TempClassFileConfig;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfmFunctionDefinitionMapper;
import com.huntkey.rx.sceo.formula.provider.function.FuncionDefinedHelper;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.dao.TfmFunctionCompiledMapper;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.listener.ZkCreateListener;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.listener.ZkUpdateListener;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.utils.CompilerUtils;
import com.huntkey.rx.sceo.formula.provider.utils.HdfsUtil;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

/**
 * 自定义函数Controller
 *
 * @author nidongx
 * @create 2017-07-26
 **/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class DefineFunctionServiceImpl extends BaseService<TfmFunctionDefinition> implements DefineFunctionService {

    private static Logger logger = LoggerFactory.getLogger(DefineFunctionServiceImpl.class);

    @Autowired
    private TfmFunctionDefinitionMapper tfmFunctionDefinitionMapper;

    @Autowired
    private TfmFunctionCompiledMapper tfmFunctionCompiledMapper;

    @Autowired
    private TfdFormulaMapper tfdFormulaMapper;

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private ZkCreateListener zkCreateListener;

    @Autowired
    private ZkUpdateListener zkUpdateListener;

    @Autowired
    private FuncionDefinedHelper funcionDefinedHelper;

    @Autowired
    private TempClassFileConfig tempClassFileConfig;

    @Value("${jarHdfsDownloadPath}")
    private String jarHdfsDownloadPath;

    @Value("${hdfsJarPath}")
    private String hdfsJarPath;

    @Value("${jarUploadPath}")
    private String jarUploadPath;

    /**
     * 查询自定义函数列表
     *
     * @param classifyId
     * @param fundName
     * @param fundState
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<TfmFunctionDefinition> queryDefineFunction(String classifyId, String fundName, String fundState, String sortName, String sortOrder, int pageNum, int pageSize) {

        List<TfmFunctionDefinition> list = null;
        TfmFunctionDefinitionExample example = new TfmFunctionDefinitionExample();
        TfmFunctionDefinitionExample.Criteria criteria = example.createCriteria();

        logger.info("classifyId: {}", classifyId);
        if (!StringUtil.isNullOrEmpty(classifyId)) {
            criteria.andFundFunCatagoryIdEqualTo(classifyId);
        }
        logger.info("fundName: {}", fundName);
        if (!StringUtil.isNullOrEmpty(fundName)) {
            criteria.andFundFunNameLike("%" + fundName + "%");
        }
        logger.info("fundState: {}", fundState);
        if (!StringUtil.isNullOrEmpty(fundState)) {
            criteria.andFundStateEqualTo(fundState);
        }
        if (!StringUtil.isNullOrEmpty(sortName) && !StringUtil.isNullOrEmpty(sortOrder)) {
            example.setOrderByClause(StringUtils.underscoreName(sortName) + " " + sortOrder);
        }
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        Page page = PageHelper.startPage(pageNum, pageSize);
        list = tfmFunctionDefinitionMapper.selectByExample(example);
        logger.info("list: {}", list);
        Pagination<TfmFunctionDefinition> pageInfo = new Pagination<TfmFunctionDefinition>(list, pageNum, pageSize, page.getTotal());
        return pageInfo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String createCustomizeFunction(TfmFunctionDefinition funcDefinition, String fileName,
                                          String sourceCode, String classFullName, String classifyCode, String jarPath) {

        logger.info("classifyCode: {}", classifyCode);
        // 创建函数分类class字节码文件
        funcionDefinedHelper.createTmpClassFile(classifyCode);

        try {
            String sourceCodeEx = checkAndSubstitute(classifyCode, sourceCode);

            // 是否有依赖jar
            byte hasJar = Constant.HASJAR_NO;
            byte[] funcClassBinary = null;

            if (StringUtil.isNullOrEmpty(jarPath)) {
                // 没有jar包依赖的编译
                funcClassBinary = CompilerUtils.compile(fileName, sourceCodeEx, tempClassFileConfig.getTmpPath());
            } else {
                // 有依赖jar，将依赖jar的目录加入到classpath中，然后编译
                funcClassBinary = CompilerUtils.compileWithDependencyJar(fileName, sourceCodeEx, tempClassFileConfig.getTmpPath(), jarPath);
                // FIXME 编译成功，但load失败，怎么处理？
                hasJar = Constant.HASJAR_YSE;
            }

            this.saveSetting(funcDefinition);
            TfmFunctionCompiled funcCompiled = new TfmFunctionCompiled();
            funcCompiled.preSetting();
            funcCompiled.setFuncClassBinary(funcClassBinary);
            funcCompiled.setFuncFunSource(sourceCode.getBytes());
            funcCompiled.setFuncFunId(funcDefinition.getFundId());
            funcCompiled.setFuncClassFullName(classFullName);
            funcCompiled.setHasjar(hasJar);

            tfmFunctionDefinitionMapper.insertSelective(funcDefinition);
            tfmFunctionCompiledMapper.insertSelective(funcCompiled);

            // 将jar复制到load jar的目录，从而避免本机也需要从hdfs再次下载文件，提高效率，目录名称已在配置文件中定义
            if (!StringUtil.isNullOrEmpty(jarPath)) {
                String jarHdfsDownloadPathDir = System.getProperty("user.dir") + File.separator + jarHdfsDownloadPath;
//                String jarHdfsDownloadPathDir = "/usr/local/bizformula/back-end/" + jarHdfsDownloadPath;
//                String jarHdfsDownloadPathDir = jarHdfsDownloadPath;
                File dir = new File(jarHdfsDownloadPathDir);
                File jarHdfsFile = new File(dir, funcDefinition.getFundId());
                if (!jarHdfsFile.exists()) {
                    jarHdfsFile.mkdirs();
                }
                FileUtils.copyDirectoryCover(jarPath, jarHdfsFile.getPath() + "/", true);
                // 将jar上传到hdfs
                HdfsUtil.putFileToHdfs(jarHdfsDownloadPathDir + funcDefinition.getFundId(), hdfsJarPath + funcDefinition.getFundId());
            }

            zkClient.subscribeDataChanges(ZkCreateListener.FUNC_NODE_4_MGR_CREATE, zkCreateListener);
            String data = funcCompiled.getAddtime().getTime() + ":" + funcCompiled.getFuncId() + ";";
            String dataNode = zkClient.readData(ZkCreateListener.FUNC_NODE_4_MGR_CREATE, new Stat());
            zkClient.writeData(ZkCreateListener.FUNC_NODE_4_MGR_CREATE, (dataNode == null ? "" : dataNode) + data);

            return funcCompiled.getFuncId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 检查代码以及添加继承extends代码
     *
     * @param classifyCode
     * @param sourceFileCode
     * @return
     */
    private String checkAndSubstitute(String classifyCode, String sourceFileCode) {

        // check.
        if (!sourceFileCode.contains("protected void buildParamsDesc(FunctionDescriber describer)")) {
            throw new RuntimeException("This function must contain protected void buildParamsDesc(FunctionDescriber describer) method.");
        }

        if (!sourceFileCode.contains("public ExprValue getValue(DataProvider provider) throws FormulaExceptio")) {
            throw new RuntimeException("public ExprValue getValue(DataProvider provider) throws FormulaExceptio");
        }

        // interpolation classify.
        int firstLeftCurlyBraceIndex = sourceFileCode.indexOf("{");
        sourceFileCode = sourceFileCode.substring(0, firstLeftCurlyBraceIndex) + " extends " + classifyCode
                + sourceFileCode.substring(firstLeftCurlyBraceIndex);

        return sourceFileCode;
    }

    /**
     * 根据compiled ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @Override
    public TfmFunctionCompiled getCompileFunByCompileId(String funcId) {

        return tfmFunctionCompiledMapper.selectByPrimaryKey(funcId);
    }

    /**
     * 根据ID查找自定义函数定义数据
     *
     * @param fundId
     * @return TfmFunctionCompiled
     */
    @Override
    public TfmFunctionDefinition getDefineFunByDefineId(String fundId) {

        return tfmFunctionDefinitionMapper.selectByPrimaryKey(fundId);
    }

    /**
     * 根据defined ID查找自定义函数编译数据
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    public TfmFunctionCompiled getCompileByDefineId(String funcId) {

        TfmFunctionCompiledExample example = new TfmFunctionCompiledExample();
        TfmFunctionCompiledExample.Criteria criteria = example.createCriteria();
        criteria.andFuncFunIdEqualTo(funcId);
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        List<TfmFunctionCompiled> list = tfmFunctionCompiledMapper.selectByExample(example);

        if (list.size() > 1) {
            throw new RuntimeException("result size large than one, please check it.");
        }

        return list.size() != 0 ? list.get(0) : null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String updateCustomizeFunction(TfmFunctionDefinition funcDefinition, String fileName,
                                          String sourceCode, String classFullName, String classifyCode, String jarPath) {

        String funcId = funcDefinition.getFundId();
        TfmFunctionCompiled functionCompiled = getCompileByDefineId(funcId);

        String fundName = functionCompiled.getFuncClassFullName().substring(functionCompiled.getFuncClassFullName().lastIndexOf(".") + 1);

        try {
            if (checkSum(sourceCode.getBytes(), functionCompiled.getFuncFunSource())) {
                return doUpdate(classFullName, classifyCode, sourceCode, fileName, functionCompiled, funcDefinition, jarPath);
            } else {
                if (!checkInUsing(fundName)) {
                    return doUpdate(classFullName, classifyCode, sourceCode, fileName, functionCompiled, funcDefinition, jarPath);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    private String doUpdate(String classFullName, String classifyCode, String sourceCode, String fileName,
                            TfmFunctionCompiled funcCompiled, TfmFunctionDefinition funcDefinition, String jarPath) {

        try {
            String sourceCodeEx = checkAndSubstitute(classifyCode, sourceCode);

            // 是否有依赖jar
            byte hasJar = Constant.HASJAR_NO;
            byte[] funcClassBinary = null;
            // 根据是否有依赖jar，调用不同的编译方法
            if (StringUtil.isNullOrEmpty(jarPath)) {
                funcClassBinary = CompilerUtils.compile(fileName, sourceCodeEx, tempClassFileConfig.getTmpPath());
            } else {
                // 有依赖jar，将依赖jar的目录加入到classpath中，然后编译
                funcClassBinary = CompilerUtils.compileWithDependencyJar(fileName, sourceCodeEx, tempClassFileConfig.getTmpPath(), jarPath);
                // FIXME 编译成功，但load失败，怎么处理？
                // FIXME 1.在这里load？ 反正后面还是要load，不如现在这里load，然后根据标识让本机的zk监听事件失效，避免重复load
                // FIXME 但是整体的页面响应时间就会变长，影响体验，特别是在jar比较多的时候，是否可以限制jar的数量？ 限制数量3，限制大小5M
                hasJar = Constant.HASJAR_YSE;
            }

            funcDefinition.setModtime(new Date());
            tfmFunctionDefinitionMapper.updateByPrimaryKeySelective(funcDefinition);

            funcCompiled.setFuncClassBinary(funcClassBinary);
            funcCompiled.setFuncFunSource(sourceCode.getBytes());
            funcCompiled.setFuncFunId(funcDefinition.getFundId());
            funcCompiled.setFuncClassFullName(classFullName);
            funcCompiled.setModtime(new Date());
            funcCompiled.setHasjar(hasJar);
            tfmFunctionCompiledMapper.updateByPrimaryKeySelective(funcCompiled);

            // 删除原来已有的jarHdfs文件，并加入新的文件
//            if (!StringUtil.isNullOrEmpty(jarPath)) {
////                File jarUploadDir = new File(jarPath);
//                String jarHdfsDownloadPathDir = this.getClass().getResource("/").getPath() + jarHdfsDownloadPath;
//                File dir = new File(jarHdfsDownloadPathDir);
//                File jarHdfsFile = new File(dir, funcDefinition.getFundId());
//                if (jarHdfsFile.exists()) {
//                    FileUtils.deleteDirectory(jarHdfsFile.getPath() + "/");
//                }
//                FileUtils.createDirectory(jarHdfsDownloadPath);
//                if (!jarHdfsFile.exists()) {
//                    jarHdfsFile.mkdirs();
//                }
//                FileUtils.copyDirectoryCover(jarPath, jarHdfsFile.getPath() + "/", true);
//            }

            // TODO 可以把未更新的jar的列表传过来，根据列表来选择删除哪些jar，上传哪些jar，以减少网络传输的数据量，提高效率
            // TODO 未更新的jar，都删除

            // 删除hdfs中已有的jar，如果存在的话
            // TODO 如果采用上面的方式，这里就不需要了
            HdfsUtil.deleteDir(hdfsJarPath + funcDefinition.getFundId());

            // 由于jar在被load后，无法删除，所以这里不再将upload目录的jar移动到hdfs download目录，而在ZKUpdateListener中释放jar资源并删除
            if (!StringUtil.isNullOrEmpty(jarPath)) {
                // 将jar上传到hdfs
                HdfsUtil.putFileToHdfs(jarPath, hdfsJarPath + funcDefinition.getFundId());
            }

            String op = "";
            if ("prohibit".equals(funcDefinition.getFundState())) {
                op = ":R";
            }

            zkClient.subscribeDataChanges(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, zkUpdateListener);
//            String data = funcCompiled.getModtime().getTime() + ":" + funcCompiled.getFuncId() + op + ";";
            String data = System.currentTimeMillis() + ":" + funcCompiled.getFuncId() + op + ";";
            String dataNode = zkClient.readData(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, new Stat());
            zkClient.writeData(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, (dataNode == null ? "" : dataNode) + data);

            return funcCompiled.getFuncId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkSum(byte[] newSourceCode, byte[] originSourceCode) {

        ByteArrayInputStream bisNew = null;
        ByteArrayInputStream bisOrigin = null;

        logger.info("newSourceCode.size: {}, originSourceCode.size: {}", newSourceCode.length, originSourceCode.length);

        try {
            bisNew = new ByteArrayInputStream(newSourceCode);
            bisOrigin = new ByteArrayInputStream(originSourceCode);

            CheckedInputStream csumNew = new CheckedInputStream(bisNew, new Adler32());
            CheckedInputStream csumOrigin = new CheckedInputStream(bisOrigin, new Adler32());

            csumNew.getChecksum().update(newSourceCode, 0, newSourceCode.length);
            csumOrigin.getChecksum().update(originSourceCode, 0, originSourceCode.length);

            long newValue = csumNew.getChecksum().getValue();
            long originValue = csumOrigin.getChecksum().getValue();

            logger.info("newValue: {}, originValue: {}", newValue, originValue);

            return newValue == originValue ? true : false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != bisNew) {
                try {
                    bisNew.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (null != bisOrigin) {
                try {
                    bisOrigin.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 删除自定义函数
     *
     * @param funcId
     * @return TfmFunctionCompiled
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int deleteCustomizeFunction(String funcId) {

        TfmFunctionDefinition functionDefinition = tfmFunctionDefinitionMapper.selectByPrimaryKey(funcId);
        TfmFunctionCompiled tfmFunctionCompiled = getCompileByDefineId(funcId);
        String classFullName = tfmFunctionCompiled.getFuncClassFullName();
        String classShortName = classFullName.substring(classFullName.lastIndexOf(".") + 1);

        // 如果函数没有被使用则可以删除
        if (!checkInUsing(classShortName)) {

            // 删除classpath下的上传的jar包
//            String jarPath = this.getClass().getResource("/").getPath() + jarUploadPath + classShortName + "/";
            String jarPath = System.getProperty("user.dir") + File.separator + jarUploadPath + classShortName + File.separator;
            FileUtils.deleteDirectory(jarPath);
            String hdfsPath = this.getClass().getResource("/").getPath() + jarHdfsDownloadPath + funcId + "/";
            FileUtils.deleteDirectory(hdfsPath);

            tfmFunctionCompiled.setIsenable(Constant.ISENABLE_NO);
            tfmFunctionCompiledMapper.updateByPrimaryKey(tfmFunctionCompiled);

            functionDefinition.setIsenable(Constant.ISENABLE_NO);

            zkClient.subscribeDataChanges(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, zkUpdateListener);
            String data = System.currentTimeMillis() + ":" + tfmFunctionCompiled.getFuncId() + ":R" + ";";
            String dataNode = zkClient.readData(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, new Stat());
            zkClient.writeData(ZkUpdateListener.FUNC_NODE_4_MGR_UPDATE, (dataNode == null ? "" : dataNode) + data);

            return tfmFunctionDefinitionMapper.updateByPrimaryKey(functionDefinition);
        }

        return 0;
    }

    @Override
    public List<TfmFunctionDefinition> getFunctionDefinitionByClassifyId(String classifyId) {

        return tfmFunctionDefinitionMapper.selectByFundFunCatagoryId(classifyId);
    }

    @Override
    public FunctionDescriber getFunctionDescriberById(String fundId) {

        try {
            TfmFunctionCompiled funcCompiled = this.getCompileByDefineId(fundId);
            FunctionDescriber describer = funcionDefinedHelper.getFunctionDescriber(funcCompiled.getFuncClassFullName(),
                    funcCompiled.getFuncClassBinary());

            return describer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int checkFuncName(String fundId, String fundFunName) {
        return tfmFunctionCompiledMapper.checkFuncName(fundId, fundFunName);
    }

    /**
     * 检查自定义函数是否在用
     */
    public boolean checkInUsing(String funcName) {

        if (null == funcName) {
            throw new RuntimeException("funcName must be passed in.");
        }

        TfdFormulaExample example = new TfdFormulaExample();
        TfdFormulaExample.Criteria criteria = example.createCriteria();

        funcName = "%" + funcName.trim() + "%";
        criteria.andFrmuFormulaContentLike(funcName);
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);

        int count = tfdFormulaMapper.countByExample(example);
        logger.info("The function: {} will be used in {} places.", funcName, count);

        return count == 0 ? false : true;
    }
}
