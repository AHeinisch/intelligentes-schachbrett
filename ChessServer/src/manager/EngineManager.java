package manager;

import constants.EngineConst;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Manages the engine. The engine has to use the uci Protocol.
 * 
 * @author jendrikmuller
 *
 */
public class EngineManager {

  private final String enginePath;

  private String moveHistory = "";

  private ProcessBuilder uciEngineProcessBuilder;
  private Process uciEngineProcess;

  private InputStream engineIn;
  private OutputStream engineOut;

  /**
   * Creates a new EngineManager.
   * 
   * @param path path were the engine is stored
   */
  public EngineManager(String path) {
    this.enginePath = path;
    loadEngine();
    setupEngine();
  }

  /**
   * Clears the move the Move history to start a new game.
   */
  public void clearMoveHistory() {
    moveHistory = "";
    messageWithoutAnswer(EngineConst.NEW_GAME_COMMAND);
  }

  private void loadEngine() {
    uciEngineProcessBuilder = new ProcessBuilder(enginePath);

    try {
      uciEngineProcess = uciEngineProcessBuilder.start();
    } catch (IOException ex) {
      System.out.println("error loading engine");
    }

    engineIn = uciEngineProcess.getInputStream();
    engineOut = uciEngineProcess.getOutputStream();
  }

  /**
   * Sets up the Engine.
   */
  private void setupEngine() {
    messageToEngine(EngineConst.UCI_COMMAND);
    messageToEngine(EngineConst.REDAY_COMMAND);
    messageWithoutAnswer(EngineConst.NEW_GAME_COMMAND);
  }

  /**
   * Reeds the output of the engine.
   * 
   * @return String engine output
   */
  public String readOutputStream() {
    String buffer = "";
    boolean reading = true;
    while (reading) {
      try {
        char chunk = (char) engineIn.read();
        buffer += chunk;
        if (engineIn.available() == 0) {
          reading = false;
        }
      } catch (IOException ex) {
        System.out.println("engine read IO exception");
        reading = false;

      }
    }
    return buffer;
  }

  /**
   * Sends a message to the engine.
   * 
   * @param message
   *          message witch will be sent to the engine
   */
  public void messageWithoutAnswer(String message) {
    try {
      engineOut.write(message.getBytes());
      engineOut.flush();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * Sends a message to the engine and returns the answer.
   * 
   * @param message
   *          message message witch will be sent to the engine
   * @return String answer of the engine
   */
  public String messageToEngine(String message) {
    messageWithoutAnswer(message);
    return readOutputStream();
  }

  /**
   * Sends a turn to the engine and returns the move the engine would play.
   * 
   * @param userTurn
   *          turn witch the player wants to play
   * @return String turn the engine would play at this point
   */
  public String getTurn(String userTurn) {
    String engineOutput = "";
    moveHistory += userTurn + " ";
    boolean bestmoveFound = false;
    messageWithoutAnswer(EngineConst.POSITION_COMMAND + moveHistory + "\n");
    engineOutput = messageToEngine(
        EngineConst.GO_DEPTH_COMMAND + EngineConst.DEPTH + EngineConst.COMMAND_END);
    while (!bestmoveFound) {
      if (engineOutput.contains("bestmove")) {
        bestmoveFound = true;
      } else {
        engineOutput = readOutputStream();
      }
    }
    String[] outputLines = engineOutput.split("\n");
    String[] bestmoveLine = outputLines[outputLines.length - 1].split(" ");
    String engineTurn = bestmoveLine[EngineConst.MOVEPOS];
    moveHistory += engineTurn + " ";
    return engineTurn;
  }
}
