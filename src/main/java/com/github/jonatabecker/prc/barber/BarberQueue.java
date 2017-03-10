package com.github.jonatabecker.prc.barber;

import com.github.jonatabecker.prc.mvc.Model;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Barber queue
 *
 * @author JonataBecker
 */
public class BarberQueue extends Model<BarberClient> {

    /** Time */
    private static final int TIME = 1000;
    /** Max */
    public static final int MAX = 5;

    /** Queue */
    private final LinkedList<BarberClient> queue;
    /** Thread */
    private Thread thread;
    /** Client counter */
    private int clientCounter;

    /**
     * Creates a new barber queue
     */
    public BarberQueue() {
        this.queue = new LinkedList<>();
    }

    /**
     * Builds a new client
     *
     * @return BarberClient
     */
    private BarberClient buildClient() {
        return new BarberClient(++clientCounter);
    }

    /**
     * Run
     */
    private void run() {
        try {
            if (queue.size() < MAX) {
                BarberClient client = buildClient();
                queue.add(client);
                fireEvents(client);
            }
            Thread.sleep(TIME);
        } catch (InterruptedException e) {
        }
    }

    /**
     * Get the next client
     *
     * @return BarberClient
     * @throws QueueEmpty
     */
    public BarberClient get() throws QueueEmpty {
        if (queue.isEmpty()) {
            throw new QueueEmpty();
        }
        return queue.pop();
    }

    /**
     * Initialize the process
     */
    public void init() {
        thread = new Thread(() -> {
            while (true) {
                run();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * The the clients
     *
     * @return {@code List<BarberClient>}
     */
    public List<BarberClient> getClients() {
        return Collections.unmodifiableList(queue);
    }
    
    /**
     * Returns the barber client
     * 
     * @param pos
     * @return BarberClient
     */
    public BarberClient getClient(int pos) {
        if (pos >= queue.size()) {
            return null;
        }
        return queue.get(pos);
    }

}
