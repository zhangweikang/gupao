package my.project.boot.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源切换注解
 *
 * 该注解可直接作用于,类和方法
 *
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface TargetDataSource {
    String value() default "";
}