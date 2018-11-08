package my.project.boot.datasource;

import my.project.boot.commons.MyConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * DataSource数据源配置
 *
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DBProperties dbProperties;

    @Bean(name = "dataSource")
    public DataSource dataSource(){
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(MyConstant.MASTER_DATASOURCE_NAME,dbProperties.getMaster());
        targetDataSource.put(MyConstant.SLAVE_DATASOURCE_NAME,dbProperties.getSlave());

        DynamicDataSource dataSource = new DynamicDataSource();

        dataSource.setTargetDataSources(targetDataSource);

        dataSource.setDefaultTargetDataSource(dbProperties.getMaster());

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
