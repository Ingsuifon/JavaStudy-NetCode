package com.sxt.chat03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务端
 * 目标：封装 使用多线程实现多个客户可以正常收发多条信息
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
            new Thread(new Channel(client)).start();
        }
    }

    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        boolean isRun;

        public Channel(Socket client) {
            this.client = client;
            this.isRun = true;
            try {
                this.dis = new DataInputStream(new BufferedInputStream(this.client.getInputStream()));
                this.dos = new DataOutputStream(new BufferedOutputStream(this.client.getOutputStream()));
            } catch (IOException e) {
                System.out.println("----1----");
                release();
            }
        }

        @Override
        public void run() {
            while(isRun) {
                String msg = receive();
                if(!msg.equals(""))
                    send(msg);
            }
        }

        //接收消息
        private String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                System.out.println("----2----");
                release();
            }
            return msg;
        }

        //发送消息
        private void send(String msg) {
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                System.out.println("----3----");
                release();
            }
        }
        //释放资源
        private void release() {
            isRun = false;
            SxtUtils.close(dis, dos, client);
        }
    }
}