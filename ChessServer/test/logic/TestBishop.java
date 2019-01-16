package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import constants.LogicConst;

import org.junit.Before;
import org.junit.Test;

public class TestBishop {

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
  public void testConstructor() {
    Bishop bishop = new Bishop(true);
    assertNotEquals(bishop, null);
  }

  @Test
  public void testLegalMoveWhithNormalMove() {
    Bishop bishop = new Bishop(true);
    field[4][5].setFigure(bishop);
    Move m = new Move(4, 5, 1, 2);
    assertEquals(LogicConst.OK, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalMoveToNextField() {
    Bishop bishop = new Bishop(true);
    field[4][5].setFigure(bishop);
    Move m = new Move(4, 5, 3, 4);
    assertEquals(LogicConst.OK, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTakes() {
    Bishop bishop = new Bishop(true);
    Pawn pawn = new Pawn(false);
    field[4][5].setFigure(bishop);
    field[1][2].setFigure(pawn);
    Move m = new Move(4, 5, 1, 2);
    assertEquals(LogicConst.OK, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalWhithNoneDiagonalMove() {
    Bishop bishop = new Bishop(true);
    field[4][5].setFigure(bishop);
    Move m = new Move(4, 5, 1, 3);
    assertEquals(LogicConst.ILLEGAL, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTryToTakeOwnFigure() {
    Bishop bishop = new Bishop(true);
    Pawn pawn = new Pawn(true);
    field[4][5].setFigure(bishop);
    field[1][2].setFigure(pawn);
    Move m = new Move(4, 5, 1, 2);
    assertEquals(LogicConst.ILLEGAL, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalMoveWhithFigureInTheWay() {
    Bishop bishop = new Bishop(true);
    Pawn pawn = new Pawn(true);
    field[4][5].setFigure(bishop);
    field[3][4].setFigure(pawn);
    Move m = new Move(4, 5, 1, 2);
    assertEquals(LogicConst.ILLEGAL, bishop.legalMove(m, field));
  }

  @Test
  public void testLegalMoveWhithFigureOneStepBeforeTheEndField() {
    Bishop bishop = new Bishop(true);
    Pawn pawn = new Pawn(true);
    field[4][5].setFigure(bishop);
    field[2][3].setFigure(pawn);
    Move m = new Move(4, 5, 1, 2);
    assertEquals(LogicConst.ILLEGAL, bishop.legalMove(m, field));
  }

}
