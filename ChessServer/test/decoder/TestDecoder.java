package decoder;

import static org.junit.Assert.*;

import org.junit.Test;

import logic.Move;

public class TestDecoder {

	@Test
	public void testPlayerTurnToMove() {
		byte[] testArray = {4,3,7,5,6};
		Move m = Decoder.playerTurnToMove(testArray);
		assertEquals(7, m.getStartRow());
		assertEquals(3, m.getStartColumn());
		assertEquals(6, m.getEndRow());
		assertEquals(5, m.getEndColumn());
	}
	
	@Test
	public void testEngineTurnToMove() {
		String testTurn = "e2e4";
		Move m = Decoder.engineTurnToMove(testTurn);
		assertEquals(1, m.getStartRow());
		assertEquals(4, m.getStartColumn());
		assertEquals(3, m.getEndRow());
		assertEquals(4, m.getEndColumn());
	}

}
