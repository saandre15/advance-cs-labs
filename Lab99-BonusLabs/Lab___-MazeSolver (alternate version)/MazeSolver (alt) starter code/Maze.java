import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
    this is the Controller component
*/

class Maze extends JFrame implements ActionListener
{

    private MazeView view;
    private MazeModel model;
    private JButton solveButton;
    private Timer timer;

    Maze()
    {
        super("Maze");

        // build the buttons
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        controlPanel.add(solveButton);

        // build the view
        view = new MazeView();
        view.setBackground(Color.white);

        // put buttons, view together
        Container c = getContentPane();
        c.add(controlPanel, BorderLayout.NORTH);
        c.add(view, BorderLayout.CENTER);

        // construct the timer and the listener to step the maze
        timer = new Timer(20, new ActionListener()
                          {
                              public void actionPerformed(ActionEvent e)
                              {
                                  model.stepMaze();
                              }
                          }
                         );

        // build the model
        model = new MazeModel(view);
    }

    public void run()
    {
        timer.setCoalesce(true);
        timer.start();
    }

    // when user clicks on solve, the timer is started
    // each tick advances the maze solution.
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        if ( b == solveButton )
        {
            this.run();
            solveButton.setEnabled(false);
        }
    }

    public static void main(String[] args)
    {
        Maze conway = new Maze();
        conway.addWindowListener(new WindowAdapter()
                                 {
                                     public void windowClosing(WindowEvent e)
                                     {
                                         System.exit(0);
                                     }
                                 }
                                );
        conway.setSize(570, 640);
        conway.show();
    }
}
