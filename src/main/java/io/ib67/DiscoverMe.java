package io.ib67;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Predicate;

public enum DiscoverMe {
    INSTANCE;
    private static final Queue<ServerEntry> ENTRIES = new LinkedBlockingQueue<>();
    private static volatile boolean THREAD_LAUNCHED = false;

    static {
        if (!THREAD_LAUNCHED) {
            new BroadcastThread().start();
            THREAD_LAUNCHED = true;
        }
    }

    public void addEntry(ServerEntry... entry) {
        ENTRIES.addAll(Arrays.asList(entry));
    }

    public void addEntry(String motd) {
        addEntry(null, motd);
    }

    public void addEntry(String motd, int port) {
        addEntry(null, motd, port);
    }

    public void addEntry(String host, String motd, int port) {
        addEntry(new ServerEntry(motd, host, port));
    }

    public void addEntry(String host, String motd) {
        addEntry(host, motd, -1);
    }

    public void removeEntry(ServerEntry entry) {
        ENTRIES.remove(entry);
    }

    public void removeEntry(Predicate<ServerEntry> predicate) {
        ENTRIES.removeIf(predicate);
    }

    public void removeEntry(String host, int port) {
        ENTRIES.removeIf(e -> e.getAddress().equals(host) && e.getPort() == port);
    }

    public void removeEntry(String host) {
        ENTRIES.removeIf(e -> e.getAddress().equals(host));
    }

    protected void forEach(Consumer<? super ServerEntry> consumer) {
        ENTRIES.forEach(consumer);
    }
}
