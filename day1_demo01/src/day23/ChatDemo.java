package day23;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

class Send implements Runnable{
    private DatagramSocket socket;

    public Send(DatagramSocket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            String line = sc.nextLine();
            if (line.equals("over"))
                break;
            byte[] bytes = line.getBytes();
            try {
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 1000);
                socket.send(packet);
            } catch (Exception e) {
                throw new RuntimeException("发送失败");
            }
        }
        socket.close();
        sc.close();
    }
}

class Receive implements Runnable{
    private DatagramSocket socket;

    public Receive(DatagramSocket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            while (true){
                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes,bytes.length);
                socket.receive(packet);
                String ip = packet.getAddress().getHostAddress();
                String s = new String(packet.getData(), 0, packet.getLength());
                System.out.println("ip:"+ip+"__data:"+s);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ChatDemo {
    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket();
        DatagramSocket socketR = new DatagramSocket(1000);
        new Thread(new Send(socket)).start();
        new Thread(new Receive(socketR)).start();
    }
}
