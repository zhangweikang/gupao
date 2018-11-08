package my.project.cloud.controller;

import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

/**
 * RxJava实现自定义熔断
 *
 * @author ZhangWeiKang
 * @create 2018/8/20
 */
public class RxJavaDemo {

    public static void main(String[] args) {
        Random random = new Random();

        Single.just("Hello,World") // just 发布数据
                .subscribeOn(Schedulers.immediate()) // 订阅的线程池 immediate = Thread.currentThread()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() { // 正常结束流程
                        System.out.println("执行结束！");
                    }

                    @Override
                    public void onError(Throwable e) { // 异常流程（结束）
                        System.out.println("熔断保护！");
                    }

                    @Override
                    public void onNext(String s) { // 数据消费 s = "Hello,World"
                        // 如果随机时间 大于 100 ，那么触发容错
                        int value = random.nextInt(200);

                        if (value > 100) {
                            throw new RuntimeException("Timeout!");
                        }

                        System.out.println("helloWorld() costs " + value + " ms.");

                    }
                });
    }
}
