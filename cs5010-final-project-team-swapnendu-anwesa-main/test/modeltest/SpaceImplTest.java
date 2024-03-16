package modeltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import madworld.madworldmodel.Item;
import madworld.madworldmodel.Space;
import madworld.madworldmodel.SpaceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for testing the Space class.
 */

public class SpaceImplTest {

  private List<String> itemList;
  private List<String> itemList1;
  private Space space;

  /**
   * Initializing all the variables.
   */
  @Before
  public void setUp() {
    space = spaceHelper(16, 21, 21, 28, "Billiard Room", 1, itemList, 4);
    itemList = Arrays.asList("0 3 Crepe Pan", "1 2 Letter Opener", "2 2 Shoe Horn",
        "3 3 Sharp Knife", "0 3 Revolver");
    itemList1 = Arrays.asList("0 3 Crepe Pan", "1 2 Letter Opener");

  }

  @Test
  public void testGetRoomName() {
    String expected = "Billiard Room";
    assertEquals(expected, space.getRoomName());
  }

  @Test
  public void testGetRoomDimension() {
    int[] expected = new int[4];
    expected[0] = 16;
    expected[1] = 21;
    expected[2] = 21;
    expected[3] = 28;
    assertArrayEquals(expected, space.getRoomDimension());
  }

  @Test
  public void testGetRoomNum() {
    int expected = 1;
    assertEquals(expected, space.getRoomNum());
  }

  @Test
  public void testGetnoOfItems() {
    int totalItems = 1;
    assertEquals(totalItems, space.getnoOfItems());
    int totalItem = 2;
    assertEquals(totalItem,
        spaceHelper(16, 21, 21, 28, "Billiard Room", 0, itemList, 4).getnoOfItems());

  }

  @Test
  public void testItems() {
    List<List<Item>> item1 = new ArrayList<List<Item>>();
    item1 = spaceHelper(22, 19, 23, 26, "Armory", 0, itemList, 4).getItems();
    int roomNum = 0;
    // int size = item1.size();
    // testing a room has multiple items or not
    // int expected = space.getnoOfItems();
    // assertEquals(expected,size);
    List<Item> item2 = item1.get(roomNum);
    String[] tempList = new String[item2.size()];
    for (int i = 0; i < item2.size(); i++) {
      tempList[i] = item2.get(i).getItemName();

    }

    String[] expected = new String[2];
    expected[0] = "Crepe Pan";
    expected[1] = "Revolver";

    assertArrayEquals(expected, tempList);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomName() {
    spaceHelper(16, 21, 21, 28, " ", 1, itemList, 4);
    spaceHelper(16, 21, 21, 28, "12345 ", 1, itemList, 4);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomNum() {
    spaceHelper(16, 21, 21, 28, "Billiard Room ", -4, itemList, 4);
    spaceHelper(16, 21, 21, 28, "Billiard Room ", 25, itemList, 4);

  }
  // @Test(expected = IllegalArgumentException.class)
  // public void testInvalidTotalItems()
  // {
  // spaceHelper(16,21,21,28,"Billiard Room ",1,itemList).showItems(1);

  // }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomDimension() {
    spaceHelper(-7, 21, 21, 28, "Billiard Room ", 4, itemList, 4);
    spaceHelper(29, 21, 21, 28, "Billiard Room ", 4, itemList, 4);
    spaceHelper(20, 29, 21, 28, "Billiard Room ", 4, itemList, 4);

  }

  @Test
  public void testToString() {
    String expected = "Rooms (roomNum = 1, roomName = Billiard Room, "
        + "roomDimension = 16, 21, 21, 28)";
    assertEquals(expected, space.toString());
  }

  /**
   * A helper method to instantiate SpaceImpl class.
   */
  protected Space spaceHelper(int x1, int y1, int x2, int y2, String roomName, int roomNum,
      List itemList, int totalRoom) {
    return new SpaceImpl(x1, y1, x2, y2, roomName, roomNum, itemList, totalRoom);
  }

}
