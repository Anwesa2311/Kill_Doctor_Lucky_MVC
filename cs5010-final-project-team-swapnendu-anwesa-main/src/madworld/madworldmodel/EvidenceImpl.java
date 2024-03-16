package madworld.madworldmodel;

/**
 * Implementation of Evidence implementation class which represents evidences
 * and their attributes which were used to attack a target by a player.
 * 
 */

public class EvidenceImpl implements Evidence {

  private final String evidenceName;
  private final int damage;
  private final String attacker;

  /**
   * EvidenceImpl constructor to get values for the below parameters.
   *
   * @param evidenceName The name of the evidence
   * @param damage       The damage of the evidence
   * @param attacker     The attacker who last used this to attack item.
   *
   * 
   * 
   * @throws IllegalArguement exception for the following criteria:- evidencename
   *                          should not be blank or null damage should not be
   *                          negative attacker should not be null or blank.
   * 
   * 
   */

  public EvidenceImpl(String evidenceName, int damage, String attacker) {
    if (("").equals(evidenceName) || evidenceName == null) {
      throw new IllegalArgumentException("Item name can not be null");
    }
    if (damage < 0) {
      throw new IllegalArgumentException("damage can not be negative");
    }
    if (("").equals(attacker) || attacker == null) {
      throw new IllegalArgumentException("Invalid input");
    }
    this.evidenceName = evidenceName;
    this.damage = damage;
    this.attacker = attacker;
  }

  @Override
  public String getEvidenceName() {

    return this.evidenceName;
  }

  @Override
  public int getEvidenceDamage() {

    return this.damage;
  }

  @Override
  public String getAttacker() {

    return this.attacker;
  }

}
