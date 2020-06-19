package com.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/1910:27 上午
 */
public class HiHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        String s = (String) e.getMessage();
        System.out.println(s);
        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
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
