import java.awt.*;
import javax.swing.*;

/*
  this is the View component
*/

class MazeView extends JPanel
{
    private static int SIZE = 47;
    private MazeCell[][] myGrid;

    public MazeView()
    {
        // create the grid, then populate with random color cells
        myGrid = new MazeCell[SIZE][SIZE];
    }

    // entry point from model, requests grid be redisplayed
    public void updateView( MazeCell[][] mg )
    {
        myGrid = mg;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each Maze cell square
        int boxSize = Math.min(testHeight, testWidth);

        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (myGrid[r][c] != null)
                {
                    int ulX = (c+1) * boxSize;
                    int ulY = (r+1) * boxSize;
                    if ( myGrid[r][c].getStatus() == 0 )
                        g.setColor( Color.white );
                    else
                        if ( myGrid[r][c].getStatus() == 1 )
                            g.setColor( Color.blue );
                        else
                            g.setColor( Color.yellow );

                    int topLeftX = ulX+4, topLeftY = ulY+4;
                    int sizeX = boxSize-6, sizeY = boxSize-6;
                    g.fillRect( topLeftX, topLeftY, sizeX, sizeY);

                    // check north wall
                    if  ( r > 0 && myGrid[r-1][c].getStatus() == myGrid[r][c].getStatus()
                            && ! myGrid[r][c].getTopHedge() && ! myGrid[r-1][c].getBottomHedge() )
                        g.fillRect( topLeftX, topLeftY-6, sizeX, 6);

                    // check west wall
                    if  ( c > 0 && myGrid[r][c-1].getStatus() == myGrid[r][c].getStatus()
                            && ! myGrid[r][c].getLeftHedge() && ! myGrid[r][c-1].getRightHedge() )
                        g.fillRect( topLeftX-6, topLeftY, 6, sizeY);

                    g.setColor( Color.black );
                    if ( myGrid[r][c].getTopHedge() )
                        g.drawLine(ulX, ulY, ulX+boxSize, ulY);
                    if ( myGrid[r][c].getBottomHedge() )
                        g.drawLine(ulX, ulY+boxSize, ulX+boxSize, ulY+boxSize);
                    if ( myGrid[r][c].getRightHedge() )
                        g.drawLine(ulX+boxSize, ulY, ulX+boxSize, ulY+boxSize);
                    if ( myGrid[r][c].getLeftHedge() )
                        g.drawLine(ulX, ulY, ulX, ulY+boxSize);
                }
            }
        }
    }
}
