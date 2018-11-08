package my.project.cloud.controller;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Futrue自定义熔断
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
public class FutureDemo {

    public static void main(String[] args) {
        Random random = new Random();//创建随机数

        ExecutorService service = Executors.newFixedThreadPool(1);//创建线程池

        Future<String> future = service.submit(() -> { // 正常流程
            // 如果随机时间 大于 100 ，那么触发容错
            int value = random.nextInt(200);


            Thread.sleep(value);

            System.out.println("helloWorld() costs " + value + " ms.");

            return "Hello,World";
        });

        try {
            future.get(100, TimeUnit.MILLISECONDS);//100秒后获取,Future结果,未获取到结果抛出异常
        } catch (Exception e) {
            // 超时流程
            System.out.println("超时保护！");
        }

        service.shutdown();
    }
}
