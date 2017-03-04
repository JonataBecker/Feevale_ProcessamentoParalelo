package com.github.jonatabecker.prc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Barber container
 * 
 * @author JonataBecker
 */
public class BarberConteiner extends JComponent {

    /**
     * Creates a new barber container
     */
    public BarberConteiner() {
        super();
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        JPanel pane = new JPanel();
        pane.setBackground(Color.blue);
        add(pane);
    }
    
}
