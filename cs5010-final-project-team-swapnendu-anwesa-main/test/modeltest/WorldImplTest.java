package modeltest;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.Space;
import madworld.madworldmodel.SpaceImpl;
import madworld.madworldmodel.World;
import madworld.madworldmodel.WorldImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for testing the World class.
 */

public class WorldImplTest {

  private Scanner input;
  private Scanner input1;
  private World world;
  private World world2;
  private List<List<Space>> neighbor;
  private List<Space> neighborforroom;
  private int maxturns;

  /**
   * Initializing all the variables.
   */
  @Before
  public void setUp() {

    String filename1 = "C:\\\\CS5010Project\\\\cs5010-project-Anwesa2311\\" + "res\\\\mansion.txt";
    String filename2 = "C:\\CS5010Project\\cs5010-project-Anwesa2311\\" + "res\\dummy.txt";
    neighbor = new ArrayList<>();
    neighborforroom = new ArrayList<>();
    // String filename3 =
    try {
      input = new Scanner(new File(filename1));
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }
    try {
      input1 = new Scanner(new File(filename2));
    } catch (FileNotFoundException e) {

      e.printStackTrace();
    }
    // input2 = new Scanner(new File(filename3));
    world = worldHelper(input, 4);
    // world2 = worldHelper(input,1);

  }

  /**
   * testing total rows of the world.
   */

  @Test
  public void testGetWorldRows() {
    int expected = 36;
    assertEquals(expected, world.getWorldRows());
  }

  /**
   * testing all total columns of the world.
   */

  @Test
  public void testGetWorldColumns() {
    int expected = 30;
    assertEquals(expected, world.getWorldColumns());
  }

  /**
   * testing target character name of the world.
   */

  @Test
  public void testGetTargetChar() {
    String expected = "Doctor Lucky";
    assertEquals(expected, world.getTargetChar());
  }

  /**
   * testing the world name.
   */
  @Test
  public void testGetWorldName() {
    String expected = "Doctor Lucky's Mansion";
    assertEquals(expected, world.getWorldName());
  }

  @Test
  public void testGetTotalRooms() {
    int expected = 21;
    assertEquals(expected, world.getTotalRooms());
  }

  // @Test
  // public void testGetRoomDetails() {

  // String expected = "roomNum = 19\n" + "roomName = Wine Cellar\n"
  // + "roomDimension = 22, 5, 23, 12\n" + "Below are the roomitems \n"
  // + "ItemName = Rat Poison\n" + "ItemDamage = 2\n" + "ItemName = Piece of
  // Rope\n"
  // + "ItemDamage = 2\n" + "\n" + "no players\n" + "\n" + "";
  // assertEquals(expected, world.displayRoomInfo(19));

  // }

  /*
   * @Test public void testGetNeighborDetails() { int roomIndex = 1;
   * 
   * neighbor = world.getNeighbors(); neighborforroom = neighbor.get(roomIndex);
   * 
   * int[] tempList = new int[neighborforroom.size()]; for (int i = 0; i <
   * neighborforroom.size(); i++) { tempList[i] =
   * neighborforroom.get(i).getRoomNum();
   * 
   * }
   * 
   * int[] expected = new int[3]; expected[0] = 0; expected[1] = 3; expected[2] =
   * 18;
   * 
   * // Testing multiple neighbors. assertArrayEquals(expected, tempList);
   * 
   * roomIndex = 2; neighborforroom = neighbor.get(roomIndex);
   * 
   * tempList = new int[neighborforroom.size()]; for (int i = 0; i <
   * neighborforroom.size(); i++) { tempList[i] =
   * neighborforroom.get(i).getRoomNum(); } expected = new int[1]; expected[0] =
   * 20;
   * 
   * // Testing one neighbor. assertArrayEquals(expected, tempList);
   * 
   * }
   */
  /**
   * testing Invalid world name.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWorldName() {
    world2 = new WorldImpl(input1, 15);

  }

  /**
   * testing Invalid target name.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTargetName() {
    world2 = new WorldImpl(input1, 15);

  }

  /**
   * testing Invalid target health.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTargetHealth() {
    world2 = new WorldImpl(input1, 15);

  }

  /**
   * testing Invalid total rooms.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTotalRooms() {
    world2 = new WorldImpl(input1, 15);

  }

  /**
   * testing Invalid total items.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTotalItems() {
    world2 = new WorldImpl(input1, 15);

  }

  /**
   * testing Invalid room coordinates.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingRoomCoordinates() {
    world2 = new WorldImpl(input1, 15);

  }

  @Test
  public void testHashCode() {
    assertEquals(Objects.hashCode(world), world.hashCode());
  }

  @Test
  public void testEquals() {
    assertTrue(world.equals(world));
  }

  @Test
  public void testToString() {
    String expected = "World (world name = Doctor Lucky's Mansion, "
        + "target char = Doctor Lucky, target health = 10, currentPosition = 0, totalRoom = 21 )";
    assertEquals(expected, world.toString());
  }

  @Test
  public void testAddHumanPlayer() {
    String s = world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    String expected = "Player added suceessfully";
    assertEquals(expected, s);
  }

  /*
   * @Test public void displayHumanPlayer() { world.addPlayer("Player 1",
   * PlayerType.HUMAN, 4); String s = world.displayPlayer("Player 1"); String
   * expected = "player name = Player 1\n" + "player type = HUMAN\n" +
   * "player position =room num 4\n" + "items = No items\n" + " "; // Displaying
   * player info with 0 item. assertEquals(expected, s);
   * world.pickItemsPlayer("Player 1", "Letter Opener"); String s1 =
   * world.displayPlayer("Player 1"); String expected2 =
   * "player name = Player 1, player type = HUMAN, " +
   * "player position = 4, items = [Letter Opener]" + " "; // Displaying player
   * info with 1 item, assertEquals(expected2, s1); }
   */

  @Test
  public void moveHumanPlayer() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    String s = world.moveplayer("Player 1", "Wine Cellar");
    String expected = String.format(
        "player name = Player 1\n" + "player type = HUMAN\n" + "player position =room num 4\n"
            + "items = No items\n" + " \n" + "roomNum = 4\n" + "roomName = Drawing Room\n"
            + "roomDimension = 22, 13, 25, 18\n" + "ItemName = Letter Opener\n" + "ItemDamage = 2\n"
            + "\n" + "the players are\n" + "[Player 1]\n" + "pet=No pet\n" + "target=No target\n"
            + "\n" + "Move successful to room 19\n" + "current player info\n"
            + "player name = Player 1\n" + "player type = HUMAN\n"
            + "player position =room num 19\n" + "items = No items\n" + " \n");
    assertEquals(expected, s);

  }

  @Test
  public void testvalidTurn() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    world.addPlayer("Anny", PlayerType.HUMAN, 3);

    String expected = "Player 1";
    // Now the turn should be Player 1 as he is added first.
    assertEquals(expected, world.getTurn());
  }

  @Test
  public void testIsGameOver() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 19);
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    // max game turn was given 9, but 10 times the player 1 has given its turn.So
    // the game should be over.
    boolean g = world.isGameOver();
    assertTrue(g);
  }

  @Test
  public void testHumanPickItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 19);
    String s = world.pickItemsPlayer("Player 1", "Rat Poison");
    String expected = String.format("player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 19\n" + "items = No items\n" + " \n" + "roomNum = 19\n"
        + "roomName = Wine Cellar\n" + "roomDimension = 22, 5, 23, 12\n" + "ItemName = Rat Poison\n"
        + "ItemDamage = 2\n" + "ItemName = Piece of Rope\n" + "ItemDamage = 2\n" + "\n"
        + "the players are\n" + "[Player 1]\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Pick item is successful\n");
    assertEquals(expected, s);
  }

  /*
   * @Test
   * 
   * public void displayRoomInfowithPlayer() { world.addPlayer("Player 1",
   * PlayerType.HUMAN, 19); String expected = "roomNum = 19\n" +
   * "roomName = Wine Cellar\n" + "roomDimension = 22, 5, 23, 12\n" +
   * "Below are the roomitems\n" + "ItemName = Rat Poison\n" + "ItemDamage = 2\n"
   * + "ItemName = Piece of Rope\n" + "ItemDamage = 2\n" + "\n" +
   * "the players are\n" + "[Player 1]\n"; // Showing display of a room with one
   * player in it. assertEquals(expected, world.displayRoomInfo(19));
   * world.addPlayer("Player 2", PlayerType.HUMAN, 19); String expected2 =
   * "roomNum = 19\n" + "roomName = Wine Cellar\n" +
   * "roomDimension = 22, 5, 23, 12\n" + "Below are the roomitems\n" +
   * "ItemName = Rat Poison\n" + "ItemDamage = 2\n" + "ItemName = Piece of Rope\n"
   * + "ItemDamage = 2\n" + "\n" + "the players are\n" + "[Player 1, Player 2]\n";
   * // Showing display of a room with more than one player in it.
   * assertEquals(expected2, world.displayRoomInfo(19));
   * 
   * }
   */

  /*
   * @Test public void testTargetAfterTurn() { world.addPlayer("Player 1",
   * PlayerType.HUMAN, 4); world.lookaround("Player 1"); // after one turn target
   * should move.Lets test that. int pos = world.getCurrentPos(); int expected =
   * 1; assertEquals(expected, pos); world.lookaround("Player 1"); int expected2 =
   * 2; assertEquals(expected2, world.getCurrentPos());
   * 
   * }
   */

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidturn() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    world.addPlayer("Anny", PlayerType.HUMAN, 3);
    // According to the requirement now the turn should be "Player 1"
    // It should throw an exception if "Anny" tries to take a turn,So lets test
    // that.
    world.lookaround("Anny");
  }

  @Test

  public void testvalidMultipleTurns() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    world.addPlayer("Anny", PlayerType.HUMAN, 3);
    world.addPlayer("Sazad", PlayerType.HUMAN, 9);
    // current turn should be "Player 1".lets test that.
    assertEquals("Player 1", world.getTurn());
    // player 1 completing its turn.
    world.lookaround("Player 1");
    // now the player 1 turn should be changed to "Anny".Lets test that.
    assertEquals("Anny", world.getTurn());
    // Anny completing her turn.
    world.lookaround("Anny");
    // now Anny's turn should be changed to Sazad.lets test that.
    assertEquals("Sazad", world.getTurn());

  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidtestAddHumanPlayer() {
    world.addPlayer("", PlayerType.HUMAN, 4);
    world.addPlayer("", PlayerType.HUMAN, -9);
    world.addPlayer("", PlayerType.HUMAN, 25);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidmoveHumanPlayer() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    world.moveplayer("Player 1", "Hedge Maze");

  }

  // Test case for attacking target with invalid item.
  @Test(expected = IllegalArgumentException.class)

  public void testAttackTargetWithInvalidItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    world.attackTargetForPlayers("Player 1", "XYZ");
  }

  // Test attacking target with a item that the player does not have with him(i.e
  // a wrong item)
  @Test(expected = IllegalArgumentException.class)

  public void testAttackTargetWithWrongItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 1);
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    world.attackTargetForPlayers("Player 1", "Revolver");
  }

  // Unsuccessful attack on target as seen by other player in same room.
  @Test
  public void testUnsuccessfullAttack1() {
    // First test unsuccessful attack as seen by another player in the same room
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);

  }

  // Unsuccessful attack on target as seen by another player in neighbor rooms.
  @Test
  public void testUnsuccessfullAttack2() {
    // Unsuccessful attack as seen by another player in neighbor rooms.
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    // Adding another player in room num 2's neighbor which is room num 20.
    world.addPlayer("Player 2", PlayerType.HUMAN, 20);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);
  }

  // Successful attack on target without the help of a pet
  @Test
  public void testsuccessfullAttackWithoutPet() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 1");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack successful with damage = 4";
    assertEquals(expected, result);

  }

  // Successful attack on target with a pet but no player
  @Test
  public void testsuccessfullAttackWithPetNoPlayer() {

    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.movePetForPlayers("Player 1", 2);
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack successful with damage = 4";
    assertEquals(expected, result);

  }

  // Successful attack on target with the help of a pet with other player
  @Test
  public void testsuccessfullAttackWithPetOnePlayer() {

    // Adding a player in room num 6
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    // Adding another player in room num 6.
    world.addPlayer("Player 2", PlayerType.HUMAN, 6);
    // Performing 6 turns so that target moves to room num 6.
    world.pickItemsPlayer("Player 1", "Trowel");
    // Moving the pet to room num 6.
    world.movePetForPlayers("Player 2", 6);
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Trowel");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);

  }

  @Test
  public void testsuccessfullAttackWithPetOnePlayerInNeighbor() {

    // Adding a player in room num 6
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    // Adding another player in room num 6.
    world.addPlayer("Player 2", PlayerType.HUMAN, 7);
    // Performing 6 turns so that target moves to room num 6.
    world.pickItemsPlayer("Player 1", "Trowel");
    // Moving the pet to room num 6.
    world.movePetForPlayers("Player 2", 6);
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Trowel");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);

  }

  // Attacking target when the player has 1 item.
  @Test(expected = IllegalArgumentException.class)
  public void testAttackPlayerWithNoItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // Player currently has no item as it did not pick any.
    world.attackTargetForPlayers("Player 1", "Revolver");

  }

  // Attacking target when player has multiple items.
  @Test
  public void testAttackPlayerHavingMultItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // Finishing 8 turns so that target reaches in 8th room and moving the
    // player to the 8th room which is kitchen as well to attack the target.
    // The player has more than one item with it as it picked both revolver and
    // Billiard Cue.
    world.pickItemsPlayer("Player 1", "Revolver");
    world.moveplayer("Player 1", "Billiard Room");
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    world.moveplayer("Player 1", "Dining Hall");
    world.moveplayer("Player 1", "Kitchen");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    String result = world.attackTargetForPlayers("Player 1", "Revolver");
    // The Revolver has a damage of 3.
    String expected = "Attack successful with damage = 3";
    assertEquals(expected, result);

  }

  // Testing the wandering pet movements.
  @Test
  public void testWanderingPetMovement() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    String path;
    // getting the path of the pet from room num 0.
    path = world.getPathforPetDfS(0);
    String expected = String.format("[0, 1, 3, 4, 5, 15, 7, 6, 7, 15, 20, 2, "
        + "20, 15, 5, 4, 19, 8, 14, 16, 9, 11, 12, 10, 13,"
        + " 10, 18, 17, 18, 10, 12, 11, 9, 16, 14, 8, 19, 4, 3, 1, 0]");
    assertEquals(expected, path);
    // Now lets see if after every turn the pet is following the path or not.
    String s1 = String.format("pet name = Fortune the Cat \n" + " pet pos = 0");
    // This is to test that the pet is in 0th room or not at the start of a game.
    assertEquals(s1, world.showPet());
    // 1st turn.
    world.lookaround("Player 1");

    String s2 = String.format("pet name = Fortune the Cat \n" + " pet pos = 1");
    // See that the current position of the pet is room num 1 and as per our mansion
    // following the dfs
    // that should be its next room.
    assertEquals(s2, world.showPet());

    // 2nd turn
    world.lookaround("Player 1");
    String s3 = String.format("pet name = Fortune the Cat \n" + " pet pos = 3");
    // See that the current position of the pet is room num 3 and as per our mansion
    // following the dfs
    // that should be its next room.
    assertEquals(s3, world.showPet());

    // 3rd turn
    world.lookaround("Player 1");
    String s4 = String.format("pet name = Fortune the Cat \n" + " pet pos = 4");
    // See that the current position of the pet is room num 4 and as per our mansion
    // following the dfs that should be its next room.
    assertEquals(s4, world.showPet());

  }

  // Testing if dfs restarts after a move pet command.
  @Test
  public void testRestartDfsAfterMovePet() {
    // This test is to check if after a movepet call the dfs restarts with
    // its root node as per the new position or not.
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // 1st turn.
    world.lookaround("Player 1");

    String s2 = String.format("pet name = Fortune the Cat \n" + " pet pos = 1");
    // See that the current position of the pet is room num 1 and as per our mansion
    // following the dfs
    // that should be its next room.
    assertEquals(s2, world.showPet());

    // Now lets move the pet to a particular room. lets say room num 4.
    world.movePetForPlayers("Player 1", 7);
    // Now after this movement the pet will start from room num 7 and will follow a
    // dfs
    // path.Since move pet is itself a turn now the pet will automatically moved to
    // room num 6.
    String s3 = String.format("pet name = Fortune the Cat \n" + " pet pos = 6");
    assertEquals(s3, world.showPet());

  }

  @Test
  public void testShowTargetAfterSuccessFulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 1);
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.attackTargetForPlayers("Player 1", "Billiard Cue");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);
    String expectedtargetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=8\n" + "Target Position=2\n");
    assertEquals(expectedtargetDetails, world.showTarget());

  }

  @Test
  public void testShowTargetAfterUnSuccessFulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);
    String expectedtargetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=10\n" + "Target Position=3\n");
    assertEquals(expectedtargetDetails, world.showTarget());

  }

  @Test
  public void testShowTargetAfteraTurn() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 1);
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    String expectedtargetDetails = "Target Name=Doctor Lucky\n" + "Target Health=10\n"
        + "Target Position=1\n";
    assertEquals(expectedtargetDetails, world.showTarget());
    String result = world.attackTargetForPlayers("Player 1", "Billiard Cue");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);
    expectedtargetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=8\n" + "Target Position=2\n");
    assertEquals(expectedtargetDetails, world.showTarget());

  }

  // Player sucessfuly pokin in the eye of target without having any item
  @Test
  public void testSuccessfulPokeInTheEyeWithNoItem() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.pokeInEye("Player 1");
    String expected = "Poke in the eye successful with damage = 1";
    assertEquals(expected, result);
    String targetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=9\n" + "Target Position=1\n");
    assertEquals(targetDetails, world.showTarget());

  }

  // Poke in the eye of target with the help of pet
  @Test
  public void testSuccessfulPokeInTheEyeWithPet() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    world.addPlayer("Player 2", PlayerType.HUMAN, 0);

    // world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.pokeInEye("Player 1");
    String expected = "Poke in the eye successful with damage = 1";
    assertEquals(expected, result);
    String targetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=9\n" + "Target Position=1\n");
    assertEquals(targetDetails, world.showTarget());

  }

  // Unsucessfull poke in the eye as seen by other player.
  @Test
  public void testUnSuccessfulPokeInTheEye() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.lookaround("Player 1");
    world.lookaround("Player 2");

    // world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.pokeInEye("Player 1");
    String expected = "poke In The Eye not successful as seen by other player";
    assertEquals(expected, result);
    String targetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=10\n" + "Target Position=3\n");
    assertEquals(targetDetails, world.showTarget());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPokeInTheEye() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    // invalid attempt to poke in the eye of the target when target is not available
    // in the same
    // room
    world.pokeInEye("Player 1");
  }

  @Test
  public void testValidMovepet() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    String result = world.movePetForPlayers("Player 1", 3);
    String expected1 = String.format("player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 0\n" + "items = No items\n" + " \n" + "roomNum = 0\n"
        + "roomName = Armory\n" + "roomDimension = 22, 19, 23, 26\n" + "Below are the roomitems\n"
        + "ItemName = Revolver\n" + "ItemDamage = 3\n" + "\n" + "the players are\n" + "[Player 1]\n"
        + "pet=Fortune the Cat\n" + "target=Doctor Lucky\n" + "\n"
        + "move successful to room = 3\n");
    // As it can be seen that the move was successful to room no 3.
    assertEquals(expected1, result);
    // Now after one turn of movepet the pet will automatically move to
    // room num 0 as per dfs algorithm.So lets test that.
    String petDetails = world.showPet();
    String expected = String.format("pet name = Fortune the Cat \n" + " pet pos = 0");
    assertEquals(expected, petDetails);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidMovepet() {

    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    world.movePetForPlayers("Player 1", -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInValidattackTarget() {

    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    world.pickItemsPlayer("Player 1", "Letter Opener");
    world.attackTargetForPlayers("Player 1", "Letter Opener");
  }

  @Test
  public void testTurnAfterMovePet() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    world.addPlayer("Player 2", PlayerType.HUMAN, 0);
    world.movePetForPlayers("Player 1", 3);
    String turn = "Player 2";
    assertEquals(turn, world.getTurn());

  }

  @Test
  public void testTurnAfterAttackTargetHumanPlayer() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    // Adding another player in room num 6.
    world.addPlayer("Player 2", PlayerType.HUMAN, 6);
    // Performing 6 turns so that target moves to room num 6.
    world.pickItemsPlayer("Player 1", "Trowel");
    // Moving the pet to room num 6.
    world.movePetForPlayers("Player 2", 6);
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Trowel");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);
    String expectedturn = "Player 2";
    assertEquals(expectedturn, world.getTurn());

  }

  @Test
  public void testGetWinnerAfterGameOver() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.addPlayer("Player 1", PlayerType.HUMAN, 7);
    String s1 = world.pickItemsPlayer("Player 2", "Chain Saw");
    String s2 = world.lookaround("Player 1");
    String s3 = world.attackTargetForPlayers("Player 2", "Chain Saw");
    world.pickItemsPlayer("Player 1", "Loud Noise");
    world.moveplayer("Player 2", "Winter Garden");
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    String s4 = world.attackTargetForPlayers("Player 1", "Loud Noise");
    String targetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=0\n" + "Target Position=8\n");
    assertEquals(targetDetails, world.showTarget());
    // player 1 won as target gets killed.
    String winner = "Player 1";
    assertEquals(winner, world.getWinner());
    boolean g = world.isGameOver();
    assertTrue(g);

  }

  @Test
  public void testCompWinnerAfterGameOver() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.addComputerPlayer("Computer", 7);
    String s1 = world.pickItemsPlayer("Player 2", "Chain Saw");
    String s2 = world.computerlookaround("Computer");
    String s3 = world.attackTargetForPlayers("Player 2", "Chain Saw");
    // world.pickItemsPlayer("Player 1", "Loud Noise");
    world.pickitemComputerPlayer("Computer", false, 0);
    world.moveplayer("Player 2", "Winter Garden");
    world.computerlookaround("Computer");
    world.lookaround("Player 2");
    // String s4 = world.attackTargetForPlayers("Player 1", "Loud Noise");
    String s4 = world.attackComputerPlayers("Computer");
    String targetDetails = String
        .format("Target Name=Doctor Lucky\n" + "Target Health=0\n" + "Target Position=8\n");
    assertEquals(targetDetails, world.showTarget());
    // player 1 won as target gets killed.
    String winner = "Computer";
    assertEquals(winner, world.getWinner());
    boolean g = world.isGameOver();
    assertTrue(g);

  }

  @Test
  public void testItemRemoveAfterSuccessfulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 1);
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.attackTargetForPlayers("Player 1", "Billiard Cue");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);
    String s = world.lookaround("Player 1");
    // In the lookaorund player and room should not have the item Billiard Cue.
    String expected2 = String.format("Current roomdetails =\n" + "roomNum = 1\n"
        + "roomName = Billiard Room\n" + "roomDimension = 16, 21, 21, 28\n"
        + "No Items in this room\n" + "the players are\n" + "[Player 1]\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "Current playerdetails = \n" + "player name = Player 1\n"
        + "player type = HUMAN\n" + "player position =room num 1\n" + "items = No items\n" + " \n"
        + "neighbors = [Dining Hall, Trophy Room]\n" + "Below is the neighbor details\n"
        + "roomNum = 3\n" + "roomName = Dining Hall\n" + "roomDimension = 12, 11, 21, 20\n"
        + "No Items in this room\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 18\n" + "roomName = Trophy Room\n"
        + "roomDimension = 10, 21, 15, 26\n" + "ItemName = Duck Decoy\n" + "ItemDamage = 3\n"
        + "ItemName = Monkey Hand\n" + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected2, s);
  }

  @Test
  public void testItemRemoveAfterUnSuccessfulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    // Adding another player in room num 2's neighbor which is room num 20.
    world.addPlayer("Player 2", PlayerType.HUMAN, 20);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);
    world.lookaround("Player 2");
    String s = world.lookaround("Player 1");
    // In the lookaorund player and room should not have the item Chain Saw.
    String expected2 = String
        .format("Current roomdetails =\n" + "roomNum = 2\n" + "roomName = Carriage House\n"
            + "roomDimension = 28, 0, 35, 5\n" + "ItemName = Big Red Hammer\n" + "ItemDamage = 4\n"
            + "\n" + "the players are\n" + "[Player 1]\n" + "pet=No pet\n" + "target=No target\n"
            + "\n" + "Current playerdetails = \n" + "player name = Player 1\n"
            + "player type = HUMAN\n" + "player position =room num 2\n" + "items = No items\n"
            + " \n" + "neighbors = [Winter Garden]\n" + "Below is the neighbor details\n"
            + "roomNum = 20\n" + "roomName = Winter Garden\n" + "roomDimension = 30, 6, 35, 11\n"
            + "No Items in this room\n" + "the players are\n" + "[Player 2]\n" + "pet=No pet\n"
            + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected2, s);

  }

  @Test

  public void testEvidenceAfterSuccessfulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 1);
    world.pickItemsPlayer("Player 1", "Billiard Cue");
    String result = world.attackTargetForPlayers("Player 1", "Billiard Cue");
    String expected = "Attack successful with damage = 2";
    assertEquals(expected, result);
    String evidence = world.getEvidenceList();
    String expected2 = "[Billiard Cue:2]";
    assertEquals(expected2, evidence);
  }

  @Test

  public void testEvidenceAfterUnSuccessfulAttack() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    // Adding another player in room num 2's neighbor which is room num 20.
    world.addPlayer("Player 2", PlayerType.HUMAN, 20);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    world.lookaround("Player 2");
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);
    String evidence = world.getEvidenceList();
    String expected2 = "[Chain Saw:4]";
    assertEquals(expected2, evidence);
  }

  @Test

  public void testMultipleEvidences() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 2);
    world.addPlayer("Player 1", PlayerType.HUMAN, 7);
    String s1 = world.pickItemsPlayer("Player 2", "Chain Saw");
    String s2 = world.lookaround("Player 1");
    String s3 = world.attackTargetForPlayers("Player 2", "Chain Saw");
    world.pickItemsPlayer("Player 1", "Loud Noise");
    world.moveplayer("Player 2", "Winter Garden");
    world.lookaround("Player 1");
    world.lookaround("Player 2");
    String s4 = world.attackTargetForPlayers("Player 1", "Loud Noise");
    String evidence = world.getEvidenceList();
    String expected2 = "[Chain Saw:4, Loud Noise:6]";
    assertEquals(expected2, evidence);
  }

  // Lokking around of a room which has target and pet
  @Test
  public void testLookAroundWithPetTarget() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 0);
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    String s = world.lookaround("Player 2");
    String expected = String.format("Current roomdetails =\n" + "roomNum = 0\n"
        + "roomName = Armory\n" + "roomDimension = 22, 19, 23, 26\n" + "Below are the roomitems\n"
        + "ItemName = Revolver\n" + "ItemDamage = 3\n" + "\n" + "the players are\n"
        + "[Player 2, Player 1]\n" + "pet=Fortune the Cat\n" + "target=Doctor Lucky\n" + "\n"
        + "Current playerdetails = \n" + "player name = Player 2\n" + "player type = HUMAN\n"
        + "player position =room num 0\n" + "items = No items\n" + " \n"
        + "neighbors = [Billiard Room, Dining Hall, Drawing Room]\n"
        + "Below is the neighbor details\n" + "roomNum = 1\n" + "roomName = Billiard Room\n"
        + "roomDimension = 16, 21, 21, 28\n" + "ItemName = Billiard Cue\n" + "ItemDamage = 2\n"
        + "\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 3\n" + "roomName = Dining Hall\n"
        + "roomDimension = 12, 11, 21, 20\n" + "No Items in this room\n" + "no players\n"
        + "pet=No pet\n" + "target=No target\n" + "\n" + "Below is the neighbor details\n"
        + "roomNum = 4\n" + "roomName = Drawing Room\n" + "roomDimension = 22, 13, 25, 18\n"
        + "ItemName = Letter Opener\n" + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected, s);

  }

  // Looingaround of a room which has a neighbor with a pet and it wont show in
  // the neighbor
  // details. In this case Armory will not show.
  @Test
  public void testLookAroundWithPetInNeighbor() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 1);
    // Note that room num 1 is room 0's neighbor.
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // Now lets lookaround for player2 and player2 should not be able
    // to see player1 and room num 0 as pet is present in player1's room.

    String expected = String.format("Current roomdetails =\n" + "roomNum = 1\n"
        + "roomName = Billiard Room\n" + "roomDimension = 16, 21, 21, 28\n"
        + "ItemName = Billiard Cue\n" + "ItemDamage = 2\n" + "\n" + "the players are\n"
        + "[Player 2]\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Current playerdetails = \n" + "player name = Player 2\n" + "player type = HUMAN\n"
        + "player position =room num 1\n" + "items = No items\n" + " \n"
        + "neighbors = [Dining Hall, Trophy Room]\n" + "Below is the neighbor details\n"
        + "roomNum = 3\n" + "roomName = Dining Hall\n" + "roomDimension = 12, 11, 21, 20\n"
        + "No Items in this room\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 18\n" + "roomName = Trophy Room\n"
        + "roomDimension = 10, 21, 15, 26\n" + "ItemName = Duck Decoy\n" + "ItemDamage = 3\n"
        + "ItemName = Monkey Hand\n" + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    String result = world.lookaround("Player 2");
    // As it can be seen that in the result for the neighbors room num 0 that
    // is Armory is not showing as pet is there in that room.
    assertEquals(expected, result);

  }

  // Unsuccessful attack by a player
  @Test

  public void testUnsuccessfulAttack3() {
    // This is a test for unsuccessful attack by a player as caught
    // by another player from an invisible room.
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    // Adding another player in room num 2's neighbor which is room num 20.
    world.addPlayer("Player 2", PlayerType.HUMAN, 20);
    world.pickItemsPlayer("Player 1", "Chain Saw");
    // Player 2 becomes invisible as pet is in room 20.
    world.movePetForPlayers("Player 2", 20);
    String result = world.attackTargetForPlayers("Player 1", "Chain Saw");
    String expected = "Attack not successful as seen by other player";
    assertEquals(expected, result);

  }

  @Test(expected = IllegalArgumentException.class)

  public void testMovePlayerWithPetInNeighbor() {
    world.addPlayer("Player 2", PlayerType.HUMAN, 1);
    // Note that room num 1 is room 0's neighbor.
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    // Since in room num 0 pet is present,player can not move to
    // room num 0 as it can not be seen from room num 1.It will throw
    // an IA exception saying player can not move.
    world.moveplayer("Player 2", "Armory");
  }

  /*
   * @Test public void testLookAround3() {
   * 
   * }
   */

  /*
   * @Test public void testBasicInfoBeforTurn() {
   * 
   * }
   */

  @Test
  public void testSuccessfulComputerAttackTarget() {
    world.addComputerPlayer("Computer 1", 1);
    world.pickitemComputerPlayer("Computer 1", false, 0);
    String s = world.showTarget();
    String result = world.attackComputerPlayers("Computer 1");
    String expected = "Attack successful with item:damage = [Billiard Cue:2]";
    assertEquals(expected, result);

    // world.att
  }

  @Test
  public void testComputerAttackTargetMaxItem() {
    world.addComputerPlayer("Computer 1", 0);
    String s1 = world.pickitemComputerPlayer("Computer 1", false, 0);
    String s2 = world.moveComputerPlayer("Computer 1", false, 2);
    String s3 = world.pickitemComputerPlayer("Computer 1", false, 0);
    String s4 = world.computerlookaround("Computer 1");
    String s = world.showTarget();
    String result = world.attackComputerPlayers("Computer 1");
    String maxItem = "[Revolver:3]";
    String expected = String.format("Attack successful with item:damage = %s", maxItem);
    assertEquals(expected, result);

  }

  @Test

  public void testComputerAttackWithNoItem() {
    world.addComputerPlayer("Computer 1", 0);
    ;
    String result = world.attackComputerPlayers("Computer 1");
    String expected = "Computer player chose to poke in the eye of target with damage 1";
    ;
    assertEquals(expected, result);
    String expected2 = "Target Name=Doctor Lucky\n" + "Target Health=9\n" + "Target Position=1\n";

    String targetDetails = world.showTarget();
    assertEquals(expected2, targetDetails);
  }

  @Test

  public void testAttackWithPetOnePlayer() {
    world.addComputerPlayer("Computer 1", 0);
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    String s1 = world.attackComputerPlayers("Computer 1");
    String expected = "Computer player chose to poke in the eye of target with damage 1";
    assertEquals(expected, s1);

  }

  @Test

  public void testCompActionOnUnsuceessfulAttack() {
    world.addComputerPlayer("Computer 1", 2);
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    String s2 = world.pickitemComputerPlayer("Computer 1", false, 0);
    String s3 = world.lookaround("Player 1");
    String s1 = world.actionForComputer("Computer 1");
    String expected = String
        .format("player name = Computer 1, player type = COMPUTER, player position = 2, "
            + "items = [Chain Saw:4] \n" + "roomNum = 2\n" + "roomName = Carriage House\n"
            + "roomDimension = 28, 0, 35, 5\n" + "ItemName = Big Red Hammer\n" + "ItemDamage = 4\n"
            + "\n" + "the players are\n" + "[Computer 1, Player 1]\n" + "pet=No pet\n"
            + "target=Doctor Lucky\n" + "\n" + "Pick item is successful\n");
    assertEquals(expected, s1);
  }

  @Test

  public void testAlwaysAttemptOnAttack() {
    world.addComputerPlayer("Computer 1", 0);
    String result = world.actionForComputer("Computer 1");
    String expected = "Computer player chose to poke in the eye of target with damage 1";
    assertEquals(expected, result);
    String result2 = world.actionForComputer("Computer 1");
    String result3 = world.moveComputerPlayer("Computer 1", false, 2);
    String result4 = world.actionForComputer("Computer 1");
    String result5 = world.actionForComputer("Computer 1");
    String expected1 = "Attack successful with item:damage = [Revolver:3]";
    assertEquals(expected1, result5);

  }

  @Test
  public void testCompUnsuccessfulAttack1() {
    world.addComputerPlayer("Computer 1", 2);
    world.addPlayer("Player 1", PlayerType.HUMAN, 2);
    String s2 = world.pickitemComputerPlayer("Computer 1", false, 0);
    String s3 = world.lookaround("Player 1");
    String s1 = world.attackComputerPlayers("Computer 1");
    String expected = "VISIBLE";
    assertEquals(expected, s1);
  }

  @Test
  public void testCompUnsuccessfulAttack2() {
    world.addComputerPlayer("Computer 1", 2);
    world.addPlayer("Player 1", PlayerType.HUMAN, 20);
    String s2 = world.pickitemComputerPlayer("Computer 1", false, 0);
    String s3 = world.lookaround("Player 1");
    String s1 = world.attackComputerPlayers("Computer 1");
    String expected = "VISIBLE";
    assertEquals(expected, s1);
  }

  @Test

  public void testLookAroundSameSpace() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 0);
    world.addPlayer("Player 2", PlayerType.HUMAN, 0);

    String s2 = world.movePetForPlayers("Player 1", 5);
    String expected1 = String.format("player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 0\n" + "items = No items\n" + " \n" + "roomNum = 0\n"
        + "roomName = Armory\n" + "roomDimension = 22, 19, 23, 26\n" + "Below are the roomitems\n"
        + "ItemName = Revolver\n" + "ItemDamage = 3\n" + "\n" + "the players are\n"
        + "[Player 1, Player 2]\n" + "pet=Fortune the Cat\n" + "target=Doctor Lucky\n" + "\n"
        + "move successful to room = 5\n");
    assertEquals(expected1, s2);
    String s3 = world.lookaround("Player 2");
    String expected2 = String.format("Current roomdetails =\n" + "roomNum = 0\n"
        + "roomName = Armory\n" + "roomDimension = 22, 19, 23, 26\n" + "ItemName = Revolver\n"
        + "ItemDamage = 3\n" + "\n" + "the players are\n" + "[Player 1, Player 2]\n"
        + "pet=No pet\n" + "target=No target\n" + "\n" + "Current playerdetails = \n"
        + "player name = Player 2\n" + "player type = HUMAN\n" + "player position =room num 0\n"
        + "items = No items\n" + " \n" + "neighbors = [Billiard Room, Dining Hall, Drawing Room]\n"
        + "Below is the neighbor details\n" + "roomNum = 1\n" + "roomName = Billiard Room\n"
        + "roomDimension = 16, 21, 21, 28\n" + "ItemName = Billiard Cue\n" + "ItemDamage = 2\n"
        + "\n" + "no players\n" + "pet=No pet\n" + "target=Doctor Lucky\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 3\n" + "roomName = Dining Hall\n"
        + "roomDimension = 12, 11, 21, 20\n" + "No Items in this room\n" + "no players\n"
        + "pet=No pet\n" + "target=No target\n" + "\n" + "Below is the neighbor details\n"
        + "roomNum = 4\n" + "roomName = Drawing Room\n" + "roomDimension = 22, 13, 25, 18\n"
        + "ItemName = Letter Opener\n" + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected2, s3);

  }

  @Test

  public void testLookAroundNoItemSameSpace() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 3);
    String s1 = world.lookaround("Player 1");
    String expected = String.format("Current roomdetails =\n" + "roomNum = 3\n"
        + "roomName = Dining Hall\n" + "roomDimension = 12, 11, 21, 20\n"
        + "No Items in this room\n" + "the players are\n" + "[Player 1]\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "Current playerdetails = \n" + "player name = Player 1\n"
        + "player type = HUMAN\n" + "player position =room num 3\n" + "items = No items\n" + " \n"
        + "neighbors = [Billiard Room, Drawing Room, Kitchen, Parlor, "
        + "Tennessee Room, Trophy Room, Wine Cellar]\n"

        + "Below is the neighbor details\n" + "roomNum = 1\n" + "roomName = Billiard Room\n"
        + "roomDimension = 16, 21, 21, 28\n" + "ItemName = Billiard Cue\n" + "ItemDamage = 2\n"
        + "\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 4\n" + "roomName = Drawing Room\n"
        + "roomDimension = 22, 13, 25, 18\n" + "ItemName = Letter Opener\n" + "ItemDamage = 2\n"
        + "\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 8\n" + "roomName = Kitchen\n"
        + "roomDimension = 16, 3, 21, 10\n" + "ItemName = Crepe Pan\n" + "ItemDamage = 3\n"
        + "ItemName = Sharp Knife\n" + "ItemDamage = 3\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "Below is the neighbor details\n" + "roomNum = 14\n"
        + "roomName = Parlor\n" + "roomDimension = 10, 5, 15, 10\n" + "No Items in this room\n"
        + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 17\n" + "roomName = Tennessee Room\n"
        + "roomDimension = 8, 11, 11, 20\n" + "No Items in this room\n" + "no players\n"
        + "pet=No pet\n" + "target=No target\n" + "\n" + "Below is the neighbor details\n"
        + "roomNum = 18\n" + "roomName = Trophy Room\n" + "roomDimension = 10, 21, 15, 26\n"
        + "ItemName = Duck Decoy\n" + "ItemDamage = 3\n" + "ItemName = Monkey Hand\n"
        + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Below is the neighbor details\n" + "roomNum = 19\n" + "roomName = Wine Cellar\n"
        + "roomDimension = 22, 5, 23, 12\n" + "ItemName = Rat Poison\n" + "ItemDamage = 2\n"
        + "ItemName = Piece of Rope\n" + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected, s1);

  }

  @Test
  public void testLookAroundTargetInNeighbor() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 4);
    // First turn as target moves to room num 1 which is room num 3's neighbor.
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    world.lookaround("Player 1");
    String s = world.lookaround("Player 1");
    String expected = String.format("Current roomdetails =\n" + "roomNum = 4\n"
        + "roomName = Drawing Room\n" + "roomDimension = 22, 13, 25, 18\n"
        + "ItemName = Letter Opener\n" + "ItemDamage = 2\n" + "\n" + "the players are\n"
        + "[Player 1]\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Current playerdetails = \n" + "player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 4\n" + "items = No items\n" + " \n"
        + "neighbors = [Dining Hall, Foyer, Wine Cellar]\n" + "Below is the neighbor details\n"
        + "roomNum = 3\n" + "roomName = Dining Hall\n" + "roomDimension = 12, 11, 21, 20\n"
        + "No Items in this room\n" + "no players\n" + "pet=No pet\n" + "target=Doctor Lucky\n"
        + "\n" + "Below is the neighbor details\n" + "roomNum = 5\n" + "roomName = Foyer\n"
        + "roomDimension = 26, 13, 27, 18\n" + "No Items in this room\n" + "no players\n"
        + "pet=No pet\n" + "target=No target\n" + "\n" + "Below is the neighbor details\n"
        + "roomNum = 19\n" + "roomName = Wine Cellar\n" + "roomDimension = 22, 5, 23, 12\n"
        + "ItemName = Rat Poison\n" + "ItemDamage = 2\n" + "ItemName = Piece of Rope\n"
        + "ItemDamage = 2\n" + "\n" + "no players\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "\n" + "");
    assertEquals(expected, s);
  }

  @Test
  public void testLookAroundWithoutPet() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    world.addPlayer("Player 2", PlayerType.HUMAN, 7);
    String s = world.lookaround("Player 1");
    String expected = String.format("Current roomdetails =\n" + "roomNum = 6\n"
        + "roomName = Green House\n" + "roomDimension = 28, 26, 35, 29\n" + "ItemName = Trowel\n"
        + "ItemDamage = 2\n" + "ItemName = Pinking Shears\n" + "ItemDamage = 2\n" + "\n"
        + "the players are\n" + "[Player 1]\n" + "pet=No pet\n" + "target=No target\n" + "\n"
        + "Current playerdetails = \n" + "player name = Player 1\n" + "player type = HUMAN\n"
        + "player position =room num 6\n" + "items = No items\n" + " \n"
        + "neighbors = [Hedge Maze]\n" + "Below is the neighbor details\n" + "roomNum = 7\n"
        + "roomName = Hedge Maze\n" + "roomDimension = 30, 20, 35, 25\n" + "ItemName = Loud Noise\n"
        + "ItemDamage = 6\n" + "\n" + "the players are\n" + "[Player 2]\n" + "pet=No pet\n"
        + "target=No target\n" + "\n" + "\n" + "");
    assertEquals(expected, s);
  }

  @Test
  public void testgetPlayerTypeCurrentTurn() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    world.addPlayer("Player 2", PlayerType.HUMAN, 7);
    String turn = world.getTurn();
    assertEquals(PlayerType.HUMAN, world.getPlayerTypeCurrentTurn());
  }

  @Test
  public void testgetCurrentLocationPlayer() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    assertEquals(6, world.getCurrentLocationPlayer());

  }

  @Test(expected = IllegalArgumentException.class)

  public void testAddPlayerRestriction() {
    world.addPlayer("Player 1", PlayerType.HUMAN, 6);
    world.addPlayer("Player 2", PlayerType.HUMAN, 7);
    world.addPlayer("Player 3", PlayerType.HUMAN, 8);
    world.addPlayer("Player 4", PlayerType.HUMAN, 9);
    world.addPlayer("Player 5", PlayerType.HUMAN, 2);
    world.addPlayer("Player 6", PlayerType.HUMAN, 1);
    world.addPlayer("Player 7", PlayerType.HUMAN, 10);
    world.addPlayer("Player 8", PlayerType.HUMAN, 11);
    world.addPlayer("Player 9", PlayerType.HUMAN, 12);
    world.addPlayer("Player 10", PlayerType.HUMAN, 13);
    world.addPlayer("Player 11", PlayerType.HUMAN, 14);
  }

  /*
   * @Test public void testAttackTarget() {
   * 
   * }
   */

  /**
   * A helper method to instantiate WorldImpl class.
   */
  protected World worldHelper(Scanner input, int maxturns) {
    return new WorldImpl(input, maxturns);
  }
}
