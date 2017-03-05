package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.philosopher.Table;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Philosopher container
 * 
 * @author JonataBecker
 */
public class PhilosopherConteiner extends JComponent {

    /** Table */
    private final Table table;
    
    /**
     * Creates a new philosopher container
     * 
     * @param table
     */
    public PhilosopherConteiner(Table table) {
        super();
        this.table = table;
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
        return new TableComponent(table);
    } 
}
