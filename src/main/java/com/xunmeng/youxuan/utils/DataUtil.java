package com.xunmeng.youxuan.utils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * ClassName: DataUtil
 * Package: com.xunmeng.utils
 * Description: 数据处理工具类
 *
 * @Author LTM
 * @Create 2023/5/15 10:13
 * @Version 1.0
 */
public class DataUtil {
    /**
     * description:获取UUID
     * @param:
     * @return: java.lang.String
     * @author LTM
     * @date: 2023/5/15 10:15
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     *  将输入字符串进行MD5哈希处理
     * @param input
     * @return
     */
    public static String getMd5String(String input) {
        try {
            // TODO 这里的MD5转换过程尤其是原代码部分，逻辑还需要熟悉
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            String hashText = DatatypeConverter.printHexBinary(messageDigest).toUpperCase();

            if (hashText.length() < 32) {
                StringBuilder sb = new StringBuilder();
                while (sb.length() < 32 - hashText.length()) {
                    sb.append("0");
                }
                hashText = sb.toString() + hashText;
            }

            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获取随机数
     * @param numLength
     * @return
     */
    public static String getRandomNum(int numLength) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < numLength; i++) {
            String rand = String.valueOf(random.nextInt(10));
            result.append(rand);
        }
        return result.toString();
    }
}
