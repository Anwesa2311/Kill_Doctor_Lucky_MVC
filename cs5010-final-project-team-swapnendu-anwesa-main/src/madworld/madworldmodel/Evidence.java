package madworld.madworldmodel;

/**
 * Interface Evidence which represents evidences that were used to attack the
 * target by a player.
 */
public interface Evidence {

  /**
   * Get the evidence name.
   *
   * @return the evidence name.
   */
  public String getEvidenceName();

  /**
   * Get the evidence damage.
   *
   * @return the evidence damage.
   */
  public int getEvidenceDamage();

  /**
   * Get the attacker name who last used the evidence to attack the target.
   *
   * @return the name of the attacker.
   */
  public String getAttacker();

}
