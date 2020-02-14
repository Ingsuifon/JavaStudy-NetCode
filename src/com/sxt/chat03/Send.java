package com.sxt.chat03;

import java.io.*;
import java.net.Socket;

public class Send implements Runnable {
    private BufferedReader console;
    private DataOutputStream dos;
    private boolean isRun;
    private Socket client;
    private String name;
    public Send(Socket client, String name) {
        console = new BufferedReader(new InputStreamReader(System.in));
        this.name = name;
        isRun = true;
        try {
            dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
            send(name);
        } catch (IOException e) {
            System.out.println("====1====");
            release();
        }
    }

    @Override
    public void run() {
        while(isRun) {
            String msg = getStringFromConsole();
            if(!msg.equals("")) {
                send(msg);
            }
        }
    }

    private void send(String msg) {
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            System.out.println("====2====");
            release();
        }
    }

    private String getStringFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void release() {
        isRun = false;
        SxtUtils.close(dos, client);
    }
}
