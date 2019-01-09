package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestKing {

	Field[][] field;
	
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
	public void testConstruktor() {
		King king = new King(true);
		assertNotNull(king);
	}
	
	@Test
	public void testLegalMoveDiagonalStep() {
		King king = new King(true);
		field[0][4].setFigure(king);
		Move m = new Move(0,4,1,3);
		assertEquals(LogicConst.OK, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveHorizotalStep() {
		King king = new King(true);
		field[0][4].setFigure(king);
		Move m = new Move(0,4,0,5);
		assertEquals(LogicConst.OK, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveVerticalStep() {
		King king = new King(true);
		field[0][4].setFigure(king);
		Move m = new Move(0,4,1,4);
		assertEquals(LogicConst.OK, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhitIllegalMovement() {
		King king = new King(true);
		field[0][4].setFigure(king);
		Move m = new Move(0,4,1,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteShortCastle() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][7].setFigure(rook);
		Move m = new Move(0,4,0,6);
		assertEquals(LogicConst.WHITE_SHORT_CASTLE, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteShortCastleWhithKingAlreadyMoved() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][7].setFigure(rook);
		king.setMoved(true);
		Move m = new Move(0,4,0,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteShortCastleWhithRookAlreadyMoved() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][7].setFigure(rook);
		rook.setMoved(true);
		Move m = new Move(0,4,0,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteShortCastleWhithFigureInTheWay() {
		King king = new King(true);
		Rook rook = new Rook(true);
		Bishop bishop = new Bishop(false);
		field[0][4].setFigure(king);
		field[0][7].setFigure(rook);
		field[0][6].setFigure(bishop);
		Move m = new Move(0,4,0,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteLongCastle() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][0].setFigure(rook);
		Move m = new Move(0,4,0,2);
		assertEquals(LogicConst.WHITE_LONG_CASTLE, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteLongCastleWhithKingAlreadyMoved() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][0].setFigure(rook);
		king.setMoved(true);
		Move m = new Move(0,4,0,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteLongCastleWhithRookAlreadyMoved() {
		King king = new King(true);
		Rook rook = new Rook(true);
		field[0][4].setFigure(king);
		field[0][0].setFigure(rook);
		rook.setMoved(true);
		Move m = new Move(0,4,0,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhiteLongCastleWhithFigureInTheWay() {
		King king = new King(true);
		Rook rook = new Rook(true);
		Bishop bishop = new Bishop(false);
		field[0][4].setFigure(king);
		field[0][0].setFigure(rook);
		field[0][1].setFigure(bishop);
		Move m = new Move(0,4,0,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}

	
	@Test
	public void testLegalMoveBlackShortCastle() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][7].setFigure(rook);
		Move m = new Move(7,4,7,6);
		assertEquals(LogicConst.BLACK_SHORT_CASTLE, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackShortCastleWhithKingAlreadyMoved() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][7].setFigure(rook);
		king.setMoved(true);
		Move m = new Move(7,4,7,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackShortCastleWhithRookAlreadyMoved() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][7].setFigure(rook);
		rook.setMoved(true);
		Move m = new Move(7,4,7,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackShortCastleWhithFigureInTheWay() {
		King king = new King(false);
		Rook rook = new Rook(false);
		Bishop bishop = new Bishop(true);
		field[7][4].setFigure(king);
		field[7][7].setFigure(rook);
		field[7][6].setFigure(bishop);
		rook.setMoved(true);
		Move m = new Move(7,4,7,6);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackLongCastle() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][0].setFigure(rook);
		Move m = new Move(7,4,7,2);
		assertEquals(LogicConst.BLACK_LONG_CASTLE, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackLongCastleWhithKingAlreadyMoved() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][0].setFigure(rook);
		king.setMoved(true);
		Move m = new Move(7,4,7,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackLongCastleWhithRookAlreadyMoved() {
		King king = new King(false);
		Rook rook = new Rook(false);
		field[7][4].setFigure(king);
		field[7][0].setFigure(rook);
		rook.setMoved(true);
		Move m = new Move(7,4,7,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveBlackLongCastleWhithFigureInTheWay() {
		King king = new King(false);
		Rook rook = new Rook(false);
		Bishop bishop = new Bishop(true);
		field[7][4].setFigure(king);
		field[7][0].setFigure(rook);
		field[7][2].setFigure(bishop);
		rook.setMoved(true);
		Move m = new Move(7,4,7,2);
		assertEquals(LogicConst.ILLEGAL, king.legalMove(m, field));
	}

}
