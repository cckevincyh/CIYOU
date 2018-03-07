package com.ciyou.edu.utils

/**
 * @Author C.
 * @Date 2018-03-06 21:43
 */
class RGBAUtil {
    public static String randomColor(){
        int r = (int)Math.floor(Math.random() * 256)
        int g = (int)Math.floor(Math.random() * 256)
        int b = (int)Math.floor(Math.random() * 256)
        double a = ((int)Math.floor(Math.random() * 9) + 1) * 0.1
        return "rgba($r,$g,$b,$a)"
    }
}
