package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.mvc.EventListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Logger {

    private static Logger philosopher;
    private static Logger barber;

    private final List<EventListener<String>> events = new ArrayList<>();

    public void append(String text) {
        events.forEach((e) -> {
            e.observed(text);
        });
    }

    public void addEvent(EventListener<String> evet) {
        events.add(evet);
    }

    public static Logger barber() {
        if (barber == null) {
            barber = new Logger();
        }
        return barber;
    }

    public static Logger philosopher() {
        if (philosopher == null) {
            philosopher = new Logger();
        }
        return philosopher;
    }

}
