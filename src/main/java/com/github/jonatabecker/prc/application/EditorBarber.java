package com.github.jonatabecker.prc.application;

import com.github.jonatabecker.prc.gui.BarberConteiner;
import java.awt.BorderLayout;
import javax.swing.JComponent;

/**
 * Barber editor
 *
 * @author JonataBecker
 */
public class EditorBarber extends JComponent {

    /**
     * Creates a new barber editor
     */
    public EditorBarber() {
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
     * @return BarberContainer
     */
    private BarberConteiner buildContainer() {
        return new BarberConteiner();
    }
    
}
