import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tendron extends JPanel
{      
    public Tendron()
    {
        setBackground(Color.white);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Cluster c = new Cluster(g);
        // initial segment length, startX, startY
        c.display(50, 250, 250);             
    }

    public static void main(String[] args)
    {
        JFrame win = new JFrame("Tendron");
        win.setSize(500,500);
        win.getContentPane().add( new Tendron() );
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //win.show();
        win.setVisible(true);
    }
}
