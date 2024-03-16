package madworld.madworldcontroller;

//import madworld.madworldmodel.IllegalArguement;
import madworld.madworldmodel.World;

/**
 * Move class implementation for move command.
 *
 */
public class Move implements Command {

  private String playerName;
  private int row;
  private int col;
  private String result;

  /**
   * Move constructor to get values for the below parameters.
   *
   * @param playerName The name of a player
   * @param row        The row coordinates of the destination the player wants to
   *                   move to
   * @param col        The column coordinates of the destination the player wants
   *                   to move to
   * 
   */

  public Move(String playerName, int row, int col) {

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid input");

    }

    this.playerName = playerName;
    this.row = row;
    this.col = col;
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    result = w.movePlayerWithCoordinates(playerName, this.row, this.col);

  }

  @Override
  public String getResult() {

    return result;
  }

}
