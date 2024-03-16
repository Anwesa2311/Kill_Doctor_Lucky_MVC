package madworld.madworldmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of player class which represents player attributes a player
 * can perform a lot of functionalities such as move,lookaround, pick an item
 * etc.
 */
public class PlayerImpModel implements Player {

  private final String playerName;
  private final PlayerType type;
  private int playerPosition;
  private List<String> items;
  private final int maxItems;
  private boolean isLookAround;
  private boolean isMovePlayer;
  private boolean isPickItem;
  private int totalRooms;
  private int totalItems;
  private boolean movePet;
  private boolean attackTarget;

  /**
   * PlayerImpModel constructor to get values for the below parameters.
   *
   * @param playerName      The name of a player
   * @param type            The type of a player
   * @param playerPosition  The position of the player
   * @param maxItemsToCarry The max items player will carry
   * @param totalRooms      total number of rooms
   * @param totalitems      total number of items.
   *
   * 
   * @throws IllegalArguement exception for the following criteria:- Playername
   *                          should not be blank or null PlayerType should be
   *                          HUMAN PlayerPosition should not be negative or
   *                          greater than total rooms maxItemsCarry should not be
   *                          negative or greater than total items
   * 
   */

  public PlayerImpModel(String playerName, PlayerType type, int playerPosition, int maxItemsToCarry,
      int totalRooms, int totalitems) {
    // if(playerName )

    // Exceptions to write(Important) ********* Do NOT FORGET

    if ((playerName == null) || ("").equals(playerName)) {
      throw new IllegalArgumentException("player Name can not be blank or null");
    }

    if (type == null || type != PlayerType.HUMAN) {
      throw new IllegalArgumentException("Invalid Type");
    }
    if (playerPosition < 0 || playerPosition >= totalRooms) {
      throw new IllegalArgumentException("Invalid player position");
    }
    if (maxItemsToCarry < 0 || maxItemsToCarry > totalitems) {
      throw new IllegalArgumentException("Invalid max items to carry");
    }

    this.playerName = playerName;
    this.type = type;
    this.playerPosition = playerPosition;
    this.items = new ArrayList<String>();
    this.maxItems = maxItemsToCarry;
    this.isLookAround = false;
    this.isMovePlayer = false;
    this.isPickItem = false;
    this.totalRooms = totalRooms;
    this.totalItems = totalitems;
    this.movePet = false;
    this.attackTarget = false;
  }

  @Override
  public void removeItem(String itemName) {
    if ((itemName == null) || ("").equals(itemName)) {
      throw new IllegalArgumentException("Invalid item name");
    }
    for (int i = 0; i < this.items.size(); i++) {
      String item = itemName.substring(1, itemName.length() - 1);
      if (items.get(i).equals(itemName.substring(1, itemName.length() - 1))) {
        this.items.remove(i);
      }
    }
  }

  @Override
  public String getPlayerName() {
    return this.playerName;

  }

  @Override
  public PlayerType getPlayerType() {
    return this.type;

  }

  @Override
  public int getPlayerPosition() {
    return this.playerPosition;

  }

  @Override
  public String getItems() {
    // return new ArrayList<String>(this.items);
    return this.items.toString();

  }

  @Override
  public boolean isMovePlayer() {
    return this.isMovePlayer;
  }

  @Override
  public boolean isPickItem() {
    return this.isPickItem;
  }

  @Override
  public boolean isLookAround() {
    return this.isLookAround;
  }

  @Override
  public void setLookAround() {
    this.isLookAround = true;
  }

  @Override
  public void setMovePet() {
    this.movePet = true;
  }

  @Override
  public void setAttackTarget() {
    this.attackTarget = true;
  }

  @Override
  public boolean isAttackTarget() {
    return this.attackTarget;
  }

  @Override
  public boolean isMovePet() {
    return this.movePet;
  }

  @Override
  public void movePlayer(String playerName, int roomno) {
    if (this.playerName.equals(playerName) && this.playerPosition != roomno) {
      this.playerPosition = roomno;
      this.isMovePlayer = true;
    } else {
      throw new IllegalArgumentException(
          "Invalid PlayerName or the player already exists in that room");
    }
  }

  /**
   * Implements the functionality to let a player pick an item..
   * 
   * @param playerName takes the player name
   * @param itemName   takes the itemname
   * 
   */
  @Override
  public void pickitems(String playerName, String itemName, int itemDamage) {

    if (playerName == null || ("").equals(playerName)) {
      throw new IllegalArgumentException("playerName can not blank");
    }

    if (itemName == null || ("").equals(itemName)) {
      throw new IllegalArgumentException("Invalid item Name");
    }
    if (itemDamage < 0) {
      throw new IllegalArgumentException("Invalid item damage");
    }

    if (this.playerName.equals(playerName) && this.items.size() < this.maxItems) {
      String dam = Integer.toString(itemDamage);
      String itemMod = itemName + ":" + dam;
      this.items.add(itemMod);
      this.isPickItem = true;
    } else {
      throw new IllegalArgumentException("Invalid player or the palyer can not carry more items");
    }

  }

  @Override
  public String toString() {

    if (this.items.size() > 0) {
      return String.format(
          "player name = %s, player type = %s, " + "player position = %s, items = %s ",
          this.playerName, this.type.toString(), this.playerPosition, this.items);
    } else {
      return String.format(
          "player name = %s\nplayer type = %s\n"
              + "player position =room num %s\nitems = No items\n ",
          this.playerName, this.type.toString(), this.playerPosition);
    }
  }

  @Override
  public int getMaxItemscarry() {

    return this.maxItems;
  }

  @Override
  public void movenextRoom(String s, boolean randomFlag, int rand1) {
    if (!(randomFlag == true || randomFlag == false)) {
      throw new IllegalArgumentException("Invalid random flag");

    }
    if (rand1 < 0) {
      throw new IllegalArgumentException("Invalid input");

    }
    if (s == null) {
      throw new IllegalArgumentException("Invalid input");
    }

    if (this.getPlayerType() == PlayerType.HUMAN) {
      return;
    }

  }

  @Override
  public String pickItems(String roomItem, boolean randomFlag, int rand1) {
    if (!(randomFlag == true || randomFlag == false)) {
      throw new IllegalArgumentException("Invalid random flag");

    }
    if (rand1 < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (roomItem == null) {
      throw new IllegalArgumentException("Invalid room Item");
    }
    if (this.getPlayerType() == PlayerType.HUMAN) {
      return null;
    }
    return null;

  }
}
