package com.huntkey.rx.sceo.formula.provider.zkclient.test;

import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenfei on 2017/7/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class ZkClientTest {

    @Autowired
    private ZkClient zkClient;

    @Test
    public void testCreate() {

        String mockList = "aaaaaaaaa: 1234567890";

//        String path = zkClient.create("/funcNode4Update", mockList, CreateMode.PERSISTENT);
        String path = zkClient.create("/funcNode4Mgr", mockList, CreateMode.PERSISTENT);
        //输出创建节点的路径
        System.out.println("created path:" + path);

    }

    @Test
    public void testGetDate() {
        Stat stat = new Stat();
        //获取 节点中的对象
        String mockList = zkClient.readData("/funcNode4Mgr", stat);
        System.out.println("mockList: " + mockList);
    }

    @Test
    public void testExists() {

        boolean flag = zkClient.exists("/funcNode4Create");
        System.out.println("flag: " + flag);
    }

    @Test
    public void testDelete() {

        boolean flag = zkClient.delete("/funcNode4MgrCreate");

        //删除含有子节点的节点，递归删除
        boolean flag2 = zkClient.deleteRecursive("/funcNode4MgrCreate");

        System.out.println("flag: " + flag);
        System.out.println("flag2: " + flag2);
    }

    @Test
    public void testUpdate() {

        String mockList = "aaaaaaaaa: 1234567890" + "\n"
                + "bbbbbbbb: 1234567891";

        zkClient.writeData("/funcNode4Create", mockList);
    }

    @Test
    public void testSubscribeDataChanges() {

        zkClient.subscribeDataChanges("/funcNode4Create", new ZKDataListener());
    }

    /**
     * 这个Listener就是对应的Watcher
     */
    private static class ZKDataListener implements IZkDataListener {

        public void handleDataChange(String dataPath, Object data) throws Exception {

            System.out.println(dataPath + ":" + data.toString());
        }

        public void handleDataDeleted(String dataPath) throws Exception {

            System.out.println(dataPath);
        }


    }
}
