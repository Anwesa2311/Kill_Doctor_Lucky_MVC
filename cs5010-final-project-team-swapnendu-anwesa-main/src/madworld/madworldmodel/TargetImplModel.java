package madworld.madworldmodel;

/**
 * Implementation of Target implementation class which represents target and its
 * attributes and several functionalities related to that.
 * 
 */

public class TargetImplModel implements Target {

  private String targetName;
  private int targetHealth;
  private int totalRooms;
  private int position;

  /**
   * TargetImplModel constructor to get values for the below parameters.
   *
   * @param targetName   The name of the target
   * @param targetHealth The health of the target.
   * @param totalRooms   The total number of rooms n the world
   * 
   * 
   * @throws IllegalArguement exception for the following criteria:- target name
   *                          can not be blank or null target health can not be
   *                          negative totalRooms can not be negative
   * 
   */

  public TargetImplModel(String targetName, int targetHealth, int totalRooms) {
    if (("").equals(targetName) || targetName == null) {
      throw new IllegalArgumentException("Invalid target name");
    }
    if ((targetHealth < 0)) {
      throw new IllegalArgumentException("Invalid target health");
    }
    if (totalRooms < 0) {
      throw new IllegalArgumentException("Invalid totalRooms");
    }

    this.targetName = targetName;
    this.targetHealth = targetHealth;
    this.totalRooms = totalRooms;
    this.position = 0;
  }

  @Override
  public String getTargetName() {

    return targetName;
  }

  @Override
  public int getTargetHealth() {

    return targetHealth;
  }

  @Override
  public int getTargetPos() {

    return position;
  }

  @Override
  public void moveTarget() {
    int currentPosition = this.position;
    int nextPosition = currentPosition + 1;
    int totalRooms = this.totalRooms;
    if (nextPosition > 0) {
      if (nextPosition < totalRooms) {
        this.position = nextPosition;
      } else if (nextPosition == totalRooms) {
        this.position = 0;
      }
    } else {
      throw new IllegalStateException("current position of the char can not be negative");
    }
    nextPosition = this.position;
    if ((nextPosition - currentPosition) != 1
        && (currentPosition - nextPosition) != (totalRooms - 1)) {
      throw new IllegalStateException("Invalid Move");
    }

  }

  @Override

  public String toString() {
    return String.format("Target Name=%s\nTarget Health=%s\nTarget Position=%d\n", this.targetName,
        this.targetHealth, this.position);
  }

  @Override
  public void setTargetHealth(int damage) {

    if (damage < 0) {
      throw new IllegalArgumentException("Invalid damage");
    }

    this.targetHealth = this.targetHealth - damage;
  }
}
