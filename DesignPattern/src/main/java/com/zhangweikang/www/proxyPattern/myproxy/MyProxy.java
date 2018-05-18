package com.zhangweikang.www.proxyPattern.myproxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 自己的代理类
 *
 * @author ZhangWeiKang
 * @create 2018/5/18
 */
public class MyProxy {

    public static final String ln = "\r\n";

    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h)
            throws IllegalArgumentException {

        try {
            //1.拼接java文件字符串
            String s = generatorStr(interfaces);
            //2.生成java文件
            String path = MyProxy.class.getResource("").getPath();
            File file = new File(path+"$Proxy0.java");
            FileWriter fos = null;
            try {
                fos = new FileWriter(file);
                fos.write(s);
                fos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    assert fos != null;
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //3.编译class文件
            compiler(file);

            //4.加载到jvm,获取jvm构造的Class对象
            Class<?> $Proxy = loader.findClass("$Proxy0");
            //5.返回字节码重组的对象,通过构造函数重新创建一个对象
            Constructor<?> constructor = $Proxy.getConstructor(MyInvocationHandler.class);
            file.delete();

            return constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generatorStr(Class<?>[] interfaces) {
        StringBuffer sb = new StringBuffer();

        sb.append("package com.zhangweikang.www.proxyPattern.myproxy;").append(ln);
        sb.append("import java.lang.reflect.Method;").append(ln);

        StringBuffer interfacesName = new StringBuffer();
        for (Class<?> anInterface : interfaces) {
            sb.append("import ").append(anInterface.getName()).append(";").append(ln);
            interfacesName.append(anInterface.getName()).append(",");
        }

        String interfacesNames = interfacesName.substring(0, interfacesName.length() - 1);
        sb.append("public class $Proxy0 implements ").append(interfacesNames).append("{").append(ln);

        //成员变量
        sb.append("MyInvocationHandler h;");
        //构造方法
        sb.append("public $Proxy0(MyInvocationHandler h){ ").append(ln);
            sb.append("this.h = h ;").append(ln);
        sb.append("}").append(ln);

        //获取所有接口
        for (Class<?> anInterface : interfaces) {
            //获取所有接口中的方法
            for (Method method : anInterface.getMethods()) {
                //生成方法
                sb.append("public ").append(method.getReturnType()).append(" ").append(method.getName()).append("() { ").append(ln);
                    sb.append("try {").append(ln);
                        //获取接口方法
                        sb.append("Method m = ").append(anInterface.getName()).append(".class.getMethod(\"").append(method.getName()).append("\",new Class[]{});");
                        sb.append("this.h.invoke(this,m,null);").append(ln);
                    sb.append("} catch (Throwable t) { ").append(ln);
                        sb.append("t.printStackTrace();").append(ln);
                    sb.append("}").append(ln);
                sb.append("}").append(ln);
            }
        }
        sb.append("}").append(ln);
        return sb.toString();
    }

    private static void compiler(File file){
        //获取类加载器
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();

        //指定编译文件
        StandardJavaFileManager standardFileManager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = standardFileManager.getJavaFileObjects(file);

        //生成编译任务
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, standardFileManager, null, null, null, javaFileObjects);
        task.call();
        try {
            standardFileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
