package com.sxt.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpTalkServer {
    public static void main(String[] args) throws Exception {
        System.out.println("接收方启动中......");
        //1、使用DatagramSocket 指定端口 创建接收端
        DatagramSocket server = new DatagramSocket(9999);
        //2、准备容器 封装成DatagramPacket
        while(true) {
            byte[] container = new byte[1024 * 60];
            DatagramPacket packet = new DatagramPacket(container, 0, container.length);
            //3、阻塞式接收包裹receive(DatagramPacket p)
            server.receive(packet);
            //4、分析数据
            byte[] datas = packet.getData();
            String msg = new String(datas, 0, packet.getLength());
            System.out.println(msg);
            if(msg.equals("bye"))
                break;
        }
        //5、释放资源
        server.close();
    }
}
