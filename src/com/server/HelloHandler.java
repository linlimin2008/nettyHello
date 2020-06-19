package com.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/184:37 下午
 */
public class HelloHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String s = (String) e.getMessage();

        //回写数据
        s = AI.AIChat(s);
        ctx.getChannel().write(s);
        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelBound(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
