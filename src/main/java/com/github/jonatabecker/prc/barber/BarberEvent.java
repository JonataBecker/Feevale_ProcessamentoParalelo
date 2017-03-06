package com.github.jonatabecker.prc.barber;

/**
 * Barber event
 */
public class BarberEvent {

    /** Client */
    private final BarberClient client;
    /** Barber */
    private final Barber barber;

    /**
     * Creates a new barber event
     *
     * @param client
     * @param barber
     */
    public BarberEvent(BarberClient client, Barber barber) {
        this.client = client;
        this.barber = barber;
    }

    /**
     * Returns the client
     *
     * @return BarberClient
     */
    public BarberClient getClient() {
        return client;
    }

    /**
     * Return the barber
     *
     * @return Barber
     */
    public Barber getBarber() {
        return barber;
    }

}
