package com.zhangweikang.www.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author ZhangWeiKang
 * @create 2018/6/10
 */
public class ZookeeperApiDemo {

    static CountDownLatch countDownLatch;

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {

        countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("master:2181,slave1:2181,slave2:2182,slave3:2181", 3000,new MyWatched(countDownLatch));
        waitConnection(zooKeeper,countDownLatch);

        //创建节点
        //监听节点
        zooKeeper.exists("/zhang1", true);
        zooKeeper.create("/zhang1", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //修改节点
        //监听节点
        Stat stat = zooKeeper.exists("/zhang1", true);
        zooKeeper.setData("/zhang1", "2".getBytes(), stat.getVersion());

        //创建子节点
        //监听节点
        zooKeeper.exists("/zhang1", true);
        zooKeeper.create("/zhang1/node1", "1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        //修改子节点
        //监听节点
        stat = zooKeeper.exists("/zhang1/node1", true);
        zooKeeper.setData("/zhang1/node1", "2".getBytes(), stat.getVersion());

        //删除子节点
        //监听节点
        stat = zooKeeper.exists("/zhang1/node1", true);
        zooKeeper.delete("/zhang1/node1",stat.getVersion());

        //删除节点,触发NodeDeleted事件
        stat = zooKeeper.exists("/zhang1", true);
        zooKeeper.delete("/zhang1",stat.getVersion());

        System.in.read();

        //zooKeeper.close();
    }

    private static void waitConnection(ZooKeeper zookeeper,CountDownLatch countDownLatch) throws InterruptedException {
        if (zookeeper.getState().equals(ZooKeeper.States.CONNECTING)){
            countDownLatch.await();
        }
    }

    static class MyWatched implements Watcher {
        CountDownLatch countDownLatch;

        public MyWatched(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        public void process(WatchedEvent event) {
            System.out.println("eventType = " + event.getType());
            if (event.getState().equals(Watcher.Event.KeeperState.SyncConnected)){
                countDownLatch.countDown();
            }
        }
    }
}
