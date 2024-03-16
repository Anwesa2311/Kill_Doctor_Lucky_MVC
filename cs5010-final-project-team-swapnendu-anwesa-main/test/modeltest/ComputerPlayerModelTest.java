package modeltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.assertj.core.api.Assertion.*;
import static org.junit.Assert.assertTrue;

import madworld.madworldmodel.ComputerPlayerModel;
import madworld.madworldmodel.Player.PlayerType;
//import madworld.madworldmodel.PlayerImpModel;
import org.junit.Before;
import org.junit.Test;

//import madworld.madworldmodel.ComputerPlayerModel;

/**
 * Test class for testing the ComputerPlayerModelTest class.
 */
public class ComputerPlayerModelTest {

  private ComputerPlayerModel compplayer;
  private ComputerPlayerModel compplayer1;

  /**
   * Initializing all the variables.
   */

  @Before
  public void setUp() {

    compplayer = computerHelper("Computer1", PlayerType.COMPUTER, 5, 5, 0);
    compplayer1 = computerHelper("Computer1", PlayerType.COMPUTER, 2, 5, 1);

  }

  @Test
  public void choserandomRoom() {

    int actualPosition = compplayer.getPlayerPosition();
    assertTrue(actualPosition == 0 || actualPosition == 1 || actualPosition == 2
        || actualPosition == 3 || actualPosition == 4);
  }

  @Test
  public void testMovenextRoom() {

    int prevPos = compplayer.getPlayerPosition();
    String s = "[2,4,5]";
    compplayer.movenextRoom(s, true, 0);
    int currentPos = compplayer.getPlayerPosition();
    assertTrue(currentPos == 2 || currentPos == 4 || currentPos == 5);
    assertFalse(prevPos == currentPos);

  }

  @Test
  public void testPickItems() {

    String s = "[Rat Poison,Piece of Rope]";
    compplayer.pickItems(s, true, 0);
    String currentItems = compplayer.getItems();
    currentItems = currentItems.strip();
    assertTrue("[Rat Poison]".equals(currentItems) || "[Piece of Rope]".equals(currentItems));

  }

  @Test
  public void testGetPlayerName() {
    String expected = "Computer1";
    assertEquals(expected, compplayer.getPlayerName());
  }

  @Test
  public void testGetPlayerType() {
    PlayerType expected = PlayerType.COMPUTER;
    assertEquals(expected, compplayer.getPlayerType());
  }

  @Test
  public void testGetPlayerPosition() {
    int actualPosition = compplayer.getPlayerPosition();
    assertTrue(actualPosition == 0 || actualPosition == 1 || actualPosition == 2
        || actualPosition == 3 || actualPosition == 4);

  }

  @Test
  public void testToString() {
    String expected1 = String.format("player name = %s\nplayer type = %s\n"
        + "player position =room num %s\nitems = No items\n ", "Computer1", "COMPUTER", 0);

    String expected2 = String.format("player name = %s\nplayer type = %s\n"
        + "player position =room num %s\nitems = No items\n ", "Computer1", "COMPUTER", 1);
    Boolean cond1 = compplayer1.toString().equals(expected2);
    Boolean cond2 = compplayer1.toString().equals(expected1);
    assertTrue(cond1 || cond2);

  }

  /**
   * A helper method to instantiate ComputerPlayerModel class.
   */
  protected ComputerPlayerModel computerHelper(String playerName, PlayerType type, int totalRoom,
      int size, int pos) {
    return new ComputerPlayerModel(playerName, type, totalRoom, size, pos);

  }
}
