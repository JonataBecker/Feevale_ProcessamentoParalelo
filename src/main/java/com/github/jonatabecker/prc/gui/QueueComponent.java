package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.barber.BarberQueue;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * Queue Component
 *
 * @author JonataBecker
 */
public class QueueComponent extends JPanel {

    /** Barber queue */
    private final BarberQueue barberQueue;

    /**
     * Creates a new queue component
     *
     * @param barberQueue
     */
    public QueueComponent(BarberQueue barberQueue) {
        super();
        this.barberQueue = barberQueue;
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        builds();
    }


    /**
     * Builds clients
     */
    private void builds() {
        for (int i = BarberQueue.MAX - 1; i >=0; i--) {
            buildChair(i);
        }
    }

    /**
     * Builds a chair
     *
     * @param pos
     */
    private void buildChair(int pos) {
        add(new ChairComponent(pos, barberQueue));
    }

}
