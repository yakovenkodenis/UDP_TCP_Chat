package server.udp;


import server.Responder;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;


public class UDPResponder extends Responder implements Runnable {


    private SocketAddress clientAddress;

    private DatagramChannel channel;

    private ByteBuffer out;

    private DatagramManager datagramManager;

    public UDPResponder(SocketAddress clientAddress, DatagramChannel channel) {
        this.clientAddress = clientAddress;
        this.channel = channel;
        out = ByteBuffer.allocate(8);
        datagramManager = new DatagramManager(channel.socket());
    }

    @Override
    public void run() {
        try {
            channel.send(makeResponse(), clientAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected ByteBuffer makeResponse() {
        out.order(ByteOrder.BIG_ENDIAN);

        long secondsSince1970 = System.currentTimeMillis();
        out.clear();
        out.putLong(secondsSince1970);
        out.flip();

        out.position(4);
        return out;
    }
}
