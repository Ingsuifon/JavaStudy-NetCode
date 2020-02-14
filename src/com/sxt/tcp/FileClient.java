package com.sxt.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 上传文件
 * 创建客户器
 * 1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 */
public class FileClient {
    public static void main(String[] args) throws IOException {
        //1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
        Socket client = new Socket("localhost", 9999);
        //2、操作：输入输出流操作
        InputStream is = new BufferedInputStream(new FileInputStream("15.jpg"));
        OutputStream os = new BufferedOutputStream(client.getOutputStream());
        byte[] flush = new byte[1024];
        int len = -1;
        while((len = is.read(flush)) != -1)
            os.write(flush, 0, len);
        os.flush();
        //3、释放资源
        os.close();
        is.close();
        client.close();
    }
}
