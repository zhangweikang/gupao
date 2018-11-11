package com.gupao.netty.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * netty server
 *
 * @author ZhangWeiKang
 * @create 2018/11/8
 */
public class Server {

    public static void main(String[] args){
        //创建主线程,用于接受客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建工作线程,用来处理通道数据的读写
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            //创建netty服务端
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //分组设置
            serverBootstrap.group(bossGroup,workGroup)
                    //通道实例
                    .channel(NioServerSocketChannel.class)
                    //创建工作线程处理类
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline//.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                                    //数据解码
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    //数据编码
                                    .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                    //自定义业务处理
                                    .addLast(new ServerHandler());
                        }
                    });
            //绑定端口,并阻塞,等待客户端接入
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            //channelFuture.channel()获取Channel通道
            //channelFuture.channel().closeFuture()获取CloseFuture实例
            //channelFuture.channel().closeFuture().sync()阻塞CloseFuture
            //每一个Channel都对应一个CloseFuture,CloseFuture被阻塞后,再Channel调用close是被唤醒
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
