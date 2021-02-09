package src;

public enum SquareStatus implements SquareVisual{
  
  NOTEXPLORED,
  EXPLORED,
  TOEXIT;

  @Override
  public String toVisual() {
    return switch(this) {
      case NOTEXPLORED -> "o";
      case EXPLORED -> ".";
      case TOEXIT -> "x";
      default -> "?";
    };
  }

}