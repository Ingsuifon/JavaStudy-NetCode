package com.sxt.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 模拟多个客户端请求
 * 熟悉流程
 * 创建客户器
 * 1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 */
public class LoginMultiClient {
    public static void main(String[] args) throws Exception {
        System.out.println("--------client--------");
        //1、建立连接：使用Socket创建服务器 + 服务器的地址和端口
        Socket client = new Socket("localhost", 9999);
        //2、操作：输入输出流操作
        new Send(client).send();
        String result = new Receive(client).receive();
        System.out.println(result);
        //3、释放资源
        client.close();
    }

    static class Send {
        private DataOutputStream dos;
        private BufferedReader console;
        private String msg;

        private String init() {
            System.out.println("请输入用户名：");
            try {
                String uname = console.readLine();
                System.out.println("请输入密码：");
                String upass = console.readLine();
                return "uname="+uname+"&"+"upass="+upass;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        public Send(Socket client) {
            this.console = new BufferedReader(new InputStreamReader(System.in));
            msg = init();
            try {
                this.dos = new DataOutputStream(client.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void send() {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class Receive {
        private DataInputStream dis;

        public Receive(Socket client) {
            try {
                this.dis = new DataInputStream(client.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String receive() {
            String msg = null;
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return msg;
        }

    }
}