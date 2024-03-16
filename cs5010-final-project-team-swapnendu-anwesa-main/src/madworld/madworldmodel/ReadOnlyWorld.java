package madworld.madworldmodel;

import java.util.List;
import madworld.madworldmodel.Player.PlayerType;

/**
 * Interface to allow the view to access some information from the model,
 * without allowing it to modify the existing model, by effectively rendering it
 * read-only to the view.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public interface ReadOnlyWorld {

  /**
   * get the number of rows a world can contain.
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
   * to lookaround a specific space a player is in.
   *
   * @param playerName as a parameter
   *
   * @return the the lookaround details
   */

  String lookaround(String playerName);

  /**
   * to get the current player turn.
   *
   * @return the current turn
   */

  String getTurn();

  /**
   * to let the computer player lookaround the place.
   *
   * @param playername as a parameter
   * 
   * @return the lookaround details
   */

  String computerlookaround(String playername);

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
   * This functionality is for getting the target information in the world.
   * 
   * @return a string showing the information of the target in the world.
   */
  public String getTargetInfo();

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
   * This functionality is to get the details of the pet in the world.
   * 
   *
   *
   * @return the details of the pet in string.
   */

  String showPet();

  /**
   * Function to find the details like room dimensions, name of a room, room
   * number assigned to a room, list of items in a room, and total number of rooms
   * in the current game world.
   * 
   * @return ArrayList containing list with the details of the room.
   */

  public List<Space> getRoomDetails();

  /**
   * Method to determine the current location of the target character.
   * 
   * @return The room number of the current location of the target character
   */

  public int getTargetLocation();

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

  /**
   * Method to determine and display the details of a specific player, which is to
   * be then displayed by the controller.
   *
   * @param playerName Name of the player whose details are to be displayed
   * @return The details of the specified player in String format
   */
  String displayPlayerForController(String playerName);

  /**
   * Method to return the health of the target.
   * 
   * @return The remaining health of the target
   */
  int getTargetHealth();

}
