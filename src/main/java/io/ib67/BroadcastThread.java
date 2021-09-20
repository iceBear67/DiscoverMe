package io.ib67;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class BroadcastThread extends Thread {
    private static final String MINECRAFT_IGMP_ADDR = "224.0.2.60";
    private static final int MINECRAFT_IGMP_PORT = 4445;
    private DatagramSocket socket;
    private volatile boolean running = true;

    public BroadcastThread() {
        super("DiscoverMe #Broadcast");
        setDaemon(true);
        try {
            socket = new DatagramSocket();
        } catch (SocketException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running && !isInterrupted()) {
            try {
                InetAddress inetAddress = InetAddress.getByName(MINECRAFT_IGMP_ADDR);
                DiscoverMe.INSTANCE.forEach(e -> {
                    byte[] payload = e.toString().getBytes(StandardCharsets.UTF_8);
                    DatagramPacket datagramPacket = new DatagramPacket(payload, payload.length, inetAddress, MINECRAFT_IGMP_PORT);
                    try {
                        socket.send(datagramPacket);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
            } catch (Throwable t) {
                t.printStackTrace();
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignored) {

            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        this.running = false;
    }
}
