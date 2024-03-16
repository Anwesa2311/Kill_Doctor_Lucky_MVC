package madworld.madworldcontroller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import madworld.madworldmadview.MainViewFacade;
import madworld.madworldmadview.SwingWorldView;
import madworld.madworldmodel.Player.PlayerType;
import madworld.madworldmodel.ReadOnlyWorld;
import madworld.madworldmodel.Space;
import madworld.madworldmodel.World;
import madworld.madworldmodel.WorldImpl;

//import madworld.madworldmodel.Player;

/**
 * MadworldConsoleController class represents a console controller which
 * interacts with the model to pass the input from the user and return the
 * current state and proper output returned by the model to the user.This
 * Controller uses the command design pattern in order to implement the commands
 * entered by the user.
 *
 */

public class MadworldConsoleController implements MadworldController, Features {

  private Command cmd;
  /*
   * private final Appendable out; private final Scanner scan;
   */
  private MainViewFacade view;
  private World model;
  private ReadOnlyWorld readOnlyModel;
  private String result;

  /**
   * Constructor for the console controller class.
   * 
   * @param view1 Object of the view
   * @param w     Object of the model
   */
  public MadworldConsoleController(MainViewFacade view1, World w) {
    if (view1 == null || w == null) {
      throw new IllegalArgumentException("model or view objects can not be null");
    }
    this.view = view1;
    this.model = w;
    this.cmd = null;
    this.result = "";
    this.readOnlyModel = null;
  }

  @Override
  public void playGame() {
    view.setFeatures(this);
    view.startGame();

  }

  private String gameOver() {
    String status = "";
    if (model.isGameOver()) {

      if (model.getWinner().equals("")) {
        status = "The game is over as it reached max turns and target escaped\n";
      } else {
        status = String.format(
            "\nThe game is over as target is killed" + " and the winner is:-%s \n",
            model.getWinner());

      }
    }
    return status;

  }

  @Override
  public void pressKeyLookaround() {
    cmd = null;
    String playerName = model.getTurn();
    if (!(" ").equals(playerName)) {
      if (model.getPlayerTypeCurrentTurn() == PlayerType.HUMAN) {

        if (model.isGameOver()) {
          String status = gameOver();
          view.showFinalStatus(status);
          return;
        }
        try {
          cmd = new LookAround(playerName);
          executeCommand();
        } catch (IllegalArgumentException e23) {
          this.result = e23.getMessage();
        }

      } else {
        this.result = "Press L will not work as not the turn of a Human";
      }
    } else {
      this.result = "Can not lookaround as no player added";
    }
    view.displayMessage(this.result);
    view.viewRefresh();
  }

  @Override
  public void pressKeyAttack() {

    String playerName = model.getTurn();

    if (!(" ").equals(playerName)) {

      if (model.getPlayerTypeCurrentTurn() == PlayerType.HUMAN) {
        String input = view.askForAttackInput("Enter the item name to attack target");
        if (model.isGameOver()) {
          String status = gameOver();
          view.showFinalStatus(status);
          return;
        }
        try {
          cmd = new AttackTarget(playerName, input);
          executeCommand();
          String ouput = this.result;
        } catch (IllegalArgumentException e17) {
          this.result = e17.getMessage();
        }
      } else {
        this.result = "Press A will not work as not the turn of a Human";
      }
    } else {
      this.result = "Can not attack as no player added";
    }
    view.displayMessage(this.result);
    view.viewRefresh();
  }

  @Override
  public void pressKeyPickItem() {

    cmd = null;

    String playerName = model.getTurn();
    if (!(" ").equals(playerName)) {
      if (model.getPlayerTypeCurrentTurn() == PlayerType.HUMAN) {
        String itemname = view.askForPickinput("Enter the item name to pick");

        if (model.isGameOver()) {
          String status = gameOver();
          view.showFinalStatus(status);
          return;
        }
        try {
          cmd = new Pickitem(playerName, itemname);
          executeCommand();
          // String ouput = this.result;
        } catch (IllegalArgumentException e16) {
          this.result = e16.getMessage();
        }
      } else {
        this.result = "Press p will not work as not the turn of a Human";
      }
    } else {
      this.result = "Can not pick item as no player added yet";
    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }

  @Override
  public void handleClickMove(int row, int col) {

    this.result = "";

    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid position to move");
    }
    String playername = model.getTurn();
    if (!(" ").equals(playername)) {
      if (model.isGameOver()) {
        String status = gameOver();
        view.showFinalStatus(status);
        return;
      }
      try {
        // String roomName = list.get(nextRoom).getRoomName();
        cmd = new Move(playername, row, col);
        executeCommand();
      } catch (IllegalArgumentException e14) {
        this.result = e14.getMessage();
      }
    } else {
      this.result = "Can not move as no player added";
    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }

  @Override
  public void clickButtonAddHuman() {

    this.result = "";

    String input = view.askInputForAddPlayer(
        "Enter name of the new player and the room number where you want the player to start");
    try {
      String[] input1 = input.split(" ");
      String playerName = input1[0];
      int roomNum = Integer.parseInt(input1[1]);
      if (model.isGameOver()) {
        String status = gameOver();
        view.showFinalStatus(status);
        return;
      }
      try {
        cmd = new Addhuman(playerName, PlayerType.HUMAN, roomNum);
        executeCommand();
        // String ouput = this.result;
      } catch (IllegalArgumentException e21) {
        this.result = e21.getMessage();
      }
    } catch (NumberFormatException n) {
      this.result = "Invalid input";

    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }

  private void executeCommand() {
    if (cmd != null) {
      try {
        cmd.execute(model);
        this.result = cmd.getResult();
        cmd = null;
      } catch (IllegalArgumentException e7) {
        this.result = e7.getMessage();

      }

    }

  }

  @Override
  public void clickBtnAddComputer() {

    this.result = "";
    String playerName = "";
    int roomNum = 0;
    String input = view.askForInputForAddComputerPlayer("Enter name of the new computer player and "
        + "the room number where you want the player to start");
    try {
      String[] input1 = input.split(" ");
      playerName = input1[0];
      roomNum = Integer.parseInt(input1[1]);
    } catch (NumberFormatException n) {
      this.result = "Invalid input";

    }
    // view.displayMessageForAddComputer(this.result);
    // view.viewRefresh();
    if (!("Invalid input").equals(this.result)) {
      if (model.isGameOver()) {
        String status = gameOver();
        view.showFinalStatus(status);
        return;
      }
      try {
        cmd = new Addcomputer(playerName, roomNum);
        executeCommand();
        // String output = this.result;
      } catch (IllegalArgumentException e10) {
        this.result = e10.getMessage();
      }
    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }

  @Override
  public void clickJmenuItemStartDefault() {
    view.showMainScreen();

  }

  @Override
  public void clickJmenuItemStartNew() {

    this.result = "";
    try {
      view.startGameWithNewFile(this.readOnlyModel);
    } catch (IllegalArgumentException e3) {
      this.result = e3.getMessage();

    }
    if (!("").equals(this.result)) {
      this.result = "File not chosen";
      view.displayMessage(this.result);
    }
  }

  @Override
  public void clickJmenuItemQuit() {

    System.exit(0);
  }

  @Override
  public void processNewFile(Scanner file) {
    if (file == null) {
      view.displayMessage("File can not be empty!!");
    }
    int maxTurn = model.getMaxTurns();
    this.readOnlyModel = new WorldImpl(file, maxTurn);

  }

  @Override
  public void pressKeyComputerAction() {

    this.result = "";

    String playername = model.getTurn();
    if (!(" ").equals(playername)) {

      if (model.getPlayerTypeCurrentTurn() == PlayerType.COMPUTER) {
        if (model.isGameOver()) {
          String status = gameOver();
          view.showFinalStatus(status);
          return;
        }
        try {
          cmd = new ComputerAction(playername);
          executeCommand();
          String output = this.result;
        } catch (IllegalArgumentException e18) {
          this.result = e18.getMessage();
        }
      } else {
        this.result = "Press c will not work as not a turn of Computer";
      }
    } else {
      this.result = "Cant take computerAction as no computer player added";
    }
    view.displayMessage(this.result);
    view.viewRefresh();
  }

  @Override
  public void clickPlayerButton(String playerName) {
    if (playerName == null || ("").equals(playerName)) {
      throw new IllegalArgumentException("Invalid playerName");
    }

    try {
      this.result = model.displayPlayerForController(playerName);
    } catch (IllegalArgumentException e24) {
      this.result = e24.getMessage();
    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }

  @Override
  public void pressKeyPoke() {
    cmd = null;
    String playerName = model.getTurn();
    if (!(" ").equals(playerName)) {
      if (model.getPlayerTypeCurrentTurn() == PlayerType.HUMAN) {

        if (model.isGameOver()) {
          String status = gameOver();
          view.showFinalStatus(status);
          return;
        }
        try {
          cmd = new PokeInTheEye(playerName);
          executeCommand();
        } catch (IllegalArgumentException e23) {
          this.result = e23.getMessage();
        }

      } else {
        this.result = "Press L will not work as not the turn of a Human";
      }
    } else {
      this.result = "Can not lookaround as no player added";
    }
    view.displayMessage(this.result);
    view.viewRefresh();

  }
}
