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
public class BarberConfigurationComponent extends JComponent {

    /** Default size */
    private static final int SIZE = 5;
    /** Default time processing */
    private static final int PROCESSING = 800;
    /** Default time sleeping */
    private static final int SLEEPING = 5000;
    /** Default time queue */
    private static final int QUEUE = 1000;
    
    /** Event */
    private final List<EventListener<Bean>> events;
    /** Size */
    private JTextField size;
    /** Time processing */
    private JTextField processing;
    /** Sleeping */
    private JTextField sleeping;
    /** Queue */
    private JTextField queue;
    /** Button */
    private JButton button;
    
    /**
     * Creates a new configuration component
     */
    public BarberConfigurationComponent() {
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
                int ti = Integer.valueOf(this.processing.getText());
                int sl = Integer.valueOf(this.sleeping.getText());
                int qu = Integer.valueOf(this.queue.getText());
                e.observed(new Bean(si, ti, sl, qu));
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
        panel.add(new JLabel("Tamanho fila:"));
        panel.add(buildSize());
        panel.add(new JLabel("Tempo fila:"));
        panel.add(buildQueue());
        panel.add(new JLabel("Processamento:"));
        panel.add(buildProcessing());
        panel.add(new JLabel("Espera:"));
        panel.add(buildSleep());
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
        size.setEditable(false);
        return size;
    }
    
    /**
     * Builds the queue field
     *
     * @return JComponennt
     */
    private JComponent buildQueue() {
        queue = new JTextField();
        queue.setText(String.valueOf(QUEUE));
        return queue;
    }

    /**
     * Builds the time processing
     *
     * @return JComponennt
     */
    private JComponent buildProcessing() {
        processing = new JTextField();
        processing.setText(String.valueOf(PROCESSING));
        return processing;
    }

    /**
     * Builds the sleeping
     *
     * @return JComponennt
     */
    private JComponent buildSleep() {
        sleeping = new JTextField();
        sleeping.setText(String.valueOf(SLEEPING));
        return sleeping;
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
        private final int processing;
        /** Sleeping */
        private final int sleeping;
        /** Queue */
        private final int queue;

        /**
         * Creates a new configuration information
         *
         * @param size
         * @param processing
         * @param sleeping
         * @param queue
         */
        public Bean(int size, int processing, int sleeping, int queue) {
            this.size = size;
            this.processing = processing;
            this.sleeping = sleeping;
            this.queue = queue;
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
        public int getProcessing() {
            return processing;
        }

        /**
         * Returns the sleeping time
         *
         * @return int
         */
        public int getSleeping() {
            return sleeping;
        }

        /**
         * Returns the queue time
         *
         * @return int
         */
        public int getQueue() {
            return queue;
        }

    }

}
