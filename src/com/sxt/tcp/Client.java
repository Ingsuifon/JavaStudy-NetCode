package com.sxt.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建客户器
 * 1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
        Socket client = new Socket("localhost", 9999);
        //2、操作：输入输出流操作
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        String data = "Hello";
        dos.writeUTF(data);
        dos.flush();
        //3、释放资源
        dos.close();
        client.close();
    }
}
