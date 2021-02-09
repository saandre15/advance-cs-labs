import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
    this is the Controller component
*/

class CookieMonster extends JFrame
            implements ActionListener
{

    private CookieView view;
    private CookieModel model;
    private JButton popButton, solveButton;

    CookieMonster()
    {
        super("Cookie Monster");

        popButton = new JButton("New Map");
        popButton.addActionListener(this);
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(popButton);
        controlPanel.add(solveButton);
        view = new CookieView();
        view.setBackground(Color.white);

        Container c = getContentPane();
        c.add(controlPanel, BorderLayout.NORTH);
        c.add(view, BorderLayout.CENTER);
        model = new CookieModel(view);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        if ( b == popButton )
        {
            model.rePopulate();
            solveButton.setEnabled(true);
        }
        else if ( b == solveButton )
        {
            model.solve();
            solveButton.setEnabled(false);
        }
    }

    public static void main(String[] args)
    {
        CookieMonster monster = new CookieMonster();
        monster.addWindowListener(new WindowAdapter()
                                  {
                                      public void windowClosing(WindowEvent e)
                                      {
                                          System.exit(0);
                                      }
                                  }
                                 );
        monster.setSize(400, 400);
        //monster.show();
        monster.setVisible(true);
    }
}
