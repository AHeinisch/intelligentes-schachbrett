package logic;

import constants.LogicConst;

/**
 * This class represents the chess figure Rook.
 * 
 * @author jendrikmuller
 *
 */
public class Rook extends Figure {

  /**
   * Creates a new instance of the type Rook.
   * 
   * @param white
   *          true for white and false for black
   */
  public Rook(boolean white) {
    super(white);
  }

  @Override
  public byte legalMove(Move move, Field[][] field) {
    int rowDiff = Math.abs(move.getStartRow() - move.getEndRow());
    int columnDiff = Math.abs(move.getStartColumn() - move.getEndColumn());
    if (!((rowDiff == 0 && columnDiff != 0) || (rowDiff != 0 && columnDiff == 0))) {
      return LogicConst.ILLEGAL;
    }
    if (rowDiff == 0) {
      int column = Math.min(move.getStartColumn(), move.getEndColumn()) + 1;
      int maxColumn = Math.max(move.getStartColumn(), move.getEndColumn());
      while (column < maxColumn) {
        if (field[move.getStartRow()][column].getFigure() != null) {
          return LogicConst.ILLEGAL;
        }
        column++;
      }
    } else {
      int row = Math.min(move.getStartRow(), move.getEndRow()) + 1;
      int maxRow = Math.max(move.getStartRow(), move.getEndRow());
      while (row < maxRow) {
        if (field[row][move.getStartColumn()].getFigure() != null) {
          return LogicConst.ILLEGAL;
        }
        row++;
      }
    }
    if (!checkEndField(field[move.getEndRow()][move.getEndColumn()])) {
      return LogicConst.ILLEGAL;
    }
    return LogicConst.OK;
  }

  @Override
  public String toString() {
    return "R";
  }
}
