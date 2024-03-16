package madworld.madworldmodel;

/**
 * Implementation of Pet class which represents a pet and its attributes and
 * several functionalities related to that.
 * 
 */

public class PetImplModel implements Pet {

  private final String petName;
  private final int totalRooms;
  private int currentposition;

  /**
   * PetImplModel constructor to get values for the below parameters.
   *
   * @param petName    The name of the pet.
   * @param totalRooms The total number of rooms in the world.
   * 
   *
   * 
   * 
   * @throws IllegalArguement exception for the following criteria:- petName
   *                          should not be blank or null totalRooms should not be
   *                          negative attacker should not be null or blank.
   * 
   * 
   */
  public PetImplModel(String petName, int totalRooms) {
    if (("").equals(petName) || petName == null) {
      throw new IllegalArgumentException("petName can not be blank or null");
    }
    if (totalRooms < 0) {
      throw new IllegalArgumentException("Total rooms can not be negative");
    }

    this.petName = petName;
    this.totalRooms = totalRooms;
    this.currentposition = 0;

  }

  @Override
  public String getPetName() {

    return petName;
  }

  @Override
  public void movePet(int roomNum) {

    if (roomNum < 0 || roomNum >= totalRooms) {
      throw new IllegalArgumentException("roomNum is invalid");
    }

    this.currentposition = roomNum;

  }

  @Override
  public int getPetPosition() {

    return currentposition;
  }

  @Override
  public String toString() {
    return String.format("pet name = %s \n pet pos = %d", this.petName, this.currentposition);
  }

}
