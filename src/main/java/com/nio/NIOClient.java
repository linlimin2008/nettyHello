package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/10/134:32 下午
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        while (!socketChannel.finishConnect()){
            //没连接上，则一直等待
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        //发送内容
        String msg = scanner.nextLine();
        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        while (byteBuffer.hasRemaining()){
            socketChannel.write(byteBuffer);
        }
        //读取响应
        System.out.println("收到服务器响应");
        ByteBuffer request = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen()&&socketChannel.read(request)!=-1){
            if (request.position()>0){
                break;
            }
        }
        request.flip();
        byte[] content = new byte[request.limit()];
        request.get(content);
        System.out.println(new String(content));
        scanner.close();
    }
}
