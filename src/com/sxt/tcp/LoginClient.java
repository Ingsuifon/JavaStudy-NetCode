package com.sxt.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.server.ExportException;

/**
 * 模拟登陆 单向
 * 熟悉流程
 * 创建客户器
 * 1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 */
public class LoginClient {
    public static void main(String[] args) throws Exception {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入用户名：");
        String uname = console.readLine();
        System.out.println("请输入密码：");
        String upass = console.readLine();
        //1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
        Socket client = new Socket("localhost", 9999);

        //2、操作：输入输出流操作
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        dos.writeUTF("uname="+uname+"&"+"upass="+upass);
        dos.flush();
        //3、释放资源
        dos.close();
        client.close();
    }
}
