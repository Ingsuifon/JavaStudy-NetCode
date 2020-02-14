package com.sxt.loc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络爬虫原理
 *
 */
public class SpiderTest01 {
    public static void main(String[] args) throws Exception {
        //获取URL
        //URL url = new URL("https://www.jd.com");
        URL url = new URL("https://www.dianping.com");
        //下载资源
        InputStream is = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String msg = null;
        while ((msg = br.readLine()) != null)
            System.out.println(msg);
        br.close();
    }
}
