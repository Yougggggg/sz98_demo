package day23;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.*;

class TcpClient{
    public static void main(String[] args) throws IOException {
        //创建Socket对象，指定主机和端口
        //Socket socket = new Socket(InetAddress.getLocalHost(),20004);
        //URL对象
        URL url = new URL("http://localhost:1000");
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoOutput(true);
        OutputStream outputStream = urlConnection.getOutputStream();
        //为了发送数据获取socket中的输出流
//        OutputStream stream = socket.getOutputStream();
//        stream.write("服务器 你好".getBytes());
        outputStream.write("服务器 你好".getBytes());

        InputStream inputStream = urlConnection.getInputStream();
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        System.out.println("服务器反馈信息："+new String(bytes,0,read));

        //接收服务器的反馈信息
//        InputStream inputStream = socket.getInputStream();
//        byte[] bytes = new byte[1024];
//        int read = inputStream.read(bytes);
//        System.out.println("服务器反馈信息："+new String(bytes,0,read));
//        stream.close();
    }
}

class TcpService{
    public static void main(String[] args) throws IOException {
        //建立服务器ServiceSocket对象，并监听一个端口
        ServerSocket socket = new ServerSocket(1000);
        //获取连接过来的客服端对象

            Socket accept = socket.accept();
            String ip = accept.getInetAddress().getHostAddress();//查看IP确定客服端有没有连进来
            System.out.println(ip);
            //使用获取的客户端对象读取客服端传来的数据
        InputStream inputStream = accept.getInputStream();//源：网络流
        byte[] bytes = new byte[1024];
        int read = inputStream.read(bytes);
        System.out.println("ip:"+ip+"__data:"+new String(bytes,0,read));

            //给客户端反馈
//            OutputStream outputStream = accept.getOutputStream();
//            outputStream.write("客户端 你也好".getBytes());
//            accept.close();//关闭从连接获取的客服端
            PrintWriter printWriter = new PrintWriter(accept.getOutputStream());
            printWriter.println("你好你好");
            accept.close();
            socket.close();
    }
}

public class TcpDemo {
    public static void main(String[] args) {
    }
}
