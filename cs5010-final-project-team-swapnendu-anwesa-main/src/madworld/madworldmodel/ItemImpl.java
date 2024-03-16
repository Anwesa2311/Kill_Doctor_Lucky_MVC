package madworld.madworldmodel;

/**
 * Implementation of Item which implements each item in a room by taking
 * parameters such as item name,item damage,room num etc..
 */
public class ItemImpl implements Item {

  private final String itemName;
  private final int roomNum;
  private final int itemDamage;
  private final int totalRooms;

  /**
   * SpaceImpl constructor to get values for the below parameters.
   *
   * @param itemName   The name of the item.
   * @param roomNum    associated with the item.
   * @param itemDamage the damage an item can do
   * @param totalRooms the total number of rooms
   * 
   * 
   * @throws IllegalArgumentException for the following criteria:- Item Name can
   *                          not be blank or null. Room Num can not be negative
   *                          or greater than total rooms. Item Num can not be
   *                          negative.
   * 
   */

  public ItemImpl(String itemName, int roomNum, int itemDamage, int totalRooms) {

    for (int i = 0; i < itemName.length(); i++) {
      if (Character.isDigit(itemName.charAt(i))) {
        throw new IllegalArgumentException(
            "Item name can not contain number.Enter a valid item name");
      }

    }

    if ((" ").equals(itemName) || (itemName == null)) {
      throw new IllegalArgumentException("Item name can not be blank");
    }

    // if below conditions dont match it will throw exception//
    if ((roomNum < 0) || (roomNum > totalRooms)) {
      throw new IllegalArgumentException("Invalid room num");
    }
    if ((itemDamage < 0)) {
      throw new IllegalArgumentException("Invalid item Damage");
    }
    if (totalRooms < 0) {
      throw new IllegalArgumentException("Invalid total Rooms");
    }

    this.itemName = itemName;
    this.roomNum = roomNum;
    this.itemDamage = itemDamage;
    this.totalRooms = totalRooms;

  }

  @Override
  public int getItemRoom() {

    return this.roomNum;
  }

  @Override
  public String getItemName() {

    return this.itemName;
  }

  @Override
  public int getItemDamage() {

    return this.itemDamage;
  }

  // @Override
  // public int getNoOfItems() {

  // return 0;
  // }

  @Override
  public String toString() {

    return String.format("ItemName = %s\nItemDamage = %s", this.itemName, this.itemDamage);
  }

}
