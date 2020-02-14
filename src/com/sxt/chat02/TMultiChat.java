package com.sxt.chat02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 目标：使用多线程实现多个客户可以正常收发多条信息
 */
public class TMultiChat {
    public static void main(String[] args) throws Exception {
        System.out.println("------Server------");
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式地等待连接 accept
        while(true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");

            new Thread(()-> {
                DataInputStream dis = null;
                DataOutputStream dos = null;
                try {
                    dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                    dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean isRun = true;
                while (isRun) {
                    //3、接收消息
                    String msg = null;
                    try {
                        msg = dis.readUTF();
                        //4、返回消息
                        dos.writeUTF(msg);
                        dos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                        isRun = false;
                    }
                }
                try {
                    if (dos != null)
                        dos.close();
                    if (dis != null)
                        dis.close();
                    if (client != null)
                        client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}