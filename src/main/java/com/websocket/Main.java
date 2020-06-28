package com.websocket;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/282:51 下午
 */
public class Main {
    public static void main(String[] args) {
        new WebSocketServer("127.0.0.1",9999).start();
    }
}
