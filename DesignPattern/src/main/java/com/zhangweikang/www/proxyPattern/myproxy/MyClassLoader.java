package com.zhangweikang.www.proxyPattern.myproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClassLoader
 *
 * @author ZhangWeiKang
 * @create 2018/5/18
 */
public class MyClassLoader extends ClassLoader{

    private File classPathFile;

    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(path);
    }

    @Override
    protected Class<?> findClass(String name){

        //获取类,包名
        String className = MyClassLoader.class.getPackage().getName() + "." + name;

        //获取类class文件名,并创建一个文件对象
        if (classPathFile != null){
            File file = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
            //将class文件加载到内存
            FileInputStream fis = null;
            ByteArrayOutputStream bos = null;

            try {
                fis = new FileInputStream(file);
                bos = new ByteArrayOutputStream();

                int len;
                byte[] bytes = new byte[1024];
                while ((len = fis.read(bytes)) != -1){
                    bos.write(bytes,0,len);
                }

                //构造一个class
                return defineClass(className,bos.toByteArray(),0,bos.size());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null){
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }
}
