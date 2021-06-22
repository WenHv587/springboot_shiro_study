package edu.cqupt.springboot_shiro_jsp.utils;

import java.util.Random;

/**
 * @author LWenH
 * @create 2021/6/20 - 0:28
 *
 * 随机盐生成工具类
 */
public class SaltUtils {

    /**
     * 生成随机salt
     * @param n 随机salt的长度
     * @return
     */
    public static String getSalt(int n) {
        char[] chars = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chars[new Random().nextInt(chars.length)];
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(5));
    }

}
