package com.huntkey.rx.sceo.formula.provider.function.functionclassify.listener;

import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.listener.ListenerUtils;
import com.huntkey.rx.sceo.formula.provider.function.functionclassify.service.FunctionClassifyService;
import com.huntkey.rx.sceo.formula.provider.utils.ClassLoaderUtils;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 更新监听
 *
 * @author zhangyu
 * @create 2017-08-09 10:38
 **/
@Component
public class ZKDataListener4Update implements IZkDataListener {

    private Logger logger = LoggerFactory.getLogger(ZKDataListener4Update.class);

    @Autowired
    private FunctionClassifyService functionClassifyService;

    @Autowired
    private FunctionDescriberService functionDescriberService;

    @Autowired
    private ZkClient zkClient;

    public static String FUNC_NODE_4_CAF_UPDATE = "/funcNode4Update";

    private Map<Long, String> cache = new HashMap<Long, String>();

    @PostConstruct
    public void init() {
        try {
            zkClient.create(FUNC_NODE_4_CAF_UPDATE, null, CreateMode.PERSISTENT);
        } catch (RuntimeException e) {

        }
        Stat stat = new Stat();
        String nodeData = zkClient.readData(FUNC_NODE_4_CAF_UPDATE, stat);
        logger.info("init node data for classifyUPdate: {}", nodeData);
        ListenerUtils.parseNodeData2Map(nodeData, cache);
    }

    /**
     * 数据改变时触发
     *
     * @param dataPath
     * @param data
     * @throws Exception
     */
    @Override
    public void handleDataChange(String dataPath, Object data) throws Exception {
        logger.info("updaData: {}", data);
        Map<Long, String> tmp = new HashMap<Long, String>();

        if (null != data) {
            ListenerUtils.parseNodeData2Map(data.toString(), tmp);
        }

        Set<Long> keySet = tmp.keySet();
        logger.info("tmp set: {}", keySet);
        logger.info("cache set: {}", cache.keySet());

        keySet.removeAll(cache.keySet());
        logger.info("diff set: {}", keySet);

        for (Long key : keySet) {
            String id = tmp.get(key);
            byte[] classCode = functionClassifyService.getFuccClassifyBinaryById(id);
            String className = functionClassifyService.getFnccClassifyCodeById(id);

            // 加载到JVM
            ClassLoaderUtils.loadClass(classCode, className);

            // add classify to orderMap for showing.
            functionDescriberService.addClassify(className);
            cache.put(key, id);
        }
    }

    /**
     * 数据删除时触发
     *
     * @param dataPath
     * @throws Exception
     */
    @Override
    public void handleDataDeleted(String dataPath) throws Exception {
        // nothing to do.
    }
}
