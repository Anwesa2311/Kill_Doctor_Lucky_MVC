package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * Pickitem class implementation for pick command for human player.
 *
 */

public class Pickitem implements Command {

  private String playerName;
  private String itemName;
  private String result;

  /**
   * Pickitem constructor to get values for the below parameters.
   *
   * @param playerName The name of a player
   * @param itemname   The name of the item to pick
   * 
   * 
   */
  public Pickitem(String playerName, String itemname) {

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid playername");
    }

    if (("").equals(itemname) || itemname == null) {
      throw new IllegalArgumentException("Invalid itemname");
    }

    this.playerName = playerName;
    this.itemName = itemname;
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }
    result = w.pickItemsPlayer(playerName, itemName);

  }

  @Override
  public String getResult() {

    return result;
  }
}
