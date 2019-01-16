package logic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import constants.LogicConst;
import org.junit.Before;
import org.junit.Test;

public class TestFigure {

  Field[][] field;

  /**
   * Creates an empty chess field before every test.
   */
  @Before
  public void setUp() {
    field = new Field[LogicConst.ROWS][LogicConst.COLUMS];
    for (int i = 0; i < LogicConst.ROWS; i++) {
      for (int j = 0; j < LogicConst.COLUMS; j++) {
        field[i][j] = new Field();
      }
    }
  }

  @Test
  public void testCheckEndFieldMoveToEmptyField() {
    Bishop bishop = new Bishop(true);
    assertTrue(bishop.checkEndField(field[2][3]));
  }

  @Test
  public void testCheckEndFieldMoveToFieldWhithFigureOfOppositeColor() {
    Bishop bishop = new Bishop(true);
    field[2][3].setFigure(new Rook(false));
    assertTrue(bishop.checkEndField(field[2][3]));
  }

  @Test
  public void testCheckEndFieldMoveToFieldWhithFigureOfTheSameColor() {
    Bishop bishop = new Bishop(true);
    field[2][3].setFigure(new Rook(true));
    assertFalse(bishop.checkEndField(field[2][3]));
  }

}
