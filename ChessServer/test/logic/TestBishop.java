package logic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Constants.LogicConst;

public class TestBishop {
	
	Field[][] field;
	
	@Before
	public void setUp() {
		field = new Field[LogicConst.ROWS][LogicConst.COLUMS];
		for (int i = 0; i < LogicConst.ROWS; i++) {
			for (int j = 0; j < LogicConst.COLUMS; j++) {
				field[i][j] = new Field();
			}
		}
		Bishop bishop = new Bishop(true);
		field[4][4].setFigure(bishop);
	}
	
	@Test
	public void testConstruktor() {
		Bishop bishop = new Bishop(true);
		assertNotEquals(bishop, null);
	}

}
