package com.sxt.loc;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IP：定位一个节点：计算机、路由、通讯设备
 * InetAddress:
 * 1、getLocalHost：本机
 * 2、getByName：根据域名DNS | IP
 */
public class IPTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());  //返回地址
        System.out.println(addr.getHostName());     //返回主机名

        addr = InetAddress.getByName("www.shsxt.com");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());

        addr = InetAddress.getByName("123.56.138.176");
        System.out.println(addr.getHostAddress());
        System.out.println(addr.getHostName());
    }
}
