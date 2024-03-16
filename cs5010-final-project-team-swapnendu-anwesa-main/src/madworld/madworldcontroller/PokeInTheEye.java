package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the MovePet command which will
 * be taken as an user input in the console controller.This
 * command will  help the user poke in the eye of target which will
 * do damage of 1.
 * 
 */

public class PokeInTheEye implements Command {

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

  public PokeInTheEye(String playerName) {
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("invalid playerName");
    }

    this.playerName = playerName;
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    result = w.pokeInEye(playerName);

  }

  @Override
  public String getResult() {

    return result;
  }

}
