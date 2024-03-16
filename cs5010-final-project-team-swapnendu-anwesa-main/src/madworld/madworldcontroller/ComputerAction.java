package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the ComputerAction command which will be taken as an user
 * input in the console controller.This command will trigger an action for computer player.
 * 
 */

public class ComputerAction implements Command {

  private final String playerName;
  private String result;
  
  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   *
   *@throws IllegalArgumentException if playerName is invalid. 
   */


  public ComputerAction(String playerName) {
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid input");
    }

    this.playerName = playerName;
    this.result = "";
  }

  @Override
  public void execute(World w) {
    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    this.result = w.actionForComputer(playerName);

  }

  @Override
  public String getResult() {

    return this.result;
  }

}
