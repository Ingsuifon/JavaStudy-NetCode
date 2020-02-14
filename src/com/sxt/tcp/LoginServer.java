package com.sxt.tcp;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆 单向
 * 创建服务器
 * 1、指定端口 使用ServerSocket创建服务器
 * 2、阻塞式地等待连接 accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginServer {
    public static void main(String[] args) throws Exception {
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式地等待连接 accept
        Socket client = server.accept();
        System.out.println("一个客户端建立了连接");
        //3、操作：输入输出流操作
        DataInputStream dis = new DataInputStream(client.getInputStream());
        String data = dis.readUTF();

        String[] dataArray = data.split("&");
        for (String info : dataArray) {
            String[] user = info.split("=");
            if(user[0].equals("uname"))
                System.out.println("你的用户名为：" + user[1]);
            else if(user[0].equals("upass"))
                System.out.println("你的密码为：" + user[1]);
        }
        //4、释放资源
        dis.close();
        client.close();

        server.close();
    }
}
