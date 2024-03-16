package controllertest;

import madworld.madworldcontroller.Features;
import madworld.madworldmadview.SwingWorldView;

/**
 * Mock view class in order to mock the main game window for individual
 * controller testing.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public class MockViewSwing implements SwingWorldView {

  private StringBuilder log;
  private final int uniqueCode;
  private String inputs;

  /**
   * Constructor to accept below parameters, and initialize the variables of the
   * mock view for the game window.
   * 
   * @param log        an appendable to append the input logs
   * @param uniqueCode an unique code sent by the controller to test the output
   * @param inputs     mocking the input from the user to the view
   */
  public MockViewSwing(StringBuilder log, int uniqueCode, String inputs) {

    this.log = log;
    this.uniqueCode = uniqueCode;
    this.inputs = inputs;
  }

  @Override
  public void setFeatures(Features f) {
    log.append("Input: " + f + "\n");
    log.append(uniqueCode);

  }

  @Override
  public void setDisplay(String message) {
    log.append("Input: " + message + "\n");
    log.append(uniqueCode);

  }

  @Override
  public void refresh() {
    log.append(uniqueCode);

  }

  @Override
  public void makeVisible() {

    log.append(uniqueCode);
  }

  @Override
  public String getInputAddPlayer(String message) {

    log.append("Input: " + message + "\n" + Integer.toString(uniqueCode));

    if (inputs == null) {
      throw new IllegalArgumentException("Playername can not be null");
    }

    String output = this.inputs;

    return output;
  }

  @Override
  public String getInputAddComputerPlayer(String message) {

    log.append("Input: " + message + "\n" + Integer.toString(uniqueCode));

    if (inputs == null) {
      throw new IllegalArgumentException("Playername can not be null");
    }

    String output = this.inputs;

    return output;
  }

  @Override
  public String processPickInput(String input) {

    log.append("Input: " + input + "\n" + Integer.toString(uniqueCode));

    String output = this.inputs;

    return output;
  }

  @Override
  public String processAttackInput(String input) {

    log.append("Input: " + input + "\n" + Integer.toString(uniqueCode));

    String output = this.inputs;

    return output;
  }

  @Override
  public void showStatus(String message) {
    log.append("Input: " + message + "\n");
    log.append(uniqueCode);

  }

}
