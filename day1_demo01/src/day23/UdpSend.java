package day23;

import java.io.IOException;
import java.net.*;

public class UdpSend {
    public static void main(String[] args) throws IOException {
        //传教datagramSocket对象
        DatagramSocket datagramSocket = new DatagramSocket();
        //定义数据内容
        byte[] bytes = "Send UdpSend Message2222".getBytes();
        //定义数据包，包含数据，数据长度，接收端地址，接收端口
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 1000);
        //发送
        datagramSocket.send(packet);
        datagramSocket.close();
    }
}
