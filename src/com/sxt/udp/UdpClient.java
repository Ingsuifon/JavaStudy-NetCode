package com.sxt.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * 发送端
 * 1、使用DatagramSocket 指定端口 创建发送端
 * 2、准备数据 一定转成字节数组 封装成DatagramPacket
 * 3、封装成DatagramPacket包裹，需要指定目的地
 * 4、发送包裹send(DatagramPacket p)
 * 5、释放资源
 */

public class UdpClient {
    public static void main(String[] args) throws Exception {
        System.out.println("发送方启动中......");
        //1、使用DatagramSocket 指定端口 创建发送端
        DatagramSocket client = new DatagramSocket(8888);
        //2、准备数据 一定转成字节数组 封装成DatagramPacket
        String data = "上海尚学堂";
        byte[] datas = data.getBytes();
        //3、封装成DatagramPacket包裹，需要指定目的地
        DatagramPacket packet = new DatagramPacket(datas, 0, datas.length, new InetSocketAddress("localhost", 9999));
        //4、发送包裹send(DatagramPacket p)
        client.send(packet);
        //5、释放资源
        client.close();
    }
}
