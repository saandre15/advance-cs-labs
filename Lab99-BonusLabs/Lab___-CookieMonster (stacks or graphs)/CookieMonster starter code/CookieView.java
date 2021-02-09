import java.awt.*;
import javax.swing.*;

/*
  this is the View component
*/

class CookieView extends JPanel
{
    private static int SIZE = 12;
    private int[][] myGrid;
    private int maxCookies;

    public CookieView()
    {
        myGrid = new int[SIZE][SIZE];
        maxCookies = -1;
    }

    public void update( int[][] mg, int mc )
    {
        myGrid = mg;
        maxCookies = mc;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        int boxWidth = w / 14;
        int boxHeight = h / 15;

        for ( int r=0; r<SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (myGrid[r][c] == -1)
                {
                    g.setColor(new Color(180,180,180));
                    g.fillRect( (c+1)*boxWidth, (r+1)* boxHeight, boxWidth-2, boxHeight-2);
                }
                else if (myGrid[r][c] >= 100)
                {
                    g.setColor(Color.red);
                    g.fillRect( (c+1)*boxWidth, (r+1)* boxHeight, boxWidth-2, boxHeight-2);
                    g.setColor(Color.black);
                    g.drawString(""+(myGrid[r][c]-100) , (c+1)*boxWidth+5+2, (r+1)* boxHeight+15);
                }
                else
                {
                    g.setColor(Color.yellow);
                    g.fillRect( (c+1)*boxWidth, (r+1)* boxHeight, boxWidth-2, boxHeight-2);
                    g.setColor(Color.black);
                    g.drawString(""+myGrid[r][c], (c+1)*boxWidth+5+2, (r+1)* boxHeight+15);
                }
            }
        }
        if ( maxCookies >= 0 )
            g.drawString("max cookies: " + maxCookies, w/3, h-20);
    }
}
