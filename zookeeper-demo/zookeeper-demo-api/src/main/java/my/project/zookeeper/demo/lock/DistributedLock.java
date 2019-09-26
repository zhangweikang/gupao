package my.project.zookeeper.demo.lock;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 分布式锁
 *
 * @author ZhangWeiKang
 * @create 2018/12/13
 */
public class DistributedLock implements Watcher, Lock {
    private static Logger logger = Logger.getLogger(DistributedLock.class);

    // 客户端
    private ZooKeeper client = null;
    // 集群地址
    private static final String HostPort = "master:2181,slave1:2181,slave2:2181,slave3:2181";
    // 根节点
    private static final String RootPath = "/lock";
    // 当前节点
    private String nowNode;
    // 阻塞的节点
    private String waitNode;
    // zk连接线程阻塞
    private CountDownLatch connectLatch = new CountDownLatch(1);
    // 阻塞节点线程
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public DistributedLock() {
        try {
            client = new ZooKeeper(HostPort, 4000, this);
            await(client, connectLatch);

            Stat exists = client.exists(RootPath, false);
            if (exists == null) {
                client.create(RootPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (Exception e) {
            logger.error("创建节点失败", e);
        }
    }

    private void await(ZooKeeper client, CountDownLatch countDownLatch) throws InterruptedException {
        if (client.getState().compareTo(ZooKeeper.States.CONNECTING) == 0) {
            countDownLatch.await();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getState().compareTo(Event.KeeperState.SyncConnected) == 0) {
            if (event.getType().compareTo(Event.EventType.None) == 0) {
                connectLatch.countDown();
            } else if (event.getType().compareTo(Event.EventType.NodeDeleted) == 0) {
                countDownLatch.countDown();
            }
        }
    }

    @Override
    public void lock() {
        try {
            if (tryLock()) {
                logger.info(nowNode + " : 节点获取锁-----> " + Thread.currentThread().getName());
                return;
            }
            awaitLock(waitNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void awaitLock(String waitNode) throws KeeperException, InterruptedException {
        Stat exists = client.exists(waitNode, true);
        if (exists != null) {
            logger.info(nowNode + " 等待 " + waitNode +" 释放锁-----> " + Thread.currentThread().getName());
            countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
            logger.info(nowNode + " : 节点获得锁-----> " + Thread.currentThread().getName());
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 判定是否获得锁
     */
    @Override
    public boolean tryLock() {
        try {
            // 创建一个有序节点
            nowNode =
                client.create(RootPath + "/", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            logger.info(nowNode + " : 节点尝试获得锁获取锁-----> " + Thread.currentThread().getName());
            // 获取根目录下的所有节点
            List<String> childrens = client.getChildren(RootPath, false);

            SortedSet<String> sortedSet = new TreeSet<>();
            for (String children : childrens) {
                sortedSet.add(RootPath + "/" + children);
            }

            // 获取子节点中,最小的节点
            String firstNode = sortedSet.first();
            if (nowNode.equals(firstNode)) {
                return true;
            }

            SortedSet<String> lessNextNode = ((TreeSet<String>)sortedSet).headSet(nowNode);
            if (!lessNextNode.isEmpty()) {
                waitNode = lessNextNode.last();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        logger.info(nowNode + " : 节点释放锁-----> " + Thread.currentThread().getName());
        try {
            client.delete(nowNode, -1);
            nowNode = null;
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
