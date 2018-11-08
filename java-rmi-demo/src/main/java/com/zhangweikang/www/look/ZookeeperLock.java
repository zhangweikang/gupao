package com.zhangweikang.www.look;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author ZhangWeiKang
 * @create 2018/6/11
 */
public class ZookeeperLock implements Lock, Watcher {

    public final static String connectionString = "master:2181,slave1:2181,slave2:2182,slave3:2181";
    private ZooKeeper zooKeeper = null;
    private String ROOT_NODE = "/locks";
    private String waitNode;
    private String nowNode;
    private CountDownLatch lockCountDownLatch;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public ZookeeperLock() {
        try {
            zooKeeper = new ZooKeeper(connectionString, 4000, this);
            waitConnection(countDownLatch, zooKeeper);
            //Thread.sleep(2000);

            Stat exists = zooKeeper.exists(ROOT_NODE, false);

            if (exists == null) {
                zooKeeper.create(ROOT_NODE, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
        }
    }

    private void waitConnection(CountDownLatch countDownLatch, ZooKeeper zooKeeper) throws InterruptedException {
        System.out.println("zooKeeperState = " + zooKeeper.getState());
        if (zooKeeper.getState().equals(ZooKeeper.States.CONNECTING)) {
            System.out.println(Thread.currentThread().getName() + "->链接线程等待");
            countDownLatch.await();
        }
    }

    //获得锁
    public void lock() {
        try {
            if (tryLock()) {
                System.out.println(Thread.currentThread().getName() + "->" + nowNode + ",获得锁");
            } else {
                waitForLock(waitNode);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        try {
            String threadName = Thread.currentThread().getName();
            //创建临时有序节点
            nowNode = zooKeeper.create(ROOT_NODE + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(threadName + "->" + nowNode + "尝试竞争锁");

            //获取根节点下的所有节点
            List<String> children = zooKeeper.getChildren(ROOT_NODE, false);

            //为所有节点排序
            SortedSet<String> sortedSet = new TreeSet<>();
            for (String s : children) {
                sortedSet.add(ROOT_NODE + "/" + s);
            }

            String first = sortedSet.first();//获取子节点中最小的节点
            SortedSet<String> lossNodes = sortedSet.headSet(nowNode);//获取比当前节点小的所有节点
            if (nowNode.equals(first)) {
                return true;
            }
            if (lossNodes.size() > 0) {
                waitNode = lossNodes.last();//获取当前节点的上一个节点为等待的节点
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        try {
            zooKeeper.delete(nowNode, -1);
            nowNode = null;
            zooKeeper.close();
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }

    private void waitForLock(String waitNode) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(waitNode, true);
        if (exists != null) {
            System.out.println(Thread.currentThread().getName() + "等待->" + waitNode + "释放锁");
            lockCountDownLatch = new CountDownLatch(1);
            lockCountDownLatch.await();
            System.out.println(Thread.currentThread().getName() + "->" + nowNode + "获得锁");
        }
    }

    public void process(WatchedEvent event) {
        System.out.println("eventState = " + event.getState());
        if (event.getState().equals(Watcher.Event.KeeperState.SyncConnected)) {
            //链接线程释放
            System.out.println(Thread.currentThread().getName() + "->链接线程释放");
            countDownLatch.countDown();
        }

        if (lockCountDownLatch != null) {
            //获取根节点下的所有节点
            List<String> children = null;
            try {
                children = zooKeeper.getChildren(ROOT_NODE, false);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

            //为所有节点排序
            SortedSet<String> sortedSet = new TreeSet<>();
            for (String s : children) {
                sortedSet.add(ROOT_NODE + "/" + s);
            }

            String first = sortedSet.first();//获取子节点中最小的节点

            if (nowNode.equals(first)) {
                lockCountDownLatch.countDown();
            }
        }
    }
}
