import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.util.Random;
import java.awt.Point;

public class SierpinskiPanel extends JPanel {

    
    public SierpinskiPanel() {
        super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        // Draws a right triangle
        // drawTriangle(g, 0, 0, 0, 100, 100, 0);
        // Draw a right angle sierpinski triangle with depth 6
        drawRight(g, 7);

    }

    public void drawTriangle(Graphics g, int x, int y, int x1, int y1, int x2, int y2) {
        g.drawLine(x, y, x1, y1);
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x, y);
    }

    public void drawTriangle(Graphics g, double x, double y, double x1, double y1, double x2, double y2) {
        drawTriangle(g, (int)x, (int)y, (int)x1, (int)y1, (int)x2, (int)y2);
    }

    public void drawRight(Graphics g, int depth) {

        drawRight(g, depth, new Point(0, 0), new Point(0, getHeight()), new Point(getWidth(), 0));

    }

    public void drawRight(Graphics g, int depth, Point a, Point b, Point c) {
        if(depth == 0) return;
        
        drawTriangle(g, a.getX(), a.getY(), b.getX(), b.getY(), c.getX(), c.getY());
        
        Point i = a;
        Point j = getMidPoint(a, b);
        Point k = b;
        Point l = getMidPoint(b, c);
        Point m = c;
        Point n = getMidPoint(c, a);
        
        drawRight(g, depth - 1, i, j, n);
        drawRight(g, depth - 1, j, k, l);
        drawRight(g, depth - 1, l, m, n);
        
    }

    private Point getMidPoint(Point a, Point b) {
        return new Point((int)((b.getX() + a.getX())) / 2, (int) ((b.getY() + a.getY()) / 2));
    }

}
