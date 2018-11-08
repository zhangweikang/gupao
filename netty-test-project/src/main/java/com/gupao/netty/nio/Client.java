package com.gupao.netty.nioIMeaagae;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * test nio
 *
 * @author ZhangWeiKang
 * @create 2018/11/7
 */
public class Client implements Runnable {

    public static String writerMessage = "客户端消息";

    private SocketChannel channel = null;

    private Selector selector = null;

    public static void main(String[] args) {
        new Client().run();
    }

    @Override
    public void run() {
        try {
            channel = SocketChannel.open();
            //获取当前本地ip,设置服务port
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            channel.configureBlocking(false);

            //开启一个选择器
            selector = Selector.open();

            // 注册感兴趣事件 连接事件
            channel.register(selector, SelectionKey.OP_CONNECT);

            //连接服务端,建立连接
            channel.connect(inetSocketAddress);

            while (true) {
                if (selector.select(1000) == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                if (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isValid()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        //连接成功,并且完成3次握手,为通道注册read事件
                        if (key.isConnectable() && channel.finishConnect()) {
                            System.out.println(" 监听到Connect事件 ");
                            //int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
                            int interestSet = SelectionKey.OP_READ;

                            socketChannel.register(selector, interestSet);

                            //启动一个system.in监听器
                            new SystemInListener(socketChannel, selector, this).run();
                        } else if (key.isReadable()) {
                            System.out.println(" 监听到Read事件 ");
                            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                            //将os数据拷贝至buffer
                            int read = socketChannel.read(readBuffer);
                            if (read > 0) {
                                //读取buffer数据
                                readBuffer.flip();
                                //创建数据长度字节数组
                                byte[] data = new byte[readBuffer.remaining()];
                                //将buffer中的数据读取到字节数组中
                                readBuffer.get(data);
                                //将字节转化为字符串
                                String readString = new String(data, "UTF-8");

                                System.out.println(" get server send message : " + readString);
                                //key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            }
                        } else if (key.isWritable()) {
                            System.out.println(" 监听到Writer事件 ");
                            byte[] bytes = writerMessage.getBytes("UTF-8");

                            ByteBuffer writerBuffer = ByteBuffer.allocate(bytes.length);
                            writerBuffer.put(bytes);
                            writerBuffer.flip();

                            //os缓冲区写完后会触发服务端的read事件
                            socketChannel.write(writerBuffer);
                            writerBuffer.clear();
                            System.out.println(" send server message : " + writerMessage);

                            /*取消通道的写事件*/
                            key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                        } else {
                            key.cancel();
                        }
                    } else {
                        key.cancel();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
