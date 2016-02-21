package server.udp;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;


public final class DatagramManager {

    private final DatagramSocket socket;

    public DatagramManager(DatagramSocket socket) {
        this.socket = socket;
    }

    public String readLine(DatagramPacket packet) throws IOException {
        socket.receive(packet);
        return new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(packet.getData())),
                UDPServer.MAX_BUFFER_SIZE).readLine();
    }

    public void writeLine(DatagramPacket packet, String string) throws IOException {
        packet.setData(string.concat("\r\n").getBytes());
        socket.send(packet);
    }
}
