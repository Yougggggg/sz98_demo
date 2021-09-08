package day23;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceive {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(1000);//指定端口号，端口不能重复
        while (true){//一直开启接收端
            byte[] bytes = new byte[1024];
            //定义一个数据包接收数据
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            //socket将接收到的数据存入数据包
            socket.receive(packet);
            //通过数据包方法取出数据
            String address = packet.getAddress().getHostAddress();
            String s = new String(packet.getData(), 0, packet.getLength());
            int port = packet.getPort();
            System.out.println(address+"::"+s+"::"+port);
        }
        //socket.close();接收端或者服务器一般都是一只开着就不用关闭
    }
}
