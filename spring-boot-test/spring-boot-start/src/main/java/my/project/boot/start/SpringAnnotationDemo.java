package my.project.boot.start;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * 注解驱动启动spring
 *
 * @author ZhangWeiKang
 * @create 2018/7/19
 */
@Configuration
public class SpringAnnotationDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);

        context.addApplicationListener(event -> System.out.println("event = " + event.getSource()));
        context.refresh();

        context.publishEvent(new MyApplicationEvent("Hello,Word"));
        //System.out.println("context = " + context.getBean(SpringAnnotationDemo.class));
    }


    public static class MyApplicationEvent extends ApplicationEvent {
        public MyApplicationEvent(Object source) {
            super(source);
        }
    }
}
