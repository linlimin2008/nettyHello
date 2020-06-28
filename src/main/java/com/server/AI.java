package com.server;

/**
 * @功能:
 * @项目名:nettyHello
 * @作者:0cm
 * @日期:2020/6/1911:02 上午
 */
public class AI {
    public static String AIChat(String str){
        str = str.replace("吗","");
        str = str.replace("?","!");
        str = str.replace("我","你");
        return str;
    }
}
