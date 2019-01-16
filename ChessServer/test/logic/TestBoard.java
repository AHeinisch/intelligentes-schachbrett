package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import constants.LogicConst;
import org.junit.Test;


public class TestBoard {

  @Test
  public void testConstructor() {
    Board board = new Board();
    assertNotNull(board);
  }

  @Test
  public void testplayerTurnNormalMove() {
    Board board = new Board();
    byte[] actual = board.playerTurn(new Move(1, 4, 3, 4));
    assertEquals(LogicConst.OK, actual[0]);
  }

  @Test
  public void testplayerTurnIllegalMove() {
    Board board = new Board();
    byte[] actual = board.playerTurn(new Move(1, 4, 3, 5));
    assertEquals(LogicConst.ILLEGAL, actual[0]);
    assertEquals(5, actual[1]);
    assertEquals(3, actual[2]);
    assertEquals(4, actual[3]);
    assertEquals(1, actual[4]);
  }

  @Test
  public void testplayerTurnCheckmate() {
    Board board = new Board();
    board.playerTurn(new Move(1, 4, 3, 4));
    board.playerTurn(new Move(6, 5, 5, 5));
    board.playerTurn(new Move(1, 3, 2, 3));
    board.playerTurn(new Move(6, 6, 4, 6));
    byte[] actual = board.playerTurn(new Move(0, 3, 4, 7));
    assertEquals(LogicConst.CHECKMATE, actual[0]);
    assertEquals(4, actual[1]);
    assertEquals(7, actual[2]);
  }

  @Test
  public void testplayerTurnCastle() {
    Board board = new Board();
    board.setMove(new Move(0, 6, 6, 6));
    board.setMove(new Move(0, 5, 6, 6));
    byte[] actual = board.playerTurn(new Move(0, 4, 0, 6));
    assertEquals(LogicConst.CASTLING, actual[0]);
    assertEquals(7, actual[1]);
    assertEquals(0, actual[2]);
    assertEquals(5, actual[3]);
    assertEquals(0, actual[4]);
  }

  @Test
  public void testplayerTurnEnPassant() {
    Board board = new Board();
    board.setMove(new Move(1, 3, 4, 3));
    board.playerTurn(new Move(1, 0, 2, 0));
    board.playerTurn(new Move(6, 4, 4, 4));
    byte[] actual = board.playerTurn(new Move(4, 3, 5, 4));
    assertEquals(LogicConst.EN_PASSANT, actual[0]);
    assertEquals(4, actual[1]);
    assertEquals(4, actual[2]);
  }

  @Test
  public void testWhiteKingChecked() {
    Board board = new Board();
    board.setMove(new Move(7, 3, 0, 3));
    assertTrue(board.whiteKingChecked());
  }

  @Test
  public void testBlackKingChecked() {
    Board board = new Board();
    board.setMove(new Move(0, 3, 6, 3));
    assertTrue(board.blackKingChecked());
  }

}
