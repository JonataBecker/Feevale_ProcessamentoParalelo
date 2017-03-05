package com.github.jonatabecker.prc.philosopher;

/**
 * Fork in use
 *
 * @author JonataBecker
 */
public class ForkUsesException extends Exception {

    public ForkUsesException() {
        super("Fork in use");
    }
    
}
