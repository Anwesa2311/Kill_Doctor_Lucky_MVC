package modeltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.PlayerImpModel;
import org.junit.Before;
import org.junit.Test;

//import madworld.madworldmodel.Item;
//import madworld.madworldmodel.ItemImpl;

/**
 * Test class for testing the PlayerImpModelTest class.
 */

public class PlayerImpModelTest {

  private PlayerImpModel player;
  private PlayerImpModel player2;

  /**
   * Initializing all the variables.
   */

  @Before
  public void setUp() {

    player = playerHelper("Player 1", PlayerType.HUMAN, 4, 3, 21, 20);
    player2 = playerHelper("Player 3", PlayerType.HUMAN, 19, 1, 21, 20);

  }

  @Test
  public void testGetPlayerName() {
    String expected = "Player 1";
    assertEquals(expected, player.getPlayerName());
  }

  @Test
  public void testGetPlayerType() {
    PlayerType expected = PlayerType.HUMAN;
    assertEquals(expected, player.getPlayerType());
  }

  @Test
  public void testGetPlayerPosition() {
    int expected = 4;
    assertEquals(expected, player.getPlayerPosition());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerName() {
    playerHelper("", PlayerType.HUMAN, 4, 3, 21, 20);
    playerHelper(null, PlayerType.HUMAN, 4, 3, 21, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerposition() {
    playerHelper("Player 1", PlayerType.HUMAN, -6, 3, 21, 20);
    playerHelper(null, PlayerType.HUMAN, 4, 3, 22, 20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayertype() {
    playerHelper("Player 1", null, 6, 3, 21, 20);
    playerHelper("Player 1", PlayerType.COMPUTER, 4, 3, 19, 20);
  }

  @Test
  public void testPickItem() {
    String playerName = "Player 1";
    String itemName = "Rat Poison";
    int itemDamage = 3;
    player.pickitems(playerName, itemName, itemDamage);
    String expected = "[Rat Poison:3]";
    assertEquals(expected, player.getItems());

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidtestPickItem() {
    String playerName = "Player 1";
    String itemName = null;
    int itemDamage = -9;
    player.pickitems(playerName, itemName, itemDamage);
    player.pickitems("", itemName, 3);

  }

  @Test
  public void testMovePlayer() {
    String playerName = "Player 1";
    int playerPosition = 19;
    player.movePlayer(playerName, playerPosition);
    int expected = 19;
    assertEquals(expected, player.getPlayerPosition());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMaxItemLim() {

    // player2 object has a max item capacity as 1.So if it tries to
    // pick an item more than one then it will give an exception as it
    // will exeed its max limit.
    player2.pickitems("Player 3", "Rat Poison", 3);
    player2.pickitems("Player 3", "Piece of Rope", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlayer() {
    String playerName = "Player 1";
    int playerPosition = 4;
    player.movePlayer(playerName, playerPosition);

  }

  @Test
  public void testToString() {
    String expected = "player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 4\n" + "items = No items\n ";
    assertEquals(expected, player.toString());
  }

  /**
   * A helper method to instantiate PlayerImpModel class.
   */
  protected PlayerImpModel playerHelper(String playerName, PlayerType type, int position,
      int maxItemstoCarry, int totalRoom, int totalItem) {
    return new PlayerImpModel(playerName, type, position, maxItemstoCarry, totalRoom, totalItem);

  }

}
