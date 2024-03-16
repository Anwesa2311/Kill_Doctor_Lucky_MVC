package modeltest;



import static org.junit.Assert.assertEquals;

import madworld.madworldmodel.Target;
import madworld.madworldmodel.TargetImplModel;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for testing the TargetImplModel class.
 */

public class TargetImplModelTest {
  private Target target1;

  /**
   * Creating target instance.
   */
  @Before
  public void setUp() {

    target1 = new TargetImplModel("Lucky", 10, 20);

  }

  @Test
  public void testGetTargetName() {
    String expected = "Lucky";
    assertEquals(expected, target1.getTargetName());
  }

  @Test(expected = IllegalArgumentException.class)

  public void testInvalidTargetName() {
    Target target2 = new TargetImplModel("", 10, 20);

  }

  @Test
  public void testGetTargetHealth() {
    int expected = 10;
    assertEquals(10, target1.getTargetHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTargetHealth() {
    Target target2 = new TargetImplModel("L", -10, 20);

  }

  @Test
  public void testGetTargetPos() {
    int pos = 0;
    assertEquals(pos, target1.getTargetPos());
  }

  @Test
  public void testMoveTarget() {
    int startingPos = 0;
    assertEquals(startingPos, target1.getTargetPos());
    target1.moveTarget();
    int newPos = 1;
    assertEquals(newPos, target1.getTargetPos());

  }

  @Test
  public void testToString() {
    String expected = String
        .format("Target Name=Lucky\n" + "Target Health=10\n" + "Target Position=0\n");
    assertEquals(expected, target1.toString());
  }

  @Test
  public void testSetTargetHealth() {
    int damage = 2;
    int newHealth = 8;
    target1.setTargetHealth(damage);
    assertEquals(newHealth, target1.getTargetHealth());
  }

}
