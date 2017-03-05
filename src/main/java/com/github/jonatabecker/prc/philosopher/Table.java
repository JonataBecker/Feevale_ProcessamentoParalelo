package com.github.jonatabecker.prc.philosopher;

import com.github.jonatabecker.prc.mvc.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Table information
 * 
 * @author JonataBecker
 */
public class Table extends Model<Philosopher> {

    /** Max size */
    private static final int MAX = 5;
    /** Philosophers */
    private final List<Philosopher> philosophers;

    /**
     * Creates a new table information
     */
    public Table() {
        this.philosophers = new ArrayList<>();
    }
    
    /**
     * Builds a new philosopher
     * 
     * @param key
     * @param left
     * @param right
     * @return Philosopher
     */
    private Philosopher buildsPhilosopher(int key, Fork left, Fork right) {
        Philosopher philosopher = new Philosopher(key, left, right);
        philosopher.addEvent((event) -> {
            fireEvents(event);
        });
        return philosopher;
    }
    
    /**
     * Builds
     */
    private void builds() {
        Fork first = new Fork();
        Fork left = first;
        Fork right = new Fork();
        for (int i = 0; i < MAX; i++) {
            philosophers.add(buildsPhilosopher(i + 1, left, right));
            left = right;
            if (i == (MAX - 2)) {
                right = first;
            } else {
                right = new Fork();
            }
        }
    }
    
    /**
     * Returns the philosopher
     * 
     * @return {@code List<Philosopher>}
     */
    public List<Philosopher> getPhilosophers() {
        return Collections.unmodifiableList(philosophers);
    }

    /**
     * Returns the size
     * 
     * @return int
     */
    public int size() {
        return philosophers.size();
    }

    /**
     * Initializes
     */
    public void init() {
        builds();
        philosophers.forEach((philosopher) -> {
            philosopher.init();
        });
    }

}
