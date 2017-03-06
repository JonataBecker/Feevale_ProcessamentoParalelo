package com.github.jonatabecker.prc.barber;

import com.github.jonatabecker.prc.mvc.Model;

/**
 * Barber information
 *
 * @author JonataBecker
 */
public class Barber extends Model<BarberEvent> {

    /** Time waiting */
    private static final int TIME = 5000;
    /** Time processing */
    private static final int TIME_PROCESSING = 200;

    /** Queue */
    private final BarberQueue barberQueue;
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
        this.state = BarberState.WAINTING;
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
    private void run() {
        try {
            Thread.sleep(TIME);
            try {
                while (true) {
                    BarberClient client = barberQueue.get();
                    modifyState(BarberState.PROCESSING, client);
                    client.processing();
                    Thread.sleep(TIME_PROCESSING);
                    client.done();
                }
            } catch (QueueEmpty e) {
                modifyState(BarberState.WAINTING);
            }
        } catch (InterruptedException e) {
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
     */
    public void init() {
        thread = new Thread(() -> {
            while (true) {
                run();
            }
        });
        thread.start();
    }

    

}
