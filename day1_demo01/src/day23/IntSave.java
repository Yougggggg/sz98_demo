package day23;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class InterClient{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),1000);
        //读取本地文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader("hellowJava.txt"));

        //第二种定义标签方式 时间戳
//        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        long l = System.currentTimeMillis();
//        dataOutputStream.writeLong(l);//先传一个时间戳给服务器保存

        //打印流
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);//要加上true才会自动刷新
        String line;
        while ((line=bufferedReader.readLine())!=null){
            printWriter.println(line);
        }

        socket.shutdownOutput();// 第三种定义标签方式  引用方法
        // 关闭客服端输出流，相当于给流中加入一个结束标记 -1，让服务器知道客服端已经读取完了

        //printWriter.println(l);//第二种定义标签方式 时间戳 再次传给服务器时间戳让其进行比较

        //printWriter.println("over"); 第一种定义标签的方式 字符串

        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = bufferedReader1.readLine();
        System.out.println("服务器反馈信息："+s);

        bufferedReader.close();
        socket.close();
    }
}

public class IntSave {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);

        Socket accept = serverSocket.accept();

        //第一种定义标签的方式 时间戳 先保存客服端传来的时间戳 然后在客服端传输完后会在传相同的时间戳在进行比较
//        DataInputStream dataInputStream = new DataInputStream(accept.getInputStream());
//        long l = dataInputStream.readLong();

        System.out.println("IP:"+accept.getInetAddress().getHostAddress());

        PrintWriter printWriter = new PrintWriter(new FileWriter("interSava.txt"),true);
        //打印流接收的是file对象 字符串路径 字节输出流 字符输出流

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));

        String line;
        while ((line=bufferedReader.readLine())!=null){
//            if (line.equals("over")) 第一种定义标签的方式 字符串
//                break;
//            if (line.compareTo(l+"")==0) //第一种定义标签的方式 时间戳  与客服端先后传来的两次进行比较
//                break;
            printWriter.println(line);
        }

        PrintWriter printWriter1 = new PrintWriter(accept.getOutputStream(),true);
        printWriter1.println("上传成功");

        printWriter.close();
        accept.close();
        serverSocket.close();

    }
}
