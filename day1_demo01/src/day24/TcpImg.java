package day24;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ImgClient{

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(),1000);
        FileInputStream fileIn = new FileInputStream("test.jpg");
        OutputStream socOUt = socket.getOutputStream();
        byte[] bytes = new byte[fileIn.available()];
        fileIn.read(bytes);

        socOUt.write(bytes);
        socket.shutdownOutput();

        InputStream socIn = socket.getInputStream();
        byte[] bytes1 = new byte[1024];
        int i = socIn.read(bytes1);
        System.out.println("服务器反馈："+new String(bytes1,0,i));

        fileIn.close();
        socket.close();
    }
}

class ImgClient2{

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getByName("192.168.23.1"),1000);
        FileInputStream fileIn = new FileInputStream("test2.jpg");
        OutputStream socOUt = socket.getOutputStream();
        byte[] bytes = new byte[fileIn.available()];
        fileIn.read(bytes);

        socOUt.write(bytes);
        socket.shutdownOutput();

        InputStream socIn = socket.getInputStream();
        byte[] bytes1 = new byte[1024];
        int i = socIn.read(bytes1);
        System.out.println("服务器反馈："+new String(bytes1,0,i));

        fileIn.close();
        socket.close();
    }
}

class InertThread implements Runnable{

    private Socket socket;

    public InertThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        int count=1;
        try {
            String ip = socket.getInetAddress().getHostAddress();
            System.out.println(ip);

            File file = new File(ip+".jpg");
            while (file.exists()){
                file=new File(ip+"("+count+++").jpg");
            }

            InputStream socIn = socket.getInputStream();
            //写入流
            FileOutputStream fileOUt = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len=socIn.read(bytes))!=-1){
                fileOUt.write(bytes,0,len);
            }

            OutputStream serverOut = socket.getOutputStream();
            serverOut.write((ip+"上传成功").getBytes());

            fileOUt.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class TcpImg {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        while (true) {
            new Thread(new InertThread(serverSocket.accept())).start();
        }
    }
}
