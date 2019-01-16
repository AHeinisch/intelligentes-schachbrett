package logic;

import constants.LogicConst;

/**
 * This class represents the chess figure Bishop.
 * 
 * @author jendrikmuller
 *
 */
public class Bishop extends Figure {

  /**
   * creates a new instance of the type Bishop.
   * 
   * @param white
   *          true for white and false for black
   */
  public Bishop(boolean white) {
    super(white);
  }

  @Override
  public byte legalMove(Move move, Field[][] field) {
    int rowDiff = Math.abs(move.getStartRow() - move.getEndRow());
    int columnDiff = Math.abs(move.getStartColumn() - move.getEndColumn());
    if (rowDiff != columnDiff) {
      return LogicConst.ILLEGAL;
    }
    int rowStep = 1;
    if (move.getStartRow() > move.getEndRow()) {
      rowStep = -1;
    }
    int columnStep = 1;
    if (move.getStartColumn() > move.getEndColumn()) {
      columnStep = -1;
    }
    int row = move.getStartRow() + rowStep;
    int column = move.getStartColumn() + columnStep;
    while (row != move.getEndRow()) {
      if (field[row][column].getFigure() != null) {
        return LogicConst.ILLEGAL;
      }
      row += rowStep;
      column += columnStep;
    }
    if (!checkEndField(field[move.getEndRow()][move.getEndColumn()])) {
      return LogicConst.ILLEGAL;
    }
    return LogicConst.OK;
  }

  @Override
  public String toString() {
    return "B";
  }
}
