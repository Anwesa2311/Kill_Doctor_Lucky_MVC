package madworld.madworldmodel;

/**
 * Interface Pet which represents a pet in the world.
 */
public interface Pet {

  /**
   * Get the pet name.
   *
   * @return the pet name.
   */
  public String getPetName();

  /**
   * Moves the pet to the given position.
   * 
   * @param roomNum the room number to move the pet to.
   *
   */

  public void movePet(int roomNum);

  /**
   * Get the current position of the pet.
   *
   * @return the current pet position.
   */

  public int getPetPosition();

}
