package com.github.jonatabecker.prc.application;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.GraphiteSkin;

/**
 * Main Class
 *
 * @author JonataBecker
 */
public class ParallelProcessing {

    /** Application instance */
    private static ParallelProcessing instance;

    /**
     * Returns the application instance
     *
     * @return ParallelProcessing
     */
    public static ParallelProcessing get() {
        if (instance == null) {
            instance = new ParallelProcessing();
        }
        return instance;
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main(String[] args) {
        ParallelProcessing.get().start(args);
    }

    /**
     * Starts the application
     *
     * @param args
     */
    public void start(String[] args) {
        setupLookAndFeel();
        buildAndShowWindow();
    }

    /**
     * Sets up the LookAndFeel
     */
    private void setupLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new SubstanceLookAndFeel(new GraphiteSkin()) {
            });
        } catch (Exception e) {
            ExceptionHandler.get().handle(e);
        }
    }

    /**
     * Builds and show the main window
     */
    private void buildAndShowWindow() {
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = buildWindow();
            mainWindow.setVisible(true);
        });
    }

    /**
     * Builds the main window
     *
     * @return MainWindow
     */
    private MainWindow buildWindow() {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return mainWindow;
    }

}
