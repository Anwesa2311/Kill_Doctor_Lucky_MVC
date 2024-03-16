package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the AttackTarget command which will be taken as an user
 * input in the console controller.This command will attack a target if the
 * target is in player's room.
 * 
 */

public class AttackTarget implements Command {

  private String playerName;
  private String itemName;
  private String result;

  /**
   * Constructor.
   * 
   * @param playerName the name of the player to add.
   * @param itemName   the name of the item to attack the target.
   * 
   * @throws IllegalArgumentException if playerName or itemName is invalid.
   * 
   */

  public AttackTarget(String playerName, String itemName) {
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (("").equals(itemName) || itemName == null) {
      throw new IllegalArgumentException("Invalid item name");
    }
    this.playerName = playerName;
    this.itemName = itemName;
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");
    }

    result = w.attackTargetForPlayers(playerName, itemName);
  }

  @Override
  public String getResult() {

    return this.result;
  }

}
