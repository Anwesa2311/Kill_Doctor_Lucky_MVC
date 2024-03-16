package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * this class represents the ShowPet command which will be taken as an user
 * input in the console controller.This this command will help the user showing
 * the current details of the pet in the world.
 * 
 */

public class ShowPet implements Command {

  private String result;
  
  /**
   *  Default Constructor.
   * 
   * 
   */
  public ShowPet() {
    this.result = "";
  }

  @Override
  public void execute(World w) {

    if (w == null) {
      throw new IllegalArgumentException("Model can not be null");

    }
    result = w.showPet();

  }

  @Override
  public String getResult() {

    return this.result;
  }

}
