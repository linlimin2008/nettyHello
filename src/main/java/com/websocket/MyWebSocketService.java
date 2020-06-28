package com.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

/**
 * @功能:处理 WebSocket 请求中的frame
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/282:28 下午
 */
public interface MyWebSocketService {
    void handleFrame(ChannelHandlerContext ctx, WebSocketFrame frame);
}
