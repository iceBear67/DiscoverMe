package io.ib67;

/**
 * A server entry to be presented in Minecraft server list.
 */
public class ServerEntry {
    private String motd;
    private String address;
    private int port;

    /**
     * Constructs a server list ping entry, without port.
     *
     * @param motd Message
     * @param addr address
     */
    public ServerEntry(String motd, String addr) {
        this(motd, addr, -1);
    }

    /**
     * Constructs a server list ping entry
     *
     * @param motd Message
     * @param addr address
     * @param port -1 if you don't need this.
     */
    public ServerEntry(String motd, String addr, int port) {
        this.motd = motd;
        address = addr;
        this.port = port;
    }

    @Override
    public int hashCode() {
        int i = 1;
        i = i * 31 + motd.hashCode();
        i = i * 31 + port;
        i = i * 31 + address.hashCode();
        return i;
    }

    @Override
    public String toString() {
        return "[MOTD]" + motd + "[/MOTD][AD]" + (address == null ? "" : address) + (port == -1 ? "" : port) + "[/AD]";
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }

    public String getMotd() {
        return motd;
    }
}
