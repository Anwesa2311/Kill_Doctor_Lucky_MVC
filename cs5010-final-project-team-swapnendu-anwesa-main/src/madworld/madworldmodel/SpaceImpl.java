package madworld.madworldmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of Space which implements each rooms in a world and it also
 * shows the items in each room.
 */
public class SpaceImpl implements Space {

  // private static final Object Null = null;
  private final int leftrow;
  private final int rightrow;
  private final int leftcolumn;
  private final int rightcolumn;
  private final int roomNum;
  private final String roomName;
  private int[] roomDimension;
  private final List itemList;
  private int noOfItems;
  private final int totalRooms;
  private List<Space> neighbor;
  private List<List<Item>> list1;
  private List<List<Item>> list2;
  private List<String> players;
  private StringBuffer str1;
  private String petname;
  private boolean hasPet;
  private String targetName;
  private int targetHealth;
  private boolean hasTarget;

  /**
   * SpaceImpl constructor to get values for the below parameters.
   *
   * @param leftrow     The left row coordinate of the room
   * @param rightrow    The right row coordinate of the room
   * @param leftcolumn  The left column row coordinate of the room
   * @param rightcolumn The right column coordinate of the room
   * @param roomNum     The room num of the space
   * @param itemList    The itemlist of the world
   * @param totalRoom   Total number of rooms
   * @param roomName    The name of the room
   * 
   * @throws IllegalArguement exception for the following criteria:- Room Name can
   *                          not contain number. Room Name can not be blank. Room
   *                          Num can not be negative or greater than total rooms.
   *                          Room coordinates can not be negative and should be
   *                          valid.
   * 
   */
  public SpaceImpl(int leftrow, int leftcolumn, int rightrow, int rightcolumn, String roomName,
      int roomNum, List itemList, int totalRoom) {

    for (int i = 0; i < roomName.length(); i++) {
      if (Character.isDigit(roomName.charAt(i))) {
        throw new IllegalArgumentException(
            "Room name can not contain number.Enter a valid room name");
      }

    }

    if ((" ").equals(roomName) || roomName == null) {
      throw new IllegalArgumentException("Room name can not be blank");
    }

    // if below conditions dont match it will throw exception//
    if ((roomNum < 0) || (roomNum > totalRoom)) {
      throw new IllegalArgumentException("Invalid room num");
    }
    if ((leftrow < 0) || (leftrow < 0) || (rightrow < 0) || (rightcolumn < 0)) {
      throw new IllegalArgumentException("Invalid room coordinates");
    }

    this.leftrow = leftrow;
    this.leftcolumn = leftcolumn;
    this.rightrow = rightrow;
    this.rightcolumn = rightcolumn;
    this.roomName = roomName;
    this.roomNum = roomNum;
    this.roomDimension = new int[4];
    this.roomDimension[0] = this.leftrow;
    this.roomDimension[1] = this.leftcolumn;
    this.roomDimension[2] = this.rightrow;
    this.roomDimension[3] = this.rightcolumn;
    this.itemList = itemList;
    this.neighbor = new ArrayList<>();
    this.totalRooms = totalRoom;
    // this.noOfItems = itemList.size();
    this.list1 = new ArrayList<List<Item>>(totalRooms);
    this.neighbor = new ArrayList<Space>();
    list2 = createItem();
    List<Item> item1 = list2.get(roomNum);
    this.noOfItems = item1.size();
    this.players = new ArrayList();
    this.str1 = new StringBuffer();
    this.petname = "";
    this.hasPet = false;
    this.targetName = "";
    this.targetHealth = 0;
    this.hasTarget = false;
  }

  @Override
  public String getRoomName() {

    return this.roomName;
  }

  @Override
  public int[] getRoomDimension() {

    return this.roomDimension.clone();
  }

  @Override
  public int getRoomNum() {
    return this.roomNum;
  }

  @Override
  public int getnoOfItems() {

    return this.noOfItems;
  }

  @Override
  public List<List<Item>> getItems() {
    return new ArrayList<List<Item>>(list1);
  }

  @Override
  public void setPlayers(String player) {

    if (player == null || "".equals(player)) {
      throw new IllegalArgumentException("itemname can not be null or blank");
    }

    this.players.add(player);
  }

  @Override
  public void removePlayers(String player) {
    if (player == null || "".equals(player)) {
      throw new IllegalArgumentException("itemname can not be null or blank");
    }
    if (this.players.contains(player)) {
      for (int i = 0; i < this.players.size(); i++) {

        if (this.players.get(i).equals(player)) {
          // list1.remove(list1.get(this.roomNum).get(i));
          players.remove(i);

          // System.out.println(list1);

        }
      }
    }
  }

  private List<List<Item>> createItem() {
    List itemspec = this.itemList;

    for (int i = 0; i < totalRooms; i++) {
      list1.add(new ArrayList());
    }

    for (int i = 0; i < itemspec.size(); i++) {
      String str1 = (String) itemspec.get(i);
      String[] str2 = str1.split(" ", 3);
      int itemRoomNum = Integer.parseInt(str2[0]);
      int itemdamage = Integer.parseInt(str2[1]);
      String itemName = str2[2];
      Item item1;
      if (this.getRoomNum() == itemRoomNum) {
        item1 = new ItemImpl(itemName, itemRoomNum, itemdamage, totalRooms);

        list1.get(itemRoomNum).add(item1);
      }

    }
    return list1;
  }

  @Override
  public void removeItem(String itemname) {
    if (itemname == null || "".equals(itemname)) {
      throw new IllegalArgumentException("itemname can not be null or blank");
    }
    final List<List<Item>> tempList = getItems();
    final List<Item> tempitems = tempList.get(this.roomNum);
    // list1.remove(this.roomNum);
    boolean isremove = false;
    for (int i = 0; i < tempitems.size(); i++) {

      if (tempitems.get(i).getItemName().equals(itemname)) {
        // list1.remove(list1.get(this.roomNum).get(i));
        list1.get(this.roomNum).remove(i);

        // System.out.println(list1);
        isremove = true;
      }

      // list1.add(tempitems);
    }
    if (isremove = false) {
      throw new IllegalArgumentException("The item does not exist in the room");
    }
  }

  @Override
  public StringBuffer showItems(int roomNum) {
    // List<List<Item>> list2 = createItem();
    this.str1 = new StringBuffer();

    if (roomNum < 0 || roomNum >= totalRooms) {
      throw new IllegalArgumentException("Invalid room number");
    }

    List<Item> item = list1.get(roomNum);
    this.noOfItems = item.size();
    if (item.size() == 0) {
      str1.append("No Items in this room");
    } else {
      for (int i = 0; i < item.size(); i++) {

        // System.out.println("item name is:-" + item.get(i).getItemName());
        // System.out.println("item damage is:-" + item.get(i).getItemDamage());
        String str2 = item.get(i).toString();
        str1.append(String.format("%s\n", str2));

      }
    }
    return str1;

  }

  private void setNeighbor(List<Space> neighbor) {

    this.neighbor = neighbor;

  }

  @Override
  public String toString() {

    StringBuffer str = new StringBuffer();
    str = this.showItems(roomNum);
    String pet;
    String target;
    if (("").equals(this.petname)) {
      pet = "No pet";
    } else {
      pet = this.petname;
    }
    if (("").equals(this.targetName)) {
      target = "No target";
    } else {
      target = this.targetName;
    }
    List<String> str1 = this.players;

    if (str1.isEmpty() && ("").equals(pet) && ("").equals(target)) {
      return String.format(
          "roomNum = %s\nroomName = %s\nroomDimension = %s, %s, %s, %s\n"
              + "%s\nno players\npet=%s\ntarget=%s\n",
          this.roomNum, this.roomName, this.leftrow, this.leftcolumn, this.rightrow,
          this.rightcolumn, str.toString(), pet, target);
    } else {
      if (str1.isEmpty()) {
        return String.format(
            "roomNum = %s\nroomName = %s\nroomDimension = %s, %s, %s, %s\n"
                + "%s\nno players\npet=%s\ntarget=%s\n",
            this.roomNum, this.roomName, this.leftrow, this.leftcolumn, this.rightrow,
            this.rightcolumn, str.toString(), pet, target);
      } else if (("").equals(this.petname)) {
        return String.format(
            "roomNum = %s\nroomName = %s\nroomDimension = %s, %s, %s, %s\n"
                + "%s\nthe players are\n%s\npet=%s\ntarget=%s\n",
            this.roomNum, this.roomName, this.leftrow, this.leftcolumn, this.rightrow,
            this.rightcolumn, str.toString(), str1.toString(), pet, target);
      } else if (("").equals(this.targetName)) {
        return String.format(
            "roomNum = %s\nroomName = %s\nroomDimension = %s, %s, %s, %s\n"
                + "%s\nthe players are\n%s\npet=%s\ntarget=%s\n",
            this.roomNum, this.roomName, this.leftrow, this.leftcolumn, this.rightrow,
            this.rightcolumn, str.toString(), str1.toString(), pet, target);
      } else {
        return String.format(
            "roomNum = %s\nroomName = %s\nroomDimension = %s, %s, %s, %s\n"
                + "Below are the roomitems\n%s\nthe players are\n%s\npet=%s\ntarget=%s\n" + "",
            this.roomNum, this.roomName, this.leftrow, this.leftcolumn, this.rightrow,
            this.rightcolumn, str.toString(), str1.toString(), pet, target);
      }

    }

    // public List<Integer> getNeighborList()

    // {

    // }
  }

  @Override
  public void addPet(String petname) {
    if ("".equals(petname) || petname == null) {
      throw new IllegalArgumentException("Invalid petName");
    }

    this.petname = petname;
    hasPet();

  }

  @Override
  public void removePet(String petname) {
    if (("").equals(petname) || petname == null) {
      throw new IllegalArgumentException("petname can not be null or blank");
    }
    this.petname = "";
    hasPet();

  }

  private void hasPet() {
    if (!("").equals(petname)) {
      this.hasPet = true;
    } else {
      this.hasPet = false;
    }
  }

  @Override
  public boolean getHasPet() {

    return this.hasPet;
  }

  @Override
  public void addTarget(String targetName, int targetHealth) {

    if (("").equals(targetName) || targetName == null) {
      throw new IllegalArgumentException("Invalid target name");
    }

    if (targetHealth < 0) {
      throw new IllegalArgumentException("Invalid target health");
    }
    this.targetName = targetName;
    this.targetHealth = targetHealth;
    hasTarget();
  }

  @Override
  public void removeTarget() {
    if (!("").equals(this.targetName)) {
      this.targetName = "";
    } else {
      throw new IllegalArgumentException("Target does not exist in the room");
    }
    hasTarget();

  }

  private boolean hasTarget() {
    if (!("").equals(this.targetName)) {
      this.hasTarget = true;
    } else {
      this.hasTarget = false;
    }
    return this.hasTarget;
  }

  @Override
  public boolean getTarget() {
    return this.hasTarget;
  }

  @Override
  public String getPlayers() {

    return this.players.toString();
  }

}
