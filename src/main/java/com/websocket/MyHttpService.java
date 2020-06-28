package com.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/282:27 下午
 */
public interface MyHttpService {
    void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest request);
}
