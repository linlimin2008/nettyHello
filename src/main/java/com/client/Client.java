package com.client;

import com.server.HelloHandler;
import com.server.Server;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/185:55 下午
 */
public class Client {
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));


    public static void main(String[] args) throws InterruptedException {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast("decoder", new StringDecoder());
                            p.addLast("encoder", new StringEncoder());
                            p.addLast(new HiHandler());
                        }
                    });

            ChannelFuture future = b.connect(HOST, PORT).sync();
//            future.channel().writeAndFlush("Hello Netty Server ,I am a common client");
            Scanner sca=new Scanner(System.in);
            while (true){
                String str=sca.nextLine();//输入的内容
                if (str.equals("exit"))
                    break;//如果是exit则退出
                future.channel().writeAndFlush("0cm-: "+str);//将名字和信息内容一起发过去
            }
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
