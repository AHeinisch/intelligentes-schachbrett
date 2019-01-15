package logic;

/**
 * This class models the position coordinates of a figure
 * 
 * @author jendrikmuller
 *
 */
public class Position {
  private int row;
  private int column;

  /**
   * Creates a new instance of the type position.
   * 
   * @param row
   *          row of the figure
   * @param column
   *          column of the figure
   */
  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getColumn() {
    return column;
  }

  public void setColumn(int column) {
    this.column = column;
  }

  public void setPosition(int row, int column) {
    this.row = row;
    this.column = column;
  }

}
