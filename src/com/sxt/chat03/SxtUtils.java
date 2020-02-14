package com.sxt.chat03;

import java.io.Closeable;
import java.io.IOException;

public class SxtUtils {
    /**
     * 释放资源
     */
    public static void close(Closeable...targets) {
        for (Closeable t: targets) {
            if(t != null) {
                try {
                    t.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

