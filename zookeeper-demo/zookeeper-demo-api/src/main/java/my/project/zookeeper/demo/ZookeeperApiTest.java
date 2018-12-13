package my.project.zookeeper.demo;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangWeiKang
 * @create 2018/12/3
 */
public class ZookeeperApiTest {

    private static Logger logger = Logger.getLogger(ZookeeperApiTest.class);

    private static String hostPort = "master:2181,slave1:2181,slave2:2181,slave3:2181";

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        ZooKeeper zooKeeper = new ZooKeeper(hostPort, 3000, new ZookeeperWatch());

        waitCountDownLatch(zooKeeper);
        RootNodeWatch rootNodeWatch = new RootNodeWatch();

        /*zooKeeper.exists("/test1",rootNodeWatch);
        //zooKeeper.delete("/test1",-1);

        zooKeeper.create("/test1/childen1", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        byte[] data = zooKeeper.getData("/test1/childen1", rootNodeWatch, null);
        logger.info("/test1/childen1 getData :" + new String(data));
        Stat exists = zooKeeper.exists("/test1", rootNodeWatch);
        zooKeeper.setData("/test1", "test2".getBytes(),exists.getVersion());
        //Stat exists1 = zooKeeper.exists("/test1", rootNodeWatch);
        byte[] data1 = zooKeeper.getData("/test1", rootNodeWatch, null);
        logger.info("test1 getData :" + new String(data1));
        zooKeeper.delete("/test1",-1);
        zooKeeper.exists("/test1",rootNodeWatch);
        zooKeeper.create("/test1","test2".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);*/

        /*zooKeeper.delete("/test1/childen1",-1);*/
        zooKeeper.getData("/test1", rootNodeWatch, null);
        /*zooKeeper.setData("/test1", "test3".getBytes(),-1);*/
        zooKeeper.delete("/test1",-1);
        /*zooKeeper.exists("/test1",rootNodeWatch);
        zooKeeper.create("/test1","test4".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zooKeeper.getData("/test1", rootNodeWatch, null);
        zooKeeper.create("/test1/childen1", "childen1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.setData("/test1/childen1", "childen1".getBytes(), -1);
        zooKeeper.delete("/test1/childen1",-1);*/


        /*// 获取节点所有子节点
        List<String> test = zooKeeper.getChildren("/test", true);

        zooKeeper.exists("/test/hello", true);

        // 创建节点
         String s = zooKeeper.create("/test/hello6", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
         CreateMode.PERSISTENT);

        // 判断节点是否存在
        Stat exists = zooKeeper.exists("/test/hello", true);

        // 获取节点数据
        byte[] data = zooKeeper.getData("/test/hello", true, exists);*/

    }

    private static void waitCountDownLatch(ZooKeeper zooKeeper) {
        try {
            if (zooKeeper.getState().compareTo(ZooKeeper.States.CONNECTING) == 0){
                logger.info("waitCountDownLatch await");
                countDownLatch.await();
            }
        } catch (InterruptedException e) {
            logger.error("waitCountDownLatch error", e);
        }
    }

    private static class RootNodeWatch implements Watcher{
        @Override
        public void process(WatchedEvent event) {
            logger.info("RootNodeWatch type = " + event.getType() + "; state = " + event.getState());
        }
    }

    private static class ZookeeperWatch implements Watcher {
        @Override
        public void process(WatchedEvent event) {
            if (event.getState().compareTo(Event.KeeperState.SyncConnected) == 0) {
                if (event.getType().compareTo(Event.EventType.None) == 0){
                    logger.info("Zookeeper 建立连接成功");
                    countDownLatch.countDown();
                }
                logger.info(event.getType());
            } else {
                logger.info(event.getState());
                logger.info(event.getType());
            }
        }
    }
}
