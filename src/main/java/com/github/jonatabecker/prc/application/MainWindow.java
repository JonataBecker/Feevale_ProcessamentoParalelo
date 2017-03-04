package com.github.jonatabecker.prc.application;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * Application main window
 * 
 * @author JonataBecker
 */
public class MainWindow extends JFrame {

    /**
     * Creates the main window
     */
    public MainWindow() {
        super();
        initGui();
    }

    /**
     * Initializes the interface
     */
    private void initGui() {
        setTitle("Processamento paralelo");
        setSize(1024, 768);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buildEditor(), BorderLayout.CENTER);
    }
    
    /**
     * Builds the editor
     * 
     * @return Editor
     */
    private Editor buildEditor() {
        Editor editor = new Editor();
        editor.addTab("Desafio dos Filósofos", new EditorPhilosopher());
        editor.addTab("Barbeiro Dorminhoco", new EditorBarber());
        return editor;
    }
    
}
