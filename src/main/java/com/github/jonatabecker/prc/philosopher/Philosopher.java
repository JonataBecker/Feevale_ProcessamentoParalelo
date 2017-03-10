package com.github.jonatabecker.prc.philosopher;

import com.github.jonatabecker.prc.mvc.Model;
import com.github.jonatabecker.prc.application.Logger;
import java.util.Random;

/**
 * Philosopher information
 *
 * @author JonataBecker
 */
public class Philosopher extends Model<Philosopher> {

    /** Waiting time */
    private static final int TIME_WAINTING = 5;

    /** The key */
    private final int key;
    /** Processing */
    private final int processing;
    /** Left fork */
    private final Fork forkLeft;
    /** Right fork */
    private final Fork forkRight;
    /** Max Starvation */
    private final int maxStarvation;
    /** Starvation */
    private int starvation;
    /** Thread */
    private Thread thread;
    /** State */
    private PhilosopherState state;

    /**
     * Creates a new philosopher
     *
     * @param key
     * @param processing
     * @param forkLeft
     * @param forkRight
     * @param starvation
     */
    public Philosopher(int key, int processing, Fork forkLeft, Fork forkRight,
            int starvation) {
        this.key = key;
        this.processing = processing;
        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.state = PhilosopherState.WAINTING;
        this.maxStarvation = starvation;
        this.starvation = starvation;
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
     * Returns true if the philosopher is dead
     *
     * @return boolean
     */
    public boolean isDead() {
        return state == PhilosopherState.DEAD;
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
            Logger.append(String.format("[%s] Starvation :%s", getKey(), getStarvation()));
            Logger.append(String.format("[%s] Processando", getKey()));
            int p = processing;
            while (true) {
                int t = (1000 / 32);
                Thread.sleep(t);
                if (starvation < maxStarvation) {
                    starvation += t;
                }
                p -= t;
                if (p <= 0) {
                    return;
                }
            }
        } catch (ForkUsesException e) {
            getForkLeft().free(this);
            getForkRight().free(this);
        } finally {
            modifyState(PhilosopherState.WAINTING);
        }
        starvation -= TIME_WAINTING;
        if (starvation <= 0) {
            Logger.append(String.format("[%s] Morto", getKey()));
            modifyState(PhilosopherState.DEAD);
            throw new InterruptedException();
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
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Returns the starvation
     *
     * @return int
     */
    public int getStarvation() {
        return starvation;
    }

}
