package my.project.boot.datasource;

/**
 * ThreadLocal 保存当前线程正在使用的datasource name
 * 当前线程共享对象
 * @author ZhangWeiKang
 * @create 2018/7/30
 */
public class DynamicDataSourceHolder {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getDataSourceName(){
        return threadLocal.get();
    }

    public static void setDataSourceName(String name){
        threadLocal.set(name);
    }

    public static void removeDataSourceName(){
        threadLocal.remove();
    }
}
