package com.huntkey.rx.sceo.formula.provider.function.functionclassify.listener;

import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;
import com.huntkey.rx.sceo.formula.provider.function.FuncionDefinedHelper;
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
 * 新建监听
 *
 * @author zhangyu
 * @create 2017-07-27 10:56
 **/
@Component
public class ZKDataListener4Create implements IZkDataListener {

    private Logger logger = LoggerFactory.getLogger(ZKDataListener4Create.class);

    @Autowired
    private FunctionClassifyService functionClassifyService;

    @Autowired
    private FunctionDescriberService functionDescriberService;

    @Autowired
    private ZkClient zkClient;

    public static String FUNC_NODE_4_CAF_CREATE = "/funcNode4Create";

    private Map<Long, String> cache = new HashMap<Long, String>();

    @Autowired
    private FuncionDefinedHelper funcionDefinedHelper;

    @PostConstruct
    public void init() {
        try {
            zkClient.create(FUNC_NODE_4_CAF_CREATE, null, CreateMode.PERSISTENT);
        } catch (RuntimeException e) {

        }
        Stat stat = new Stat();
        String nodeData = zkClient.readData(FUNC_NODE_4_CAF_CREATE, stat);
        logger.info("init node data for classifyCreate: {}", nodeData);
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

        for (Long key : keySet) {
            String id = tmp.get(key);
            byte[] classCode = functionClassifyService.getFuccClassifyBinaryById(id);
            String className = functionClassifyService.getFnccClassifyCodeById(id);
            // 加载到JVM
            ClassLoaderUtils.loadClass(classCode, className);
            funcionDefinedHelper.cache(className, classCode);
            // add classify to orderMap for showing.
            functionDescriberService.addClassify(className);
            cache.put(key, id);
        }
    }

    @Override
    public void handleDataDeleted(String s) throws Exception {
        // nothing to do.
    }
}
