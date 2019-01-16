package logic;

import constants.DecoderConst;

/**
 * Models the movement of a Figure.
 * 
 * @author jendrikmuller
 *
 */
public class Move {
  private int startRow;
  private int startColumn;
  private int endRow;
  private int endColumn;
  private byte promotion = 0;

  /**
   * Creates a new instance of the Type Move.
   * 
   * @param startRow
   *          start row of the Figure
   * @param startColumn
   *          start column of the Figure
   * @param endRow
   *          end row of the Figure
   * @param endColumn
   *          end column of the Figure
   */
  public Move(int startRow, int startColumn, int endRow, int endColumn) {
    this.startRow = startRow;
    this.startColumn = startColumn;
    this.endRow = endRow;
    this.endColumn = endColumn;
  }

  public int getStartRow() {
    return startRow;
  }

  public int getStartColumn() {
    return startColumn;
  }

  public int getEndRow() {
    return endRow;
  }

  public int getEndColumn() {
    return endColumn;
  }

  public byte getPromotion() {
    return promotion;
  }

  public void setPromotion(byte promotion) {
    this.promotion = promotion;
  }

  @Override
  public String toString() {
    String retVal = "";
    retVal += DecoderConst.FIELD_COLUMN[startColumn];
    int row = startRow + 1;
    retVal += row;
    retVal += DecoderConst.FIELD_COLUMN[endColumn];
    row = endRow + 1;
    retVal += row;
    if (promotion != 0) {
      retVal += DecoderConst.PROMOTION_MAP_REV.get(promotion);
    }
    return retVal;
  }
}
