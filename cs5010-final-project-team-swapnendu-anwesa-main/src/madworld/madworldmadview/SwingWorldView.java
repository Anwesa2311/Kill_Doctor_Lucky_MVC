package madworld.madworldmadview;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import madworld.madworldcontroller.Features;

/**
 * Interface to act as the blueprint for the main game window class. It is being
 * implemented in the SwingWorldViewImpl class.
 * 
 * @author basu.anw, majumdar.s
 *
 */

public interface SwingWorldView {

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param ft the set of feature callbacks as a Features object
   */

  public void setFeatures(Features ft);

  /**
   * Function to display a message to the user after each action taken. The
   * message is displayed in a dialog box.
   * 
   * @param message The message to be displayed to the user.
   */
  public void setDisplay(String message);

  /**
   * Function to refresh the game view after each turn.
   */
  public void refresh();

  /**
   * Function to set the visibility of the view object to true.
   */
  public void makeVisible();

  /**
   * Function to help add a human player to the game. It accepts an input prompt
   * from the controller which is displayed to the user in an input dialog box.
   * The user's response is then forwarded to the controller.
   * 
   * @param message Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */
  public String getInputAddPlayer(String message);

  /**
   * Function to help add a computer player to the game. It accepts an input
   * prompt from the controller which is displayed to the user in an input dialog
   * box. The user's response is then forwarded to the controller.
   * 
   * @param message Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */

  public String getInputAddComputerPlayer(String message);

  /**
   * Function to help a human player pick an item using the pickItem function. It
   * accepts an input prompt from the controller which is displayed to the user in
   * an input dialog box. The user's response is then forwarded to the controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */

  public String processPickInput(String input);

  /**
   * Function to help a human player attack the target using the attackTarget
   * function. It accepts an input prompt from the controller which is displayed
   * to the user in an input dialog box. The user's response is then forwarded to
   * the controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */

  public String processAttackInput(String input);

  /**
   * Function to display the status of the game as prompted by the controller when
   * a game is deemed to be over.
   * 
   * @param message Prompt sent by the controller to be displayed to the user
   */
  public void showStatus(String message);

}
