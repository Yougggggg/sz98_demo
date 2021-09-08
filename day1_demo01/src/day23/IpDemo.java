package day23;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.toString());
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());

        InetAddress[] byName = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress i : byName) {
            System.out.println(i.getHostName());
            System.out.println(i.getHostAddress());
        }

        InetAddress byName1 = InetAddress.getByName("192.168.10.11");
        System.out.println(byName1.getHostName());
    }
}
