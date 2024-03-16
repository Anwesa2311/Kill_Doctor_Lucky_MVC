package madworld.madworldmodel;

import java.util.List;
import java.util.Map;

/**
 * The Space interface declaration.
 *
 */
public interface Space {

  /**
   * Get the room name in string.
   *
   * @return the room name.
   */

  public String getRoomName();

  /**
   * Get the room dimension in integer array.
   *
   * @return the room dimension.
   */
  public int[] getRoomDimension();

  /**
   * Get the room num in integer.
   *
   * @return the room num.
   */

  public int getRoomNum();

  // public boolean hasItem();

  // public void setnoOfItems();

  /**
   * Get the no of items of a particular room in integer.
   *
   * @return the number of items.
   */

  public int getnoOfItems();

  /**
   * Get the list of the items of a room stored in another list in an ordered
   * index. This list of List primarily has a list of rooms(room indexes) and each
   * roomIndex in the list has another list of items that the particular room in
   * the world currently has.
   *
   * @return the 2D list consisting items that all the rooms have in the world.
   */

  public List<List<Item>> getItems();

  /**
   * Show items of a particular room.
   * 
   * 
   * @param roomNum of a particular room
   * 
   * @return a stringBuffer containing the items of the room
   */
  public StringBuffer showItems(int roomNum);

  /**
   * Set players of a particular room.
   * 
   * 
   * @param player name as a parameter.
   * 
   * 
   */

  public void setPlayers(String player);

  /**
   * removes item of a particular room.
   * 
   * 
   * @param itemname of a particular room
   * 
   * 
   */

  public void removeItem(String itemname);

  /**
   * removes player of a particular room.
   * 
   * 
   * @param player of a particular room
   * 
   * 
   */

  void removePlayers(String player);

  // public void setNeighbor(List<Space> neighbor);

  /**
   * adds a pet to a particular room.
   * 
   * 
   * @param petname as a parameter.
   * 
   * 
   */

  void addPet(String petname);

  /**
   * removes a pet of a particular room.
   * 
   * 
   * @param petname as a parameter.
   * 
   * 
   */
  void removePet(String petname);

  /**
   * checks if a room has a pet or not.
   * 
   * 
   * @return true or false based on the result.
   * 
   * 
   */

  boolean getHasPet();

  /**
   * Add a target to a particular room..
   * 
   * @param targetName   the name of the target.
   * @param targetHealth the health of a target.
   * 
   */

  void addTarget(String targetName, int targetHealth);

  /**
   * checks if a room has a target or not.
   * 
   * 
   * @return true or false based on the result.
   * 
   * 
   */

  boolean getTarget();

  /**
   * removes a target of a particular room.
   * 
   * 
   */
  void removeTarget();

  /**
   * get the players of a particular room.
   * 
   * @return a list of string containing all the players of a room.
   */

  String getPlayers();

}
