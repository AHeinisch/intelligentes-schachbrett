package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestQueen {

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
		Queen queen = new Queen(true);
		assertNotNull(queen);
	}
	
	
	@Test
	public void testLegalMoveWhithNormalMove() {
		Queen queen = new Queen(true);
		field[4][5].setFigure(queen);
		Move m = new Move(4,5,1,2);
		assertEquals(LogicConst.OK, queen.legalMove(m, field));
	}
	
	@Test 
	public void testLegalMoveToNextField() {
		Queen queen = new Queen(true);
		field[4][5].setFigure(queen);
		Move m = new Move(4,5,3,4);
		assertEquals(LogicConst.OK, queen.legalMove(m, field));
	}
	
	@Test 
	public void testLegalMoveTakes() {
		Queen queen = new Queen(true);
		Pawn pawn = new Pawn(false);
		field[4][5].setFigure(queen);
		field[1][2].setFigure(pawn);
		Move m = new Move(4,5,1,2);
		assertEquals(LogicConst.OK, queen.legalMove(m, field));
	}
	
	@Test
	public void testLegalMoveTryToTakeOwnFigure() {
		Queen queen = new Queen(true);
		Pawn pawn = new Pawn(true);
		field[4][5].setFigure(queen);
		field[1][2].setFigure(pawn);
		Move m = new Move(4,5,1,2);
		assertEquals(LogicConst.ILLEGAL, queen.legalMove(m, field));
	}
	
	@Test 
	public void testLegalMoveWhithFigureInTheWay() {
		Queen queen = new Queen(true);
		Pawn pawn = new Pawn(true);
		field[4][5].setFigure(queen);
		field[3][4].setFigure(pawn);
		Move m = new Move(4,5,1,2);
		assertEquals(LogicConst.ILLEGAL, queen.legalMove(m, field));
	}
	
	@Test 
	public void testLegalMoveWhithFigureOneStepBeforeTheEndField() {
		Queen queen = new Queen(true);
		Pawn pawn = new Pawn(true);
		field[4][5].setFigure(queen);
		field[2][3].setFigure(pawn);
		Move m = new Move(4,5,1,2);
		assertEquals(LogicConst.ILLEGAL, queen.legalMove(m, field));
	}

}
