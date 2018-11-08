package my.project.boot.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * test数据源
 *
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
@Component
@ConfigurationProperties(prefix = "test")
public class DBProperties {

    private HikariDataSource master;

    private HikariDataSource slave;

    public HikariDataSource getMaster() {
        return master;
    }

    public void setMaster(HikariDataSource master) {
        this.master = master;
    }

    public HikariDataSource getSlave() {
        return slave;
    }

    public void setSlave(HikariDataSource slave) {
        this.slave = slave;
    }
}
