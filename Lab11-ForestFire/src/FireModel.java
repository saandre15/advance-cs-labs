package src;

import java.awt.Point;
import java.util.ArrayList;

public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */

    public void solve()
    {
        burn((int)(Math.random() * SIZE), (int) (Math.random() * SIZE));
        myView.updateView(myGrid);
        boolean danger = false;
        for(int i = 0 ; i < SIZE ; i++) {
            if(myGrid[0][i].getStatus() == 2) {
                danger = true;
                break;
            }
        }
        if(danger) System.out.println("Onett is in danger");
        else System.out.println("Onett is safe");
    }


    private void burn(int x, int y) {
        try {
            FireCell cell = getCell(x, y);
            if(x < 0 || x > SIZE || y < 0 || y > SIZE) return;
            if(cell.getStatus() == 0 || cell.getStatus() == 2) return;
            cell.setStatus(2);
            burn(x + 1, y);
            burn(x - 1, y);
            burn(x , y + 1);
            burn(x , y - 1);
            
        }
        catch(IndexOutOfBoundsException e) {
            return;
        }
    } 

    private FireCell getCell(int x, int y) {
            return myGrid[x][y];
    }

}
