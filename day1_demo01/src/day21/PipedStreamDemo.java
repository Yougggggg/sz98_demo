package day21;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedStreamDemo {


    public static void main(String[] args) throws Exception{
        PipedInputStream in=new PipedInputStream();
        PipedOutputStream out =new PipedOutputStream();
        in.connect(out);//管道输入流与输出流连接
        new Thread(new Read(in)).start();
        new Thread(new Write(out)).start();
    }

}
class Read implements Runnable{

    private PipedInputStream in;
    Read(PipedInputStream in){
        this.in=in;
    }

    @Override
    public void run() {
        try {
            byte[] buf=new byte[1024];
            int len=in.read(buf);
            String s=new String(buf,0,len);
            System.out.println(s);
            in.close();
        }catch (IOException e){//父类没有抛异常，所以子类中的异常只能try不能抛
            throw new RuntimeException("管道读取失败");
        }
    }
}
class Write implements Runnable{

    PipedOutputStream out;

    public Write(PipedOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {
        try {
            System.out.println("输入流睡眠，输出流等待");
            Thread.sleep(6000);
            out.write("piped lai la".getBytes());
            out.close();
        }catch (IOException e){//父类没有抛异常，所以子类中的异常只能try不能抛
            throw new RuntimeException("管道读取失败");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}