package day21;

import java.io.*;

public class ObjectStreamDemo {
    public static void main(String[] args) throws Exception {
        //writeObj();
        readObj();
    }

    static void readObj() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("obj.txt"));
        Person person=(Person) ois.readObject();
        System.out.println(person);
        ois.close();
    }

    static void writeObj() throws IOException {
        ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("obj.txt"));
        oos.writeObject(new Person("lishi",3000,"hr"));
        oos.close();
    }

}
