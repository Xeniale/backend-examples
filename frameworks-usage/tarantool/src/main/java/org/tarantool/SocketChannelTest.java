package org.tarantool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class SocketChannelTest {

    public static void main(String[] args) {
        String host = "192.168.6.134";
        int port = 3301;
        SocketAddress socketAddress = new InetSocketAddress(host, port);
        try {
            SocketChannel socketChannel1 = SocketChannel.open(socketAddress);
            System.out.println("channel 1 opened");

            SocketChannel socketChannel2 = SocketChannel.open(socketAddress);
            System.out.println("channel 2 opened");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
