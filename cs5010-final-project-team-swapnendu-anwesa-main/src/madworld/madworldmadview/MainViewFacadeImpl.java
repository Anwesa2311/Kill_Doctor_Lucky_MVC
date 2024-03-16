package madworld.madworldmadview;

import madworld.madworldcontroller.Features;
import madworld.madworldmodel.ReadOnlyWorld;

/**
 * Facade class, implemented in order to reduce coupling between the game
 * controller and view, as per the facade design pattern. It implements the
 * MainViewFacade interface.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public class MainViewFacadeImpl implements MainViewFacade {

  private ReadOnlyWorld world;
  private Features ft;
  private SwingWorldView swing;
  private LauncherInterface launch;

  /**
   * Constructor to initialize the variables of the facade class.
   * 
   * @param w Object of type ReadOnlyWorld, to enable the view to accept
   *          information from the world model, without being able to mutate the
   *          same.
   */
  public MainViewFacadeImpl(ReadOnlyWorld w) {
    if (w == null) {
      throw new IllegalArgumentException("object can not be null");
    }
    this.world = w;
    this.ft = null;
    this.launch = new GameLauncherImpl();
    this.swing = new SwingWorldViewImpl(this.world);
  }

  @Override
  public void startGame() {

    launch.start(ft);
    launch.setVisibleOn();

  }

  @Override

  public void showMainScreen() {

    swing.setFeatures(ft);
    swing.makeVisible();

  }

  @Override

  public void displayMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("message can not be null");
    }
    swing.setDisplay(message);
  }

  @Override

  public String askInputForAddPlayer(String input) {
    if (input == null || ("").equals(input)) {
      throw new IllegalArgumentException("Input can not be null or empty");
    }
    String input1 = swing.getInputAddPlayer(input);
    return input1;
  }

  @Override

  public String askForInputForAddComputerPlayer(String input) {
    if (input == null || ("").equals(input)) {
      throw new IllegalArgumentException("Input can not be null or empty");
    }

    String input1 = swing.getInputAddComputerPlayer(input);
    return input1;
  }

  @Override
  public void viewRefresh() {
    swing.refresh();
  }

  @Override
  public void startGameWithNewFile(ReadOnlyWorld m) {

    if (m != null) {
      SwingWorldView swing = new SwingWorldViewImpl(m);
      swing.makeVisible();
    } else {
      throw new IllegalArgumentException("Model can not be null");
    }

  }

  @Override
  public void setFeatures(Features feature) {

    if (feature == null) {
      throw new IllegalArgumentException("Object can not be null");
    }

    this.ft = feature;
  }

  @Override
  public String askForPickinput(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input can not be null");
    }
    String result = swing.processPickInput(input);

    return result;
  }

  @Override
  public String askForAttackInput(String input) {

    if (input == null) {
      throw new IllegalArgumentException("input can not be null");
    }

    String result = swing.processAttackInput(input);
    return result;
  }

  @Override
  public void showFinalStatus(String message) {

    if (message == null || ("").equals(message)) {
      throw new IllegalArgumentException("Input can not be null or empty");
    }
    swing.showStatus(message);

  }

}
