package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/10/134:00 下午
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        //创建服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel!=null){
                System.out.println("收到新连接："+socketChannel.getRemoteAddress());
                socketChannel.configureBlocking(false);
                try {
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    while (socketChannel.isOpen()&&socketChannel.read(requestBuffer)!=-1){
                        if(requestBuffer.position()>0)
                        {
                            break;
                        }
                    }
                    if (requestBuffer.position()==0) {
                        continue;
                    }
                    requestBuffer.flip();
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println(new String(content));
                    System.out.println("收到数据，来自："+ socketChannel.getRemoteAddress());

                    //响应结果200
                    String response = "HTTP/1.1 200 OK \r\n" +
                            "Content-Length:11 \r\n\r\n" +
                            "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()){
                        socketChannel.write(buffer);
                    }

                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
