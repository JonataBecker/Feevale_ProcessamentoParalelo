package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.barber.BarberClient;
import com.github.jonatabecker.prc.barber.BarberQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Client component
 *
 * @author JonataBecker
 */
public class ChairComponent extends JComponent {

    /** Chair position */
    private final int pos;
    /**  Queue*/
    private final BarberQueue queue;
    /** Title */
    private JLabel title;
    /** Panel */
    private JPanel panel;

    /**
     * Creates a new client component
     *
     * @param pos
     * @param queue
     */
    public ChairComponent(int pos, BarberQueue queue) {
        super();
        this.pos = pos;
        this.queue = queue;
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildsTitle(), BorderLayout.NORTH);
        add(builds());
        initEvents();
    }

    /**
     * Initializes the events
     */
    private void initEvents() {
        queue.addEvent((event) -> {
            SwingUtilities.invokeLater(() -> {
                BarberClient client = queue.getClient(pos);
                // If the positions is free
                if (client == null) {
                    panel.setBackground(Color.red);
                    title.setText("empty");
                } else {
                    panel.setBackground(Color.blue);
                    title.setText(String.valueOf(client.getKey()));
                }
            });
        });
    }

    /**
     * Builds the title
     *
     * @return JComponet
     */
    private JComponent buildsTitle() {
        title = new JLabel();
        return title;
    }

    /**
     * Builds the chair
     *
     * @return JComponet
     */
    private JComponent builds() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(150, 150));
        panel.setBackground(Color.red);
        return panel;
    }

}
