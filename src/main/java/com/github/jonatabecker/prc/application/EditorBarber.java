package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.barber.BarberShop;
import com.github.jonatabecker.prc.gui.BarberConfigurationComponent;
import com.github.jonatabecker.prc.gui.BarberConteiner;
import com.github.jonatabecker.prc.gui.LoggerComponent;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Barber editor
 *
 * @author JonataBecker
 */
public class EditorBarber extends JComponent {

    private final BarberShop barberShop;
    private BarberConfigurationComponent configuration;

    /**
     * Creates a new barber editor
     */
    public EditorBarber() {
        super();
        barberShop = new BarberShop();
        initGui();
        initEvents();
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
     * Initializes the events
     */
    private void initEvents() {
        configuration.addEvent((ev) -> {
            barberShop.init(ev.getSize(),
                    ev.getProcessing(),
                    ev.getSleeping(),
                    ev.getQueue()
            );
        });
    }

    /**
     * Builds the configurations
     *
     * @return JComponent
     */
    private JComponent buildConfiguration() {
        configuration = new BarberConfigurationComponent();
        return configuration;
    }

    /**
     * Builds the container
     *
     * @return BarberContainer
     */
    private BarberConteiner buildContainer() {
        return new BarberConteiner(barberShop);
    }

    /**
     * Builds the log component
     *
     * @return JComponent
     */
    private JComponent buildLogger() {
        return new LoggerComponent(Logger.barber());
    }

}
