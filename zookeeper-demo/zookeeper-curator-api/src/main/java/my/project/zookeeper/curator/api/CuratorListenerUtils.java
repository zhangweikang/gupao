package my.project.zookeeper.curator.api;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * curator管理zookeeper的相关方法工具类
 * 
 * @author songqinghu 要点: 1.连接的建立 (两种 OK--使用权限方式) 2.节点的管理操作,增删改查--层叠节点的操作(OK --设立命名空间) 3.节点的监听操作,和无限监听事件触发(OK --使用第三种方法)
 *         4.节点的访问控制ACL操作,密码的添加,修改-->连接中设置 5.节点原子性操作 6.节点的分布式锁操作 7.分布式队列
 */
public class CuratorListenerUtils {

    private static Logger logger = Logger.getLogger(CuratorListenerUtils.class);

    private static String hostPort = "master:2181,slave1:2181,slave2:2181,slave3:2181";

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
            4,10,10000,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(),Executors.defaultThreadFactory());

    /**
     * 先测试玩玩
     * 
     * @描述：XXXXXXX
     * @param args
     * @return void
     * @exception
     * @createTime：2016年5月17日
     * @author: songqinghu
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CuratorFramework client = clientOne();
        // setListenterDateNode();
        // setListenterThreeOne(client);
        // setListenterThreeTwo(client);
        setListenterThreeThree(client);
        // getDataNode(client, "/two");
        // setDataNode(client, "/two", "sss");

        byte[] bytes = client.getData().forPath("/node");
        logger.info("nodeValue : " + new String(bytes));
        client.setData().forPath("/node","word".getBytes());

        client.create().forPath("/node/test1","test1".getBytes());
        byte[] bytes1 = client.getData().forPath("/node/test1");
        logger.info("/node/test1 : " + new String(bytes1));
        client.setData().forPath("/node/test1","word".getBytes());
        client.delete().forPath("/node/test1");

        client.delete().forPath("/node");
        client.create().forPath("/node","test".getBytes());
        //Thread.sleep(Long.MAX_VALUE);
    }

    /**
     *
     * @描述：创建一个zookeeper连接---连接方式一: 最简单的连接
     * @return void
     * @exception
     * @createTime：2016年5月17日
     * @author: songqinghu
     */
    private static CuratorFramework clientOne() {
        // 连接时间 和重试次数
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        /*CuratorFramework client = CuratorFrameworkFactory.newClient(hostPort, retryPolicy);
        client.start();*/

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .namespace("curator")
                .connectString(hostPort)
                .sessionTimeoutMs(4000)
                .retryPolicy(retryPolicy)
                .build();
        client.start();
        return client;
    }

    /**
     *
     * @描述：创建一个zookeeper连接---连接方式二:优选这个
     * @return void
     * @exception
     * @createTime：2016年5月17日
     * @author: songqinghu
     */
    private static CuratorFramework clientTwo() {

        // 默认创建的根节点是没有做权限控制的--需要自己手动加权限???----
        ACLProvider aclProvider = new ACLProvider() {
            private List<ACL> acl;

            @Override
            public List<ACL> getDefaultAcl() {
                if (acl == null) {
                    ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
                    acl.clear();
                    acl.add(new ACL(Perms.ALL, new Id("auth", "admin:admin")));
                    this.acl = acl;
                }
                return acl;
            }

            @Override
            public List<ACL> getAclForPath(String path) {
                return acl;
            }
        };
        String scheme = "digest";
        byte[] auth = "admin:admin".getBytes();
        int connectionTimeoutMs = 5000;
        String connectString = "10.125.2.44:2181";
        String namespace = "testnamespace";
        CuratorFramework client =
            CuratorFrameworkFactory.builder().aclProvider(aclProvider).authorization(scheme, auth)
                .connectionTimeoutMs(connectionTimeoutMs).connectString(connectString).namespace(namespace)
                .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000)).build();
        client.start();
        return client;
    }

    /**
     *
     * @描述：第一种监听器的添加方式: 对指定的节点进行添加操作 仅仅能监控指定的本节点的数据修改,删除 操作 并且只能监听一次 --->不好
     * @return void
     * @exception
     * @createTime：2016年5月18日
     * @author: songqinghu
     * @throws Exception
     */
    private static void setListenterOne(CuratorFramework client) throws Exception {
        // 注册观察者，当节点变动时触发
        byte[] data = client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                logger.info("获取 two 节点 监听器 : " + event);
            }
        }).forPath("/two");
        logger.info("two 节点数据: " + new String(data));
    }

    /**
     *
     * @描述：第二种监听器的添加方式: 也是一次性的监听操作,使用后就无法在继续监听了
     * @return void
     * @exception
     * @createTime：2016年5月18日
     * @author: songqinghu
     * @throws Exception
     */
    private static void setListenterTwo(CuratorFramework client) throws Exception {

        ExecutorService pool = Executors.newCachedThreadPool();

        CuratorListener listener = new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                logger.info("监听器  : " + event.toString());
            }
        };
        client.getCuratorListenable().addListener(listener, pool);
        client.getData().inBackground().forPath("/two");
        client.getData().inBackground().forPath("/two");
        client.getData().inBackground().forPath("/two");
        client.getData().inBackground().forPath("/two");
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     *
     * @描述：第三种监听器的添加方式: Cache 的三种实现 实践 Path Cache：监视一个路径下1）孩子结点的创建、2）删除，3）以及结点数据的更新。
     *                  产生的事件会传递给注册的PathChildrenCacheListener。 Node Cache：监视一个结点的创建、更新、删除，并将结点的数据缓存在本地。 Tree Cache：Path
     *                  Cache和Node Cache的“合体”，监视路径下的创建、更新、删除事件，并缓存路径下所有孩子结点的数据。
     * @return void
     * @exception
     * @createTime：2016年5月18日
     * @author: songqinghu
     * @throws Exception
     */
    // 1.path Cache 连接 路径 是否获取数据
    // 能监听所有的字节点 且是无限监听的模式 但是 指定目录下节点的子节点不再监听
    private static void setListenterThreeOne(CuratorFramework client) throws Exception {
        PathChildrenCache childrenCache = new PathChildrenCache(client, "/test", true);
        PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                logger.info("开始进行事件分析:-----");
                ChildData data = event.getData();
                switch (event.getType()) {
                    case CHILD_ADDED:
                        logger.info("CHILD_ADDED : " + data.getPath() + "  数据:" + data.getData());
                        break;
                    case CHILD_REMOVED:
                        logger.info("CHILD_REMOVED : " + data.getPath() + "  数据:" + data.getData());
                        break;
                    case CHILD_UPDATED:
                        logger.info("CHILD_UPDATED : " + data.getPath() + "  数据:" + data.getData());
                        break;
                    default:
                        break;
                }
            }
        };
        childrenCache.getListenable().addListener(childrenCacheListener);
        logger.info("Register zk watcher successfully!");
        childrenCache.start(StartMode.POST_INITIALIZED_EVENT);
    }

    // 2.Node Cache 监控本节点的变化情况 连接 目录 是否压缩
    // 监听本节点的变化 节点可以进行修改操作 删除节点后会再次创建(空节点)
    private static void setListenterThreeTwo(CuratorFramework client) throws Exception {
        // 设置节点的cache
        final NodeCache nodeCache = new NodeCache(client, "/test", false);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                logger.info("the test node is change and result is :");
                logger.info("path : " + nodeCache.getCurrentData().getPath());
                logger.info("data : " + new String(nodeCache.getCurrentData().getData()));
                logger.info("stat : " + nodeCache.getCurrentData().getStat());
            }
        });
        nodeCache.start();
    }

    // 3.Tree Cache
    // 监控 指定节点和节点下的所有的节点的变化--无限监听 可以进行本节点的删除(不在创建)
    private static void setListenterThreeThree(CuratorFramework client) throws Exception {
        // 设置节点的cache
        TreeCache treeCache = new TreeCache(client, "/node");
        // 设置监听器和处理过程
        treeCache.getListenable().addListener(new TreeCacheListener() {
            @Override
            public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                logger.info("TreeCacheEvent : " + event);
                ChildData data = event.getData();
                if (data != null) {
                    switch (event.getType()) {
                        case NODE_ADDED:
                            logger.info("NODE_ADDED : " + data.getPath() + "  数据:" + new String(data.getData()));
                            break;
                        case NODE_REMOVED:
                            logger.info("NODE_REMOVED : " + data.getPath() + "  数据:"
                                + new String(data.getData()));
                            break;
                        case NODE_UPDATED:
                            logger.info("NODE_UPDATED : " + data.getPath() + "  数据:"
                                + new String(data.getData()));
                            break;
                        default:
                            break;
                    }
                } else {
                    logger.info("data is null : " + event.getType());
                }
            }
        });
        // 开始监听
        treeCache.start();
    }

}