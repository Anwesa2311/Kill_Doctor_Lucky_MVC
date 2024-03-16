package madworld.madworldcontroller;

import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.World;

/**
 * this class represents the Addhuman command.
 * 
 */

public class Addhuman implements Command {

  private final String playerName;
  private final PlayerType type;
  private final int position;
  private String result;

  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   * @param type       the type of the player to add.
   * @param position   of the player to add.
   * 
   * @throws IllegalArgumentException if playerName,type,position are invalid. 
   */

  public Addhuman(String playerName, PlayerType type, int position) {

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }

    if (type == PlayerType.COMPUTER) {
      throw new IllegalArgumentException("Invalid player type");
    }

    if (position < 0) {
      throw new IllegalArgumentException("Invalid player position");
    }

    this.playerName = playerName;
    this.type = type;
    this.position = position;
    this.result = "";

  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }

    result = w.addPlayer(playerName, type, position);

  }

  @Override
  public String getResult() {

    return result;
  }

}
