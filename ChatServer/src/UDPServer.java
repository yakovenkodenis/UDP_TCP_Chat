import util.inet.InetUtils;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


public class UDPServer {

    public static int DEFAULT_PORT = 37;

    final ExecutorService executor = Executors.newWorkStealingPool();

    private AtomicInteger threadsCount = new AtomicInteger(0);

    public void run() throws Exception {

        ByteBuffer in = ByteBuffer.allocate(8192);
        ByteBuffer out = ByteBuffer.allocate(8);
        out.order(ByteOrder.BIG_ENDIAN);

        SocketAddress socketAddress = new InetSocketAddress(InetUtils.getLocalHostLANAddress(), DEFAULT_PORT);

        DatagramChannel channel = DatagramChannel.open();
        DatagramSocket socket = channel.socket();
        socket.bind(socketAddress);

        System.out.println("Bound to " + socketAddress);

        while(true) {
            try {
                in.clear();

                SocketAddress client = channel.receive(in);
                System.out.println(client);

                long secondsSince1970 = System.currentTimeMillis();
                out.clear();
                out.putLong(secondsSince1970);
                out.flip();

                out.position(4);
                channel.send(out, client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
