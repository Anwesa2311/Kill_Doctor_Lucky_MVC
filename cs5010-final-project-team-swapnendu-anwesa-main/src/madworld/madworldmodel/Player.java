package madworld.madworldmodel;

import java.util.List;

/**
 * Player interface declaration.
 *
 */

public interface Player {

  /**
   * player type that can be used.
   */

  enum PlayerType {
    /**
     * the playertype human.
     */
    HUMAN,
    /**
     * the player type computer.
     */

    COMPUTER
  }

  /**
   * to get the player name.
   *
   * @return the player name.
   */

  public String getPlayerName();

  /**
   * to get the player type.
   *
   * @return the player type.
   */

  public PlayerType getPlayerType();

  /**
   * to get the player position.
   *
   * @return the player position.
   */
  public int getPlayerPosition();
  // public void addItems();
  // public List<String> getItems();

  /**
   * to get the maximum no of items a player can carry with him/her.
   *
   * @return the maximum no of items a player can carry.
   */
  public int getMaxItemscarry();

  // void movePlayer(String playerName, int roomno);
  // void pickitems(String playerName, String itemName);
  /**
   * to check if a player has moved or not..
   *
   * @return true or false based on the result..
   */

  public boolean isMovePlayer();

  /**
   * to check if a player has picked an item or not..
   *
   * @return true or false based on the result..
   */
  public boolean isPickItem();

  /**
   * to check if a player has looked around or not.
   *
   * @return true or false based on the result..
   */
  public boolean isLookAround();

  /**
   * to set the lookaround flag.
   *
   *
   */

  public void setLookAround();

  // public void removeItem(String itemName);
  /**
   * to get all the item names that a player currently has.
   *
   * @returns a list of string containing all the item names.
   */

  public String getItems();

  /**
   * to set the MovePet flag.
   *
   *
   */

  void setMovePet();

  /**
   * to set the AttackTarget flag.
   *
   *
   */
  void setAttackTarget();

  /**
   * to check if a player has attacked the target or not.
   *
   * @return true or false based on the result..
   */
  boolean isAttackTarget();

  /**
   * to check if a player has moved the pet or not.
   *
   * @return true or false based on the result..
   */
  boolean isMovePet();

  /**
   * Implements the functionality to move a computer player..
   * 
   * @param s          represents the neighbors of a current room.
   * @param randomFlag represents a flag which will decide the randomness of the
   *                   computer player.
   * @param rand1      represents a predictive value passed to the computer player
   *                   for testing.
   */
  void movenextRoom(String s, boolean randomFlag, int rand1);

  /**
   * Implements the functionality to let a computer player pick an item..
   * 
   * @param roomItem   represents current items of a room
   * @param randomFlag represents a flag which will decide the randomness of the
   *                   computer player.
   * @param rand1      represents a predictive value passed to the computer player
   *                   for testing.
   * @return the item as a string that the computer has picked.
   * 
   */
  String pickItems(String roomItem, boolean randomFlag, int rand1);

  /**
   * Implements the functionality to move a player..
   * 
   * @param playerName takes the player name
   * @param roomno     takes the roomno to move the player to.
   */
  void movePlayer(String playerName, int roomno);

  /**
   * Implements the functionality to let a player pick an item..
   * 
   * @param playerName takes the player name
   * @param itemName   takes the itemname to pick.
   * @param itemDamage takes the damage of an item.
   */
  void pickitems(String playerName, String itemName, int itemDamage);

  /**
   * Implements the functionality to remove an item that a player currently has.
   * 
   * @param item3 takes the item details concatenated as a string.
   *
   */
  void removeItem(String item3);

}
