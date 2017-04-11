package com.github.jonatabecker.prc.barber;

import com.github.jonatabecker.prc.mvc.Model;

/**
 * Barber client
 * 
 * @author JonataBecker
 */
public class BarberClient extends Model<BarberClient>{

    /** Key */
    private final int key;
    /** State */
    private ClientState state;
    
    /**
     * Creates a new client
     * 
     * @param key 
     */
    public BarberClient(int key) {
        this.key = key;
        this.state = ClientState.WAITING;
    }
    
    /**
     * Returns the key
     * 
     * @return int
     */
    public int getKey() {
        return key;
    }
    
    private void modifyState(ClientState state) {
        this.state = state;
        fireEvents(this);
    }
    
    /**
     * Processing
     */
    public void processing() {
        modifyState(ClientState.PROCESSING);
    }
    
    /**
     * Done
     */
    public void done() {
        modifyState(ClientState.DONE);
    }
    
    /**
     * Returns true if the client is processing
     * 
     * @return boolean
     */
    public boolean isProcessing() {
        return state == ClientState.PROCESSING;
    }
    
    /**
     * Returns true if the client is done
     * 
     * @return boolean
     */
    public boolean isDone() {
        return state == ClientState.DONE;
    }
}
