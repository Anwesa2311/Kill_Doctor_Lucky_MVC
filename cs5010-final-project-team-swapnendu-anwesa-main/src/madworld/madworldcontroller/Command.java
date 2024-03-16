package madworld.madworldcontroller;

import madworld.madworldmodel.World;

/**
 * Command interface is used to declare the methods which are getting overriden
 * by different command concrete classes.Command interface represents an interface 
 * to execute commands and get the result of those commands from model.
 *
 */

public interface Command {
  
  /**
   * Executes the given command functionality.
   * 
   * @param w as a model object
   *
   */
  
  void execute(World w);
  
  /**
   * Gets the result from the model by invoking model's methods 
   * and store it in a String.
   * 
   * @return a String consists of the result from the model.
   *
   */
  
  String getResult();

}
