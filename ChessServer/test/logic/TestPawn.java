package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import constants.LogicConst;
import org.junit.Before;
import org.junit.Test;

public class TestPawn {

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
    Pawn pawn = new Pawn(true);
    assertNotNull(pawn);
  }

  @Test
  public void testLegalMoveOneStepForward() {
    Pawn pawn = new Pawn(true);
    Move m = new Move(1, 4, 2, 4);
    assertEquals(LogicConst.OK, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTwoStepsForward() {
    Pawn pawn = new Pawn(true);
    Move m = new Move(1, 4, 3, 4);
    assertEquals(LogicConst.OK, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTwoStepsForwardWhithPawnAlreadyMoved() {
    Pawn pawn = new Pawn(true);
    pawn.setMoved(true);
    Move m = new Move(1, 4, 3, 4);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveStepBackwards() {
    Pawn pawn = new Pawn(true);
    Move m = new Move(1, 4, 0, 4);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveBlackOneStepForward() {
    Pawn pawn = new Pawn(false);
    Move m = new Move(6, 4, 5, 4);
    assertEquals(LogicConst.OK, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveBlackTwoStepsForward() {
    Pawn pawn = new Pawn(false);
    Move m = new Move(6, 4, 4, 4);
    assertEquals(LogicConst.OK, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveBlackTwoStepsForwardWhithPawnAlreadyMoved() {
    Pawn pawn = new Pawn(false);
    pawn.setMoved(true);
    Move m = new Move(6, 4, 4, 4);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveBlackOneStepBackwards() {
    Pawn pawn = new Pawn(false);
    Move m = new Move(6, 4, 7, 4);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveOneStepDiagonal() {
    Pawn pawn = new Pawn(true);
    Move m = new Move(1, 4, 2, 3);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTakes() {
    Pawn pawn = new Pawn(true);
    Bishop bishop = new Bishop(false);
    field[2][3].setFigure(bishop);
    Move m = new Move(1, 4, 2, 3);
    assertEquals(LogicConst.OK, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTryingToTakeOwnFigure() {
    Pawn pawn = new Pawn(true);
    Bishop bishop = new Bishop(true);
    field[2][3].setFigure(bishop);
    Move m = new Move(1, 4, 2, 3);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveTryingToTakeForward() {
    Pawn pawn = new Pawn(true);
    Bishop bishop = new Bishop(false);
    field[2][4].setFigure(bishop);
    Move m = new Move(1, 4, 2, 4);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveEnPassant() {
    Pawn pawn = new Pawn(true);
    Pawn enemyPawn = new Pawn(false);
    field[4][5].setFigure(enemyPawn);
    Move m = new Move(4, 4, 5, 5);
    assertEquals(LogicConst.EN_PASSANT, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMoveEnPassantWhithoutPwn() {
    Pawn pawn = new Pawn(true);
    Bishop bishop = new Bishop(false);
    field[4][5].setFigure(bishop);
    Move m = new Move(4, 4, 5, 5);
    assertEquals(LogicConst.ILLEGAL, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMovePromotion() {
    Pawn pawn = new Pawn(true);
    Move m = new Move(6, 4, 7, 4);
    assertEquals(LogicConst.PROMOTION, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMovePromotionAndTakes() {
    Pawn pawn = new Pawn(true);
    Bishop bishop = new Bishop(false);
    field[7][3].setFigure(bishop);
    Move m = new Move(6, 4, 7, 3);
    assertEquals(LogicConst.PROMOTION, pawn.legalMove(m, field));
  }

  @Test
  public void testLegalMovePromotionBlack() {
    Pawn pawn = new Pawn(false);
    Move m = new Move(1, 4, 0, 4);
    assertEquals(LogicConst.PROMOTION, pawn.legalMove(m, field));
  }

}
