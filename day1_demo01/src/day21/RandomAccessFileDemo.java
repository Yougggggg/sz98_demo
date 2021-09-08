package day21;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {
    public static void main(String[] args) throws IOException{
        writeFile();
        //readFile();
    }
    public static void readFile() throws IOException{
        RandomAccessFile raf=new RandomAccessFile("ran.txt","r");
        raf.seek(8);//设置指针，获取指定位置的文件数据
        byte[] buf=new byte[4];
        raf.read(buf);
        String name=new String(buf);
        int age=raf.readInt();
        System.out.println(name+":"+age);
        raf.close();
    }
    public static void writeFile() throws IOException {
        RandomAccessFile raf=new RandomAccessFile("ran.txt","rw");
        raf.seek(0);//通过指针指定数据插入文件的位置
        raf.write("游勇".getBytes());
        //raf.write(258);//write写出的数据最多一个字节是8位，而258的字节9位，所以存在数据丢失
        raf.writeInt(100);//这是将4个字节都写出去
        raf.close();
    }
}
