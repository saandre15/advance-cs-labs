package src;

public class MazeSolverStack extends MazeSolver {

  private MyStack<Square> toExit;

  public MazeSolverStack(Maze maze) {
    super(maze);
    this.toExit = new MyStack<>();
    toExit.push(maze.getStart());
    maze.getStart().setStatus(SquareStatus.TOEXIT);
  }

  @Override
  Square next() {
    return toExit.peek();
  }

  @Override
  Square removeNext() {
    return toExit.pop();
  }

  @Override
  boolean isEmpty() {
    return toExit.isEmpty();
  }

  @Override
  void makeEmpty() {
    toExit = new MyStack<>();
  }

  @Override
  void add(Square s) {
    toExit.push(s);
  }

}