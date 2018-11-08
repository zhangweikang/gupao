package my.project.boot.datasource.aop;

import my.project.boot.annotation.TargetDataSource;
import my.project.boot.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义数据源切换切面
 *
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
@Component//交由Spring管理
@Aspect//定义切面注解
public class DataSourceAspect {

    /**
     * 配置aop,切面包名
     */
    @Pointcut(value = "execution(* my.project.boot.mapper..*.*(..))")
    public void  dataSourcePointcut() {
    }

    /**
     * 执行类方法前调用
     *
     * 类上的TargetDataSource优于方法上的TargetDataSource标记
     * 设置当前线程数据源名称
     */
    @Before("dataSourcePointcut()")
    public void before(JoinPoint joinPoint){

        try {
            Object target = joinPoint.getTarget();
            Class<?>[] interfaces = target.getClass().getInterfaces();
            Class<?> anInterface = interfaces[0];

            TargetDataSource annotation = anInterface.getAnnotation(TargetDataSource.class);

            if (annotation != null){
                String value = annotation.value();
                DynamicDataSourceHolder.setDataSourceName(value);
                System.out.println(" class annotation switch datasource success , datasource = " + value);
            } else {
                String methodName = joinPoint.getSignature().getName();
                Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
                Method method = anInterface.getMethod(methodName, parameterTypes);

                if (method!=null){
                    TargetDataSource annotation1 = method.getAnnotation(TargetDataSource.class);
                    if (annotation1 != null){
                        String value = annotation1.value();
                        DynamicDataSourceHolder.setDataSourceName(value);
                        System.out.println(" method annotation switch datasource success , datasource = " + value);
                    } else {
                        System.out.println(" switch datasource fail , default datasource ");
                    }
                } else {
                    System.out.println(" switch datasource fail , default datasource ");
                }
            }
        } catch (NoSuchMethodException e) {
            System.out.println(" switch datasource error , default datasource ");
        }
    }

    /**
     * 执行类方法后调用
     *
     * 删除当前线程数据源名称
     */
    @After("dataSourcePointcut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.removeDataSourceName();
    }
}
