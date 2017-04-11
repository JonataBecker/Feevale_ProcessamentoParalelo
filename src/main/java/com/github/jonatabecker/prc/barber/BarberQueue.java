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

    /** Queue */
    private final LinkedList<BarberClient> queue;
    /** Thread */
    private Thread thread;
    /** Time */
    private int time;
    /** Size */
    private int size;
    /** Client counter */
    private int clientCounter;

    /**
     * Creates a new barber queue
     */
    public BarberQueue() {
        this.queue = new LinkedList<>();
        this.size = 5;
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
    private synchronized void run() {
        if (queue.size() < size) {
            BarberClient client = buildClient();
            queue.add(client);
            fireEvents(client);
        }
    }

    /**
     * Get the next client
     *
     * @return BarberClient
     * @throws QueueEmpty
     */
    public synchronized BarberClient get() throws QueueEmpty {
        if (queue.isEmpty()) {
            throw new QueueEmpty();
        }
        BarberClient client = queue.pop();
        fireEvents(client);
        return client;
    }

    /**
     * Initialize the process
     *
     * @param time
     * @param size
     */
    public void init(int time, int size) {
        this.time = time;
        this.size = size;
        queue.clear();
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(() -> {
            while (true) {
                try {
                    run();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    break;
                }
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

    /**
     * Returns the queue size
     *
     * @return int
     */
    public int getSize() {
        return size;
    }
}
