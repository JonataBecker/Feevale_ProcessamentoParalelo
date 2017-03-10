package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.philosopher.Philosopher;
import com.github.jonatabecker.prc.philosopher.Table;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;

/**
 * Table component
 *
 * @author JonataBecker
 */
public class TableComponent extends JComponent {

    /** FPS */
    private static final int FRAMES_PER_SECOND = 30;
    /** Plate size */
    private static final int SIZE_PLATE = 110;
    /** Table size */
    private static final int SIZE_TABLE = 500;
    /** Table */
    private final Table table;

    /**
     * Creates a new table component
     *
     * @param table
     */
    public TableComponent(Table table) {
        super();
        this.table = table;
        initEvents();
    }

    /**
     * Initializes events
     */
    private void initEvents() {
        Thread updateThread = new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000 / FRAMES_PER_SECOND);
                    repaint();
                }
            } catch (Exception e) {
            }
        });
        updateThread.setDaemon(true);
        updateThread.start();
    }

    /**
     * Returns a node list
     *
     * @return {@code List<Point>}
     */
    private List<Node> getNodes() {
        double slice = 2 * Math.PI / table.size();
        return table.getPhilosophers().stream().map((philosopher) -> {
            double angle = slice * philosopher.getKey() - 1;
            int newX = (int) (200 + 170 * Math.cos(angle));
            int newY = (int) (200 + 170 * Math.sin(angle));
            return new Node(new Point(newX, newY), philosopher);
        }).collect(Collectors.toList());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(0, 0, SIZE_TABLE, SIZE_TABLE);
        getNodes().forEach((node) -> {
            g2d.setColor(Color.red);
            if (node.philosopher.isProcessing()) {
                g2d.setColor(Color.blue);
            }
            if (node.philosopher.isDead()) {
                g2d.setColor(Color.pink);
            }
            g2d.fillOval(node.point.x, node.point.y, SIZE_PLATE, SIZE_PLATE);
            g2d.setColor(Color.black);
            g2d.drawString(
                    String.valueOf(node.philosopher.getKey()), 
                    node.point.x + SIZE_PLATE / 2 - 5, 
                    node.point.y + 25
            );
            if (!node.philosopher.isDead()) {
                int x = node.point.x + 10;
                int y = node.point.y + (SIZE_PLATE / 2) - 5;
                int per = (node.philosopher.getStarvation() * 100) / table.getStarvation();
                int pro = (int) ((((float) per / 100)) * (SIZE_PLATE - 20));
                g2d.setColor(Color.yellow);
                g2d.fillRect(x, y, SIZE_PLATE - 20, 10);
                g2d.setColor(Color.cyan);
                g2d.fillRect(x + 1, y + 1, pro - 2, 8);
            }
        });
    }

    /**
     * Node information
     */
    private class Node {

        /** Position */
        private final Point point;
        /** Philosopher */
        private final Philosopher philosopher;

        /**
         * Creates a new node information
         *
         * @param point
         * @param philosopher
         */
        public Node(Point point, Philosopher philosopher) {
            this.point = point;
            this.philosopher = philosopher;
        }
    }

}
