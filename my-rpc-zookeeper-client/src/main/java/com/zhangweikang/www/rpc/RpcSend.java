package com.zhangweikang.www.rpc;

import com.zhangweikang.www.commons.ISerializer;
import com.zhangweikang.www.commons.ProtosbufSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 发送rpc请求
 *
 * @author ZhangWeiKang
 * @create 2018/6/14
 */
public class RpcSend {

    private String port;
    private String ip;
    private Socket socket = null;

    public RpcSend(String ip,String port) {
        this.ip = ip;
        this.port = port;
    }

    public Object send(RpcRequest request){
        try {
            if (socket == null){
                socket = new Socket(ip,Integer.valueOf(port));
            }

            ISerializer serializer = new ProtosbufSerializer();
            byte[] data = serializer.serializer(request);

            //发送请求数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(data);
            outputStream.flush();

            //获取请求返回数据
            /*ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());*/
            byte[] bytes = input2byte(socket.getInputStream());
            Object deserializer = null;
            if (bytes != null && bytes.length > 0){
                deserializer = serializer.deserializer(Object.class, bytes);
            }
            outputStream.close();
            /*objectInputStream.close();
            objectInputStream.readObject()*/
            return deserializer;
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {}
            }
        }
        return null;
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
}
