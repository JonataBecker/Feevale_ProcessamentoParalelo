package com.github.jonatabecker.prc.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JonataBecker
 * @param <T>
 */
public abstract class Model<T> {

    private final List<EventListener<T>> events;

    public Model() {
        this.events = new ArrayList<>();
    }

    public void addEvent(EventListener<T> event) {
        events.add(event);
    }

    protected void fireEvents(T obj) {
        events.forEach((event) -> {
            event.observed(obj);
        });
    }

}
