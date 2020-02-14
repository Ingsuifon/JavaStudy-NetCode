package com.sxt.chat04;

import com.sxt.chat03.SxtUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室：服务端
 * 目标：加入容器实现群聊
 */
public class Chat {
    private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        System.out.println("------Server------");
        //1、指定端口 使用ServerSocket创建服务器
        ServerSocket server = new ServerSocket(9999);
        //2、阻塞式地等待连接 accept
        while(true) {
            Socket client = server.accept();
            System.out.println("一个客户端建立了连接");
            Channel c = new Channel(client);
            all.add(c);  //管理所有成员
            new Thread(c).start();
        }
    }

    static class Channel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket client;
        private String name;
        private boolean isRun;

        public Channel(Socket client) {
            this.client = client;
            this.isRun = true;
            try {
                this.dis = new DataInputStream(new BufferedInputStream(this.client.getInputStream()));
                this.dos = new DataOutputStream(new BufferedOutputStream(this.client.getOutputStream()));
                name = receive();
                send("欢迎加入聊天室！");
                sendOthers(name + "进入了聊天室", true);
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
                    sendOthers(msg, false);
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

        //群聊
        private void sendOthers(String msg, boolean isSys) {
            for(Channel c: all) {
                if(c != this) {
                    if(!isSys)
                        c.send(name + ": " + msg);
                    else
                        c.send(msg);
                }
            }
        }
        //释放资源
        private void release() {
            isRun = false;
            SxtUtils.close(dis, dos, client);
            all.remove(this);
            sendOthers(name + "离开了聊天室", true);
        }
    }
}