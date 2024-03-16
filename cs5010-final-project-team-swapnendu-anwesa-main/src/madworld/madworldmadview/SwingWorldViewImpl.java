package madworld.madworldmadview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import madworld.madworldcontroller.Features;
import madworld.madworldmodel.ReadOnlyWorld;
import madworld.madworldmodel.Space;
import madworld.madworldmodel.WorldImpl;

/**
 * Class to implement the main view of the game. It implements the
 * SwingWorldView interface.
 * 
 * @author basu.anw, majumdar.s
 *
 */
public class SwingWorldViewImpl extends JFrame implements SwingWorldView {

  /**
   * Declaring the variables required by the class.
   * 
   */
  private static final long serialVersionUID = -5893499250346962588L;
  private ReadOnlyWorld world;
  private PaintWorldMap paint;
  private LauncherInterface launcher;
  private JMenuBar menuBar;
  private JMenu addPlayerMenu;
  private JMenuItem addHumanPlayerMenuItem;
  private JMenuItem addCompPlayerMenuItem;
  private String input;
  private JScrollPane scroll;
  private JButton[] playerBtn;
  private Features ft;

  /*
   * public static void main(String[] args) { try { Scanner sc = new Scanner(new
   * File( "C:\\Users\\majumdar.s\\OneDrive - Northeastern University\\Documents\\
   * GitHub\\cs5010-project-SwapnenduM\\src\\LuckyMansion\\mansion.txt" ));
   * ReadOnlyWorld T = (ReadOnlyWorld) new WorldImpl(sc, 15); SwingWorldView frame
   * = new SwingWorldViewImpl(T); ((Window) frame).setVisible(true); } catch
   * (Exception e) { e.printStackTrace(); } }
   */

  /**
   * Constructor to initialize the variables for the class to implement the main
   * view of the game.
   * 
   * @param w Object of type ReadOnlyWorld, to enable the view to accept
   *          information from the world model, without being able to mutate the
   *          same.
   */
  public SwingWorldViewImpl(ReadOnlyWorld w) {
    super("Milestone 4 - The View, Implementation - Team Swapnendu Anwesa");
    if (w == null) {
      throw new IllegalArgumentException("ReadOnly model object can not be null");
    }
    this.world = w;
    this.launcher = new GameLauncherImpl();
    this.setLocation(400, 4);
    this.setPreferredSize(new Dimension(800, 950));
    // this.setSize(600, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // launcher.start();
    this.paint = new PaintWorldMap(world);
    paint.setLocation(0, 0);
    // paint.setLayout(new BorderLayout());
    paint.setLayout(null);
    // paint.setForeground(Color.DARK_GRAY);
    paint.setMinimumSize(new Dimension(300, 300));
    paint.setPreferredSize(new Dimension(2048, 1768));
    scroll = new JScrollPane(paint, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    getContentPane().add(scroll);

    // button = new JButton("Let's go!");
    // button.setFont(new Font("Arial", Font.PLAIN, 8));
    // button.setBounds(75, 75, 40, 15);
    // button.setMargin(new Insets(0, 0, 0, 0));
    // paint.add(button);
    // //paint.remove(button);
    // button.setBounds(100, 100, 40, 15);
    // paint.add(button);

    // this.getContentPane().add(scroll, BorderLayout.CENTER);

    // this.getContentPane().add(scroll, BorderLayout.CENTER);

    // gameStatus = new JLabel("Welcome to the game!");
    // scroll.setColumnHeaderView(gameStatus);

    this.menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    this.addPlayerMenu = new JMenu("Add player");
    menuBar.add(addPlayerMenu);

    this.addHumanPlayerMenuItem = new JMenuItem("Add Human Player");
    addHumanPlayerMenuItem.setActionCommand("Add new human player");
    addPlayerMenu.add(addHumanPlayerMenuItem);

    this.addCompPlayerMenuItem = new JMenuItem("Add Computer Player");
    addCompPlayerMenuItem.setActionCommand("Add new computer player");
    // addCompPlayerMenuItem.
    addPlayerMenu.add(addCompPlayerMenuItem);
    // addButtons();
    //
    this.input = "";
    this.playerBtn = new JButton[10];
    this.ft = null;
    // this.refresh();
    this.pack();

  }

  private void addButtons() {
    List<Space> roomList = world.getRoomDetails();

    // List<Space> roomList = getRoomObject();
    for (int i = 0; i < roomList.size(); i++) {

      int[] a = roomList.get(i).getRoomDimension();
      String players = roomList.get(i).getPlayers();
      if (players.length() > 2) {

        players = players.substring(1, players.length() - 1);
        String[] playerList = players.split(", ");
        playerBtn = new JButton[playerList.length];
        int x1a = a[0];
        int y1a = a[1];
        int x2a = a[2] + 1;
        int y2a = a[3] + 1;

        for (int j = 0; j < playerList.length; j++) {

          playerBtn[j] = new JButton(playerList[j].strip());
          paint.add(playerBtn[j]);

          playerBtn[j].setFocusable(false);
          playerBtn[j].setFont(new Font("Arial", Font.PLAIN, 8));
          playerBtn[j].setMargin(new Insets(0, 0, 0, 0));
          playerBtn[j].setBounds(y2a * 25 - 41, x1a * 25 + j * 15 + 2, 40, 15);
          String playerName = playerBtn[j].getText();
          playerBtn[j].addActionListener(l -> ft.clickPlayerButton(playerName));
          // playerBtn[j].addActionListener(l
          // ->ft.clickPlayerButton(playerBtn[j].getText()));
        }
      }
    }
  }

  @Override
  public void setFeatures(Features f) {

    if (f == null) {
      throw new IllegalArgumentException("object can not be null");
    }

    this.ft = f;

    addHumanPlayerMenuItem.addActionListener(l -> f.clickButtonAddHuman());
    addCompPlayerMenuItem.addActionListener(l -> f.clickBtnAddComputer());
    this.addKeyListener(new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p' || e.getKeyChar() == 'P') {
          f.pressKeyPickItem();
        }
        if (e.getKeyChar() == 'l' || e.getKeyChar() == 'L') {
          f.pressKeyLookaround();
        }
        if (e.getKeyChar() == 'c' || e.getKeyChar() == 'C') {
          f.pressKeyComputerAction();
        }
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A') {
          f.pressKeyAttack();
        }
        if (e.getKeyChar() == 'k' || e.getKeyChar() == 'K') {
          f.pressKeyPoke();
        }
      }

    });

    // this.addMouseListener(new MouseAdapter());

    paint.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent m) {
        // System.out.println(m.getComponent());
        int x = m.getX();
        int y = m.getY();
        int row = x / 25;
        int col = y / 25;
        try {
          f.handleClickMove(row, col);
        } catch (IllegalArgumentException e1) {
          JOptionPane.showMessageDialog(null, "Invalid board position");
        } catch (IllegalStateException s1) {
          JOptionPane.showMessageDialog(null, "Game is over!");
        }
      }

      @Override
      public void mouseReleased(MouseEvent m) {

        // System.out.println(m.getButton());
        int x = m.getX();
        int y = m.getY();
        int col = x / 25;
        int row = y / 25;
        try {
          f.handleClickMove(row, col);
        } catch (IllegalArgumentException e1) {
          JOptionPane.showMessageDialog(null, "Invalid board position");
        } catch (IllegalStateException s1) {
          JOptionPane.showMessageDialog(null, "Game is over!");
        }
      }

    });

  }

  @Override
  public void setDisplay(String message) {

    if (message != null || !("").equals(message)) {
      JOptionPane.showMessageDialog(rootPane, message);
    } else {
      throw new IllegalArgumentException("message can not be null");
    }
  }

  @Override
  public String getInputAddPlayer(String message) {

    if (message != null || !("").equals(message)) {
      input = JOptionPane.showInputDialog(rootPane, message);
    } else {
      throw new IllegalArgumentException("Invalid message");
    }
    return input;
  }

  @Override
  public String getInputAddComputerPlayer(String message) {

    if (message != null || !("").equals(message)) {

      input = JOptionPane.showInputDialog(rootPane, message);

    } else {
      throw new IllegalArgumentException("Invalid message");
    }
    return input;
  }

  @Override
  public void refresh() {
    // showStatus();
    paint.removeAll();
    repaint();
    addButtons();
    //setBtnFeatures();

    // mapButtons();
  }

  private void setBtnFeatures() {
    if (playerBtn != null) {
      for (int i = 0; i < playerBtn.length; i++) {
        String playerName = playerBtn[i].getText();
        playerBtn[i].addActionListener(l -> ft.clickPlayerButton(playerName));
      }
    }
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public String processPickInput(String input) {
    if (input == null) {
      throw new IllegalArgumentException("Input can not be null");
    }
    this.input = JOptionPane.showInputDialog(rootPane, input);
    return this.input;
  }

  @Override
  public String processAttackInput(String input) {

    if (input == null) {
      throw new IllegalArgumentException("input can not be null");
    }
    this.input = JOptionPane.showInputDialog(rootPane, input);
    return this.input;
  }

  @Override
  public void showStatus(String message) {

    if (message == null || ("").equals(message)) {
      throw new IllegalArgumentException("message can not be null");
    }
    JOptionPane.showMessageDialog(rootPane, message);

  }

}