import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Point;
import java.util.Random;

class SnowFlakePanel extends JPanel
{
	public SnowFlakePanel()
	{
		super.setPreferredSize(new Dimension(400, 400));
		super.setBackground(Color.WHITE);
	}

	public void paintComponent(Graphics g)
	{
		

        super.paintComponent(g);

        // drawLine(g);
        // drawStar(g, 6);
        // drawSnowflake(g, 6, 3);
        drawBlizzard(g, 50, 50);
    }

    public void drawLine(Graphics g) {
        int width  = getWidth();
        int height = getHeight();
        
        g.setColor(Color.BLUE);
        g.drawLine(0, 0, width - 1, height - 1);
    }
    
    public void drawStar(Graphics g, int sides) {
        int width = getWidth();
        int height = getHeight();

        drawStar(g, sides, width / 2, height / 2, width / 4, height / 4);
    }

    public void drawStar(Graphics g, int sides, int x, int y, int deltaX, int deltaY) {
        int width = getWidth();
        int height = getHeight();
        

        int theta = 360 / sides + 1;
        System.out.println(theta);
        for(int i = 0 ; i < sides ; i++) {
            int curTheta = theta * i;
            g.drawLine(
                x, 
                y, 
                (int) (x + deltaX * Math.cos(Math.toRadians(curTheta))), 
                (int) (y + deltaY * Math.sin(Math.toRadians(curTheta)))
            );
        }
    }

    public void drawSnowflake(Graphics g, int sides, int depth) {
        drawSnowflake(g, sides, depth, getWidth() / 2, getHeight() / 2, getWidth() / 4, getHeight() / 4);
    }

    public void drawSnowflake(Graphics g, int sides, int depth, int x, int y, int deltaX, int deltaY) {
        if(depth == 0) return;
        drawStar(g, sides, x, y, deltaX, deltaY);
        int theta = 360 / sides + 1;
        for(int i = 0 ; i < sides ; i++) {
            int curTheta = theta * i;
            drawSnowflake(
                g, 
                sides, 
                depth - 1, 
                (int) (x + deltaX * Math.cos(Math.toRadians(curTheta))), 
                (int) (y + deltaY * Math.sin(Math.toRadians(curTheta))),
                deltaX / 4,
                deltaY / 4
            );
        }
        
    }

    public void drawBlizzard(Graphics g, int n, int range) {
        Random random = new Random();
        int width = getWidth();
        int height = getHeight();

        for(int i = 0 ; i < n ; i++) {
            Color c = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
            g.setColor(c);
            int variationSize = (int) (range * random.nextFloat());
            drawSnowflake(
                g, 
                6, 
                3, 
                (int) (width * random.nextFloat()), 
                (int) (height * random.nextFloat()),
                variationSize, 
                variationSize
            );
        }
    }
}
