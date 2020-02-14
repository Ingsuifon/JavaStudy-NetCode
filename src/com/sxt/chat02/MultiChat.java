package com.sxt.chat02;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 目标：实现多个客户可以正常收发多条信息
 */
public class MultiChat {
    public static void main(String[] args) throws Exception {
        System.out.println("------Server------");
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式地等待连接 accept
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        DataInputStream dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
        boolean isRun = true;
        while(isRun) {
            //3、接收消息
            String msg = dis.readUTF();
            //4、返回消息
            dos.writeUTF(msg);
            dos.flush();
        }
        dos.close();
        dis.close();
        client.close();
    }
}
