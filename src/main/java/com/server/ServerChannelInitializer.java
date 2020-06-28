package com.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/2310:36 上午
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new HelloHandler());
    }
}
