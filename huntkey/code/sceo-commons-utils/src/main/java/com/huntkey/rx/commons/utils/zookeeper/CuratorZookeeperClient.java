package com.huntkey.rx.commons.utils.zookeeper;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

/**
 * Zookeeper Curator工具类
 */
public class CuratorZookeeperClient {

    private static final int CONNECT_TIMEOUT = 15000;
    private static final int RETRY_TIME = Integer.MAX_VALUE;
    private static final int RETRY_INTERVAL = 1000;
    private static final Logger logger = LoggerFactory.getLogger(CuratorZookeeperClient.class);
    private CuratorFramework curator;

    private volatile static CuratorZookeeperClient instance;

    /**
     * key:父路径，如/jobcenter/client/goodscenter
     * value：Map-->key:子路径，如/jobcenter/client/goodscenter/goodscenter00000001,value:路径中的值
     */
    private static ConcurrentHashMap<String, Map<String, String>> zkCacheMap = new ConcurrentHashMap<String, Map<String, String>>();

    public static Map<String, Map<String, String>> getZkCacheMap() {
        return zkCacheMap;
    }

    /**
     * 获取Curator实例
     *
     * @param zkServers
     * @return
     */
    public static CuratorFramework newCurator(String zkServers) {
        return CuratorFrameworkFactory.builder().connectString(zkServers)
                .retryPolicy(new RetryNTimes(RETRY_TIME, RETRY_INTERVAL))
                .connectionTimeoutMs(CONNECT_TIMEOUT).build();
    }

    private CuratorZookeeperClient(String zkServers) {
        if (curator == null) {
            curator = newCurator(zkServers);
            curator.getConnectionStateListenable().addListener(new ConnectionStateListener() {
                public void stateChanged(CuratorFramework client, ConnectionState state) {
                    if (state == ConnectionState.LOST) {
                        //连接丢失
                        logger.info("lost session with zookeeper");
                    } else if (state == ConnectionState.CONNECTED) {
                        //连接新建
                        logger.info("connected with zookeeper");
                    } else if (state == ConnectionState.RECONNECTED) {
                        logger.info("reconnected with zookeeper");
                        //连接重连
                        for (ZkStateListener s : stateListeners) {
                            s.reconnected();
                        }
                    }
                }
            });
            curator.start();
        }
    }

    /**
     * 获取CuratorZookeeperClient对象，不要直接用调用构造方法创建对象
     *
     * @param zkServers
     * @return
     */
    public static CuratorZookeeperClient getInstance(String zkServers) {
        if (instance == null) {
            synchronized (CuratorZookeeperClient.class) {
                if (instance == null) {
                    logger.info("initial CuratorZookeeperClient instance");
                    instance = new CuratorZookeeperClient(zkServers);
                }
            }
        }
        return instance;
    }

    /**
     * 写数据
     *
     * @param path
     * @param content
     * @return 返回真正写到的路径
     * @throws Exception
     */
    public String write(String path, String content) throws Exception {
        try {
            StringBuilder sb = new StringBuilder(path);
            String writePath = curator.create().creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(sb.toString(), content.getBytes("utf-8"));
            return writePath;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute write function: ", e);
            return null;
        } finally {
            close();
        }
    }

    /**
     * 随机读取一个path子路径
     * 先从cache中读取，如果没有，再从zookeeper中查询
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String readRandom(String path) throws Exception {
        try {
            String parentPath = path;
            Map<String, String> cacheMap = zkCacheMap.get(path);
            if (cacheMap != null && cacheMap.size() > 0) {
                logger.debug("get random value from cache,path=" + path);
                return getRandomValue4Map(cacheMap);
            }
            if (curator.checkExists().forPath(path) == null) {
                logger.debug("path [{}] is not exists,return null", path);
                return null;
            } else {
                logger.debug("read random from zookeeper,path=" + path);
                cacheMap = new HashMap<String, String>();
                List<String> list = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
                if (list == null || list.size() == 0) {
                    logger.debug("path [{}] has no children return null", path);
                    return null;
                }
                Random rand = new Random();
                String child = list.get(rand.nextInt(list.size()));
                path = path + "/" + child;
                byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, path))
                        .forPath(path);
                String value = new String(b, "utf-8");
                if (StringUtils.isNotBlank(value)) {
                    cacheMap.put(path, value);
                    zkCacheMap.put(parentPath, cacheMap);
                }
                return value;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute readRandom function: ", e);
            return null;
        } finally {
            close();
        }
    }

    /**
     * 读取path下所有子路径下的内容
     * 先从map中读取，如果不存在，再从zookeeper中查询
     *
     * @param path
     * @return
     * @throws Exception
     */
    public List<String> readAll(String path) throws Exception {
        try {
            String parentPath = path;
            Map<String, String> cacheMap = zkCacheMap.get(path);
            List<String> list = new ArrayList<String>();
            if (cacheMap != null) {
                logger.debug("read all from cache,path=" + path);
                list.addAll(cacheMap.values());
                return list;
            }
            if (curator.checkExists().forPath(path) == null) {
                logger.debug("path [{}] is not exists,return null", path);
                return null;
            } else {
                cacheMap = new HashMap<String, String>();
                List<String> children = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
                if (children == null || children.size() == 0) {
                    logger.debug("path [{}] has no children,return null", path);
                    return null;
                } else {
                    logger.debug("read all from zookeeper,path=" + path);
                    String basePath = path;
                    for (String child : children) {
                        path = basePath + "/" + child;
                        byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, path))
                                .forPath(path);
                        String value = new String(b, "utf-8");
                        if (StringUtils.isNotBlank(value)) {
                            list.add(value);
                            cacheMap.put(path, value);
                        }
                    }
                }
                zkCacheMap.put(parentPath, cacheMap);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute readAll function: ", e);
            return null;
        } finally {
            close();
        }
    }

    /**
     * 随机获取Map中的一个值
     *
     * @param map
     * @return
     */
    public String getRandomValue4Map(Map<String, String> map) {
        Object[] values = map.values().toArray();
        Random rand = new Random();
        return values[rand.nextInt(values.length)].toString();
    }

    /**
     * 删除节点
     *
     * @param path
     * @throws Exception
     */
    public void delete(String path) throws Exception {
        try {
            if (curator.checkExists().forPath(path) != null) {
                curator.delete().inBackground().forPath(path);
                zkCacheMap.remove(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute delete function: ", e);
        } finally {
            close(); // 关闭连接
        }
    }

    /**
     * 获取路径下的所有子路径以及节点
     *
     * @param path
     * @return
     */
    public List<String> getChildren(String path) throws Exception {
        try {
            if (curator.checkExists().forPath(path) == null) {
                logger.debug("path [{}] is not exists,return null", path);
                return null;
            } else {
                List<String> children = curator.getChildren().forPath(path);
                return children;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute getChildren function: ", e);
            return null;
        } finally {
            close(); // 关闭连接
        }
    }

    /**
     * 关闭连接
     */
    public void close() {
        if (curator != null) {
            curator.close();
            curator = null;
        }
        zkCacheMap.clear();
    }

    /**
     * leader选举
     *
     * @param client
     * @param path
     * @param name
     */
    public static void selectLeader(CuratorFramework client, String path, String name) {

        try {
            LeaderClient leaderClient = new LeaderClient(client, path, name);
            leaderClient.start(); // 开启选举
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("some error occur when execute selectLeader function: ", e);
        } finally {
            client.close();
        }
    }

    /**
     * 分布式锁
     *
     * @param client
     * @param lockPath
     * @param resource
     * @param clientName
     * @param time
     * @param unit
     */
    public static void distributedLock(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName, Long time, TimeUnit unit) {
        try {
            LockClient lockClient = new LockClient(client, lockPath, resource, clientName);
            lockClient.doWork(time, unit);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("some error occur when execute distributedLock function: ", e);
        } finally {
            client.close();
        }
    }


    public static void main(String[] args) throws Exception {
        long time1 = System.currentTimeMillis();
        CuratorZookeeperClient curatorZookeeperClient = CuratorZookeeperClient.getInstance("10.3.98.161:2181,10.3.98.162:2181,10.3.98.163:2181");
//        CuratorZookeeperClient curatorZookeeperClient = new CuratorZookeeperClient("10.3.98.161:2181,10.3.98.162:2181,10.3.98.163:2181");
//        System.out.println(curatorZookeeperClient.getChildren("/storm/assignments"));
//        System.out.println(curatorZookeeperClient.readAll("/storm/assignments"));
//        curatorZookeeperClient.delete("/test"); // 删除节点
//        curatorZookeeperClient.write("/test","test");

        CuratorFramework curator = newCurator("10.3.98.161:2181,10.3.98.162:2181,10.3.98.163:2181");
        curator.start();
        CuratorZookeeperClient.selectLeader(curator, "/example", "testName");


        long time2 = System.currentTimeMillis();
        System.out.println("耗时：" + (time2 - time1) + "毫秒");
    }


    /**
     * zookeeper监听节点数据变化
     */
    public class ZKWatcher implements CuratorWatcher {
        private String parentPath;
        private String path;

        public ZKWatcher(String parentPath, String path) {
            this.parentPath = parentPath;
            this.path = path;
        }

        public void process(WatchedEvent event) throws Exception {
            Map<String, String> cacheMap = zkCacheMap.get(parentPath);
            if (cacheMap == null) {
                cacheMap = new HashMap<String, String>();
            }
            if (event.getType() == Event.EventType.NodeDataChanged
                    || event.getType() == Event.EventType.NodeCreated) {
                byte[] data = curator.getData().
                        usingWatcher(this).forPath(path);
                cacheMap.put(path, new String(data, "utf-8"));
                logger.info("add cache={}", new String(data, "utf-8"));
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                cacheMap.remove(path);
                logger.info("remove cache path={}", path);
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                //子节点发生变化，重新进行缓存
                cacheMap.clear();
                List<String> children = curator.getChildren().usingWatcher(new ZKWatcher(parentPath, path)).forPath(path);
                if (children != null && children.size() > 0) {
                    for (String child : children) {
                        String childPath = parentPath + "/" + child;
                        byte[] b = curator.getData().usingWatcher(new ZKWatcher(parentPath, childPath))
                                .forPath(childPath);
                        String value = new String(b, "utf-8");
                        if (StringUtils.isNotBlank(value)) {
                            cacheMap.put(childPath, value);
                        }
                    }
                }
                logger.info("node children changed,recaching path={}", path);
            }
            zkCacheMap.put(parentPath, cacheMap);
        }
    }

    public final Set<ZkStateListener> stateListeners = new CopyOnWriteArraySet<ZkStateListener>();

    public void addStateListener(ZkStateListener listener) {
        stateListeners.add(listener);
    }
}