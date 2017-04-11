package com.github.jonatabecker.prc.barber;

import com.github.jonatabecker.prc.application.Logger;
import com.github.jonatabecker.prc.mvc.Model;

/**
 * Barber information
 *
 * @author JonataBecker
 */
public class Barber extends Model<BarberEvent> {

    /** Queue */
    private final BarberQueue barberQueue;
    /** Time sleep */
    private int sleep;
    /** Time processing */
    private int processing;
    /** State */
    private BarberState state;
    /** Thread */
    private Thread thread;

    /**
     * Creates a new barber
     *
     * @param barberQueue
     */
    public Barber(BarberQueue barberQueue) {
        this.barberQueue = barberQueue;
        this.state = BarberState.WAITING;
    }

    /**
     * Modify the state
     *
     * @param state
     * @param client
     */
    private void modifyState(BarberState state, BarberClient client) {
        this.state = state;
        fireEvents(new BarberEvent(client, this));
    }

    /**
     * Modify the state
     *
     * @param state
     */
    private void modifyState(BarberState state) {
        modifyState(state, null);
    }

    /**
     * Run
     */
    private void run() throws InterruptedException {
        Logger.barber().append("Dormindo");
        Thread.sleep(sleep);
        try {
            while (true) {
                BarberClient client = barberQueue.get();
                Logger.barber().append("Processando cliente " + client.getKey());
                modifyState(BarberState.PROCESSING, client);
                client.processing();
                Thread.sleep(processing);
                client.done();
            }
        } catch (QueueEmpty e) {
            modifyState(BarberState.WAITING);
        }
    }

    /**
     * Returns true if the barber is processing
     *
     * @return boolean
     */
    public boolean isProcessing() {
        return state == BarberState.PROCESSING;
    }

    /**
     * Initializes the process
     *
     * @param sleep
     * @param processing
     */
    public void init(int sleep, int processing) {
        this.sleep = sleep;
        this.processing = processing;
        if (thread != null) {
            thread.interrupt();
        }
        thread = new Thread(() -> {
            while (true) {
                try {
                    run();
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
