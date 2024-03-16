package controllertest;

import java.awt.image.BufferedImage;
import java.util.List;
import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.Space;
import madworld.madworldmodel.World;

/**
 * A mock model class for mocking the world model.
 * 
 * @author basu.anw, majumdar.s
 */

public class MockModelclass implements World {

  private StringBuilder log;
  private final int uniqueCode;
  private String inputs;
  private PlayerType type;

  /**
   * A constructor for MockModelclass which takes below parameter.
   * 
   * @param log        an appendable to append the input logs
   * @param uniqueCode an unique code sent by the controller to test the output
   * @param inputs     The current turn of the game
   * @param type       The type of the player (Human or computer controlled)
   */
  public MockModelclass(StringBuilder log, int uniqueCode, String inputs, PlayerType type) {
    this.log = log;
    this.uniqueCode = uniqueCode;
    this.inputs = inputs;
    this.type = type;
  }

  @Override
  public String addPlayer(String playerName, PlayerType type, int position) {
    log.append("Input: " + playerName + " " + type + " " + position + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);

  }

  @Override
  public String lookaround(String playerName) {
    log.append("Input: " + playerName + "\n");

    return Integer.toString(uniqueCode);
  }

  @Override
  public String moveplayer(String playerName, String roomname) {
    log.append("Input: " + playerName + " " + roomname + "\n");
    log.append(uniqueCode);

    return Integer.toString(uniqueCode);

  }

  @Override
  public String pickItemsPlayer(String playerName, String itemName) {
    if (playerName == null || ("").equals(playerName) || itemName == null
        || ("").equals(itemName)) {
      throw new IllegalArgumentException("Invalid input for item");
    }
    log.append("Input: " + playerName + " " + itemName + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);

  }

  @Override
  public String getTurn() {

    log.append(uniqueCode + "\n");

    return inputs;
  }

  @Override
  public String computerlookaround(String playername) {

    log.append("Input: " + playername + "\n");

    return Integer.toString(uniqueCode);
  }

  @Override
  public int getWorldRows() {

    return uniqueCode;
  }

  @Override
  public int getWorldColumns() {

    return uniqueCode;
  }

  @Override
  public String getTargetChar() {

    return null;
  }

  @Override
  public String getWorldName() {

    return null;
  }

  @Override
  public void moveTargetChar() {

    log.append(uniqueCode);

  }

  @Override
  public int getTotalRooms() {

    return uniqueCode;
  }

  @Override
  public int getCurrentPos() {

    return uniqueCode;
  }

  @Override
  public int targetHealth() {

    return uniqueCode;
  }

  @Override
  public void displayNeighborDetails(int roomIndex) {
    log.append("Input: " + roomIndex + "\n");

  }

  @Override
  public void displayMap(BufferedImage image) {

    log.append("Input: " + image + "\n");
    log.append(uniqueCode);
  }

  @Override
  public int getNoOfTurns() {

    return uniqueCode;
  }

  @Override
  public boolean isGameOver() {

    return false;
  }

  @Override
  public boolean isComputer(String playerName) {

    return false;
  }

  @Override
  public String movePetForPlayers(String playerName, int nextPos) {

    log.append("Input: " + playerName + " " + nextPos + "\n");
    log.append(uniqueCode);

    return Integer.toString(uniqueCode);
  }

  @Override
  public String getTargetInfo() {

    return Integer.toString(uniqueCode);
  }

  @Override
  public String attackTargetForPlayers(String playerName, String itemName) {

    if (playerName == null || (" ").equals(playerName) || itemName == null
        || (" ").equals(itemName)) {
      throw new IllegalArgumentException("Invalid input");
    }

    log.append("Input: " + playerName + " " + itemName + "\n");
    log.append(uniqueCode);

    return Integer.toString(uniqueCode);
  }

  @Override
  public String getWinner() {

    return Integer.toString(uniqueCode);
  }

  @Override
  public String showTarget() {

    return Integer.toString(uniqueCode);
  }

  @Override
  public String pokeInEye(String playerName) {

    log.append("Input: " + playerName + "\n");
    log.append(uniqueCode);

    return Integer.toString(uniqueCode);
  }

  @Override
  public String attackComputerPlayers(String playerName) {
    log.append("Input: " + playerName + "\n");
    log.append(uniqueCode);

    return Integer.toString(uniqueCode);
  }

  @Override
  public String addComputerPlayer(String playername, int pos) {
    log.append("Input: " + playername + " " + pos + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);

  }

  @Override
  public String moveComputerPlayer(String playername, boolean randomFlag, int rand) {

    log.append("Input: " + playername + " " + randomFlag + " " + rand + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);
  }

  @Override
  public String pickitemComputerPlayer(String playername, boolean randomFlag, int rand) {

    log.append("Input: " + playername + " " + randomFlag + " " + rand + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);
  }

  @Override
  public String actionForComputer(String playername) {

    log.append("Input: " + playername + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);
  }

  @Override
  public String getEvidenceList() {
    return Integer.toString(uniqueCode);
  }

  @Override
  public String showPet() {

    return Integer.toString(uniqueCode);
  }

  @Override
  public String getPathforPetDfS(int root) {

    log.append("Input: " + root + "\n");
    return Integer.toString(uniqueCode);
  }

  @Override
  public int getTotalCurrentPlayers() {

    return uniqueCode;
  }

  @Override
  public int getTargetLocation() {

    return uniqueCode;
  }

  @Override
  public List<Space> getRoomDetails() {

    return null;
  }

  @Override
  public int getCurrentLocationPlayer() {

    return uniqueCode;
  }

  @Override
  public int getMaxTurns() {

    return uniqueCode;
  }

  @Override
  public PlayerType getPlayerTypeCurrentTurn() {

    return type;
  }

  @Override
  public String displayPlayerForController(String playerName) {
    log.append("Input: " + playerName + "\n");
    log.append(uniqueCode);
    return Integer.toString(uniqueCode);
  }

  @Override
  public String movePlayerWithCoordinates(String playerName, int row, int col) {
    log.append("Input: " + playerName + " " + row + " " + col + "\n");
    return Integer.toString(uniqueCode);
  }

  @Override
  public int getTargetHealth() {
   
    return uniqueCode;
  }

}
