package src;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MazeSolver {

  abstract void makeEmpty();
  abstract boolean isEmpty();
  abstract void add(Square s);
  abstract Square removeNext();
  abstract Square next();

  private Maze maze;
  
  public MazeSolver(Maze maze) {
    this.maze = maze;
  }

  public boolean isSolved() {
    // If there is no square that means the stack has gone through all the path
    // If the last square is an exit that means a working path was found
    return isEmpty() || next().getType() == SquareTypes.EXIT;

    // Square start = maze.getStart();
    // Square exit = maze.getExit();
    // // Stores the explored squares so I don't come back
    // List<Square> explored = new ArrayList<>();
    // explored.add(start);
    // Square cur = start;
    // while(cur != null) {
    //   List<Square> neighbors = maze.getNeighbors(cur);
    //   List<Square> exitSquares = neighbors.stream() 
    //     .filter(sqaure -> sqaure.getStatus() == SquareStatus.TOEXIT)
    //     .collect(Collectors.toList());
    //   Square next = exitSquares.stream()
    //     .filter(sqaure -> !explored.contains(sqaure))
    //     .collect(Collectors.toList())
    //     .get(0);
    //   // 2. Solved when there is no such path
    //   if(next == null) return true;
    //   // 1. Solved when it reaches the exit otherwise rerun the loop with the next square
    //   cur = exit.equals(next) ? null : next; 
    // }
    // return true;
  }
  
  public void step() {
    System.out.println("Step taken");
    List<Square> neighbors = maze.getNeighbors(next());
    System.out.println(neighbors.toString());
    for(int i = 0 ; i < neighbors.size() ; i++) {
      Square neighbor = neighbors.get(i);
      SquareTypes type = neighbor.getType();
      // Checks if the sqaure has not been explored before
      System.out.println(neighbor.getStatus());
      if((type == SquareTypes.EMPTY || 
        type == SquareTypes.EXIT) && 
        neighbor.getStatus() == SquareStatus.NOTEXPLORED
      ) {
        add(neighbor);
        // sets the unexplored sqaure to exit sqaure
        neighbor.setStatus(SquareStatus.TOEXIT);
        return;
      } 
    }
    // If all the neighbor sqaures are explored then mark this square as explored and move back to the last square
    removeNext().setStatus(SquareStatus.EXPLORED);
    
  }

  public String getPath() {
    if(isEmpty()) return "Unsolveable";
    if(next().getType() == SquareTypes.EXIT) return "Solved";
    return "Not yet solved";
  }
  
  public void solve() {
    if(!isSolved()) step();
  }

}