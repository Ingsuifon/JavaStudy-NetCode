package com.sxt.chat01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：实现一个客户可以正常收发信息
 */
public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("------Client------");
        //1、建立连接：使用Socket创建服务器+服务器的地址和端口
        Socket client = new Socket("localhost", 9999);
        //2、客户端发送消息
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String msg = console.readLine();
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        dos.writeUTF(msg);
        dos.flush();
        //3、接收消息
        DataInputStream dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        msg = dis.readUTF();
        System.out.println(msg);

        dos.close();
        dis.close();
        client.close();
    }
}
