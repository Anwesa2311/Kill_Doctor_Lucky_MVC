package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the addcomputer command.
 * 
 */

public class Addcomputer implements Command {

  private final String playerName;
  private String result;
  private int position;

  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   * @param position   the position where the player will be added.
   * 
   * @throws IllegalArgumentException if playerName and position are invalid.
   */

  public Addcomputer(String playerName, int position) {

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (position < 0) {
      throw new IllegalArgumentException("position can not be negative");
    }

    this.playerName = playerName;
    this.result = "";
    this.position = position;
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    this.result = w.addComputerPlayer(playerName, position);

  }

  @Override
  public String getResult() {

    return result;
  }

}
