package com.zhangweikang.www.rpc;

import com.zhangweikang.www.commons.ISerializer;
import com.zhangweikang.www.commons.ProtosbufSerializer;
import com.zhangweikang.www.ioc.BeanFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket请求处理
 *
 * @author ZhangWeiKang
 * @create 2018/6/11
 */
public class RpcServer {

    public void socketServer(String port) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(Integer.valueOf(port));

            while (true) {
                //监听服务端socket
                Socket accept = socket.accept();

                ObjectInputStream objectInputStream = null;
                try {
                    InputStream inputStream = accept.getInputStream();
                    //objectInputStream = new ObjectInputStream(inputStream);

                    byte[] result = input2byte(inputStream);
                    ISerializer serializer = new ProtosbufSerializer();
                    RpcRequest request = null;
                    if (result != null && result.length > 0){
                        request = serializer.deserializer(RpcRequest.class, result);
                    }

                    System.out.println("request = " + request);

                    //执行方法
                    Object response = null;
                    if (request != null){
                        response = invoke(request);
                    }

                    byte[] serializer1 = new byte[]{};
                    if (response != null){
                        serializer1 = serializer.serializer(response);
                    }
                    /*ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                    objectOutputStream.writeObject(response);*/

                    OutputStream outputStream = accept.getOutputStream();
                    outputStream.write(serializer1);
                    outputStream.flush();

                    /*objectOutputStream.flush();
                    objectOutputStream.close();*/
                    //objectInputStream.close();
                } finally {
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public  byte[] input2byte(InputStream is) throws IOException {
        byte[] data = null;
        try {
            if(is.available()==0){//严谨起见,一定要加上这个判断,不要返回data[]长度为0的数组指针
                return data;
            }
            data = new byte[is.available()];
            is.read(data);
            is.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return data;
        }
    }

    private Object invoke(RpcRequest request) {
        Object[] methodArges = request.getMethodArges();

        //获取所有参数类型
        int length = methodArges.length;
        Class<?>[] types = new Class<?>[length];
        for (int i = 0; i < length; i++) {
            types[i] = methodArges[i].getClass();
        }

        try {
            Method method = Class.forName(request.getInterfaceName()).getMethod(request.getMethodName(), types);
            Object server = BeanFactory.getInstance().getBean(request.getInterfaceName());
            return method.invoke(server, methodArges);
        } catch (NoSuchMethodException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
