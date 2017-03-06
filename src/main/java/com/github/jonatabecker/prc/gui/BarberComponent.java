package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.barber.Barber;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Barber container
 *
 * @author JonataBecker
 */
public class BarberComponent extends JComponent {

    /** Barber information */
    private final Barber barber;
    /** Panel */
    private JPanel panel;
    /** Title */
    private JLabel label;

    /**
     * Creates a new barber component
     *
     * @param barber
     */
    public BarberComponent(Barber barber) {
        super();
        this.barber = barber;
        initGui();
        initEvents();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(builds());
    }

    /**
     * Initializes the events
     */
    private void initEvents() {
        barber.addEvent((event) -> {
            SwingUtilities.invokeLater(() -> {
                panel.setBackground(Color.red);
                label.setText("empty");
                if (event.getBarber().isProcessing()) {
                    panel.setBackground(Color.blue);
                    label.setText(String.valueOf(event.getClient().getKey()));
                }
            });
        });
    }

    /**
     * Builds the barber
     *
     * @return JComponent
     */
    private JComponent builds() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(150, 150));
        panel.setBackground(Color.red);
        label = new JLabel("empty");
        JPanel box = new JPanel();
        box.setLayout(new BorderLayout());
        box.add(label, BorderLayout.NORTH);
        box.add(panel);
        return box;
    }

}
