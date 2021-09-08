package day21;

import java.io.*;

public class EncodeStream {
    public static void main(String[] args) throws IOException {
        //writeText();
        readText();
    }
    public static void readText() throws IOException{
        InputStreamReader isr=new InputStreamReader(new FileInputStream("utf.txt"));
        char[] buf=new char[10];//转换流后读出的是字符，所以用char数组
        int i = isr.read(buf);//返回读入缓冲区char[]中的字符总数，读到流末尾返回-1
        String s=new String(buf,0,i);
        System.out.println(s);

        isr.close();

    }
    public static void writeText() throws IOException{
        //默认以UTF-8格式转换，也可以自己指定编码格式
        OutputStreamWriter osw =new OutputStreamWriter(new FileOutputStream("utf.txt"));
        osw.write("你好");
        osw.close();
    }
}
