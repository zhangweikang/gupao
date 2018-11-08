package com.gupao.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Test
 *
 * @author ZhangWeiKang
 * @create 2018/11/6
 */
public class Server implements Runnable {
    public static String writerMessage = "服务端消息";

    private ServerSocketChannel serverSocketChannel = null;

    private Selector selector = null;

    public static void main(String[] args) {
        new Server().run();
    }

    @Override
    public void run() {
        try {
            //打开服务端通道
            serverSocketChannel = ServerSocketChannel.open();
            //获取当前本地ip,设置服务port
            InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
            //设置当前socket非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取服务端ServerSocket
            ServerSocket socket = serverSocketChannel.socket();
            //为ServerSocket绑定IP
            socket.bind(inetSocketAddress);
            //创建一个选择器
            selector = Selector.open();
            //为通道注册一个选择器,选择器监听Accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                if (selector.select(1000) == 0) {
                    continue;
                }
                //获取所有事件key
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //获取set迭代器
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                //迭代所有元素
                while (iterator.hasNext()) {
                    //获取当前事件key
                    SelectionKey key = iterator.next();
                    //删除当前事件key
                    iterator.remove();
                    //判断当前key是否有效
                    if (key.isValid()) {
                        //判断当前是accept消息
                        if (key.isAcceptable()) {
                            System.out.println(" 监听到Accept事件 ");
                            //获取当前key对应的SocketChannel
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            //设置接入的客户端通道非阻塞模式
                            socketChannel.configureBlocking(false);
                            //沿用当前socket端口
                            socketChannel.socket().setReuseAddress(true);
                            //为通道注册一个选择器,选择器监听read事件
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            //启动system.in监听
                            new SystemInListener(socketChannel, selector, this).run();
                        } else if (key.isReadable()) {//判断当前是read消息
                            //获取接入客户端SocketChannel
                            System.out.println(" 监听到Read事件 ");
                            SocketChannel channel = (SocketChannel) key.channel();
                            //创建ByteBuffer,用来读取客户端传输的数据
                            //Buffer 有4个关键的变量
                            //1. mark : 用来做标记,可对标记的位置重复读取数据
                            //2. position : 当前Buffer第一个未读区域的下标
                            //3. limit : 读取的位置下标
                            //4. capacity : 可写数据的最大位置
                            //当创建buffer时,mark=-1(未标记任何位置),position=0(还未写入数据),limit<=capacity,capacity用户自定义(如果
                            // 接受的数据容量大于创建的buffer容量,将会丢失数据)
                            //mark=-1,position=0,limit=1024,capacity=1024
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            //将客户端数据读取到ByteBuffer中
                            //往buffer中写数据put操作,mark=-1,position=客户端传输的数据长度+1,limit=1024,capacity=1024;
                            int read = channel.read(byteBuffer);
                            //判断读取字节大于0
                            if (read > 0) {
                                //从buffer中读取数据,会移动position位置,limit位置
                                //mark=-1,position=0,limit=客户端传输的数据长度+1(也就是写入buffer时,position的值),capacity=1024
                                byteBuffer.flip();
                                //创建字节数组,长度为客户端传输的数据长度+1,也就是(limit-position)
                                byte[] data = new byte[byteBuffer.remaining()];
                                //将buffer数据放入数组
                                byteBuffer.get(data);
                                //解码
                                String string = new String(data, "UTF-8");
                                System.out.println(" get client message : " + string);
                                //key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            }
                        } else if (key.isWritable()) {
                            System.out.println(" 监听到Writer事件 ");
                            //获取控制台输入数据,utf-8编码转换字节
                            byte[] bytes = writerMessage.getBytes("UTF-8");
                            //创建一个写buffer
                            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
                            //将控制台数据写入buffer
                            writeBuffer.put(bytes);
                            //buffer读取数据
                            writeBuffer.flip();

                            //对外写数据
                            //os缓冲区写完后会触发客户端的read事件
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            int len = 0;
                            while (writeBuffer.hasRemaining()) {
                                len = socketChannel.write(writeBuffer);
                                if (len == 0) {
                                    break;
                                }
                            }

                            writeBuffer.compact();

                            if (len != 0) {
                                /*取消通道的写事件*/
                                key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
