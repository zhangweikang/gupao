package my.spring.servlet;

import my.demo.controller.DemoController;
import my.spring.annotation.Autowried;
import my.spring.annotation.Controller;
import my.spring.annotation.Service;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DispatcherServlet
 *
 * @author ZhangWeiKang
 * @create 2018/7/24
 */
public class DispatcherServlet extends HttpServlet {

    //web.xml中初始化加载的文件
    private final String INIT_PARAM_NAME = "contextConfigLocation";

    private Properties config = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> iocMaps = new ConcurrentHashMap<>();

    private List<String> annotations = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {

        //加载配置文件
        doLoadConfig(getServletConfig().getInitParameter(INIT_PARAM_NAME));

        //扫描
        doScan(config.getProperty("packageName"));

        //注册
        doRegistry();

        //依赖注入
        doAutowried();

        DemoController demoController = (DemoController)iocMaps.get("demoController");

        demoController.query();

    }

    private void doAutowried(){
        try {
            if (iocMaps.isEmpty()){return;}

            for (String className : iocMaps.keySet()) {
                Object o = iocMaps.get(className);
                Field[] fields = o.getClass().getDeclaredFields();
                for (Field field : fields) {
                    Autowried annotation = field.getAnnotation(Autowried.class);
                    if (annotation==null){continue;}
                    field.setAccessible(true);

                    String name = annotation.name();
                    if (StringUtils.isBlank(name)){
                        name = field.getType().getName();
                    }
                    field.set(o,iocMaps.get(name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doRegistry() {
        try {
            if (classNames.isEmpty()) {
                return;
            }

            for (String className : classNames) {
                Class<?> aClass = Class.forName(className);

                if (aClass.isInterface()) {
                    return;
                }

                if (aClass.isAnnotationPresent(Controller.class)) {

                    Controller annotation = aClass.getAnnotation(Controller.class);
                    String name = annotation.name();
                    if (StringUtils.isBlank(name)) {
                        name = firstUpCase(aClass.getSimpleName());
                    }
                    iocMaps.put(name, aClass.newInstance());
                } else if (aClass.isAnnotationPresent(Service.class)) {

                    Service annotation = aClass.getAnnotation(Service.class);
                    String name = annotation.name();
                    if (StringUtils.isBlank(name)) {
                        name = firstUpCase(aClass.getSimpleName());
                    }
                    Object instance = aClass.newInstance();
                    iocMaps.put(name, instance);

                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                        iocMaps.put(anInterface.getName(),instance);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doScan(String packageName) {
        try {
            URL resource = this.getClass().getClassLoader().getResource("");
            if (resource == null) {
                return;
            }
            String s = resource.getFile() + "/" + packageName.replaceAll("\\.", "\\/");
            File file = new File(s);
            File[] files = file.listFiles();
            if (files == null || files.length <= 0) {
                return;
            }

            for (File file1 : files) {
                if (file1.isDirectory()) {
                    doScan(packageName + "." + file1.getName());
                } else {
                    classNames.add(packageName + "." + file1.getName().replace(".class", ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:", ""));
        try {
            config.load(resourceAsStream);
        } catch (IOException e) {
            if (resourceAsStream != null) {
                try {
                    resourceAsStream.close();
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String firstUpCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
