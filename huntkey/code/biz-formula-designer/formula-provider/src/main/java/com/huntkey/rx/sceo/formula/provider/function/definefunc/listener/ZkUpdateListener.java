package com.huntkey.rx.sceo.formula.provider.function.definefunc.listener;

import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.engine.function.Function;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.function.FuncionDefinedHelper;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.provider.utils.ClassLoaderUtils;
import com.huntkey.rx.sceo.formula.provider.utils.HdfsUtil;
import com.huntkey.rx.sceo.formula.provider.utils.JarClassLoader;
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
public class ZkUpdateListener implements IZkDataListener {

    private Logger logger = LoggerFactory.getLogger(ZkUpdateListener.class);
    @Autowired
    DefineFunctionService defineFunctionService;

    @Autowired
    FunctionHandlerBuildIn functionHandlerBuildIn;

    @Autowired
    private ZkClient zkClient;

    @Autowired
    private FuncionDefinedHelper funcionDefinedHelper;

    @Autowired
    private FunctionClassifyService functionClassifyService;

    public static String FUNC_NODE_4_MGR_UPDATE = "/funcNode4MgrUpdate";
    private Map<Long, String> cache = new HashMap<Long, String>();

    @Value("${jarHdfsDownloadPath}")
    private String jarHdfsDownloadPath;

    @Value("${hdfsJarPath}")
    private String hdfsJarPath;

    @PostConstruct
    public void init() {

        // TODO the node data will increase endlessly, so need to clear it.
        try {
            zkClient.create(FUNC_NODE_4_MGR_UPDATE, null, CreateMode.PERSISTENT);
        } catch (RuntimeException e) {
            // ignore.
        }
        Stat stat = new Stat();
        String nodeData = zkClient.readData(FUNC_NODE_4_MGR_UPDATE, stat);
        logger.info("init node data for update: {}", nodeData);

        ListenerUtils.parseNodeData2Map(nodeData, cache);
    }

    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {

        logger.info("ZkUpdateListener hash code: {}", this.hashCode());

        logger.info("data: {}", data);
        Map<Long, String> tmp = new HashMap<Long, String>();
        Map<Long, Tuple2> mapTuple2 = new HashMap<Long, Tuple2>();

        if (null != data) {
            ListenerUtils.parseNodeData2Map(data.toString(), tmp);
            parseNodeData2TupleTwo(data.toString(), mapTuple2);
        }

        logger.info("mapTuple2: {}", mapTuple2);

        Set<Long> keySet = tmp.keySet();
        logger.info("tmp set: {}", keySet);
        logger.info("cache set: {}", cache.keySet());

        keySet.removeAll(cache.keySet());
        logger.info("diff set: {}", keySet);

        for (Long key : keySet) {
            String id = tmp.get(key);

            TfmFunctionCompiled tfmFunctionCompiled = defineFunctionService.getCompileFunByCompileId(id);
            byte[] classBinary = tfmFunctionCompiled.getFuncClassBinary();
            String classFullName = tfmFunctionCompiled.getFuncClassFullName();
            // 通过id获取hasJar
            byte hasJar = tfmFunctionCompiled.getHasjar();

            try {
                // 不管是更新还是删除自定义函数，都需要卸载自定义函数jar，但这里只是从map中移除class
                logger.info("unloadClass: {}", classFullName);
                functionHandlerBuildIn.unloadCustomizedFunctionFromFromulaEngine(classFullName.substring(classFullName.lastIndexOf(".") + 1));
                // 获取本地hdfs down目录
//                String hdfsJarDownloadPath = this.getClass().getResource("/").getPath() + jarHdfsDownloadPath;
                String hdfsJarDownloadPath = System.getProperty("user.dir") + File.separator + jarHdfsDownloadPath;
                // 卸载jar，这里是真正的卸载
                JarClassLoader cl = ClassLoaderUtils.FUNC_CLASSLOADER_MAP.get(tfmFunctionCompiled.getFuncFunId());
                if (cl != null) {
                    cl.unloadJarFiles();
                    // 清除classloader引用，使其可以被回收
                    ClassLoaderUtils.FUNC_CLASSLOADER_MAP.remove(tfmFunctionCompiled.getFuncFunId());
                }
                // 删除本地旧的jar包
                File file = new File(hdfsJarDownloadPath + tfmFunctionCompiled.getFuncFunId());
                if (file.exists()) {
                    for (File f : file.listFiles()) {
                        f.delete();
                    }
                    file.delete();
                }
                // update Customized function
                if (mapTuple2.get(key) == null) {
                    Class<Function> clazz = null;
                    // 判断更新后是否有依赖jar
                    if (hasJar == Constant.HASJAR_NO) {
                        // 如果是更新后没有依赖jar了，则删除hdfs中已有的jar
                        HdfsUtil.deleteDir(hdfsJarPath + tfmFunctionCompiled.getFuncFunId());
                        clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName);
                    } else {
                        // 从hdfs下载最新的jar
                        HdfsUtil.copyFileToLocal(hdfsJarPath + tfmFunctionCompiled.getFuncFunId(), hdfsJarDownloadPath);
                        // load jar & class
                        clazz = (Class<Function>) ClassLoaderUtils.loadClass(classBinary, classFullName, hdfsJarDownloadPath, tfmFunctionCompiled.getFuncFunId());
                    }
                    functionHandlerBuildIn.loadCustomizedFunction2FormulaEngine(clazz);
                    logger.info("loadClass: {}", classFullName);
                } else {// 删除自定义函数
                    // 判断是否有依赖jar， 有则从hdfs删除
                    if (hasJar == Constant.HASJAR_YSE) {
                        HdfsUtil.deleteDir(hdfsJarPath + tfmFunctionCompiled.getFuncFunId());
                    }
                }
                cache.put(key, id);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("load failure." + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }

    static class Tuple2 {

        private String one;
        private String two;

        public Tuple2(String one, String two) {
            this.one = one;
            this.two = two;
        }

        public String getOne() {
            return one;
        }

        public void setOne(String one) {
            this.one = one;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        @Override
        public String toString() {
            return "Tuple2{" +
                    "one='" + one + '\'' +
                    ", two='" + two + '\'' +
                    '}';
        }
    }

    private void parseNodeData2TupleTwo(String nodeData, Map<Long, Tuple2> map) {

        if (null != nodeData) {
            String[] nodeArr = nodeData.split(";");
            for (String node : nodeArr) {

                if (node.trim().length() == 0) {
                    continue;
                }

                try {
                    String[] arr = node.split(":");
                    Long timestamp = Long.parseLong(arr[0]);
                    String compiledId = arr[1];
                    if (arr.length == 3) {
                        String op = arr[2];
                        map.put(timestamp, new Tuple2(compiledId, op));
                    }
                } catch (NumberFormatException e) {
                    // ignore.
                }
            }
        }
    }

    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        // nothing to do.
    }

}
