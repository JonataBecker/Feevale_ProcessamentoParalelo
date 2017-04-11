package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.mvc.EventListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Configuration component
 *
 * @author JonataBecker
 */
public class PhilosopherConfigurationComponent extends JComponent {

    /** Default size */
    private static final int SIZE = 5;
    /** Default time processing */
    private static final int PROCESSING = 1800;
    /** Default time life */
    private static final int STARVATION = 30000;
    
    /** Event */
    private final List<EventListener<Bean>> events;
    /** Size */
    private JTextField size;
    /** Time processing */
    private JTextField timeProcessing;
    /** Starvation */
    private JTextField starvation;
    /** Button */
    private JButton button;
    
    /**
     * Creates a new configuration component
     */
    public PhilosopherConfigurationComponent() {
        super();
        this.events = new ArrayList<>();
        initGui();
        initEvent();
    }

    /**
     * Initializes de interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 0));
        add(buildForm(), BorderLayout.NORTH);
    }
    
    /**
     * Initializes the events
     */
    private void initEvent() {
        button.addActionListener((event) -> {
            events.forEach((e) -> {
                int si = Integer.valueOf(this.size.getText());
                int ti = Integer.valueOf(this.timeProcessing.getText());
                int st = Integer.valueOf(this.starvation.getText());
                e.observed(new Bean(si, ti, st));
            });
        });
    }
    
    /**
     * Builds the form
     * 
     * @return 
     */
    private JComponent buildForm() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));
        panel.add(new JLabel("Filosofos:"));
        panel.add(buildSize());
        panel.add(new JLabel("Processamento:"));
        panel.add(buildProcessing());
        panel.add(new JLabel("Starvation:"));
        panel.add(buildStarvation());
        panel.add(buildButton());
        return panel;
    }

    /**
     * Builds the size field
     *
     * @return JComponennt
     */
    private JComponent buildSize() {
        size = new JTextField();
        size.setText(String.valueOf(SIZE));
        return size;
    }

    /**
     * Builds the time processing
     *
     * @return JComponennt
     */
    private JComponent buildProcessing() {
        timeProcessing = new JTextField();
        timeProcessing.setText(String.valueOf(PROCESSING));
        return timeProcessing;
    }

    /**
     * Builds the starvation
     *
     * @return JComponennt
     */
    private JComponent buildStarvation() {
        starvation = new JTextField();
        starvation.setText(String.valueOf(STARVATION));
        return starvation;
    }

    /**
     * Builds the submit button
     * 
     * @return 
     */
    private JComponent buildButton() {
        button = new JButton("Iniciar");
        return button;
    }
    
    /**
     * Adds an event
     *
     * @param event
     */
    public void addEvent(EventListener<Bean> event) {
        events.add(event);
    }

    /**
     * Configurations
     */
    public static class Bean {

        /** Size */
        private final int size;
        /** Time processing */
        private final int timeProcessing;
        /** Starvation */
        private final int starvation;

        /**
         * Creates a new configuration information
         *
         * @param size
         * @param timeProcessing
         * @param timeWaiting
         */
        public Bean(int size, int timeProcessing, int timeWaiting) {
            this.size = size;
            this.timeProcessing = timeProcessing;
            this.starvation = timeWaiting;
        }

        /**
         * Returns the size
         *
         * @return int
         */
        public int getSize() {
            return size;
        }

        /**
         * Returns the time processing
         *
         * @return int
         */
        public int getTimeProcessing() {
            return timeProcessing;
        }

        /**
         * Returns the starvation
         *
         * @return int
         */
        public int getStarvation() {
            return starvation;
        }

    }

}
