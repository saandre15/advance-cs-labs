import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Timer;

import java.util.Random;


public class LifeModel implements ActionListener
{

	/*
	 *  This is the Model component.
	 */

	private static int SIZE = 60;
	private LifeCell[][] grid;
	private LifeCell[][] initGrid;
	private Random random;
	
	LifeView myView;
	Timer timer;

	/** Construct a new model using a particular file */
	public LifeModel(LifeView view, String fileName) throws IOException, RuntimeException
	{       
		int r, c;
		grid = new LifeCell[SIZE][SIZE];
		for ( r = 0; r < SIZE; r++ )
			for ( c = 0; c < SIZE; c++ )
				grid[r][c] = new LifeCell();

		if ( fileName == null ) //use random population
		{                                           
			for ( r = 0; r < SIZE; r++ )
			{
				for ( c = 0; c < SIZE; c++ )
				{
					if ( Math.random() > 0.85) //15% chance of a cell starting alive
						grid[r][c].setAliveNow(true);
				}
			}
		}
		else
		{                 
			Scanner input = new Scanner(new File(fileName));
			int numInitialCells = input.nextInt();
			for (int count=0; count<numInitialCells; count++)
			{
				r = input.nextInt();
				c = input.nextInt();
				grid[r][c].setAliveNow(true);
			}
			input.close();
		}

		initGrid = new LifeCell[this.grid.length][this.grid[0].length];
		this.cp(grid, initGrid);

		myView = view;
		myView.updateView(grid);
		
		random = new Random();
	}

	/** Constructor a randomized model */
	public LifeModel(LifeView view) throws IOException
	{
		this(view, null);
	}

	private void cp(LifeCell[][] from, LifeCell[][] to) throws RuntimeException {
		for(int r = 0 ; r  < from.length ; r++) {
			for(int c = 0 ; c < from[0].length ; c++) {
				try {
					to[r][c] = from[r][c].clone();
				}
				catch(CloneNotSupportedException e) {
					System.err.println("Unable to copy a life cell");
					throw new RuntimeException();
				}
			}
		}
	}

	/** pause the simulation (the pause button in the GUI */
	public void pause()
	{
		timer.stop();
	}
	
	/** resume the simulation (the pause button in the GUI */
	public void resume()
	{
		timer.restart();
	}
	
	/** run the simulation (the pause button in the GUI */
	public void run()
	{
		timer = new Timer(50, this);
		timer.setCoalesce(true);
		timer.start();
	}

	/** called each time timer fires */
	public void actionPerformed(ActionEvent e)
	{
		oneGeneration();
		myView.updateView(grid);
		mvToNextGen();
	}

	/** main logic method for updating the state of the grid / simulation */
	private void oneGeneration()
	{
		for(int r = 0 ; r  < grid.length ; r++) {
			for(int c = 0 ; c < grid[0].length ; c++) {
				LifeCell cell = grid[r][c];
				int neighbors = getAmountOfNeighbors(r, c);
				System.out.println(neighbors);
				if(cell.isAliveNow()) {
					if(!(neighbors == 2) || !(neighbors == 3))
						cell.setAliveNext(false);
				}
				else {
					if(neighbors == 3)
						cell.setAliveNext(true);
				}
				
			}
		}
	}

	private void mvToNextGen() {
		for(int r = 0 ; r  < grid.length ; r++) {
			for(int c = 0 ; c < grid[0].length ; c++) {
				LifeCell cell = grid[r][c];
				cell.setAliveNow(cell.isAliveNext());				
			}
		}
	}

	public void reset()
	{
		pause();
		this.cp(initGrid, grid);
		myView.updateView(grid);
	}

	public void randomAliveColor() {
		myView.setAliveColor(getRandomByte(), getRandomByte(), getRandomByte());
		myView.updateView(grid);
	}

	private byte getRandomByte() {
		byte[] bs = new byte[1];
		random.nextBytes(bs);
		return (byte)Math.abs((int)bs[0]);
	}

	private int getAmountOfNeighbors(int r, int c) {
		int total = 0;
		total += doesCellExist(r - 1, c - 1) ? 1 : 0;
		total += doesCellExist(r - 1, c) ? 1 : 0;
		total += doesCellExist(r - 1, c + 1) ? 1 : 0;
		total += doesCellExist(r, c - 1) ? 1 : 0;
		total += doesCellExist(r, c + 1) ? 1 : 0;
		total += doesCellExist(r + 1, c - 1) ? 1 : 0;
		total += doesCellExist(r + 1, c) ? 1 : 0;
		total += doesCellExist(r + 1, c + 1) ? 1 : 0;
		return total;
	}

	private boolean doesCellExist(int r, int c) {
		try {
			return grid[r][c].isAliveNow();
		}
		catch(IndexOutOfBoundsException e) {
			return false;
		}
	}
}

