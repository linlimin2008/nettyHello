package com.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/10/123:27 下午
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()){
            try {
                Socket request = serverSocket.accept();
                System.out.println("收到新连接："+request.toString());
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
        }
    }
}
