package main;

import logic.Board;
import logic.Move;

public class Main {
	public static void main(String [] args) {
		Board board = new Board();
		byte [] tmp = new byte[5];
		tmp = board.playerTurn(new Move(1,4,3,4));
		System.out.println(tmp[0]);
		System.out.println(board);
		tmp = board.playerTurn(new Move(6,3,4,3));
		System.out.println(tmp[0]);
		System.out.println(board);
		tmp = board.playerTurn(new Move(1,7,2,7));
		System.out.println(tmp[0]);
		System.out.println(board);
		
	}
}
