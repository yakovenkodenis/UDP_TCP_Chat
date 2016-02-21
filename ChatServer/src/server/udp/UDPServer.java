package server.udp;

import server.Server;
import util.inet.InetUtils;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class UDPServer extends Server implements Runnable {

    private final ExecutorService executor = Executors.newWorkStealingPool();

    protected static final int MAX_BUFFER_SIZE = 8192;

    @Override
    public void run() {
        try {
            ByteBuffer in = ByteBuffer.allocate(MAX_BUFFER_SIZE);
            ByteBuffer out = ByteBuffer.allocate(8);
            out.order(ByteOrder.BIG_ENDIAN);

            SocketAddress socketAddress = new InetSocketAddress(InetUtils.getLocalHostLANAddress(), DEFAULT_PORT);

            DatagramChannel channel = DatagramChannel.open();
            DatagramSocket socket = channel.socket();
            socket.bind(socketAddress);

            System.out.println("Bound to " + socketAddress);

            while (isRunning) {
                try {
                    in.clear();

                    SocketAddress clientAddress = channel.receive(in);
                    System.out.println(clientAddress);


                    executor.execute(new UDPResponder(clientAddress, channel));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
        } finally {
            executor.shutdown();
        }
    }

    @Override
    protected void shutdown() {
        isRunning = false;
    }
}
