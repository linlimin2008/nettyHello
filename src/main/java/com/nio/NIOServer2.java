package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * 直接基于非阻塞的写法，一个线程处理轮询所有请求
 */
public class NIOServer2 {
    private static ArrayList<SocketChannel> channels = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        //1、创建服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //2、构建一个selector 选择器，并且将channel注册上去
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector,0,serverSocketChannel);//将serverSocketChannel注册到selector
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);//对serverSocketChannerl上面的accept事件感兴趣（serversocketchannel只能支持accept操作）

        //3.绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("启动成功");

        while (true){
            //不再轮询通道，改用下面轮询事件的方式，select方法有阻塞的效果，直到有事件通知才会有返回
            selector.select();
            //获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历查询结果
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                //被封装的查询结果
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()){
                    ServerSocketChannel server =(ServerSocketChannel) key.attachment();
                    SocketChannel clientSocketChannel = server.accept();
                    clientSocketChannel.configureBlocking(false);
                    clientSocketChannel.register(selector,SelectionKey.OP_READ,clientSocketChannel);
                    System.out.println("收到新连接："+ clientSocketChannel.getRemoteAddress());
                }
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.isOpen()&&socketChannel.read(requestBuffer)!=-1){
                            if(requestBuffer.position()>0)
                            {
                                break;
                            }
                        }
                        if (requestBuffer.position()==0){
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
}
