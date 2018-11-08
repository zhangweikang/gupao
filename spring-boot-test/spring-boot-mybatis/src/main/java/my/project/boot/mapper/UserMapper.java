package my.project.boot.mapper;

import my.project.boot.annotation.TargetDataSource;
import my.project.boot.commons.MyConstant;
import my.project.boot.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * User
 *
 * @author ZhangWeiKang
 * @create 2018/7/27
 */
public interface UserMapper {
    @TargetDataSource(MyConstant.MASTER_DATASOURCE_NAME)
    User selectByPrimaryKey(@Param("id") Long id);
}
