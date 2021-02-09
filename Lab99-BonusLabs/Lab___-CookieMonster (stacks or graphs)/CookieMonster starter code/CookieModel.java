import java.util.*;

class CookieModel {

    /*
     *  This is the Model component.
     *  Created my the Controller component, solves the problem, updates the View component.
     *  Mark the path for maximum cookies by adding 100 to each cookie value along the path.
     */

    private static int SIZE = 12;
    private int[][] grid;

    CookieView myView;

    public CookieModel(CookieView view)
    {
        grid = new int[SIZE][SIZE];
        // populate the grid
        int row, col, nCookies;
        for (row = 0; row < SIZE; row++)
            for (col = 0; col < SIZE; col++)
            {
                if ( Math.random() > 0.85 )
                    nCookies = -1;      // barrier
                else
                    nCookies = (int)(Math.random() * 10);
                grid[row][col] = nCookies;
            }
        // start, end must not have a barrier
        grid[0][0] = (int)(Math.random() * 10);
        grid[SIZE-1][SIZE-1] = (int)(Math.random() * 10);

        myView = view;
        myView.update(grid, -1);
    }

    public void rePopulate()
    {
        int row, col, nCookies;
        for (row = 0; row < SIZE; row++)
            for (col = 0; col < SIZE; col++)
            {
                if ( Math.random() > 0.85 )
                    nCookies = -1;
                else
                    nCookies = (int)(Math.random() * 10);
                grid[row][col] = nCookies;
            }
        grid[0][0] = (int)(Math.random() * 10);
        grid[SIZE-1][SIZE-1] = (int)(Math.random() * 10);
        myView.update(grid, -1);
    }

    public void solve()
    {
    	this.solve(0, 0);
    }

    private void solve(int r, int c)
    {
        /* student code here */


        myView.update(grid, -1);
    }
}
