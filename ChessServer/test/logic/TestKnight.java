package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestKnight {

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
		Knight knight = new Knight(true);
		assertNotNull(knight);
	}
	
	@Test
	public void testLegalMoveToUpperLeft() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,5,2);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToUpperRight() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,5,4);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToLeftUpper() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,4,1);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToLeftLower() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,2,1);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToLowerLeft() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,1,2);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToLowerRight() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,1,4);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToRightLower() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,2,5);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveToRightUpper() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,4,5);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveTakes() {
		Knight knight = new Knight(true);
		Bishop bishop = new Bishop(false);
		field[4][5].setFigure(bishop);
		Move m = new Move(3,3,4,5);
		assertEquals(LogicConst.OK, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveTryingToTakeOwnFigure() {
		Knight knight = new Knight(true);
		Bishop bishop = new Bishop(true);
		field[4][5].setFigure(bishop);
		Move m = new Move(3,3,4,5);
		assertEquals(LogicConst.ILLEGAL, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveHorizontalMove() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,3,5);
		assertEquals(LogicConst.ILLEGAL, knight.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveVerticalMove() {
		Knight knight = new Knight(true);
		Move m = new Move(3,3,0,3);
		assertEquals(LogicConst.ILLEGAL, knight.legalMove(m, field));
	}

}
