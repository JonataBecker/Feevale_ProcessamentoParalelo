package com.github.jonatabecker.prc.gui;

import com.github.jonatabecker.prc.philosopher.Philosopher;
import com.github.jonatabecker.prc.philosopher.Table;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JComponent;

/**
 * Table component
 *
 * @author JonataBecker
 */
public class TableComponent extends JComponent {

    /** Plate size */
    private static final int SIZE_PLATE = 110;
    /** Table size */
    private static final int SIZE_TABLE = 400;
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
        table.addEvent((event) -> {
            repaint();
        });
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
            int newX = (int) (142 + 120 * Math.cos(angle));
            int newY = (int) (142 + 120 * Math.sin(angle));
            return new Node(new Point(newX, newY), philosopher);
        }).collect(Collectors.toList());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(0, 0, SIZE_TABLE, SIZE_TABLE);
        getNodes().forEach((node) -> {
            g2d.setColor(Color.red);
            if (node.philosopher.isProcessing()) {
                g2d.setColor(Color.blue);
            }
            
            g2d.fillOval(node.point.x, node.point.y, SIZE_PLATE, SIZE_PLATE);
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
