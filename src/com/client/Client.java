package com.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

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
    public static void main(String[] args) {
        //客户类
        ClientBootstrap bootstrap = new ClientBootstrap();
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket 工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decode",new StringDecoder());
                pipeline.addLast("encode",new StringEncoder());
                pipeline.addLast("hiHandler",new HiHandler());
                return pipeline;
            }
        });

        //连接服务端
        ChannelFuture connect = bootstrap.connect(new InetSocketAddress("127.0.0.1",10101));
        Channel channel = connect.getChannel();

        System.out.println("client start");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入");
            channel.write(scanner.next());
        }

    }
}
