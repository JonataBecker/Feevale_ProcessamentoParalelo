package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.application.Logger;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/**
 * Logger component
 *
 * @author JonataBecker
 */
public class LoggerComponent extends JComponent {

    private final Logger logger;
    private JTextArea textArea;
    
    public LoggerComponent(Logger logger) {
        super();
        this.logger = logger;
        initGui();
        initEvents();
    }

    private void initGui() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 180));
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane pane = new JScrollPane(textArea);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        add(pane);
    }

    private void initEvents() {
        logger.addEvent((e) -> {
            textArea.append(e + "\n");
        });
    }

}
