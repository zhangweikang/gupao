import com.zhangweikang.orm.demo.modal.User;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jdbc测试类
 *
 * @author ZhangWeiKang
 * @create 2018/5/11
 */
public class JdbcTest {

    public static void main(String[] args) {
        //List list = list(CustomerInfo.class);

        List list = list(User.class);
        System.out.println("list = " + list);
    }

    private static Connection getMysqlConnection(String ip, String port, String schema, String userName, String passWord) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + schema, userName, passWord);
    }

    private static <T> List list(Class<T> clazz) {

        List<Object> lists = new ArrayList<Object>();
        try {
            Connection connection = getMysqlConnection("127.0.0.1", "3306", "test", "root", "root");

            Table annotation = clazz.getAnnotation(Table.class);

            if (annotation == null) {
                System.out.println(" ----- Please This Class Appoint TableName -----");
                return null;
            }

            String tableName = annotation.name();

            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + tableName);

            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String,String> columnMap = new HashMap<>();


            //获取所有实体,为标注忽略Transient注解,标注Column注解的属性名称,与数据库字段名称对应关系
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Transient aTransient = declaredField.getAnnotation(Transient.class);
                if (aTransient == null){
                    Column column = declaredField.getAnnotation(Column.class);
                    if (column != null){
                        columnMap.put(column.name(),declaredField.getName());
                    }
                }
            }

            //获取一行数据列数
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            //遍历所有数据
            while (resultSet.next()) {
                //获取对象实例
                T object = clazz.newInstance();
                //遍历获取所有列
                for (int i = 1; i <= columnCount; i++) {
                    //获取当前列名
                    String columnName = metaData.getColumnName(i);

                    //注解优先
                    String fieldName = columnMap.containsKey(columnName)?columnMap.get(columnName):toUpCase(columnName);

                    //获取对象实例字段
                    Field declaredField = clazz.getDeclaredField(fieldName);
                    if (declaredField != null){
                        Transient aTransient = declaredField.getAnnotation(Transient.class);
                        //忽略所有标记有Transient字段
                        if (aTransient == null){
                            //操作私有成员变量
                            declaredField.setAccessible(true);
                            declaredField.set(object, resultSet.getObject(columnName));
                        }
                    }
                }

                lists.add(object);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lists;
    }

    private static String toUpCase(String str) {
        String resultString;
        if (str.contains("_")) {
            String[] split = str.split("_");
            StringBuffer sb = new StringBuffer();
            for (int i = 1, length = split.length; i < length; i++) {
                String s = split[i];
                char[] chars = s.toCharArray();
                if (chars[0] >= 'a' && chars[0] <= 'z') {
                    chars[0] -= 32;
                }
                sb.append(chars);
            }
            resultString = split[0] + sb.toString();
        } else {
            resultString = str;
        }
        return resultString;
    }
}
