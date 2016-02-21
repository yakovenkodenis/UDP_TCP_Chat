package server;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

    protected String host;

    protected int port;

    protected DatagramSocket socket;

    protected DatagramPacket request;

    protected int DATAGRAM_LENGTH;

    protected byte[] buffer;

    public UDPServer(String host, int port, int DATAGRAM_LENGTH) throws SocketException {
        this.host = host;
        this.port = port;
        this.DATAGRAM_LENGTH = DATAGRAM_LENGTH;

        socket = new DatagramSocket(this.port);
        buffer = new byte[this.DATAGRAM_LENGTH];
        request = new DatagramPacket(buffer, buffer.length);
    }

    public UDPServer(String host, int port) throws SocketException {
        this(host, port, 6400);
    }

    public UDPServer() {}

    public String receiveRequest() {
        String data = null;

        try {
            buffer = new byte[DATAGRAM_LENGTH];
            request = new DatagramPacket(buffer, buffer.length);
            socket.receive(request);
            data = new String(request.getData());
        } catch(IOException e) {
            e.printStackTrace();
        }

        return data != null ? data.trim() : "";
    }

    public void sendResponse(String response) {
        DatagramPacket reply = new DatagramPacket(response.getBytes(), response.length(),
                request.getAddress(), request.getPort());

        try {
            socket.send(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isNull() {
        return socket == null;
    }

    public void close() {
        if (!isNull()) {
            socket.close();
        }
    }
}
