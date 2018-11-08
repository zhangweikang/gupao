package my.project.boot.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 扩展DataSource数据源路由对象
 *
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSourceName();
    }
}
