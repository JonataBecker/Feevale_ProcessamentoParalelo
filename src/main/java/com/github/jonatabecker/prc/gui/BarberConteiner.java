package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.barber.BarberShop;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Barber container
 * 
 * @author JonataBecker
 */
public class BarberConteiner extends JComponent {

    /** Barber shop */
    private final BarberShop barberShop;
    
    /**
     * Creates a new barber container
     * 
     * @param barberShop
     */
    public BarberConteiner(BarberShop barberShop) {
        super();
        this.barberShop = barberShop;
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setLayout(new BorderLayout());
        add(buildQueue(), BorderLayout.NORTH);
        add(build(), BorderLayout.CENTER);
    }
    
    /**
     * Builds the queue
     * 
     * @return JComponent
     */
    private JComponent buildQueue() {
        return new QueueComponent(barberShop.getBarberQueue());
    }
    
    /**
     * Builds the barber
     * 
     * @return JComponent
     */
    private JComponent build() {
        return new BarberComponent(barberShop.getBarber());
    }
    
}
