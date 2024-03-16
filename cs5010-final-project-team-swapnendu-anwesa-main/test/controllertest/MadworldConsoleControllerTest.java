package controllertest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;
import madworld.madworldcontroller.MadworldConsoleController;
import madworld.madworldmadview.MainViewFacade;
import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.World;
import org.junit.Test;

/**
 * Test cases for madworldConsoleController controller, using mocks for readable
 * and appendable.
 */
public class MadworldConsoleControllerTest {

  @Test
  public void testAddHuman() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "", null);
    // Sending the input(playername and roomnum) to the mockview class which in
    // normal game user gives to
    // the view in the jOptionPane.
    MainViewFacade v = new MockViewClass(viewlog, 144566, "Anny 0");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    // c.playGame();

    c.clickButtonAddHuman();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[0];
    String modeluniquecode = lines[1];
    // The input is correctly going to the mockmodel class from the mock view
    // through the controller
    assertEquals("Input: Anny HUMAN 0", input);
    assertEquals("123432", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    // This is the input controller passes to the view for asking the details
    // This input is successfully getting passed to the view.
    String expectedaskInput = "Input:" + " Enter name of the new player and"
        + " the room number where you want the player to start";
    assertEquals(expectedaskInput, askInput);
    assertTrue(r.contains("144566"));

  }

  @Test
  public void testAddComputer() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "", null);
    MainViewFacade v = new MockViewClass(viewlog, 1445660, "Comp 0");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    // c.playGame();

    c.clickBtnAddComputer();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[0];
    String modeluniquecode = lines[1];
    assertEquals("Input: Comp 0", input);
    assertEquals("1234321", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    String expectedaskInput = "Input: " + "Enter name of the new computer player and "
        + "the room number where you want the player to start";
    assertEquals(expectedaskInput, askInput);
    assertTrue(r.contains("1445660"));

  }

  @Test
  public void testPickItem() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "Revolver");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyPickItem();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modeluniquecode = lines[0];
    assertEquals("Input: Anwesa Revolver", input);
    assertEquals("1234321", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    String expectedaskInput = "Input: Enter the item name to pick";
    assertEquals(expectedaskInput, askInput);
    assertTrue(r.contains("14456601"));

  }

  @Test
  public void testLookAround() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Swappy", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "no input needed");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyLookaround();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modeluniquecode = lines[0];
    assertEquals("Input: Swappy", input);
    assertEquals("1234321", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));

  }

  @Test
  public void testAttackTarget() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "Revolver");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyAttack();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modeluniquecode = lines[0];
    assertEquals("Input: Anwesa Revolver", input);
    assertEquals("1234321", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    String expectedaskInput = "Input: Enter the item name to attack target";
    assertEquals(expectedaskInput, askInput);
    assertTrue(r.contains("14456601"));
  }

  @Test
  public void testPokeTarget() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyPoke();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modeluniquecode = lines[0];
    assertEquals("Input: Anwesa", input);
    assertEquals("1234321", modeluniquecode);

    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));
  }

  @Test
  public void testshowPlayerDesc() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.clickPlayerButton("Anwesa");
    String[] lines = modellog.toString().split("\\n");
    String input = lines[0];
    String modelUnique = lines[1];
    assertEquals("Input: Anwesa", input);
    assertEquals("1234321", modelUnique);
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));
  }

  @Test

  public void testMovePlayer() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    int row = 25;
    int col = 20;
    c.handleClickMove(row, col);
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modelUnique = lines[0];
    assertEquals("Input: Anwesa 25 20", input);
    assertEquals("1234321", modelUnique);
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));
  }

  @Test

  public void testComputerAction() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234231, "Comp", PlayerType.COMPUTER);
    MainViewFacade v = new MockViewClass(viewlog, 14456603, "abc");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyComputerAction();
    String[] lines = modellog.toString().split("\\n");
    String input = lines[1];
    String modelUnique = lines[0];
    assertEquals("Input: Comp", input);
    assertEquals("1234231", modelUnique);
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String askInput = viewLines[0];
    String r = viewLines[1];
    assertTrue(r.contains("14456603"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234231, "Comp", PlayerType.COMPUTER);
    MainViewFacade v = new MockViewClass(viewlog, 14456603, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.handleClickMove(-10, -20);
  }

  @Test
  public void testInvalidAttacktarget() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, " ");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyAttack();
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));
    assertTrue(r.contains("Invalid input"));

  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidShowPlayerDesc() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.clickPlayerButton(null);

  }

  @Test

  public void testInvalidPickItem() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 1234321, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 14456601, null);
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.pressKeyPickItem();
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String r = viewLines[1];
    assertTrue(r.contains("14456601"));
    assertTrue(r.contains("Invalid itemname"));
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidAddHuman() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "", null);
    MainViewFacade v = new MockViewClass(viewlog, 144566, null);
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    // c.playGame();

    c.clickButtonAddHuman();
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidAddComputer() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "", null);
    MainViewFacade v = new MockViewClass(viewlog, 144566, null);
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    // c.playGame();

    c.clickBtnAddComputer();
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidProcessFile() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "", null);
    MainViewFacade v = new MockViewClass(viewlog, 144566, null);
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    // c.playGame();

    c.processNewFile(null);
  }

  @Test

  public void startGameWithNewFile() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 144566, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.clickJmenuItemStartNew();
    String s = viewlog.toString();
    String[] viewLines = s.split("\n");
    String sa = viewLines[0];
    String r = viewLines[1];
    assertTrue(sa.contains("Input: File not chosen"));
    assertTrue(r.contains("144566"));
  }

  @Test

  public void startGameWithDefaultFile() {
    StringBuilder modellog = new StringBuilder();
    StringBuilder viewlog = new StringBuilder();
    World w = new MockModelclass(modellog, 123432, "Anwesa", PlayerType.HUMAN);
    MainViewFacade v = new MockViewClass(viewlog, 144566, "");
    MadworldConsoleController c = new MadworldConsoleController(v, w);
    c.clickJmenuItemStartDefault();
    String s = viewlog.toString();
    assertTrue(s.contains("144566"));
  }
}