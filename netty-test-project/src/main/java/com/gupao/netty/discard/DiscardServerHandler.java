package com.gupao.netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * discard 协议 server handler
 *
 * @author ZhangWeiKang
 * @create 2018/11/8
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端与服务端建立连接时,会回调该方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" 服务连接 ");
    }

    /**
     * 当服务端收到客户端的消息时,会回调该方法
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(" 数据调用 ");
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                System.out.println((char) in.readByte());
                System.out.flush();
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    /**
     * 当服务出现异常的时候,会回调该方法
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(" 服务异常 ");
        cause.printStackTrace();
        ctx.close();
    }
}
