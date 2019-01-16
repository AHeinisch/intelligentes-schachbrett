package manager;

import constants.EngineConst;
import constants.LogicConst;
import constants.ServerConst;
import decoder.Decoder;
import helper.Helper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import logic.Board;
import logic.Move;

/**
 * This class manages the connection between the Logic of the Server and the
 * client.
 * 
 * @author jendrikmuller
 *
 */
public class SocketManager {

  private Socket clientSocket;
  private OutputStream socketOut;
  private ServerSocket serverSocket;

  private boolean gameStarted = false;
  private boolean versusEngine = false;
  private boolean awaitingAck = false;

  private EngineManager stockfish;
  private FileManager fileManager;
  private Board board;

  /**
   * Creates a new SocketManager.
   */
  public SocketManager() {
    stockfish = new EngineManager(EngineConst.PATH);
    fileManager = new FileManager();
  }

  /**
   * Sends a massage to the client.
   * 
   * @param message
   *          information that will be send to the client
   */
  private void messageToClient(byte[] message) {
    Helper.debugPrintByteArray(message);
    try {
      socketOut.write(message);
      socketOut.flush();
    } catch (IOException e) {
      System.err.println("Couldn´t send Message");
    }
  }

  /**
   * Starts the necessery Logic. If the Start was Successful an Ok is send to the
   * client and otherwise an Error.
   * 
   * @param input
   *          tells if the new Game is against a player(0) or an engine(1)
   */
  private void processStartCommand(byte input) {
    if (input == ServerConst.PLAYER) {
      board = new Board();
      gameStarted = true;
      versusEngine = false;
      fileManager.createNewHistory();
      messageToClient(ServerConst.Ok_RESPONSE);
      return;
    } else if (input == ServerConst.ENGINE) {
      board = new Board();
      gameStarted = true;
      versusEngine = true;
      stockfish.clearMoveHistory();
      fileManager.createNewHistory();
      messageToClient(ServerConst.Ok_RESPONSE);
      return;
    }
    messageToClient(ServerConst.ERROR_RESPONSE);
  }

  /**
   * Resets the game Logic. If the Reset was Successful an Ok is send to the
   * client and otherwise an Error.
   */
  private void reset() {
    if (gameStarted) {
      board = new Board();
      stockfish.clearMoveHistory();
      fileManager.createNewHistory();
      messageToClient(ServerConst.Ok_RESPONSE);
    }
    messageToClient(ServerConst.ERROR_RESPONSE);
  }

  /**
   * Gives the Turn sent by the client over to the logic and returns a Response
   * based on the protocol to the client.
   * 
   * @param input
   *          message sent by the client, witch contains the turn
   */
  private void processTurn(byte[] input) {
    if (!gameStarted || awaitingAck) {
      messageToClient(ServerConst.ERROR_RESPONSE);
      return;
    }
    Move playerMove = Decoder.playerTurnToMove(input);
    byte[] boardRetPlayer = board.playerTurn(playerMove);
    if (boardRetPlayer[0] != LogicConst.ILLEGAL && boardRetPlayer[0] != LogicConst.PROMOTION) {
      fileManager.appendTurnToHistory(playerMove.toString());
    }
    if (boardRetPlayer[0] != LogicConst.ILLEGAL && boardRetPlayer[0] 
        != LogicConst.PROMOTION && versusEngine) {
      String engineTurn = stockfish.getTurn(playerMove.toString());
      Move engineMove = Decoder.engineTurnToMove(engineTurn);
      System.out.println(playerMove);
      System.out.println(engineMove);
      byte[] boardRetEngine = board.engineTurn(engineMove);
      fileManager.appendTurnToHistory(engineTurn);
      byte[] response = Helper.concatArrays(boardRetPlayer, boardRetEngine);
      messageToClient(response);
      return;
    }
    if (boardRetPlayer[0] == LogicConst.PROMOTION) {
      awaitingAck = true;
    }
    messageToClient(boardRetPlayer);
  }

  /**
   * The the logic what the promoted figure should be. If there is no figure to
   * promote an error is returned to the client.
   * 
   * @param figure
   *          the byte representation of the figure the client wants to have
   */
  private void processPromotionAck(byte figure) {
    if (!awaitingAck) {
      messageToClient(ServerConst.ERROR_RESPONSE);
      return;
    }
    awaitingAck = false;
    byte[] boardRet = board.setPromotedFigure(figure);
    fileManager.appendTurnToHistory(board.getLastMove().toString());
    if (versusEngine) {
      String engineTurn = stockfish.getTurn(board.getLastMove().toString());
      Move engineMove = Decoder.engineTurnToMove(engineTurn);
      byte[] boardRetEngine = board.engineTurn(engineMove);
      fileManager.appendTurnToHistory(engineTurn);
      byte[] response = Helper.concatArrays(boardRet, boardRetEngine);
      messageToClient(response);
      return;
    }
    messageToClient(boardRet);
  }

  /**
   * Processes the input sent by the client.
   * 
   * @param input
   *          message from the client to the server
   */
  public void processMessage(byte[] input) {

    switch (input[0]) {
      case ServerConst.START:
        processStartCommand(input[1]);
        break;
      case ServerConst.RESET:
        reset();
        break;
      case ServerConst.NEW_TURN:
        processTurn(input);
        break;
      case ServerConst.PROMOTION_ACK:
        processPromotionAck(input[1]);
        break;
      default:
        messageToClient(ServerConst.ERROR_RESPONSE);
        break;
    }

  }

  /**
   * Keeps the Server open and connects to the client.
   * 
   * @throws IOException
   *           gets thrown if the server fails to build up the connection.
   */
  public void manageConnection() throws IOException {
    serverSocket = new ServerSocket(ServerConst.PORT);
    System.err.println("Started server on port " + ServerConst.PORT);

    while (true) {
      clientSocket = serverSocket.accept();
      System.err.println("Accepted connection from client");

      socketOut = clientSocket.getOutputStream();
      InputStream socketIn = clientSocket.getInputStream();
      byte[] buffer = new byte[ServerConst.MAX_INPUT_LENGTH];
      socketIn.read(buffer);
      Helper.debugPrintByteArray(buffer);
      processMessage(buffer);

      System.err.println("Closing connection with client");
      socketOut.close();
      clientSocket.close();
    }

  }

  /**
   * Entry point to the program witch starts the server.
   * 
   * @param args
   *          arguments given at the start
   */
  public static void main(String[] args) {
    SocketManager mySocketManager = new SocketManager();
    try {
      mySocketManager.manageConnection();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
