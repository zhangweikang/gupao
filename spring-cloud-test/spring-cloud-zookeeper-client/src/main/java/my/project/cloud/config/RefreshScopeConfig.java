package my.project.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * 配置刷新管理类
 *
 * @author ZhangWeiKang
 * @create 2018/8/5
 */
@Configuration
@RefreshScope
public class RefreshScopeConfig {
    @Value("${name:null}")
    private String name;

    public String getName() {
        return name;
    }
}
