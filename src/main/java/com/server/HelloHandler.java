package com.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/184:37 下午
 */
public class HelloHandler extends ChannelInboundHandlerAdapter {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception{
        channels.add(ctx.channel());//加入ChannelGroup
        System.out.println(ctx.channel()+" come into the chattingroom,"+"Online: "+channels.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive----->");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println("server channelRead..");
//        System.out.println(ctx.channel().remoteAddress()+"->Server :"+ msg.toString());
//        ctx.write("server write"+msg);
//        ctx.flush();

        //打印消息然后群发
        System.out.println(msg.toString());
        for (Channel channel:channels){
            channel.writeAndFlush(msg.toString());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
