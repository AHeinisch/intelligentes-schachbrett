package helper;

/**
 * This class provides help functions.
 * 
 * @author jendrikmuller
 *
 */
public class Helper {

  /**
   * Concats two arrays to one array. The length of the new array is equal to the
   * sum of the input array lengths
   * 
   * @param firstArr
   *          array that will be the first in the resulting array
   * @param secondArr
   *          array that will be the second in the resulting array
   * @return byte[] array that is concatenation of the input array
   */
  public static byte[] concatArrays(byte[] firstArr, byte[] secondArr) {
    byte[] retVal = new byte[firstArr.length + secondArr.length];
    for (int i = 0; i < firstArr.length; i++) {
      retVal[i] = firstArr[i];
    }
    for (int i = 0; i < secondArr.length; i++) {
      retVal[firstArr.length + i] = secondArr[i];
    }
    return retVal;
  }

  /**
   * Prints out an array on the console.
   * 
   * @param array
   *          array to print
   */
  public static void debugPrintByteArray(byte[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.print("\n");
  }
}
