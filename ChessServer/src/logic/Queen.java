package logic;

import constants.LogicConst;

/**
 * This class represents the chess figure Queen
 * 
 * @author jendrikmuller
 *
 */
public class Queen extends Figure {

  private Bishop bishop;
  private Rook rook;

  /**
   * Creates a new instance of the type Queen.
   * 
   * @param white
   *          true for white and false for black
   */
  public Queen(boolean white) {
    super(white);
    bishop = new Bishop(white);
    rook = new Rook(white);
  }

  @Override
  public byte legalMove(Move move, Field[][] field) {
    if (bishop.legalMove(move, field) == LogicConst.OK 
        || rook.legalMove(move, field) == LogicConst.OK) {
      return LogicConst.OK;
    }
    return LogicConst.ILLEGAL;
  }

  @Override
  public String toString() {
    return "Q";
  }
}
