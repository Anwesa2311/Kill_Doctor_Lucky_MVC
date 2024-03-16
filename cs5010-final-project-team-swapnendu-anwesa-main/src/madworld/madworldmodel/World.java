package madworld.madworldmodel;

import java.awt.image.BufferedImage;
import java.util.List;
import madworld.madworldmodel.Player.PlayerType;

/**
 * World interface declaration.
 *
 */
public interface World extends ReadOnlyWorld {

  /**
   * get the number of rows a world can contain.
   * 
   * 
   * @return the number of rows.
   */

  public int getWorldRows();

  /**
   * get the number of columns a world can contain.
   *
   * @return the number of columns.
   */

  public int getWorldColumns();

  /**
   * Get the name of the target character of a world.
   *
   * @return the name of the target char.
   */
  public String getTargetChar();

  /**
   * Get the world name of a particular world.
   *
   * @return the world name.
   */
  public String getWorldName();

  /**
   * Move the target character to the next position.
   *
   */
  public void moveTargetChar();

  /**
   * Get total number of rooms in a world.
   *
   * @return total rooms.
   */

  public int getTotalRooms();

  /**
   * Get current position of the target character.
   *
   * @return the current position.
   */

  public int getCurrentPos();

  /**
   * Get the target health.
   *
   * @return the target health.
   */

  public int targetHealth();

  /**
   * Display info of neighbors of a room.
   *
   * @param roomIndex as a parameter
   *
   */

  public void displayNeighborDetails(int roomIndex);

  /**
   * Display map of the world.
   *
   * @param image as a parameter
   */

  public void displayMap(BufferedImage image);

  /**
   * add a new player to a game.
   *
   * @param playerName as a parameter
   * @param type       as a parameter
   * @param position   as a parameter
   *
   * @return the result of this action.
   */

  public String addPlayer(String playerName, PlayerType type, int position);

  // public String displayPlayer(String playerName);

  /**
   * to lookaround a specific space a player is in.
   *
   * @param playerName as a parameter
   *
   * @return the the lookaround details
   */

  String lookaround(String playerName);

  /**
   * to move a specific player to its neighbouring space.
   *
   * @param playerName as a parameter
   * @param roomname   as a parameter
   *
   * @return the result of this action
   */

  public String moveplayer(String playerName, String roomname);

  /**
   * to let the human player pick items from a place.
   *
   * @param playerName as a parameter
   * @param itemName   as a parameter
   * 
   * @return the result of this action.
   */

  String pickItemsPlayer(String playerName, String itemName);

  /**
   * to get the current player turn.
   *
   * @return the current turn
   */

  String getTurn();

  /**
   * to add a computer player.
   *
   * @param playername as a parameter
   * @param pos        as a parameter
   *
   * @return the result of this action.
   * 
   */

  String addComputerPlayer(String playername, int pos);

  /**
   * to let the computer player lookaround the place.
   *
   * @param playername as a parameter
   * 
   * @return the lookaround details
   */

  String computerlookaround(String playername);

  /**
   * to let the computer player move to its neighboring place.
   *
   * @param playername the name of the player as a parameter
   * @param randomFlag a radonmFlag to decide the randomness as a parameter
   * @param rand       a specified fixed value as a parameter.
   * 
   * @return a String containing the result of this action.
   */

  String moveComputerPlayer(String playername, boolean randomFlag, int rand);

  /**
   * to let the computer player pick items from a place.
   *
   * @param playername the name of the player as a parameter
   * @param randomFlag a radonmFlag to decide the randomness as a parameter
   * @param rand       a specified fixed value for testing as a parameter.
   *
   * @return a String containing the result of this action.
   */

  String pickitemComputerPlayer(String playername, boolean randomFlag, int rand);

  /**
   * to get the total no of turns of a game.
   *
   * @return the total no of turns
   */

  int getNoOfTurns();

  /**
   * to get if the game is over.
   * 
   * @return true or false based on the result
   */

  boolean isGameOver();

  /**
   * to get if the player is computer or not.
   * 
   * @param playerName player name
   *
   * @return true or false based on the result
   */

  boolean isComputer(String playerName);

  /**
   * This functionality is for moving a pet by a human player.
   * 
   * @param playerName player name
   * @param nextPos    the next position to move the pet to
   *
   * @return a string showing a result for this action.
   */

  String movePetForPlayers(String playerName, int nextPos);

  /**
   * This functionality is for getting the target information in the world.
   * 
   * @return a string showing the information of the target in the world.
   */
  public String getTargetInfo();

  /**
   * This functionality is for attacking a target by a human player.
   * 
   * @param playerName as parameter
   * @param itemName   the name of the item to attack as a parameter
   *
   * @return the result of the attack as a string
   */
  public String attackTargetForPlayers(String playerName, String itemName);

  /**
   * This functionality is to get the winner of the game.
   * 
   *
   *
   * @return the winner name as a string.
   */
  public String getWinner();

  /**
   * This functionality is to get the details of target in the world.
   * 
   *
   *
   * @return the details of target in string.
   */

  public String showTarget();

  /**
   * This functionality is to poke in the eye of target by the human player.
   * 
   * @param playerName as a parameter.
   *
   * @return the details of this action.
   */

  public String pokeInEye(String playerName);

  /**
   * This functionality is to attack target by a computer player.
   * 
   * @param playerName as a parameter.
   *
   * @return the details of this action.
   */

  String attackComputerPlayers(String playerName);

  /**
   * This functionality is to chose an action by the computer player.
   * 
   * @param playerName as a parameter.
   *
   * @return the details of this action.
   */

  String actionForComputer(String playerName);

  /**
   * This functionality is to get the list of evidences.
   * 
   *
   *
   * @return the evidence list.
   */

  String getEvidenceList();

  /**
   * This functionality is to get the details of the pet in the world.
   * 
   *
   *
   * @return the details of the pet in string.
   */

  String showPet();

  /**
   * This functionality is to get the path of the pet. it will follow from a root
   * following dfs algorithm.
   * 
   *
   * @param root the root node of the tree. It is the current location of the pet
   *             at the start of a path.
   * @return the list of room nums(in String) as a path.
   */

  String getPathforPetDfS(int root);

  /**
   * Function to find the total number of players currently in the game.
   * 
   * @return The total number of players currently in the game.
   */
  int getTotalCurrentPlayers();

  /**
   * Function to find the details like room dimensions, name of a room, room
   * number assigned to a room, list of items in a room, and total number of rooms
   * in the current game world.
   * 
   * @return ArrayList containing list with the details of the room.
   */
  public List<Space> getRoomDetails();

  /**
   * Method to determine the current location of a player.
   * 
   * @return The room number the player is in.
   */
  public int getCurrentLocationPlayer();

  /**
   * Method to determine the maximum number of turns in the game.
   * 
   * @return the maximum number of turns to be played in the game.
   */
  int getMaxTurns();

  // String displayPlayer(String playerName);
  /**
   * Method to determine the type of player who is to play the current turn.
   * 
   * @return The type of player PlayerType (Computer controlled or Human
   *         controlled)
   */
  PlayerType getPlayerTypeCurrentTurn();

  // String displayPlayerForController();

  /**
   * Method to determine and display the details of a specific player, which is to
   * be then displayed by the controller.
   *
   * @param playerName Name of the player whose details are to be displayed
   * @return The details of the specified player in String format
   */
  String displayPlayerForController(String playerName);

  /**
   * Method to display the result when attempting to move a player from one room
   * to another room using the moveplayer function.
   * 
   * @param playerName Name of the player attempting the move
   * @param row        Row coordinates of the move destination
   * @param col        Column coordinates of the move destination
   * @return The result of the attempted move
   */
  String movePlayerWithCoordinates(String playerName, int row, int col);

  /**
   * Method to determine the current location of the target character.
   * 
   * @return The room number of the current location of the target character
   */
  public int getTargetLocation();

  /**
   * Method to return the health of the target.
   * 
   * @return The remaining health of the target
   */
  int getTargetHealth();

}
