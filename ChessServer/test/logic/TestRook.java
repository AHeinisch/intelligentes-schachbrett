package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestRook {

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
	public void testConstructor() {
		Rook rook = new Rook(true);
		assertNotNull(rook);
	}
	
	@Test
	public void testLegalMoveHorizontalRight() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,4,7);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveHorizontalLeft() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,4,0);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveVerticalUp() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,7,4);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveVerticalDown() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,7,4);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToNextFieldVertical() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,3,4);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToNextFieldHorizontal() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,4,3);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhithIllegalMovement() {
		Rook rook = new Rook(true);
		Move m = new Move(4,4,3,3);
		assertEquals(LogicConst.ILLEGAL, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveTakes() {
		Rook rook = new Rook(true);
		Bishop bishop = new Bishop(false);
		field[7][4].setFigure(bishop);
		Move m = new Move(4,4,7,4);
		assertEquals(LogicConst.OK, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveTryingToTakeOwnFigure() {
		Rook rook = new Rook(true);
		Bishop bishop = new Bishop(true);
		field[7][4].setFigure(bishop);
		Move m = new Move(4,4,7,4);
		assertEquals(LogicConst.ILLEGAL, rook.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveWhithFigureInTheWay() {
		Rook rook = new Rook(true);
		Bishop bishop = new Bishop(false);
		field[6][4].setFigure(bishop);
		Move m = new Move(4,4,7,4);
		assertEquals(LogicConst.ILLEGAL, rook.legalMove(m, field));
	}

}
