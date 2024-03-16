package madworld.madworldmadview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Taskbar.Feature;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import madworld.madworldcontroller.Features;
import madworld.madworldmodel.ReadOnlyWorld;
import madworld.madworldmodel.Space;

/**
 *
 * Class to implement the JPanel,which facilitates the graphics implementation
 * of the game view in SwingWorldViewImpl class.
 *
 * @author basu.anw, majumdar.s
 *
 */
public class PaintWorldMap extends JPanel {

  /**
   * Declaring the variables, to be used by the PaintWorldMap, required to
   * implement the graphics using JPanel.
   */
  private static final long serialVersionUID = -8034450484282813404L;
  private ReadOnlyWorld world;
  private int targetLocation;

  /**
   * Constructor method to initialize the variables of the PaintWorldMap class.
   *
   * @param w Object of type ReadOnlyWorld, to enable the view to accept
   *          information from the world model, without being able to mutate the
   *          same.
   */

  public PaintWorldMap(ReadOnlyWorld w) {
    if (w == null) {
      throw new IllegalArgumentException("World can not be null");
    }
    this.world = w;
    this.targetLocation = 0;

    // this.targetLocation = w.getTargetLocation();

  }

  @Override
  public void paintComponent(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException("Invalid parameter");
    }
    List<Space> roomList = world.getRoomDetails();

    // List<Space> roomList = getRoomObject();
    for (int i = 0; i < roomList.size(); i++) {

      g.setColor(Color.DARK_GRAY);
      super.setBackground(Color.DARK_GRAY);

      int[] a = roomList.get(i).getRoomDimension();

      // int b[] = list2.get(j).getRoomDimension();
      int x1a = a[0];
      int y1a = a[1];
      int x2a = a[2] + 1;
      int y2a = a[3] + 1;

      g.drawLine(y1a * 25, x1a * 25, y2a * 25, x1a * 25);
      g.drawLine(y1a * 25, x1a * 25, y1a * 25, x2a * 25);
      g.drawLine(y1a * 25, x2a * 25, y2a * 25, x2a * 25);
      g.drawLine(y2a * 25, x2a * 25, y2a * 25, x1a * 25);

      g.setColor(Color.DARK_GRAY);
      int height;
      int width;
      if (y1a > x1a) {
        height = (y1a - x1a);
      } else {
        height = (x1a - y1a);
      }
      if (y2a > x2a) {
        width = (y2a - x2a);
      } else {
        width = (x2a - y2a);
      }
      String str = roomList.get(i).getRoomName();

      g.drawString(str, y1a * 25, x1a * 25 + 20);
      targetLocation = world.getTargetLocation();
      int targetHealth = world.getTargetHealth();
      if (targetLocation == i && targetHealth > 0) {
        g.setColor(Color.RED);
        String s = String.format("%s%s(%d)", "Target", "\n", targetHealth);
        g.drawString(s, y2a * 25 - 100, x2a * 25 - 5);

        // g.drawString("Target is here", y2a*25-13, x2a*25-5);

        g.setColor(Color.BLACK);
      }
      // players = roomList.get(i).getPlayers();
      g.setColor(Color.BLUE);
      // g.drawString(players, y2a*25-100, x2a*25-25);
      g.setColor(Color.BLACK);
      // g.drawImage(image, 40, 40, Color.WHITE, null);

      // g.drawRect(leftx, lefty, height, width);
      showStatus(g);

      // addMouseListener(new MouseAdapterExtension(this.F));

    }
  }

  private void showStatus(Graphics g) {

    if (g != null) {
      if (!world.isGameOver()) {
        String turn = world.getTurn();
        if (!" ".equals(turn) || turn.isEmpty()) {
          // message.setText(String.format("Current turn %s",turn));
          int currentLoc = world.getCurrentLocationPlayer();
          String output = String.format("Current turn %s , Current Location room num %d", turn,
              currentLoc);
          g.setColor(Color.RED);
          g.drawString(output, 250, 25);
        }

        // } else if (world.getWinner().equals("")) {
        // // message.setText("The game is a draw as it reached max turns");
        // String output = "The game is a draw as it reached max turns";
        // g.setColor(Color.RED);
        // g.drawString(output, 250, 25);
        // } else {
        // // message.setText(String.format("%s","%s","The game is over and the winner
        // // is",world.getWinner()));
        // String output = String.format("%s%s", "The game is over and the winner is ",
        // world.getWinner());
        // g.setColor(Color.RED);
        // g.drawString(output, 250, 25);

      }
      // this.add(message);
      revalidate();
    }
  }

}