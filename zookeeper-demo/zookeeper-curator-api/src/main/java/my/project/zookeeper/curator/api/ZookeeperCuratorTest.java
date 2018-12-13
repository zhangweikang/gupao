package my.project.zookeeper.curator.api;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.api.UnhandledErrorListener;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

/**
 * @author ZhangWeiKang
 * @create 2018/12/7
 */
public class ZookeeperCuratorTest {

    private static Logger logger = Logger.getLogger(ZookeeperCuratorTest.class);

    private static String hostPort = "master:2181,slave1:2181,slave2:2181,slave3:2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client =
            CuratorFrameworkFactory.builder().connectString(hostPort).retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .sessionTimeoutMs(4000).namespace("curator").build();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                logger.info("state :" + client.getState() + ";newState : " + newState);
            }
        });
        client.getCuratorListenable().addListener(new CuratorListener() {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
                logger.info(" CuratorEvent : " + event);
            }
        });
        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener() {
            @Override
            public void unhandledError(String message, Throwable e) {
                logger.info("message :" + message + ";error : " + e.getMessage());
            }
        });

        client.start();

        NodeCache nodeCache = new NodeCache(client,"/test",false);
        nodeCache.getListenable().addListener(()-> logger.info("NodeCache : " + nodeCache.getCurrentData()) );
        nodeCache.start(true);

        client = nodeCache.getClient();

        client.create().creatingParentsIfNeeded().forPath("/node","nodeValue".getBytes());

        /*List<String> strings =
                nodeCache.getClient().getChildren()
                    //.usingWatcher((CuratorWatcher) event -> System.out.println("event = " + event))
                .forPath("/");
        System.out.println("strings = " + strings);
        byte[] hello1s = nodeCache.getClient().getData().forPath("/hello9");
        System.out.println("hello1s = " + new String(hello1s));
        nodeCache.getClient().setData().forPath("/hello9","test".getBytes());
        nodeCache.getClient().delete().forPath("/hello9");
        nodeCache.getClient().create().forPath("/hello4");*/

    }
}
