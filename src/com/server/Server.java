package com.server;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/184:29 下午
 */
public class Server {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //设置niosocket 工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));

        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decode",new StringDecoder());
                pipeline.addLast("endcode",new StringEncoder());
                pipeline.addLast("helloHandler",new HelloHandler());
                return pipeline;
            }
        });

        //服务类绑定端口
        bootstrap.bind(new InetSocketAddress(10101));
        System.out.println("start!!");
    }
}
