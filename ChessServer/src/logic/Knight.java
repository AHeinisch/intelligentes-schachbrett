package logic;

import constants.LogicConst;

/**
 * This class represents the chess figure Knight.
 * 
 * @author jendrikmuller
 *
 */
public class Knight extends Figure {

  /**
   * Creates a new instance of the type Knight.
   * 
   * @param white
   *          true for white and false for black
   */
  public Knight(boolean white) {
    super(white);
  }

  @Override
  public byte legalMove(Move move, Field[][] field) {
    int rowDiff = Math.abs(move.getStartRow() - move.getEndRow());
    int columnDiff = Math.abs(move.getStartColumn() - move.getEndColumn());
    if (!((rowDiff == 2 && columnDiff == 1) || rowDiff == 1 && columnDiff == 2)) {
      return LogicConst.ILLEGAL;
    }
    if (!checkEndField(field[move.getEndRow()][move.getEndColumn()])) {
      return LogicConst.ILLEGAL;
    }
    return LogicConst.OK;
  }

  @Override
  public String toString() {
    return "N";
  }
}
