package controllertest;

import madworld.madworldcontroller.Features;
import madworld.madworldmadview.MainViewFacade;
import madworld.madworldmodel.ReadOnlyWorld;

/**
 * Mock view class in order to mock the main view facade for individual
 * controller testing.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public class MockViewClass implements MainViewFacade {

  private StringBuilder log;
  private final int uniqueCode;
  private MockViewSwing swing;
  private String inputs;

  /**
   * Constructor to accept below parameters, and initialize the variables of the
   * mock view for the main view facade.
   * 
   * @param log        an appendable to append the input logs
   * @param uniqueCode an unique code sent by the controller to test the output
   * @param inputs     mocking the input from the user to the view
   */

  public MockViewClass(StringBuilder log, int uniqueCode, String inputs) {
    this.log = log;
    this.uniqueCode = uniqueCode;
    this.inputs = inputs;
    this.swing = new MockViewSwing(log, uniqueCode, inputs);

  }

  @Override
  public void startGame() {

    log.append(uniqueCode);
  }

  @Override
  public void setFeatures(Features ft) {
    log.append("Input: " + ft + "\n");
    log.append(uniqueCode);

  }

  @Override
  public void showMainScreen() {
    log.append(uniqueCode);

  }

  @Override
  public void startGameWithNewFile(ReadOnlyWorld m) {
    if (m == null) {
      throw new IllegalArgumentException("model can not be null");
    }
    log.append("Input: " + m + "\n");
    log.append(uniqueCode);

  }

  @Override
  public void displayMessage(String message) {
    swing.setDisplay(message);

  }

  @Override
  public void viewRefresh() {
    swing.refresh();

  }

  @Override
  public String askForPickinput(String input) {

    return swing.processPickInput(input);
  }

  @Override
  public String askForAttackInput(String input) {

    return swing.processAttackInput(input);
  }

  @Override
  public void showFinalStatus(String message) {

    swing.showStatus(message);
  }

  @Override
  public String askForInputForAddComputerPlayer(String input) {

    return swing.getInputAddComputerPlayer(input);
  }

  @Override
  public String askInputForAddPlayer(String input) {

    return swing.getInputAddPlayer(input);
  }

}
