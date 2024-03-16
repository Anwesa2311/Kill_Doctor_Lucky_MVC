package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the ShowTarget command which will be taken as an user
 * input in the console controller.This this command will help the user showing
 * the current details of the target in the world.
 * 
 */
public class ShowTarget implements Command {

  private String result;

  /**
   *  Default Constructor.
   * 
   * 
   */
  public ShowTarget() {
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");

    }
    result = w.showTarget();

  }

  @Override
  public String getResult() {

    return this.result;
  }

}
