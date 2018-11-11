package com.gupao.netty.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * netty client
 *
 * @author ZhangWeiKang
 * @create 2018/11/8
 */
public class Client {
    public static void main(String[] args) {
        // 创建线程
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            // 创建netty客户端
            Bootstrap bootstrap = new Bootstrap();
            // 设置参数
            bootstrap.group(loopGroup)
            // 设置通道实例
                .channel(NioSocketChannel.class)
                // .option(ChannelOption.TCP_NODELAY, true)
                // 设置处理类
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        // 获取所有处理类对象
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline
                        // .addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4))
                        // .addLast(new LengthFieldPrepender(4))
                        // 解码
                        .addLast(new StringDecoder(CharsetUtil.UTF_8))
                        // 编码
                        .addLast(new StringEncoder(CharsetUtil.UTF_8))
                        // 本地业务逻辑
                        .addLast(new ClientHandler());
                    }
                });
            //连接服务端
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress(8899)).sync();
            //获取通道
            Channel channel = channelFuture.channel();
            int i = 0;
            while (true) {
                Thread.sleep(1000);
                //channel.writer(...)writer往外写数据的时候只是写到了缓冲区并没有写对外写,必须flush
                channel.writeAndFlush("hello server " + i);
                i++;
                if (i >= 100) {
                    channel.closeFuture().sync();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
