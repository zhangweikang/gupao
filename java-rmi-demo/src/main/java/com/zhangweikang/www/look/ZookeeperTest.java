package com.zhangweikang.www.look;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper原生分布式锁基本实现
 *
 * @author ZhangWeiKang
 * @create 2018/6/11
 */
public class ZookeeperTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {

            new Thread(()->{
                try {
                    ZookeeperLock zookeeperLock = new ZookeeperLock();
                    countDownLatch.await();
                    zookeeperLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-"+i).start();
            countDownLatch.countDown();
        }

        System.in.read();
    }
}
