package server;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastServer extends UDPServer {

    public UDPMulticastServer(String host, int port, int DATAGRAM_LENGTH) throws IOException {
        super();

        this.host = host;
        this.port = port;
        this.DATAGRAM_LENGTH = DATAGRAM_LENGTH;

        buffer = new byte[this.DATAGRAM_LENGTH];
        socket = new MulticastSocket(this.port);
        request = new DatagramPacket(buffer, buffer.length);

        ((MulticastSocket)socket).joinGroup(InetAddress.getByName(this.host));
    }

    public UDPMulticastServer(String host, int port) throws IOException {
        this(host, port, 6400);
    }

    public String receive() {
        String data = null;

        try {
            ((MulticastSocket)socket).receive(request);
//            Arrays.fill(buffer, (byte) 0);
            data = new String(request.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data != null ? data.trim() : "";
    }
}
