package com.github.jonatabecker.prc.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;

/**
 * Table component
 *
 * @author JonataBecker
 */
public class TableComponent extends JComponent {

    private static final int MAX = 5;
    
    private static final int SIZE_PLATE = 110;
    
    private static final int SIZE_TABLE = 400;
    
    
    public TableComponent() {
        super();
    }

    private List<Point> getPoints() {
        List<Point> list = new ArrayList<>();
        double slice = 2 * Math.PI / MAX;
        for (int i = 0; i < MAX; i++) {
            double angle = slice * i;
            int newX = (int) (142 + 120 * Math.cos(angle));
            int newY = (int) (142 + 120 * Math.sin(angle));
            list.add(new Point(newX, newY));
        }
        return list;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.fillOval(0, 0, SIZE_TABLE, SIZE_TABLE);
        g2d.setColor(Color.red);
        getPoints().forEach((point) -> {
            g2d.fillOval(point.x, point.y, SIZE_PLATE, SIZE_PLATE);
        });
    }

}
