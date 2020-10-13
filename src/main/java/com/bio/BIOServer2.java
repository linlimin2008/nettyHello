package com.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/10/123:27 下午
 */
public class BIOServer2 {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()){
            Socket request = serverSocket.accept();
            System.out.println("收到新连接："+request.toString());
            threadPool.execute(()->{
                try {
                    InputStream inputStream = request.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                    String msg;
                    while ((msg = reader.readLine())!=null){
                        if (msg.length()==0){
                            break;
                        }
                        System.out.println(msg);
                    }
                    System.out.println("收到数据，来自："+request.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                }
            });
        }
    }
}
