package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the MovePet command which will
 * be taken as an user input in the console controller.This
 * command will  help the user moving the pet.
 * 
 */


public class MovePet implements Command {

  public String playerName;
  public int nextPos;
  public String result;
  
  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   * @param nextPos the position of the pet to move to next.
   * 
   * @throws IllegalArgumentException if playerName or nextPos is invalid.
   * 
   */

  public MovePet(String playerName, int nextPos) {
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid petname");
    }
    if (nextPos < 0) {
      throw new IllegalArgumentException("Invalid next position");
    }
    this.playerName = playerName;
    this.nextPos = nextPos;
    this.result = "";
  }

  @Override
  public void execute(World w) {
    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }

    result = w.movePetForPlayers(playerName, nextPos);

  }

  @Override
  public String getResult() {

    return result;
  }

}
