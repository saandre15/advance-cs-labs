package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Maze {
  
  private Square start, exit;
  private Square[][] grid;

  public Maze() {  }

  public boolean loadMaze(String filename) {
    try {
      Scanner s = new Scanner(new File(filename));
      grid = new Square[s.nextInt()][s.nextInt()];
      for(int r = 0 ; r < grid.length ; r++) {
        for(int c = 0 ;  c < grid[0].length ; c++) {
          grid[r][c] = new Square(r, c, s.nextInt());
          if(grid[r][c].getType() == SquareTypes.START)
            start = grid[r][c];
            if(grid[r][c].getType() == SquareTypes.EXIT)
            exit = grid[r][c];
        }
      }
      return true;
    } catch(FileNotFoundException e) {
      System.err.println("File name entered was not found.");
      return false;
    }
  }

  public List<Square> getNeighbors(Square s) {
    List<Square> list = new ArrayList<>();
    int r = s.getRow();
    int c = s.getCol();
    list.add(getCell(r - 1, c));
    list.add(getCell(r + 1, c));
    list.add(getCell(r , c - 1));
    list.add(getCell(r, c + 1));
    list = list.stream()
      .filter(cell -> cell != null)
      .collect(Collectors.toList());
    return list;
  }

  private Square getCell(int r, int c) {
    try {
      return grid[r][c];
    }
    catch(IndexOutOfBoundsException e) {
      return null;
    }
  }

  public Square getExit() {
    return exit;
  }

  public Square getStart() {
    return start;
  }

  public void reset() {
    for(int r = 0 ; r < grid.length ; r++) {
      for(int c = 0 ;  c < grid[0].length ; c++) {
        grid[r][c] = new Square(r, c, grid[r][c].getType().getValue());
      }
    }
  }

  @Override
  public String toString() {
    String s = "";
    for(int r = 0 ; r < grid.length ; r++) {
      for(int c = 0 ;  c < grid[0].length ; c++) {
        s += grid[r][c].toString() + " ";
      }
      s += "\n";
    }
    return s;
  }

  public static void main(String[] args) {
    Maze maze = new Maze();
    boolean loaded = maze.loadMaze("maze-1");
    if(!loaded)
      return;
    System.out.println("MAZE");
    System.out.println(maze.toString());
  }

}