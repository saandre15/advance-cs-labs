package src;

public class Square {
  
  private final SquareTypes type;
  private SquareStatus status;
  
  private int row, col;

  public Square(int row, int col, int type) {
    this.row = row;
    this.col = col;
    this.type =  SquareTypes.values()[type];
    this.status = SquareStatus.NOTEXPLORED;
  }

  @Override
  public String toString() {
    return type.toVisual() + (type == SquareTypes.EMPTY ? status.toVisual() : " ");
  }

  @Override
  public boolean equals(Object obj) {
    if(obj instanceof Square) {
      Square square = (Square)obj;
      if(row == square.row && col == square.col) return true;
    }
    return false;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public SquareTypes getType() {
    return type;
  }

  public SquareStatus getStatus() {
    return status;
  }

  public void setStatus(SquareStatus status) {
    this.status = status;
  }

}