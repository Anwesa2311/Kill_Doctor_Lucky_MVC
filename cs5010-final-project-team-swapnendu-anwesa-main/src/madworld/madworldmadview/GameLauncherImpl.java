
package madworld.madworldmadview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.View;
import madworld.madworldcontroller.Features;

/**
 * Implementation of the launcher class. It will display the about screen to the
 * user, along with allowing the user to start a new game with a new or existing
 * configuration (map). The user can also quit the game from this launcher
 * screen.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public class GameLauncherImpl extends JFrame implements LauncherInterface {

  /**
   * Declaring the variables which are to be used in the launcher class.
   */
  private static final long serialVersionUID = 3262664515335890392L;
  private JLabel welcome;
  private JLabel gameName;
  private JLabel teamName;
  private JLabel anwesaDetails;
  private JLabel swapnenduDetails;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem menuItemstartNew;
  private JMenuItem menuItemstartDefault;
  private File selectedFile;
  private JFileChooser fileChooser;
  private JMenuItem menuItemQuit;

  /**
   * Constructor to initialize the variables to be used in the launcher class.
   */
  public GameLauncherImpl() {

    super("Milestone 4 - The View, Implementation - Team Swapnendu Anwesa");
    setSize(600, 300);
    setLocation(512, 384);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    getContentPane().setLayout(null);

    this.welcome = new JLabel("Welcome to the game\n");

    welcome.setBounds(138, 47, 438, 34);
    getContentPane().add(welcome);

    this.gameName = new JLabel("Kill Dr. Lucky!");
    gameName.setBounds(138, 91, 438, 14);
    getContentPane().add(gameName);

    this.teamName = new JLabel("Team Swapnendu Anwesa");
    teamName.setBounds(137, 140, 439, 14);
    getContentPane().add(teamName);

    this.anwesaDetails = new JLabel("Anwesa Basu (basu.anw@northeastern.edu)");
    anwesaDetails.setBounds(141, 165, 435, 14);
    getContentPane().add(anwesaDetails);

    this.swapnenduDetails = new JLabel(" Swapnendu Majumdar (majumdar.s@northeastern.edu)");
    swapnenduDetails.setBounds(137, 190, 439, 14);
    getContentPane().add(swapnenduDetails);

    this.menuBar = new JMenuBar();
    menuBar.setBounds(0, 0, 49, 22);
    getContentPane().add(menuBar);

    this.menu = new JMenu("Launch");
    menuBar.add(menu);

    this.menuItemstartDefault = new JMenuItem("Start game (default config)");

    menu.add(menuItemstartDefault);

    this.menuItemstartNew = new JMenuItem("Start game (new config)");
    this.fileChooser = new JFileChooser();
    this.selectedFile = null;

    // menuItemstartNew.

    menu.add(menuItemstartNew);

    this.menuItemQuit = new JMenuItem("Quit");

    menu.add(menuItemQuit);

  }

  @Override
  public void start(Features ft) {

    if (ft == null) {
      throw new IllegalArgumentException("object can not be null");
    }

    setVisibleOn();
    setFeatures(ft);

  }

  private void setFeatures(Features f) {
    if (f == null) {
      throw new IllegalArgumentException("object can not be null");
    }

    menuItemstartDefault.addActionListener(l -> f.clickJmenuItemStartDefault());
    menuItemstartNew.addActionListener(l -> f.clickJmenuItemStartNew());
    menuItemQuit.addActionListener(l -> f.clickJmenuItemQuit());

    menuItemstartNew.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(menuItemstartNew);
        if (result == JFileChooser.APPROVE_OPTION) {
          try {
            File selectedFile = fileChooser.getSelectedFile();
            // System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            Scanner input = null;

            input = new Scanner(selectedFile);
            f.processNewFile(input);
          } catch (FileNotFoundException e1) {
            JOptionPane.showMessageDialog(rootPane, e1);
          }
        }
      }
    });
  }

  @Override

  public void setVisibleOn() {
    setVisible(true);
  }

}
