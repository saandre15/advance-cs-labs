package src;

public enum SquareTypes implements SquareVisual {
  EMPTY(0),
  WALL(1),
  START(2),
  EXIT(3);

  private int value;

  private SquareTypes(int val) {
    this.value = val;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toVisual() {
    return switch(value) {
      case 0 -> "_";
      case 1 -> "#";
      case 2 -> "S";
      case 3 -> "E";
      default -> "X";
    };
  }
}