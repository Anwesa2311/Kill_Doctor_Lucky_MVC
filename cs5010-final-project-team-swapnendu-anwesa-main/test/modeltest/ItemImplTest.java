package modeltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import madworld.madworldmodel.Item;
import madworld.madworldmodel.ItemImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for testing the Item class.
 */

public class ItemImplTest {

  private Item item;

  /**
   * Creating item instance.
   */
  @Before
  public void setUp() {

    item = itemHelper("Crepe Pan", 8, 3, 21);

  }

  /**
   * Test method for {@link madworld.madworldmodel.ItemImpl#getItemRoom()}.
   */
  @Test
  public void testGetItemRoom() {
    int expected = 8;
    assertEquals(expected, item.getItemRoom());
  }

  /**
   * Test method for {@link madworld.madworldmodel.ItemImpl#getItemName()}.
   */
  @Test
  public void testGetItemName() {
    String expected = "Crepe Pan";
    assertEquals(expected, item.getItemName());
  }

  /**
   * Test method for {@link madworld.madworldmodel.ItemImpl#getItemDamage()}.
   */
  @Test
  public void testGetItemDamage() {
    int expected = 3;
    assertEquals(expected, item.getItemDamage());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomNum() {

    itemHelper("Crepe Pan", -4, 3, 21);
    itemHelper("Crepe Pan", 22, 3, 21);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItemDamage() {

    itemHelper("Crepe Pan", 4, -3, 21);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidItemName() {

    itemHelper(" ", 4, 3, 21);
    itemHelper("123456", 4, 3, 21);

  }

  @Test
  public void testToString() {
    String expected = "ItemName = Crepe Pan\n" + "ItemDamage = 3";
    assertEquals(expected, item.toString());
  }

  /**
   * A helper method to instantiate itemImpl class.
   */
  protected Item itemHelper(String itemName, int roomNum, int itemDamage, int totalRooms) {
    return new ItemImpl(itemName, roomNum, itemDamage, totalRooms);

  }
}
