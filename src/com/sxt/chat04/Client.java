package com.sxt.chat04;

import com.sxt.chat03.Receive;
import com.sxt.chat03.Send;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：加入容器实现群聊
 * 问题：
 * 1、代码不好维护
 * 2、客户端读写没有分开 必须先写后读
 */
public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("------Client------");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String name = br.readLine();
        //1、建立连接：使用Socket创建服务器+服务器的地址和端口
            Socket client = new Socket("localhost", 9999);
            //2、客户端发送消息
            new Thread(new Send(client, name)).start();
            new Thread(new Receive(client)).start();
    }
}