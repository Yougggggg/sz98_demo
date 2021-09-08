package day21;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class EncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s="你好";
        byte[] bytes = s.getBytes("GBK");//编码 默认utf-8
        System.out.println(Arrays.toString(bytes));

        String s1 = new String(bytes, "ISO8859-1");//解码 默认utf-8

        System.out.println(s1);

        byte[] gbks = s1.getBytes("ISO8859-1");//当解码错误时，就用该码重新编码会原有的码，再用正确格式解码
        System.out.println(Arrays.toString(gbks));

        System.out.println(new String(gbks,"GBK"));

        System.out.println("联通");
        String lt="联通";
        byte[] gbks1 = lt.getBytes("GBK");
        System.out.println(Arrays.toString(gbks1));

        for(byte g : gbks1){
            System.out.println(Integer.toBinaryString(g&255));
        }

    }

}
