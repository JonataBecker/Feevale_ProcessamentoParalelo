package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.gui.PhilosopherConteiner;
import com.github.jonatabecker.prc.philosopher.Table;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Philosopher editor
 *
 * @author JonataBecker
 */
public class EditorPhilosopher extends JComponent {

    private final Table table;
    
    /**
     * Creates a new philosopher editor
     */
    public EditorPhilosopher() {
        super();
        table = new Table();
        initGui();
        table.init();
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
        return new PhilosopherConteiner(table);
    }
    
}
