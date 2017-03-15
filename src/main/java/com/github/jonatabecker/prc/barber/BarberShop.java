package com.github.jonatabecker.prc.barber;

/**
 * Barber shop
 *
 * @author JonataBecker
 */
public class BarberShop {

    /** Barber queue */
    private final BarberQueue barberQueue;
    /** Barber */
    private final Barber barber;

    /**
     * Creates a new barber shop
     */
    public BarberShop() {
        this.barberQueue = new BarberQueue();
        this.barber = new Barber(barberQueue);
    }

    /**
     * Initializes the process
     *
     * @param size
     * @param processing
     * @param sleeping
     * @param queue
     */
    public void init(int size, int processing, int sleeping, int queue) {
        barberQueue.init(queue, size);
        barber.init(sleeping, processing);
    }

    /**
     * Returns the barber queue
     *
     * @return BarnerQueue
     */
    public BarberQueue getBarberQueue() {
        return barberQueue;
    }

    /**
     * Returns the barber
     *
     * @return barber
     */
    public Barber getBarber() {
        return barber;
    }

}
