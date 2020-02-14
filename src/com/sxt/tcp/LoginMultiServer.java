package com.sxt.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟多个客户端请求
 * 创建服务器
 * 1、指定端口 使用ServerSocket创建服务器
 * 2、阻塞式地等待连接 accept
 * 3、操作：输入输出流操作
 * 4、释放资源
 */
public class LoginMultiServer {
    public static void main(String[] args) throws Exception {
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        boolean isRunning = true;
        //2、阻塞式地等待连接 accept
        while(isRunning) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            new Thread(new Channel(client)).start();
        }
    }

    static class Channel implements Runnable {
        private Socket client;
        private DataInputStream dis;
        private DataOutputStream dos;

        public Channel(Socket client) {
            this.client = client;
            try {
                dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
                dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                release();
            }
        }

        @Override
        public void run() {
            //3、操作：输入输出流操作
            String uname = "";
            String upass = "";
            String[] dataArray = receive().split("&");
            for (String info : dataArray) {
                String[] user = info.split("=");
                if (user[0].equals("uname")) {
                    System.out.println("你的用户名为：" + user[1]);
                    uname = user[1];
                } else if (user[0].equals("upass")) {
                    System.out.println("你的密码为：" + user[1]);
                    upass = user[1];
                }
            }
            if (uname.equals("shsxt") && upass.equals("laopei"))
                send("登陆成功！");
            else
                send("用户名或密码错误！");
            release();
        }

        private String receive() {
            String data = null;
            try {
                data = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void release() {
            //4、释放资源
            try {
                if(dos != null)
                    dos.close();
                if(dis != null)
                    dis.close();
                if(client != null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}