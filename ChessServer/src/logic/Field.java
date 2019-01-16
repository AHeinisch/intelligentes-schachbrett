package logic;

/**
 * This class represents a single field of the chess board.
 * 
 * @author jendrikmuller
 *
 */
public class Field {
  private Figure figure;

  /**
   * Returns the figure on the field.
   * 
   * @returnFigure figure on the field. If there is no figure this method will
   *               return null
   */
  public Figure getFigure() {
    return figure;
  }

  /**
   * sets the figure on the field
   * 
   * @param figure
   *          figure to place on the field. If the field should be empty this
   *          parameter has to be null
   */
  public void setFigure(Figure figure) {
    this.figure = figure;
  }

  @Override
  public String toString() {
    if (figure == null) {
      return "_";
    }
    return figure.toString();
  }
}
