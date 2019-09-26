package my.project.zookeeper.demo.lock;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 分布式锁启动类
 *
 * @author ZhangWeiKang
 * @create 2018/12/13
 */
public class LockTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock = new DistributedLock();
                    distributedLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程" + i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
