package decoder;

import constants.DecoderConst;
import logic.Move;

/**
 * Class to convert between forms of representation.
 * 
 * @author jendrikmuller
 *
 */
public class Decoder {

  /**
   * Converts the input after the communication protocol to a move.
   * @param input information the client sent to the server
   * @return Move the move representation of the given input
   */
  public static Move playerTurnToMove(byte[] input) {
    Move retVal = new Move(input[2], input[1], input[4], input[3]);
    return retVal;
  }

  /**
   * converts a turn in string format to an instance of the class Move.
   * 
   * @param turn a move in string representation
   * @return Move the move representation of the given input
   */
  public static Move engineTurnToMove(String turn) {
    int startColumn = DecoderConst.COLUMNMAP.get(turn.charAt(DecoderConst.START_COLUMN_POSITION));
    int endColumn = DecoderConst.COLUMNMAP.get(turn.charAt(DecoderConst.END_COLUMN_POSITION));
    int startRow = Integer
        .parseInt(turn.substring(
            DecoderConst.START_ROW_POSITION, DecoderConst.START_ROW_POSITION + 1));
    startRow--;
    int endRow = Integer.parseInt(turn.substring(
        DecoderConst.END_ROW_POSITION, DecoderConst.END_ROW_POSITION + 1));
    endRow--;
    Move retVal = new Move(startRow, startColumn, endRow, endColumn);
    if (turn.length() == DecoderConst.PROMOTION_TURN_LENGTH) {
      retVal.setPromotion((byte) turn.charAt(DecoderConst.PROMOTION_POSITION));
    }
    return retVal;
  }
}
