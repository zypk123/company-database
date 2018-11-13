package com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.impl;

import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassify;
import com.huntkey.rx.sceo.formula.common.model.FtmFunctionClassifyExample;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;
import com.huntkey.rx.sceo.formula.provider.config.TempClassFileConfig;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfmFunctionDefinitionMapper;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.dao.FtmFunctionClassifyMapper;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.listener.ZKDataListener4Create;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.listener.ZKDataListener4Update;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.provider.utils.CompilerUtils;
import com.huntkey.rx.sceo.formula.provider.utils.FreeMarkerUtils;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;

/**
 * 函数分类Service接口实现类
 *
 * @author zhangyu
 * @create 2017-07-26 11:50
 **/
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class FunctionClassifyServiceImpl extends BaseService<FtmFunctionClassify> implements FunctionClassifyService {

    private Logger logger = LoggerFactory.getLogger(FunctionClassifyServiceImpl.class);

    @Autowired
    private FtmFunctionClassifyMapper ftmFunctionClassifyMapper;

    @Autowired
    private TfmFunctionDefinitionMapper functionDefinitionMapper;

    @Autowired
    private FreeMarkerUtils freeMarkerUtils;

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private ZKDataListener4Create zKDataListener4Create;

    @Autowired
    private ZKDataListener4Update zKDataListener4Update;

    @Autowired
    private TempClassFileConfig tempClassFileConfig;

    /**
     * 修改函数分类的处理函数
     *
     * @param ftmFunctionClassify
     */
    private void handler(FtmFunctionClassify ftmFunctionClassify) {
        try {
            String fnccId = ftmFunctionClassify.getFnccId();
            // 生成源文件
            freeMarkerUtils.createSrcFile(ftmFunctionClassify.getFnccClassifyCode(), ftmFunctionClassify.getFnccClassifyDesc(), ftmFunctionClassify.getFnccClassifyName());

            File fileDir = ResourceUtils.getFile(freeMarkerUtils.getSrcFilePath());
            File file = new File(fileDir.getPath() + File.separator +
                    ftmFunctionClassify.getFnccClassifyCode().substring(0, ftmFunctionClassify.getFnccClassifyCode().lastIndexOf(".")).replace(".", "/"));
            // 通过CompilerUtils工具类对刚生成的源文件进行编译，生成二进制内容,并存入数据库
            File srcFileDir = new File(file + File.separator + ftmFunctionClassify.getFnccClassifyCode().substring((ftmFunctionClassify.getFnccClassifyCode().lastIndexOf(".") + 1)) + ".java");

            byte[] classCode = CompilerUtils.compile(srcFileDir, tempClassFileConfig.getTmpPath());
            //将重新编译后的文件存入数据库
            ftmFunctionClassifyMapper.updateBinary(fnccId, classCode);

            // 修改zookeeper订阅的节点，向该节点传入新的数据,内容:(新添加的函数分类id:时间戳)
            zkClient.subscribeDataChanges(ZKDataListener4Update.FUNC_NODE_4_CAF_UPDATE, zKDataListener4Update);
            String data = ftmFunctionClassify.getModtime().getTime() + ":" + fnccId + ";";
            String dataNode = zkClient.readData(ZKDataListener4Update.FUNC_NODE_4_CAF_UPDATE, new Stat());
            zkClient.writeData(ZKDataListener4Update.FUNC_NODE_4_CAF_UPDATE, (dataNode == null ? "" : dataNode) + data);
        } catch (Exception e) {
            logger.error("修改函数分类发生异常:", e);
            e.printStackTrace();
        } finally {
            // FileUtils.deleteDirectory(freeMarkerUtils.getSrcFilePath()); // 操作完成后删除文件夹
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int createFunctionClassify(FtmFunctionClassify ftmFunctionClassify) {
        int retInt = 0;
        try {
            // 1. 根据参数传入的数据通过freemarker生成函数分类超类模板
            File sourceFile = freeMarkerUtils.createSrcFile(ftmFunctionClassify.getFnccClassifyCode(), ftmFunctionClassify.getFnccClassifyDesc(), ftmFunctionClassify.getFnccClassifyName());
            // 2. 通过CompilerUtils工具类对刚生成的源文件进行编译，生成二进制内容,并存入数据库
            byte[] classCode = CompilerUtils.compile(sourceFile, tempClassFileConfig.getTmpPath());
            ftmFunctionClassify.setFuccClassifyBinary(classCode);
            saveSetting(ftmFunctionClassify);
            // 入库
            retInt = ftmFunctionClassifyMapper.insertSelective(ftmFunctionClassify);

            // 3. 修改zookeeper订阅的节点，向该节点传入新的数据,内容:(新添加的函数分类id:时间戳)
            zkClient.subscribeDataChanges(ZKDataListener4Create.FUNC_NODE_4_CAF_CREATE, zKDataListener4Create);
            String data = ftmFunctionClassify.getAddtime().getTime() + ":" + ftmFunctionClassify.getFnccId() + ";";
            logger.info("zk-data: {}", data);
            String dataNode = zkClient.readData(ZKDataListener4Create.FUNC_NODE_4_CAF_CREATE, new Stat());
            zkClient.writeData(ZKDataListener4Create.FUNC_NODE_4_CAF_CREATE, (dataNode == null ? "" : dataNode) + data);

        } catch (Exception e) {
            logger.error("新建函数分类发生异常:", e);
            e.printStackTrace();
        } finally {
            // FileUtils.deleteDirectory(freeMarkerUtils.getSrcFilePath()); // 操作完成后删除文件夹
        }
        return retInt;
    }

    private void loadImportClass(File sourceFile) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));

            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("import")) {
                    String classImported = line.replace("import", "")
                            .replace(";", "");
                    logger.info("load class: {}", classImported);

                    CompilerUtils.class.getClassLoader().loadClass(classImported.trim());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    @Override
    public byte[] getFuccClassifyBinaryById(String fnccId) {
        FtmFunctionClassify functionClassify = ftmFunctionClassifyMapper.selectByPrimaryKey(fnccId);
        return functionClassify.getFuccClassifyBinary();
    }

    @Override
    public String getFnccClassifyCodeById(String fnccId) throws Exception {
        FtmFunctionClassify functionClassify = ftmFunctionClassifyMapper.selectByPrimaryKey(fnccId);
        return functionClassify.getFnccClassifyCode();
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int deleteFunctionClassify(String fnccId) {
        // 如果该函数分类下有子类，不允许删除
        List<TfmFunctionDefinition> functionDefinitions = functionDefinitionMapper.selectByFundFunCatagoryId(fnccId);
        if (null != functionDefinitions && functionDefinitions.size() > 0) {
//            throw new RuntimeException("该分类下存在实现的子类，不允许删除");
            return -1;
        }
        int retInt = ftmFunctionClassifyMapper.updateIsenableByPrimaryKey(fnccId);
        return retInt;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public synchronized int updateFunctionClassify(FtmFunctionClassify ftmFunctionClassify) throws Exception {
        int retInt = 0;
        // 如果该函数分类下没有子类，直接进行更新操作
        String fnccId = ftmFunctionClassify.getFnccId();
        List<TfmFunctionDefinition> functionDefinitions = functionDefinitionMapper.selectByFundFunCatagoryId(fnccId);
        // 没有子类 ,随便改
        if (null == functionDefinitions || functionDefinitions.size() == 0) {

            updateSetting(ftmFunctionClassify);
            retInt = ftmFunctionClassifyMapper.updateByPrimaryKeySelective(ftmFunctionClassify);
            this.handler(ftmFunctionClassify);
            return retInt;

        } else { // 存在子类，只能修改分类名称以及描述，其他的不准修改
            updateSetting(ftmFunctionClassify);
            retInt = ftmFunctionClassifyMapper.updateClassifyNameAndDesc(fnccId, ftmFunctionClassify.getFnccClassifyName(), ftmFunctionClassify.getFnccClassifyDesc());
            this.handler(ftmFunctionClassify);
            return retInt;
        }
    }

    @Override
    public List<FtmFunctionClassify> getFtmFunctionClassifyList() {

        FtmFunctionClassifyExample example = new FtmFunctionClassifyExample();
        FtmFunctionClassifyExample.Criteria criteria = example.createCriteria();
        // 只查询未删除的
        criteria.andIsenableEqualTo((byte) 1);
        return ftmFunctionClassifyMapper.selectByExample(example);
    }


    @Override
    public FtmFunctionClassify selectByFnccId(String fnccId) {
        return ftmFunctionClassifyMapper.selectByPrimaryKey(fnccId);
    }

    @Override
    public String checkNameUnique(String fnccClassifyName) {
        String errorStr = "";
        int resInt = ftmFunctionClassifyMapper.checkNameUnique(fnccClassifyName);
        if (resInt > 0) {
            errorStr = "分类名称已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkCodeUnique(String fnccClassifyCode) {
        String errorStr = "";
        int resInt = ftmFunctionClassifyMapper.checkCodeUnique(fnccClassifyCode);
        if (resInt > 0) {
            errorStr = "分类编码已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public List<FtmFunctionClassify> getClassifyNameList() {
        FtmFunctionClassifyExample example = new FtmFunctionClassifyExample();
        FtmFunctionClassifyExample.Criteria criteria = example.createCriteria();
        criteria.andFnccStateEqualTo("0");
        // 只查询未删除的
        criteria.andIsenableEqualTo((byte) 1);
        return ftmFunctionClassifyMapper.selectByExample(example);
    }
}
