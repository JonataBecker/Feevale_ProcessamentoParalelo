package com.github.jonatabecker.prc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Philosopher container
 * 
 * @author JonataBecker
 */
public class PhilosopherConteiner extends JComponent {

    /**
     * Creates a new philosopher container
     */
    public PhilosopherConteiner() {
        super();
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(build());
    }
    
    private JComponent build() {
        return new TableComponent();
    } 
}
