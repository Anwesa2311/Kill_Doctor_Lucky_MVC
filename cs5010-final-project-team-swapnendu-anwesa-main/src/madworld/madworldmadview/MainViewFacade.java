package madworld.madworldmadview;

import madworld.madworldcontroller.Features;
import madworld.madworldmodel.ReadOnlyWorld;

/**
 * Interface which serves as the blueprint for the facade implementation of the
 * game, reducing the coupling between all the views and the controller, as per
 * the facade design pattern.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public interface MainViewFacade {

  /**
   * Function to start the game with the default configuration.
   */
  public void startGame();

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param ft the set of feature callbacks as a Features object
   */

  public void setFeatures(Features ft);

  /**
   * Function to display the main screen to the user.
   */
  void showMainScreen();

  /**
   * Function to start the game with the new configuration provided by user.
   * 
   * @param m read-only object of the model to allow the view to access model data
   *          without allowing the view to manipulate it
   */
  void startGameWithNewFile(ReadOnlyWorld m);

  /**
   * Function to display a message to the user after each action taken. The
   * message is displayed in a dialog box, through the SwingWorldViewImpl view.
   * 
   * @param message The message to be displayed to the user.
   */
  void displayMessage(String message);

  /**
   * Function to refresh the game view after each turn.
   */
  void viewRefresh();

  /**
   * Function to help a human player pick an item using the pickItem function. It
   * accepts an input prompt from the controller which is displayed to the user in
   * an input dialog box, through the SwingWorldViewImpl view. The user's response
   * is then accepted from the SwingWorldViewImpl view and forwarded to the
   * controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */
  String askForPickinput(String input);

  /**
   * Function to help a human player attack the target using the attackTarget
   * function. It accepts an input prompt from the controller which is displayed
   * to the user in an input dialog box, through the SwingWorldViewImpl view. The
   * user's response is then accepted from the SwingWorldViewImpl view and
   * forwarded to the controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */
  public String askForAttackInput(String input);

  /**
   * Function to display the status of the game as prompted by the controller when
   * a game is deemed to be over.
   * 
   * @param message Prompt sent by the controller to be displayed to the user
   *                through the SwingWorldViewImpl view
   */
  public void showFinalStatus(String message);

  /**
   * Function to help add a computer player to the game. It accepts an input
   * prompt from the controller which is displayed to the user in an input dialog
   * box through the SwingWorldViewImpl view. The user's response is then
   * forwarded to the controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */
  String askForInputForAddComputerPlayer(String input);

  /**
   * Function to help add a human player to the game. It accepts an input
   * prompt from the controller which is displayed to the user in an input dialog
   * box through the SwingWorldViewImpl view. The user's response is then
   * forwarded to the controller.
   * 
   * @param input Prompt sent by the controller to assist the user to input
   * @return The input sent by the user
   */
  String askInputForAddPlayer(String input);

}
