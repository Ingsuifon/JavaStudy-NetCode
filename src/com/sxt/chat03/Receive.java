package com.sxt.chat03;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable {
    private DataInputStream dis;
    private Socket client;
    private boolean isRun;

    public Receive(Socket client) {
        this.client = client;
        this.isRun = true;
        try {
            dis = new DataInputStream(new BufferedInputStream(client.getInputStream()));
        } catch (IOException e) {
            System.out.println("====4====");
            release();
        }
    }

    @Override
    public void run() {
        while(isRun) {
            String msg = receive();
            if(!msg.equals(""))
                System.out.println(msg);
        }
    }

    private String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            System.out.println("====5====");
            release();
        }
        return msg;
    }

    private void release() {
        isRun = false;
        SxtUtils.close(dis, client);
    }
}
