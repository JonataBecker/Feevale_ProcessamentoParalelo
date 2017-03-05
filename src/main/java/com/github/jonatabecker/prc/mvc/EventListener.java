package com.github.jonatabecker.prc.mvc;

/**
 * Event listener for the model
 *
 * @author JonataBecker
 * @param <T>
 */
public interface EventListener<T> {

    /**
     * And event was observed
     *
     * @param event
     */
    public void observed(T event);

}
