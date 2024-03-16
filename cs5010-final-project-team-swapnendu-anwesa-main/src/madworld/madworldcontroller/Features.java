package madworld.madworldcontroller;

//import java.io.File;
import java.util.Scanner;

/**
 * 
 * Features class representing the set of features that the game offers. The
 * features have been exposed as individual functions, which are used as
 * callback by the view to pass control to the controller.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public interface Features {

  /**
   * Feature to implement the lookAround function by pressing a key on the
   * keyboard.
   * 
   */
  public void pressKeyLookaround();

  /**
   * Feature to implement the attack function by pressing a key on the keyboard.
   * 
   */
  public void pressKeyAttack();

  /**
   * Feature to implement the pickItem function by pressing a key on the keyboard.
   * 
   */
  public void pressKeyPickItem();

  /**
   * Feature to implement the handleClickMove function by clicking on a location
   * within the program window.
   * 
   * @param row The row component of the destination that the player is attempting
   *            to move to
   * @param col The column component of the destination that the player is
   *            attempting to move to
   */
  public void handleClickMove(int row, int col);

  /**
   * Feature to implement the addPlayerHuman function by clicking the button.
   */
  public void clickButtonAddHuman();

  /**
   * Feature to implement the addPlayerComputer function by clicking the button.
   */
  public void clickBtnAddComputer();

  /**
   * Feature to start a new game using the default configuration by clicking the
   * "Start new game (Default configuration)" menu item in the "Launch" menu.
   */
  public void clickJmenuItemStartDefault();

  /**
   * Feature to start a new game using the new configuration provided by the user
   * by clicking the "Start new game (New configuration)" menu item in the
   * "Launch" menu.
   * 
   */
  public void clickJmenuItemStartNew();

  /**
   * Feature to quit the launcher by clicking the "Quit game" menu item in the
   * "Launch" menu.
   * 
   */
  public void clickJmenuItemQuit();

  // public void processNewFile(File file);

  /**
   * Feature to allow the controller to process the new configuration file
   * provided to the program by the user,which is accepted by the view.
   * 
   * @param file The file specified by the user as the new configuration
   */
  void processNewFile(Scanner file);

  /**
   * Feature to implement a computer action by pressing a key on the keyboard.
   */
  public void pressKeyComputerAction();

  /**
   * Feature to implement the displayPlayerInfo function by clicking the button
   * associated to the player.
   * 
   * @param playerName Name of the player whose information is to displayed by the
   *                   player.
   */
  public void clickPlayerButton(String playerName);

  /**
   * Feature to implement the pokeInTheEye function by pressing a key on the
   * keyboard.
   */
  public void pressKeyPoke();

}
