package com.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/10/123:57 下午
 */
public class BIOClient {
    private static Charset charset = Charset.forName("UTF-8");
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost",8080);
        OutputStream out = s.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String msg = scanner.nextLine();
        out.write(msg.getBytes(charset));
        scanner.close();
        s.close();
    }
}
