import javax.swing.JFrame;

public class Sierpinski {

    public static void main ( String[] args )
	{
		/*
		 * A frame is a container for a panel
		 * The panel is where the drawing will take place
		 */
		JFrame frame = new JFrame("Sierpinski");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new SierpinskiPanel());
		frame.pack();
		frame.setVisible(true);
    }
    
}
