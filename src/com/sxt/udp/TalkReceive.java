package com.sxt.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端：使用面向对象封装
 */
public class TalkReceive implements Runnable {
    DatagramSocket server;
    private String from;
    public TalkReceive(int port, String from) {
        this.from = from;
        try {
            this.server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            byte[] container = new byte[1024 * 60];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            //3、阻塞式接收包裹receive(DatagramPacket p)
            try {
                server.receive(packet);
                //4、分析数据
                byte[] datas = packet.getData();
                String msg = new String(datas, 0, packet.getLength());
                System.out.println(from + "：" + msg);
                if(msg.equals("bye"))
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //5、释放资源
        server.close();
    }
}
