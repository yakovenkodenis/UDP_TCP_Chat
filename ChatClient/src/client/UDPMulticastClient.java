package client;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UDPMulticastClient extends UDPClient {

    public UDPMulticastClient(String host, int port) throws IOException {
        super(host, port);
    }

    public void broadcast(String message) {
        try {
            socket = new MulticastSocket(port);
            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(),
                    InetAddress.getByName(host), port);
            socket.send(packet);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }
}
