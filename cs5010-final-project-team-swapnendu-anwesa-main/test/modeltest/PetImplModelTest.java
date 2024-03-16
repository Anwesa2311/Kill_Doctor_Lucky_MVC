package modeltest;

import static org.junit.Assert.assertEquals;

import madworld.madworldmodel.Pet;
import madworld.madworldmodel.PetImplModel;
import madworld.madworldmodel.Player.PlayerType;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for testing the PetImplModel class.
 */
public class PetImplModelTest {
  private Pet pet1;
  private Pet pet2;

  /**
   * Creating pet instance.
   */

  @Before
  public void setUp() {

    pet1 = new PetImplModel("Pickachu", 20);

  }

  @Test
  public void testGetPetName() {
    String expected = "Pickachu";
    assertEquals(expected, pet1.getPetName());
  }

  @Test
  public void testStartingPosition() {
    int expected = 0;
    assertEquals(expected, pet1.getPetPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetPetName() {
    String expected = "";
    pet2 = new PetImplModel("", 20);
    assertEquals(expected, pet2.getPetName());
  }

  @Test
  public void testMovePet() {
    pet1.movePet(3);
    int expected = 3;
    assertEquals(expected, pet1.getPetPosition());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePet() {
    pet1.movePet(-7);

  }

  @Test
  public void testGetPetPosition() {
    int expected = 0;
    assertEquals(expected, pet1.getPetPosition());
  }

}
