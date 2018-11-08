package my.project.boot.mapper;

import my.project.boot.annotation.TargetDataSource;
import my.project.boot.commons.MyConstant;
import my.project.boot.domain.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * person mapper
 *
 * @author ZhangWeiKang
 * @create 2018/7/27
 */
@Mapper
@TargetDataSource(MyConstant.SLAVE_DATASOURCE_NAME)
public interface PersonMapper {

    @Select("SELECT * FROM person WHERE id = #{id}")
    Person findById(@Param("id") String id);
}
