package my.project.cloud.spring.cloud.demo.configuration.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义配置文件导入选择器
 *
 * @author zhangweikang
 * @date 2019/10/21
 */
public class MyImportSelector implements EnvironmentAware, DeferredImportSelector {

    private Environment environment;

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"my.project.cloud.spring.cloud.demo.configuration.config.MyConfiguration"};
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
