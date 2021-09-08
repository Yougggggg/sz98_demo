package day23;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//客服端发送给服务端，服务端返回大写
class TransClient{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),1000);
        //读取键盘输入
        Scanner sc = new Scanner(System.in);
        //定义目的写入到socket输入流，发送给服务器
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);//字符打印流
        // 为true就带有换行，并自动刷新不用flush
        //定义socket读取流，读取服务端返回数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line;
        while ((line=sc.nextLine())!=null){
            if (line.equals("over"))
                break;
            //bufferedWriter.write(line);//用的buff所以是写入缓冲区的，要用flush刷新
            //bufferedWriter.newLine();//但是服务器都数据使用readline，需要都到换行符才是读完一行，所以这里还需要加上换行标识
            //bufferedWriter.flush();
            printWriter.println(line);
            //读取服务器返回内容
            String s = bufferedReader.readLine();
            System.out.println("server:"+s);
        }
        sc.close();
        socket.close();
    }
}

public class TcpUp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket accept = serverSocket.accept();
        System.out.println(accept.getInetAddress().getHostAddress());
        //读取客服端数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        //写入socket输出流，发给客服端
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream()));
        PrintWriter printWriter = new PrintWriter(accept.getOutputStream(), true);//字符打印流

        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println(line);
            printWriter.println(line.toUpperCase());
            //bufferedWriter.write(line.toUpperCase());
            //bufferedWriter.newLine();
            //bufferedWriter.flush();
        }
        accept.close();
        serverSocket.close();

    }
}
