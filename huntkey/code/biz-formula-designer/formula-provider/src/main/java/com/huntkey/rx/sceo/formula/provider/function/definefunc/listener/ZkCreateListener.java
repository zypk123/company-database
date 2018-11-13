package com.huntkey.rx.sceo.formula.provider.function.definefunc.listener;

import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.engine.function.Function;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.utils.ClassLoaderUtils;
import com.huntkey.rx.sceo.formula.provider.utils.HdfsUtil;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author nidongx on 2017/7/28.
 */
@Component
public class ZkCreateListener implements IZkDataListener {

    private Logger logger = LoggerFactory.getLogger(ZkCreateListener.class);

    @Autowired
    DefineFunctionService defineFunctionService;

    @Autowired
    FunctionHandlerBuildIn functionHandlerBuildIn;

    @Autowired
    private ZkClient zkClient;

    @Value("${jarHdfsDownloadPath}")
    private String jarHdfsDownloadPath;

    @Value("${hdfsJarPath}")
    private String hdfsJarPath;

    public static String FUNC_NODE_4_MGR_CREATE = "/funcNode4MgrCreate";

    private Map<Long, String> cache = new HashMap<Long, String>();

    @PostConstruct
    public void init() {
        try {
            zkClient.create(FUNC_NODE_4_MGR_CREATE, null, CreateMode.PERSISTENT);
        } catch (RuntimeException e) {
            // ignore.
        }
        Stat stat = new Stat();
        String nodeData = zkClient.readData(FUNC_NODE_4_MGR_CREATE, stat);
        logger.info("init node data for create: {}", nodeData);

        ListenerUtils.parseNodeData2Map(nodeData, cache);
    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        logger.info("data: {}", data);
        Map<Long, String> tmp = new HashMap<Long, String>();

        if (null != data) {
            ListenerUtils.parseNodeData2Map(data.toString(), tmp);
        }

        Set<Long> keySet = tmp.keySet();
        logger.info("tmp set: {}", keySet);
        logger.info("cache set: {}", cache.keySet());

        keySet.removeAll(cache.keySet());
        logger.info("diff set: {}", keySet);

        try {
            for (Long key : keySet) {
                String id = tmp.get(key);
                TfmFunctionCompiled functionCompiled = defineFunctionService.getCompileFunByCompileId(id);
                byte[] classBinary = functionCompiled.getFuncClassBinary();
                String classFullName = functionCompiled.getFuncClassFullName();
                // 通过id获取hasJar
                byte hasJar = functionCompiled.getHasjar();

                Class<Function> clazz = null;
                // 判断是否有依赖jar
                if (hasJar == Constant.HASJAR_NO) {
                    clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName);
                } else {
                    // 下载jar，并将下载后的jar的目录传给ClassLoader，目录名要和上传的目录不同，目录名称已在配置文件中定义
                    // 需要先判断目录是否为空，不为空则代表是在此节点上新建的自定义函数，直接使用目录中的jar，无需再从hdfs下载
//                    String hdfsJarDownloadPath = this.getClass().getResource("/").getPath() + jarHdfsDownloadPath;
                    String hdfsJarDownloadPath = System.getProperty("user.dir") + File.separator + jarHdfsDownloadPath;
                    File file = new File(hdfsJarDownloadPath + functionCompiled.getFuncFunId());
                    if (!file.exists() && file.list().length == 0) {
                        // 从hdfs下载jar
                        HdfsUtil.copyFileToLocal(hdfsJarPath + functionCompiled.getFuncFunId(), hdfsJarDownloadPath);
                    }
                    clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName, hdfsJarDownloadPath, functionCompiled.getFuncFunId());
                }

                functionHandlerBuildIn.loadCustomizedFunction2FormulaEngine(clazz);
//                Map map = FunctionHandlerBuildIn.STATIC_MAPPINGS;
                cache.put(key, id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        // nothing to do.
    }

}
