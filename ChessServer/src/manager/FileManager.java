package manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;

/**
 * This class controls the file in witch the game history gets stored.
 * 
 * @author jendrikmuller
 *
 */
public class FileManager {

  private final String fileEnd = ".txt";

  private String historyName;
  FileWriter fw;
  BufferedWriter bw;

  /**
   * Creates a new game history file with the name of the current time.
   */
  public void createNewHistory() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    historyName = timestamp.toString() + fileEnd;

    try {
      fw = new FileWriter(historyName);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * Appends a new turn to the game history.
   * 
   * @param turn
   *          String format of the turn
   */
  public void appendTurnToHistory(String turn) {
    try {
      fw = new FileWriter(historyName, true);
      bw = new BufferedWriter(fw);
      bw.append(turn);
      bw.newLine();
      bw.newLine();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
