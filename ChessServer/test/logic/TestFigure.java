package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestFigure {
	
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
	public void testCheckEndFieldMoveToEmptyField() {
		Bishop bishop = new Bishop(true);
		assertTrue(bishop.checkEndField(2, 3, field));
	}
	
	@Test
	public void testCheckEndFieldMoveToFieldWhithFigureOfOppositeColor() {
		Bishop bishop = new Bishop(true);
		field[2][3].setFigure(new Rook(false));
		assertTrue(bishop.checkEndField(2, 3, field));
	}
	
	@Test
	public void testCheckEndFieldMoveToFieldWhithFigureOfTheSameColor() {
		Bishop bishop = new Bishop(true);
		field[2][3].setFigure(new Rook(true));
		assertFalse(bishop.checkEndField(2, 3, field));
	}
	

}
