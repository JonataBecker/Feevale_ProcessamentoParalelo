package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.gui.PhilosopherConteiner;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Philosopher editor
 *
 * @author JonataBecker
 */
public class EditorPhilosopher extends JComponent {

    /**
     * Creates a new philosopher editor
     */
    public EditorPhilosopher() {
        super();
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildContainer(), BorderLayout.CENTER);
    }
    
    /**
     * Builds the container
     * 
     * @return PhilosopherContainer
     */
    private PhilosopherConteiner buildContainer() {
        return new PhilosopherConteiner();
    }
    
}
