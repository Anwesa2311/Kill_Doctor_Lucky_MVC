
package madworld.madworldmodel;

import java.util.Map;

/**
 * Item interface decleration.
 *
 */
public interface Item {

  /**
   * Get the room number that currently has this item.
   *
   * @return the room num.
   */
  public int getItemRoom();

  /**
   * Get the item name.
   *
   * @return the item name.
   */
  public String getItemName();

  /**
   * Get the item damage.
   *
   * @return the item damage.
   */
  public int getItemDamage();

  // public int getNoOfItems();

}
