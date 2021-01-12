package md5Test;

import lombok.SneakyThrows;

import java.security.MessageDigest;

/**
 * @author xucheng.liu
 * @date 2020/12/10
 */
public class Test {

    static char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    @SneakyThrows
    public static void main(String[] args) {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        String input = "18523281593";
        md5.update(input.getBytes());
        System.out.print(bytes2Hex(md5.digest()));
    }

    private static String bytes2Hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte byte0 = bytes[i];
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
}
