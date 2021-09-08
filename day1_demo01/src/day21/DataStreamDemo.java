package day21;

import java.io.*;

public class DataStreamDemo {
    public static void main(String[] args) throws IOException {
        //writeData();
        raedData();
    }
    public static void raedData() throws IOException {
        DataInputStream dis =new DataInputStream(new FileInputStream("data.txt"));
        int num = dis.readInt();
        boolean b= dis.readBoolean();
        double d=dis.readDouble();
        System.out.println(num+""+":"+b+":"+d);
    }
    public static void writeData() throws IOException {
        DataOutputStream dos =new DataOutputStream(new FileOutputStream("data.txt"));
        dos.writeInt(234);
        dos.writeBoolean(true);
        dos.writeDouble(9887.64);

        dos.close();
    }
}
