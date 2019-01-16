package constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Collection of constants that are needed to convert between forms of
 * representation.
 * 
 * @author jendrikmuller
 *
 */
public class DecoderConst {

  public static final int START_COLUMN_POSITION = 0;
  public static final int START_ROW_POSITION = 1;
  public static final int END_COLUMN_POSITION = 2;
  public static final int END_ROW_POSITION = 3;
  public static final int PROMOTION_POSITION = 4;

  public static final int PROMOTION_TURN_LENGTH = 5;

  public static final String[] FIELD_COLUMN = { "a", "b", "c", "d", "e", "f", "g", "h" };
  public static final String[] PROMOTION = { "q", "n", "r", "b" };

  public static final Map<Character, Integer> PROMOTION_MAP;
  
  static {
    HashMap<Character, Integer> tmp = new HashMap<Character, Integer>();
    tmp.put('q', 80);
    tmp.put('n', 78);
    tmp.put('b', 66);
    tmp.put('r', 82);
    PROMOTION_MAP = Collections.unmodifiableMap(tmp);
  }

  public static final Map<Integer, Character> PROMOTION_MAP_REV;
  
  static {
    HashMap<Integer, Character> tmp = new HashMap<Integer, Character>();
    tmp.put(80, 'q');
    tmp.put(78, 'n');
    tmp.put(66, 'b');
    tmp.put(82, 'r');
    PROMOTION_MAP_REV = Collections.unmodifiableMap(tmp);
  }

  public static final Map<Character, Integer> COLUMNMAP;
  
  static {
    HashMap<Character, Integer> tmp = new HashMap<Character, Integer>();
    tmp.put('a', 0);
    tmp.put('b', 1);
    tmp.put('c', 2);
    tmp.put('d', 3);
    tmp.put('e', 4);
    tmp.put('f', 5);
    tmp.put('g', 6);
    tmp.put('h', 7);
    COLUMNMAP = Collections.unmodifiableMap(tmp);
  }
}
