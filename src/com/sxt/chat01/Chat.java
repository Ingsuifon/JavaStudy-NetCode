package com.sxt.chat01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 目标：实现一个客户可以正常收发信息
 */
public class Chat {
    public static void main(String[] args) throws Exception {
        System.out.println("------Server------");
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式地等待连接 accept
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //3、接收消息
        DataInputStream dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        String msg = dis.readUTF();
        //4、返回消息
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        dos.writeUTF(msg);
        dos.flush();

        dos.close();
        dis.close();
        client.close();
    }
}
