package logic;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	TestBishop.class,
	TestFigure.class,
	TestFigure.class,
	TestKing.class,
	TestKnight.class,
	TestPawn.class,
	TestRook.class,
	TestQueen.class,
	TestBoard.class
	})
public class AllTests {

}
