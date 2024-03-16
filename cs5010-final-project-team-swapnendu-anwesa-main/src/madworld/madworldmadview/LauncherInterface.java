package madworld.madworldmadview;

import madworld.madworldcontroller.Features;

/**
 * Interface for the game launcher class, acting as the blueprint for the same.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public interface LauncherInterface {

  /**
   * Set the visibility of the view as true.
   *
   */

  void setVisibleOn();

  /**
   * 
   * Function to start the game.
   * 
   * @param ft the set of feature callbacks as a Features object
   */

  void start(Features ft);

}
