package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.gui.ConfigurationComponent;
import com.github.jonatabecker.prc.gui.PhilosopherConteiner;
import com.github.jonatabecker.prc.gui.LoggerComponent;
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

    private ConfigurationComponent configuration;
    
    /**
     * Creates a new philosopher editor
     */
    public EditorPhilosopher() {
        super();
        table = new Table();
        initGui();
        initEvent();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildContainer(), BorderLayout.CENTER);
        add(buildConfiguration(), BorderLayout.EAST);
        add(buildLogger(), BorderLayout.SOUTH);
    }

    /**
     * Initialize events
     */
    private void initEvent() {
        configuration.addEvent((e) -> {
            table.init(e.getSize(), e.getTimeProcessing(), e.getStarvation());
        });
    }
    
    /**
     * Builds the container
     *
     * @return PhilosopherContainer
     */
    private PhilosopherConteiner buildContainer() {
        return new PhilosopherConteiner(table);
    }

    /**
     * Builds the configurations
     *
     * @return JComponent
     */
    private JComponent buildConfiguration() {
        configuration = new ConfigurationComponent();
        return configuration;
    }

    /**
     * Builds the log component
     *
     * @return JComponent
     */
    private JComponent buildLogger() {
        return new LoggerComponent();
    }

}
