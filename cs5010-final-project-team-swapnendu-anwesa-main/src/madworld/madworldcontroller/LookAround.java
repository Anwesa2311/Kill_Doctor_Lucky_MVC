package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the LookAround command which will
 * be taken as an user input in the console controller.This
 * This command will help the user lookaround a room the player
 * is currently in.
 * 
 */

public class LookAround implements Command {

  private String playerName;
  private String result;
  
  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   * 
   * 
   * @throws IllegalArgumentException if playerName is invalid.
   * 
   */

  public LookAround(String playerName) {
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("player name is invalid");
    }

    this.playerName = playerName;
    this.result = "";

  }

  @Override
  public void execute(World w) {
    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    this.result = w.lookaround(playerName);

  }

  @Override
  public String getResult() {

    return this.result;
  }

}
