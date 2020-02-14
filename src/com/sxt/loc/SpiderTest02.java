package com.sxt.loc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络爬虫原理+模拟浏览器
 *
 */
public class SpiderTest02 {
    public static void main(String[] args) throws Exception {
        //获取URL
        //URL url = new URL("https://www.jd.com");
        URL url = new URL("https://www.dianping.com");
        //下载资源
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", " Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.87 Safari/537.36 Edg/80.0.361.50");
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg = null;
        while ((msg = br.readLine()) != null)
            System.out.println(msg);
        br.close();
    }
}
