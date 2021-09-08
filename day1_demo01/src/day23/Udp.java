package day23;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Udp {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if (line.equals("over"))
                break;
            byte[] bytes = line.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("192.168.23.255"), 1000);
            socket.send(packet);
        }
        scanner.close();
        socket.close();
    }
}
class UdpRe{
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024*64];//一次最多接收64kb
        DatagramSocket socket = new DatagramSocket(1000);
        while (true){
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);//接收数据存入数据包
            String address = packet.getAddress().getHostAddress();
            String s = new String(packet.getData(), 0, packet.getLength());
            System.out.println("IP:"+address+"--data:"+s+"--port:"+packet.getPort());
        }
    }
}