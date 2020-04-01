package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class EncryptUtil {

    private static final byte KEY = 1;

    //加密 先做编码，再做异或
    public static String encrypt(String s) throws UnsupportedEncodingException {

        s = URLEncoder.encode(s, "utf-8");

        byte[] bytes = s.getBytes();

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= KEY;
        }

        return new String(bytes);
    }

    //解密 先做异或，再做解码
    public static String decrypt(String s) throws UnsupportedEncodingException {

        byte[] bytes = s.getBytes();

        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= KEY;
        }

        return URLDecoder.decode(new String(bytes), "utf-8");

    }

    public static String getMD5String(String s) {

        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        try {
            byte[] btInput = s.getBytes();

            //获取MessageDigest对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            //使用指定的字节更新摘要
            messageDigest.update(btInput);

            //获得密文
            byte[] md = messageDigest.digest();

            //把密文转换成十六进制的字符串形式
            int len = md.length;

            char result[] = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {

                byte b = md[i];

                result[k++] = hexDigits[b >>> 4 & 0xf];
                result[k++] = hexDigits[b & 0xf];
            }

            return new String(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //String s = encrypt("用户2");
        //System.out.println(decrypt(s));

        System.out.println(getMD5String("abc"));  //900150983CD24FB0D6963F7D28E17F72
        System.out.println(getMD5String("东软sdfsdsdfsdg"));  //F59D905D2609B085E17CD404C689684B

    }
}
