package com.gupao.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author ZhangWeiKang
 * @create //9
 */
public class DiscardClientHandler extends ChannelInboundHandlerAdapter {

    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(" 服务连接 ");

        this.ctx = ctx;

        // Initialize the message.
        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

        // Send the initial messages.
        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println(" channelInactive ");
        content.release();
    }

    /*@Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Server is supposed to send nothing, but if it sends something, discard it.
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println(" exceptionCaught ");
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    long counter;

    private void generateTraffic() {
        // Flush the outbound buffer to the socket.
        // Once flushed, generate the same amount of traffic again.
        //content.retainedDuplicate()
        try {
            content.clear();
            String message = "send message in client";
            content.writeBytes(message.getBytes("UTF-8"));
            ctx.writeAndFlush(content.retainedDuplicate()).addListener(trafficGenerator);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private final ChannelFutureListener trafficGenerator = future -> {
        Thread.sleep(1000);
        if (future.isSuccess()) {
            generateTraffic();
        } else {
            future.cause().printStackTrace();
            future.channel().close();
        }
    };
}
