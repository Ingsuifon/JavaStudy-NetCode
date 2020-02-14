package com.sxt.chat02;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：实现多个客户可以正常收发多条信息
 */
public class MultiClient {
    public static void main(String[] args) throws Exception {
        System.out.println("------Client------");
        //1、建立连接：使用Socket创建服务器+服务器的地址和端口
        while(true) {
            Socket client = new Socket("localhost", 9999);
            //2、客户端发送消息
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            DataInputStream dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
            boolean isRun = true;
            while (isRun) {
                String msg = console.readLine();
                dos.writeUTF(msg);
                dos.flush();
                //3、接收消息
                msg = dis.readUTF();
                System.out.println(msg);
            }
            dos.close();
            dis.close();
            client.close();
        }
    }
}
