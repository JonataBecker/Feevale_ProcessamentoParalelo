package com.github.jonatabecker.prc.philosopher;

import com.github.jonatabecker.prc.mvc.Model;
import java.util.Random;

/**
 * Philosopher information
 * 
 * @author JonataBecker
 */
public class Philosopher extends Model<Philosopher> {
    
    /** Processing time */
    private static final int TIME = 5000;
    /** Waiting time */
    private static final int TIME_WAINTING = 100;

    /** The key */
    private final int key;
    /** Left fork */
    private final Fork forkLeft;
    /** Right fork */
    private final Fork forkRight;
    /** Thread */
    private Thread thread;
    /** State */
    private PhilosopherState state;

    /**
     * Creates a new philosopher
     * 
     * @param key
     * @param forkLeft
     * @param forkRight 
     */
    public Philosopher(int key, Fork forkLeft, Fork forkRight) {
        this.key = key;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.state = PhilosopherState.WAINTING;
    }

    /**
     * Returns the key
     * 
     * @return int
     */
    public int getKey() {
        return key;
    }

    /**
     * Returns the left fork
     * 
     * @return Fork
     */
    public Fork getForkLeft() {
        return forkLeft;
    }

    /**
     * Returns the left fork
     * 
     * @return Fork
     */
    public Fork getForkRight() {
        return forkRight;
    }

    /**
     * Returns true if the philosopher is processing
     * 
     * @return boolean
     */
    public boolean isProcessing() {
        return state == PhilosopherState.PROCESSING;
    }

    /**
     * Modify the state
     * 
     * @param state 
     */
    private void modifyState(PhilosopherState state) {
        this.state = state;
        fireEvents(this);
    }

    /**
     * Execute the initial sleep
     * 
     * @throws InterruptedException 
     */
    private void initSleep() throws InterruptedException {
        Thread.sleep((int) (new Random().nextDouble() * 100));
    }

    /**
     * Run
     * 
     * @throws InterruptedException 
     */
    private void run() throws InterruptedException {
        try {

            getForkLeft().get(this);
            getForkRight().get(this);
            modifyState(PhilosopherState.PROCESSING);
            System.out.println(String.format("[%s] Prossesing", getKey()));
            Thread.sleep(TIME);
        } catch (ForkUsesException e) {
            getForkLeft().free(this);
            getForkRight().free(this);
        } finally {
            modifyState(PhilosopherState.WAINTING);
        }
        Thread.sleep(TIME_WAINTING);
    }

    /**
     * Execute the initials process
     */
    public void init() {
        thread = new Thread(() -> {
            try {
                initSleep();
                while (true) {
                    run();
                }
            } catch (InterruptedException ex) {
            }
        });
        thread.start();
    }

}
