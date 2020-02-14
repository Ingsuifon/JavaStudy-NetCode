package com.sxt.chat03;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：封装 使用多线程实现多个客户可以正常收发多条信息
 * 问题：
 * 1、代码不好维护
 * 2、客户端读写没有分开 必须先写后读
 */
public class TMultiClient {
    public static void main(String[] args) throws Exception {
        System.out.println("------Client------");
        //1、建立连接：使用Socket创建服务器+服务器的地址和端口
            Socket client = new Socket("localhost", 9999);
            //2、客户端发送消息
            //new Thread(new Send(client)).start();
            new Thread(new Receive(client)).start();
    }
}