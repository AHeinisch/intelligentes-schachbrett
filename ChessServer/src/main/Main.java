package main;

import logic.Board;
import logic.Move;

public class Main {
  
  /**
   * Entry Point for the program, witch is mostly used for debug purposes.
   * @param args start arguments given to the program
   */
  public static void main(String[] args) {
    Board board = new Board();
    byte[] tmp = new byte[5];
    tmp = board.playerTurn(new Move(1, 4, 3, 4));
    tmp = board.playerTurn(new Move(6, 3, 4, 3));
    tmp = board.playerTurn(new Move(4, 1, 4, 3));
    tmp = board.playerTurn(new Move(3, 4, 4, 3));
    tmp = board.playerTurn(new Move(1, 3, 3, 3));

    System.out.println(tmp[0]);
    System.out.println(tmp[1]);
    System.out.println(tmp[2]);
    System.out.println(tmp[3]);
    System.out.println(tmp[4]);

  }
}
