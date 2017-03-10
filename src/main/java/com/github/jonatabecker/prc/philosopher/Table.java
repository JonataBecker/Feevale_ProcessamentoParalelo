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

    /** Philosophers */
    private final List<Philosopher> philosophers;
    /** Table size */
    private int size;
    /** Processing time */
    private int processing;
    /** Starvation time */
    private int starvation;

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
        Philosopher philosopher = new Philosopher(
                key, processing, left, right, starvation
        );
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
        for (int i = 0; i < size; i++) {
            philosophers.add(buildsPhilosopher(i + 1, left, right));
            left = right;
            if (i == (size - 2)) {
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
     *
     * @param size
     * @param processing
     * @param starvation
     */
    public void init(int size, int processing, int starvation) {
        this.size = size;
        this.processing = processing;
        this.starvation = starvation;
        philosophers.clear();
        builds();
        philosophers.forEach((philosopher) -> {
            philosopher.init();
        });
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
