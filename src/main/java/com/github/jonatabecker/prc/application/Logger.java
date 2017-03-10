package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.mvc.EventListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Logger {

    private static final List<EventListener<String>> EVENTS = new ArrayList<>();


    public static void append(String text) {
        EVENTS.forEach((e) -> {
            e.observed(text);
        });
    }
    
    public static void addEvent(EventListener<String> evet) {
        EVENTS.add(evet);
    }

}
