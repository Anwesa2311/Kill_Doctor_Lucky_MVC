package madworld.madworldmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import madworld.madworldmodel.Player.PlayerType;

/**
 * Implementation of computer player class which represents computer player
 * attributes a player. can perform a lot of functionalities such as
 * move,lookaround, pick an item on its own.
 * 
 */
public class ComputerPlayerModel implements Player {

  private final String playerName;
  private final PlayerType type;
  private final int totalRoom;
  private final int size;
  private int position;
  private int maxItem;
  private boolean isMovePlayer;
  private boolean isPickItem;
  private boolean isLookAround;
  private List<String> items;
  private boolean attackTarget;
  private boolean movePet;

  /**
   * ComputerPlayerModel constructor to get values for the below parameters.
   *
   * @param playerName The name of a player
   * @param type       The type of a player
   * @param totalRoom  The total number of rooms
   * @param size       The total item size
   * @param pos        The pos to add the computer player
   *
   * 
   * @throws IllegalArguement exception for the following criteria:- Playername
   *                          should not be blank or null PlayerType should be
   *                          COMPUTER totalRoom & size should not be negative
   * 
   * 
   */

  public ComputerPlayerModel(String playerName, PlayerType type, int totalRoom, int size, int pos) {

    if (playerName == null || ("").equals(playerName)) {
      throw new IllegalArgumentException("Player Name can not be blank");
    }
    if (type != PlayerType.COMPUTER || type == null) {
      throw new IllegalArgumentException("Invalid player type");
    }
    if (totalRoom < 0 || size < 0) {
      throw new IllegalArgumentException("Invalid totalRoom or size");
    }
    if (pos < 0 || pos >= totalRoom) {
      throw new IllegalArgumentException("Invalid position");
    }
    this.playerName = playerName;
    this.type = type;
    this.totalRoom = totalRoom;
    this.size = size;
    this.position = pos;
    this.maxItem = 0;
    this.isMovePlayer = false;
    this.isLookAround = false;
    this.isPickItem = false;
    this.items = new ArrayList();
    this.attackTarget = false;
    this.movePet = false;
    // choseRandomRoom();
    choseMaxItemToCarry();

  }

  private void choseRandomRoom() {
    Random rand = new Random();
    int rommNo = rand.nextInt(totalRoom);
    this.position = rommNo;
  }

  private void choseMaxItemToCarry() {
    Random rand = new Random();
    int maxItem = rand.nextInt(size) + 2;
    this.maxItem = maxItem;
  }

  @Override
  public void movenextRoom(String s, boolean randomFlag, int rand1) {

    if (("").equals(s) || s == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (!(randomFlag == true || randomFlag == false)) {
      throw new IllegalArgumentException("Invalid random flag");

    }
    if (rand1 < 0) {
      throw new IllegalArgumentException("Invalid input");
    }

    String[] str;
    s = s.substring(1, s.length() - 1);
    str = s.split(",");

    for (int i = 0; i < str.length; i++) {
      str[i] = str[i].strip();
    }
    int nextRoomNum = 0;
    if (randomFlag == true) {
      Random rand = new Random();
      int nextRandomNum = rand.nextInt(str.length);
      nextRoomNum = Integer.parseInt(str[nextRandomNum]);
    } else {
      nextRoomNum = Integer.parseInt(str[rand1]);
    }
    if (this.position != nextRoomNum) {
      this.position = nextRoomNum;
      this.isMovePlayer = true;
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
    if (roomItem != null) {
      String[] str;
      String[] str1;
      int nextroomitem = 0;
      roomItem = roomItem.substring(1, roomItem.length() - 1);
      str = roomItem.split(",");
      List<String> computeritems = new ArrayList<String>(Arrays.asList(roomItem.split(",")));
      if (randomFlag == true) {
        Random rand = new Random();
        nextroomitem = rand.nextInt(str.length);
      } else {
        nextroomitem = rand1;
      }
      // if(this.items.size())
      this.items.add(str[nextroomitem]);
      this.isPickItem = true;
      return str[nextroomitem];
    } else {
      throw new IllegalArgumentException("Room items can not be null");
    }
  }

  @Override
  public String getItems() {
    return this.items.toString();
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

    return this.position;
  }

  @Override
  public int getMaxItemscarry() {

    return this.maxItem;
  }

  // @Override
  // public List<String> getItems() {

  // return null;
  // }

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
  public String toString() {

    if (this.items.size() > 0) {
      return String.format(
          "player name = %s, player type = %s, " + "player position = %s, items = %s ",
          this.playerName, this.type.toString(), this.position, this.items);
    } else {
      return String.format(
          "player name = %s\nplayer type = %s\n"
              + "player position =room num %s\nitems = No items\n ",
          this.playerName, this.type.toString(), this.position);
    }
  }

  @Override
  public void removeItem(String item3) {
    for (int i = 0; i < this.items.size(); i++) {
      if (items.get(i).equals(item3.substring(1, item3.length() - 1))) {
        this.items.remove(i);
      }

    }

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

    if (playerName == null || ("").equals(playerName)) {
      throw new IllegalArgumentException("playerName can not blank");
    }
    if (roomno < 0) {
      throw new IllegalArgumentException("Invalid item damage");
    }
    if (this.getPlayerType() == PlayerType.COMPUTER) {
      return;
    }
  }

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
    if (this.getPlayerType() == PlayerType.COMPUTER) {
      return;
    }

  }
}
