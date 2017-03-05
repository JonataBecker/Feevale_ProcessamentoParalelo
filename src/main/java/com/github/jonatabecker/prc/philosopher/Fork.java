package com.github.jonatabecker.prc.philosopher;

/**
 * Fork information
 *
 * @author JonataBecker
 */
public class Fork {

    /** Free */
    private boolean free;
    /** Philosopher key */
    private int key;

    /**
     * Creates a new Fork
     */
    public Fork() {
        this.free = true;
    }

    /**
     * Returns true if the fork is free
     * 
     * @return boolean
     */
    public synchronized boolean isFree() {
        return free;
    }

    /**
     * Free the fork
     * 
     * @param philosopher 
     */
    public synchronized void free(Philosopher philosopher) {
        if (key != 0 && key != philosopher.getKey()) {
            return;
        }
        free = true;
    }
    
    /**
     * Uses the fork
     * 
     * @param philosopher
     * @throws ForkUsesException Fork in use
     */
    public synchronized void get(Philosopher philosopher) throws ForkUsesException {
        if (!isFree()) {
            throw new ForkUsesException();
        }
        free = false;
        key = philosopher.getKey();
    }

}
