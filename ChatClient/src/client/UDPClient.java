package client;


import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;

public class UDPClient {

    protected String host;

    protected int port;

    protected DatagramSocket socket;

    public UDPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void echoServer() {
        try {
            DatagramChannel channel = DatagramChannel.open();

            SocketAddress address = new InetSocketAddress(0);
            socket = channel.socket();
            socket.setSoTimeout(5000);
            socket.bind(address);

            SocketAddress server = new InetSocketAddress(host, port);
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            buffer.order(ByteOrder.BIG_ENDIAN);
            buffer.put((byte) 65);
            buffer.flip();

            channel.send(buffer, server);

            buffer.clear();
            buffer.put((byte) 0).put((byte) 0).put((byte) 0).put((byte) 0);

            channel.receive(buffer);
            buffer.flip();

            long secondsSince1970 = buffer.getLong();

            System.out.println("CLIENT: " + secondsSince1970);

            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String send(String message) {
        String response = null;

        try {
            socket = new DatagramSocket();
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName(host);
            DatagramPacket request = new DatagramPacket(m, message.length(), aHost, port);

            socket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            socket.setSoTimeout(2000);

            socket.receive(reply);

            response = new String(reply.getData()).trim();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }

        return response;
    }

    public void sendOnly(String message) {

        try {
            socket = new DatagramSocket();
            byte[] m = message.getBytes();
            InetAddress aHost = InetAddress.getByName(host);

            DatagramPacket request = new DatagramPacket(m, message.length(), aHost, port);
            socket.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
