package logic;

/**
 * This Class represents the basic structure of a chess figure.
 * 
 * @author jendrikmuller
 *
 */
public abstract class Figure {

  protected boolean moved;
  protected boolean white;

  /**
   * Creates a new instance of Figure.
   * 
   * @param white
   *          true if the Figure should be white and false if it should be black
   */
  public Figure(boolean white) {
    this.white = white;
    moved = false;
  }

  /**
   * Tells if the Figure is white or not.
   * 
   * @return boolean true if the Figure is white and false if it is black
   */
  public boolean isWhite() {
    return white;
  }

  /**
   * Tells if the Figure was moved or not.
   * 
   * @return boolean true if the Figure was moved
   */
  public boolean isMoved() {
    return moved;
  }

  /**
   * Sets the attribute moved.
   * 
   * @param moved
   *          new value of the attribute moved
   */
  public void setMoved(boolean moved) {
    this.moved = moved;
  }

  /**
   * Controls of the Figure can be placed on the given Field. This method
   * shouldn't be used for Figures of the type pawn cause of special rulings.
   * 
   * @param field
   *          Field to check
   * @return boolean true if the figure can be placed on the given field and
   *         otherwise false
   */
  public boolean checkEndField(Field field) {
    if (field.getFigure() == null) {
      return true;
    }
    if (field.getFigure().white == white) {
      return false;
    }
    return true;
  }

  /**
   * Evaluates the given Move and and returns a byte value 
   * describing its kind.
   * 
   * @param move The move to evaluate
   * @param field A field array describing the current board state.
   * @return byte the value of the byte describes the kind of movement(for example
   *         2 for castling)
   */
  public abstract byte legalMove(Move move, Field[][] field);
}
