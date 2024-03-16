package madworld.madworldmodel;

/**
 * The Target interface declaration.
 *
 */
public interface Target {

  /**
   * Get the target name.
   *
   * @return the target name.
   */
  String getTargetName();

  /**
   * Get the target health.
   *
   * @return the target health.
   */

  int getTargetHealth();

  /**
   * Get the target position.
   *
   * @return the target position.
   */
  int getTargetPos();

  /**
   * Moves the target in the world.
   *
   * 
   */
  void moveTarget();

  /**
   * Set the target health.
   *
   * @param damage the target damage.
   */
  void setTargetHealth(int damage);

}
