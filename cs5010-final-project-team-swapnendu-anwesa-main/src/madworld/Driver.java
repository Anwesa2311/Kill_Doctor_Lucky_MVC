package madworld;

///import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.imageio.ImageIO;
import madworld.madworldcontroller.MadworldConsoleController;
import madworld.madworldmadview.MainViewFacade;
import madworld.madworldmadview.MainViewFacadeImpl;
import madworld.madworldmadview.SwingWorldView;
import madworld.madworldmadview.SwingWorldViewImpl;
import madworld.madworldmodel.World;
import madworld.madworldmodel.WorldImpl;

/**
 * Implementation of Driver class to test scenarios.
 */
public class Driver {

  /**
   * Taking a file from command line consisting the details and passing it to the
   * World to test scenarios in the main method.
   * 
   * @param args takes a argument parameter
   */

  public static void main(String[] args) {

    // Scanner in = new Scanner(System.in);
    // System.out.println("Please enter filename");
    // String filename = in.nextLine();
    // String filename = "C:\\Users\\anwes\\Downloads\\mansion.txt";

    String filename = args[0];

    try {
      Scanner input = new Scanner(new File(filename));
      int maxturns = Integer.parseInt(args[1]);
      World world1 = new WorldImpl(input, maxturns);
      /*
       * Readable input1 = new InputStreamReader(System.in); Appendable output =
       * System.out;
       */
      SwingWorldView view = new SwingWorldViewImpl(world1);

      MainViewFacade view1 = new MainViewFacadeImpl(world1);

      new MadworldConsoleController(view1, world1).playGame();

    } catch (FileNotFoundException ex) {
      System.out.println("No File Found!");
      return;
    } catch (IOException e) {
      System.out.println("Image not found");
    } catch (NumberFormatException e7) {
      System.out.println("Please provide a valid maxturns");
    }

  }

}
