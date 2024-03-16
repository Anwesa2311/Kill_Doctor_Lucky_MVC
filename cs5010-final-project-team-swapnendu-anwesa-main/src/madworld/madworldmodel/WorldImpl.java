
package madworld.madworldmodel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
//import java.awt.Color;
//import java.awt.geom.Line2D.Float;
import java.util.Scanner;
import java.util.Stack;
import madworld.madworldmodel.Player.PlayerType;

/**
 * A class implementation for world which consists of specified rooms and items
 * in those rooms.It also consists a target character and every rooms have
 * specified neighbors.This class is implemented to display details about target
 * character,move its position and also to display room details.
 */

public class WorldImpl implements World, ReadOnlyWorld {

  private final int worldRows;
  private final int worldColumns;
  private final String targetChar;
  private final String worldName;
  // public final List worldSpec;
  private final List<String> roomSpec;
  private final List<String> itemSpec;
  private int targetHealth;
  private final int totalRoom;
  private final int totalItem;
  private int[][] worldSize;
  private Space spaceinstance;
  private boolean wall;
  private List<Space> list2;
  private List<List<Space>> neighbor;
  private List<Space> list;
  private List<Space> roomObjects;
  private int currentPosition;
  private List<String> playeritem;
  private List<String> playerturn;
  private List<String> players;
  private boolean isLookaround;
  private boolean ismoveplayer;
  private boolean ispickitem;
  private String turn;
  private List<String> neighbornames;
  private Map<String, Player> playermap;
  private Map<String, ComputerPlayerModel> compPlayerMap;
  private Map<String, PlayerImpModel> humanPlayerMap;
  private int noOfTurns;
  private final int maxTurns;
  private final String petName;
  private final Pet pet;
  private final Target target;
  private boolean movePet;
  private String winner;
  private boolean isVisible;
  private boolean isAttack;
  private final List<List<Item>> itemList;
  private String result;
  private List<Evidence> evidenceList;
  private List<String> evidences;
  private List<List<Integer>> vertices;

  /**
   * World constructor to get a file consisting world descriptions.
   *
   * @param inputfile a file containing the details of the world desc
   * @param maxturns  of a game
   * 
   * 
   * 
   * @throws IllegalArguement exception for the following criteria:- The number of
   *                          rows and columns should be valid and also target
   *                          char name and target health should be string and not
   *                          blank.totalRooms should be atleast 20.
   * 
   */
  public WorldImpl(Scanner inputfile, int maxturns) {

    if (inputfile == null) {
      throw new IllegalArgumentException("The input can not be null");
    }

    if (maxturns < 0) {
      throw new IllegalArgumentException("maxturns can not be negative");
    }

    Scanner in = inputfile;

    // this.worldSpec = worldSpec;
    // this.roomSpec = roomSpec;
    // this.itemSpec = itemSpec;

    String str = in.nextLine();
    String[] str1 = str.split(" ", 3);
    final String str2 = in.nextLine();
    final String worldName = str1[2];
    this.petName = in.nextLine();
    if ("".equals(petName) || petName == null) {
      throw new IllegalArgumentException("pet name can not be null or blank");
    }
    this.totalRoom = Integer.parseInt(in.nextLine());

    for (int i = 0; i < worldName.length(); i++) {
      if (Character.isDigit(worldName.charAt(i))) {
        throw new IllegalArgumentException(
            "Room name can not contain number.Enter a valid room name");
      }

    }
    if ((" ").equals(worldName) || (worldName.equals(null))) {
      throw new IllegalArgumentException("Room name can not be blank");
    }
    if ((totalRoom < 20)) {
      throw new IllegalArgumentException("total rooms or total items can not be less than 20");

    }
    int worldRows = Integer.parseInt(str1[0]);
    int worldColumns = Integer.parseInt(str1[1]);
    if ((worldRows < 0) || (worldColumns < 0)) {
      throw new IllegalArgumentException("Invalid world dimensions.It can not be negative");
    }
    String[] str3 = str2.split(" ", 2);
    String targetName = str3[1];
    if ((" ").equals(targetName) || (targetName == null)) {
      throw new IllegalArgumentException("Target name can not be blank");
    }
    for (int i = 0; i < targetName.length(); i++) {
      if (Character.isDigit(worldName.charAt(i))) {
        throw new IllegalArgumentException(
            "Target name can not contain number.Enter a valid room name");
      }

    }
    int targetHealth = Integer.parseInt(str3[0]);
    if (targetHealth < 0) {
      throw new IllegalArgumentException("Target health can not be negative");
    }
    this.roomSpec = new ArrayList<>(totalRoom);
    for (int i = 0; i < totalRoom; i++) {
      this.roomSpec.add(in.nextLine());
    }

    this.totalItem = Integer.parseInt(in.nextLine());
    if (totalItem < 20) {
      throw new IllegalArgumentException("Total items can not be negative");
    }

    itemSpec = new ArrayList<>(totalItem);
    for (int i = 0; i < totalItem; i++) {
      itemSpec.add(in.nextLine());
    }
    this.worldRows = worldRows;
    this.worldColumns = worldColumns;
    this.worldName = worldName;
    this.targetChar = targetName;
    this.targetHealth = Integer.parseInt(str3[0]);
    this.roomObjects = new ArrayList<>(totalRoom);
    this.list2 = new ArrayList<>();
    this.list = new ArrayList<>();
    this.neighbor = new ArrayList<>();
    this.playeritem = new ArrayList<>();
    this.playerturn = new ArrayList<>();
    this.players = new ArrayList<>();
    this.playermap = new HashMap<String, Player>();
    this.compPlayerMap = new HashMap<String, ComputerPlayerModel>();
    this.humanPlayerMap = new HashMap<String, PlayerImpModel>();
    this.currentPosition = 0;
    this.isLookaround = false;
    this.ismoveplayer = false;
    this.ispickitem = false;
    this.turn = " ";
    this.neighbornames = new ArrayList();
    this.noOfTurns = 0;
    this.maxTurns = maxturns;
    this.pet = new PetImplModel(this.petName, this.totalRoom);
    this.movePet = false;
    this.target = new TargetImplModel(this.targetChar, this.targetHealth, this.totalRoom);
    this.winner = "";
    this.isVisible = false;
    this.isAttack = true;
    this.itemList = new ArrayList<>();
    this.result = "";
    this.evidenceList = new ArrayList<>();
    this.evidences = new ArrayList<>();
    this.vertices = new ArrayList<>();
    createRoomObject();
    createNeighbor();
    addPetInRoom();
    addTargetInRoom();
    createItems();

  }

  @Override
  public int getWorldRows() {

    return this.worldRows;

  }

  @Override
  public int getWorldColumns() {

    return this.worldColumns;

  }

  /*
   * private String getPlayerTurn() { return turn;
   * 
   * }
   */
  @Override
  public String getTargetChar() {

    return this.targetChar;
  }

  @Override
  public String getWorldName() {

    return this.worldName;
  }

  @Override
  public int targetHealth() {

    return this.targetHealth;

  }

  @Override
  public int getCurrentPos() {

    return this.currentPosition;

  }

  @Override
  public int getNoOfTurns() {

    return this.noOfTurns;

  }

  @Override
  public String getTargetInfo() {

    String info = target.toString();
    return info;
  }

  @Override
  public void moveTargetChar() {

    Space s1 = list.get(target.getTargetPos());
    target.moveTarget();
    Space s2 = list.get(target.getTargetPos());
    s2.addTarget(targetChar, targetHealth);
    s1.removeTarget();

  }

  @Override
  public int getTotalRooms() {
    return this.totalRoom;
  }

  private void createRoomObject() {
    List roomspec = this.roomSpec;
    int leftrow;
    int leftcolumn;
    int rightrow;
    int rightcolumn;
    String roomname;
    List itemList = this.itemSpec;
    for (int i = 0; i < roomSpec.size(); i++) {
      String str1 = (String) roomspec.get(i);
      str1 = str1.trim();
      String[] str2 = (str1.split("\\s+", 5));
      leftrow = Integer.parseInt(str2[0]);
      leftcolumn = Integer.parseInt(str2[1]);
      rightrow = Integer.parseInt(str2[2]);
      rightcolumn = Integer.parseInt(str2[3]);
      if ((leftrow > worldRows || rightcolumn > worldColumns)) {
        throw new IllegalArgumentException("Invalid room coordinates");
      }
      roomname = str2[4];
      int roomNum = i;
      Space s1;
      s1 = new SpaceImpl(leftrow, leftcolumn, rightrow, rightcolumn, roomname, roomNum, itemList,
          totalRoom);
      list.add(i, s1);
    }
  }

  private void addPetInRoom() {
    int position = pet.getPetPosition();
    Space s = list.get(position);
    s.addPet(petName);
  }

  private void addTargetInRoom() {
    int position = target.getTargetPos();
    Space s = list.get(position);
    s.addTarget(this.targetChar, this.targetHealth);
  }

  private void createItems() {

    List<List<Item>> i1 = new ArrayList<>();
    for (int i = 0; i < list.size(); i++) {
      i1 = list.get(i).getItems();
      itemList.add(i, i1.get(i));

    }
  }

  private void createNeighbor() {
    for (int i = 0; i < totalRoom; i++) {
      neighbor.add(new ArrayList());
    }

    boolean wall = false;
    Space s1 = null;
    Space s2;
    list2 = list;

    for (int i = 0; i < list2.size(); i++) {
      for (int j = 0; j < list2.size(); j++) {
        if (i == j) {
          continue;
        }
        int[] a = list2.get(i).getRoomDimension();
        int[] b = list2.get(j).getRoomDimension();
        int x1a = a[0];
        int y1a = a[1];
        int x2a = a[2] + 1;
        int y2a = a[3] + 1;
        int x1b = b[0];
        int y1b = b[1];
        int x2b = b[2] + 1;
        int y2b = b[3] + 1;
        wall = hasWall(x1a, y1a, x2a, y2a, x1b, y1b, x2b, y2b);
        Boolean isColliding = false;
        if (wall == true) {
          // neighbor.add(list2.get(j));
          isColliding = isColliding(x1a, y1a, x2a, y2a, x1b, y1b, x2b, y2b);
          if (isColliding == true) {
            throw new IllegalArgumentException("Invalid room coordinates");
          }
          s2 = list2.get(j);
          int neighborOf = list2.get(i).getRoomNum();
          if (neighborOf != list2.get(j).getRoomNum()) {
            // s1 = new SpaceImpl(x1a, y1a, x2a, y2a, s2.getRoomName(), s2.getRoomNum(),
            // itemSpec,
            // totalRoom);
            this.neighbor.get(neighborOf).add(s2);
          } else {
            continue;
          }

        }

      }
    }
  }

  private boolean hasWall(int x1a, int y1a, int x2a, int y2a, int x1b, int y1b, int x2b, int y2b) {

    boolean line1intersect = false;
    boolean line2intersect = false;
    boolean line3intersect = false;
    boolean line4intersect = false;
    // Line2D Line1 = new Line2D.Float(x1a,y1a,x2a,y2a);
    // Line2D Line2 = new Line2D.Float(x1b,y1b,x2b,y2b);
    Line2D line1a = new Line2D.Float(x1a, y1a, x2a, y1a);
    Line2D line2a = new Line2D.Float(x1a, y1a, x1a, y2a);
    Line2D line3a = new Line2D.Float(x1a, y2a, x2a, y2a);

    Line2D line1b = new Line2D.Float(x1b, y1b, x2b, y1b);
    Line2D line2b = new Line2D.Float(x1b, y1b, x1b, y2b);
    Line2D line3b = new Line2D.Float(x1b, y2b, x2b, y2b);
    Line2D line4b = new Line2D.Float(x2b, y2b, x2b, y1b);

    if ((line1a.intersectsLine(line1b)) || (line1a.intersectsLine(line2b))
        || (line1a.intersectsLine(line3b)) || (line1a.intersectsLine(line4b))) {
      line1intersect = true;
    }
    if ((line2a.intersectsLine(line2b)) || (line2a.intersectsLine(line2b))
        || (line2a.intersectsLine(line3b)) || (line2a.intersectsLine(line4b))) {
      line2intersect = true;
    }
    if ((line3a.intersectsLine(line2b)) || (line3a.intersectsLine(line2b))
        || (line3a.intersectsLine(line3b)) || (line3a.intersectsLine(line4b))) {
      line3intersect = true;
    }
    Line2D line4a = new Line2D.Float(x2a, y2a, x2a, y1a);
    if ((line4a.intersectsLine(line2b)) || (line4a.intersectsLine(line2b))
        || (line4a.intersectsLine(line3b)) || (line4a.intersectsLine(line4b))) {
      line4intersect = true;
    }

    if (line1intersect == true || line2intersect == true || line3intersect == true
        || line4intersect == true) {
      return true;
    } else {
      return false;
    }
  }

  private boolean isColliding(int x1a, int y1a, int x2a, int y2a, int x1b, int y1b, int x2b,
      int y2b) {
    if (x1a < x1b && x2a > x1b && y1a > y2b && y2a > y1b) {
      return true;
    } else {
      return false;
    }

  }

  private String displayRoomInfo(int roomIndex) {
    if (roomIndex < 0 || roomIndex >= totalRoom) {
      throw new IllegalArgumentException("Invalid room index");
    }
    List<Space> list1;
    List<String> playersList = null;

    // createRoomObject();
    // list1 = getRoomObject();
    // int roomNum = list1.get(roomIndex).getRoomNum();
    // System.out.println("room number is:-" + roomNum);
    // String roomName = list1.get(roomIndex).getRoomName();
    // System.out.println("room name is:-" + roomName);
    // list.get(roomIndex).showItems(roomIndex);
    // for(int i =0; i<players.size();i++)
    // {
    // if(players.get(i).getPlayerPosition()==roomIndex)
    // {
    // playersList.add(players.get(i).getPlayerName());
    // }
    // }
    Space s;
    s = list.get(roomIndex);
    String str = s.toString();
    return str;

  }

  @Override
  public void displayNeighborDetails(int roomIndex) {

    // createNeighbor();
    if (roomIndex < 0 || roomIndex >= totalRoom) {
      throw new IllegalArgumentException("Inavlid room index");
    }
    List<Space> templist = this.neighbor.get(roomIndex);
    int count = templist.size();
    System.out.println("The room has total neighbors =" + count);
    for (int i = 0; i < templist.size(); i++) {
      String neighborName = templist.get(i).getRoomName();
      int neighborRoomNum = templist.get(i).getRoomNum();

      System.out.println("neighbor name is;-" + neighborName);
      System.out.println("neighbor room num is:-" + neighborRoomNum);

      System.out.println(templist.get(i).showItems(neighborRoomNum));

    }
  }

  @Override
  public String addPlayer(String playerName, PlayerType type, int position) {
    // String result ="";
    if (playerName == null && type == null && position < 0 && position >= totalRoom
        && ("").equals(playerName)) {
      throw new IllegalArgumentException(
          "playerName or playerType can not be blank and position can not be negative");
    } else {
      Random rand = new Random();
      int maxItemsToCarry = rand.nextInt(18) + 2;
      Player p1;
      PlayerImpModel p2;
      p1 = new PlayerImpModel(playerName, type, position, maxItemsToCarry, totalRoom,
          itemSpec.size());
      p2 = (PlayerImpModel) p1;
      addPlayerRestriction();
      List<Player> players = new ArrayList<>();
      players.add(p1);

      playerturn.add(p1.getPlayerName());

      if ((" ").equals(this.turn)) {
        this.turn = playerName;
      } else {
        changeTurn();
      }
      playermap.put(playerName, p1);
      humanPlayerMap.put(playerName, p2);
      Space tempSpace;
      tempSpace = list.get(position);
      tempSpace.setPlayers(playerName);
      result = "Player added suceessfully";
      return result;
    }

  }

  @Override
  public String actionForComputer(String playername) {
    this.result = "";
    if (("").equals(playername) || playername == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (!playermap.containsKey(playername)) {

      throw new IllegalArgumentException("Player does not exist");
    }

    if (!playername.equals(this.turn)) {
      throw new IllegalArgumentException("This is not this player's turn");
    }
    final ComputerPlayerModel temp = (ComputerPlayerModel) playermap.get(playername);
    if (temp.getPlayerType() != PlayerType.COMPUTER) {
      throw new IllegalArgumentException("Player is not a computer");
    }
    int roomNum = temp.getPlayerPosition();
    String playerInfo = displayPlayer(playername);
    String roomInfo = displayRoomInfo(roomNum);
    Space s1 = list.get(roomNum);
    if (s1.getTarget()) {
      this.result = attackComputerPlayers(playername);

    }

    if (("VISIBLE").equals(this.result) || ("").equals(this.result)) {
      if (!s1.getItems().get(roomNum).isEmpty()) {
        this.result = pickitemComputerPlayer(playername, false, 0);
      } else {
        Random rand = new Random();
        int decision = rand.nextInt(2);
        if (decision == 0) {
          this.result = computerlookaround(playername);
        } else {
          this.result = moveComputerPlayer(playername, false, 0);
        }
      }
    }
    return this.result;
  }

  @Override
  public String addComputerPlayer(String playername, int pos) {
    this.result = "";
    if (pos < 0 || pos >= totalRoom) {
      throw new IllegalArgumentException("Invalid position");
    }

    if (playername != null && !("").equals(playername)) {

      String playerName = playername;
      PlayerType type = PlayerType.COMPUTER;
      ComputerPlayerModel p1;

      p1 = new ComputerPlayerModel(playername, type, totalRoom, this.itemSpec.size(), pos);
      addPlayerRestriction();
      playerturn.add(p1.getPlayerName());

      if ((" ").equals(this.turn)) {
        this.turn = playerName;
      } else {
        changeTurn();
      }
      compPlayerMap.put(playername, p1);
      playermap.put(playername, p1);
      Space tempSpace;
      tempSpace = list.get(p1.getPlayerPosition());
      tempSpace.setPlayers(playername);
      this.result = "Computer player added successfully";
      return this.result;
    } else {
      throw new IllegalArgumentException("player name can not be null or blank");
    }
  }

  @Override
  public String computerlookaround(String playername) {
    this.result = "";
    if (playername != null && !("").equals(playername)) {
      if (playermap.containsKey(playername)) {
        if (playername.equals(this.turn)) {
          this.isLookaround = false;

          final ComputerPlayerModel temp = compPlayerMap.get(playername);
          List<String> neighborNames = new ArrayList();
          final int currentRoom = temp.getPlayerPosition();
          final String roomName = list.get(currentRoom).getRoomName();
          final List<Space> templist = this.neighbor.get(currentRoom);
          String roomInfo = displayRoomInfo(currentRoom);
          String playerInfo = displayPlayer(playername);
          StringBuffer neighborDetails = new StringBuffer();
          for (int i = 0; i < templist.size(); i++) {
            if (!templist.get(i).getHasPet()) {
              neighborNames.add(templist.get(i).getRoomName());
              neighborDetails.append(
                  String.format("Below is the neighbor details\n%s\n", templist.get(i).toString()));
            }
          }
          final String s = String.format(
              "Current roomdetails =\n%s\nCurrent playerdetails =\n%s\nneighbors = %s\n", roomInfo,
              playerInfo, neighborDetails.toString());

          temp.setLookAround();
          this.isLookaround = temp.isLookAround();
          changeTurn();
          return s;
        } else {
          throw new IllegalArgumentException("This is not this player's turn");
        }
      } else {
        throw new IllegalArgumentException("This player does not exist");
      }
    } else {
      throw new IllegalArgumentException("player name can not be null or blank");
    }
  }

  @Override
  public String moveComputerPlayer(String playername, boolean randomFlag, int rand) {
    this.result = "";
    if (!(randomFlag == true || randomFlag == false)) {
      throw new IllegalArgumentException("Invalid random flag");
    }
    if (rand < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (playername != null && !("").equals(playername)) {
      if (playermap.containsKey(playername)) {
        if (playername.equals(this.turn)) {
          ComputerPlayerModel p1;
          ;
          p1 = compPlayerMap.get(playername);
          int position = p1.getPlayerPosition();
          Space tempSpace = list.get(position);
          String roomInfo = displayRoomInfo(position);
          String playerInfo = displayPlayer(playername);
          List<Integer> neighbornums = new ArrayList();
          List<Space> tempneighbor = neighbor.get(position);
          if (tempneighbor.size() != 0) {

            for (int i = 0; i < tempneighbor.size(); i++) {
              if (!tempneighbor.get(i).getHasPet()) {
                neighbornums.add(tempneighbor.get(i).getRoomNum());
              }
            }
            p1.movenextRoom(neighbornums.toString(), randomFlag, rand);
            int currentroom = p1.getPlayerPosition();
            Space s = list.get(currentroom);
            s.setPlayers(playername);
            tempSpace.removePlayers(playername);
            changeTurn();
            String message = String.format("Move successful for computer player to room no %d",
                currentroom);
            String result = String.format("%s\n%s\n%s\n", playerInfo, roomInfo, message);
            return result;
          } else {
            throw new IllegalArgumentException("This is not this player's turn");
          }
        } else {
          throw new IllegalArgumentException("The computer can not move as there is no neighbor");
        }
      } else {
        throw new IllegalArgumentException("This player does not exist");
      }
    } else {
      throw new IllegalArgumentException("player name can not be null or blank");
    }
  }

  @Override
  public String pickitemComputerPlayer(String playername, boolean randomFlag, int rand) {
    this.result = "";
    if (!(randomFlag == true || randomFlag == false)) {
      throw new IllegalArgumentException("Invalid input");

    }
    if (rand < 0) {
      throw new IllegalArgumentException("Invalid input");
    }
    if (playername != null && !("").equals(playername)) {
      if (playermap.containsKey(playername)) {
        if (playername.equals(this.turn)) {
          ComputerPlayerModel p1;
          ;
          p1 = compPlayerMap.get(playername);
          int position = p1.getPlayerPosition();
          Space tempSpace = list.get(position);
          String playerInfo = displayPlayer(playername);
          String roomInfo = displayRoomInfo(position);
          List<String> str = new ArrayList();
          List<String> roomItems = new ArrayList();

          List<Item> roomItem = tempSpace.getItems().get(position);
          if (roomItem.isEmpty()) {
            throw new IllegalArgumentException("The current computer room does not have an item");

          } else {
            for (int k = 0; k < roomItem.size(); k++) {
              String item = roomItem.get(k).getItemName() + ":" + roomItem.get(k).getItemDamage();
              roomItems.add(item);
            }
            Space s1 = list.get(position);
            String itemName = p1.pickItems(roomItems.toString(), randomFlag, rand);
            String[] items = itemName.split(":");
            String item = items[0];
            // String item = p1.getItems();
            s1.removeItem(item);
            changeTurn();
            String message = "Pick item is successful";
            this.result = String.format("%s\n%s\n%s\n", playerInfo, roomInfo, message);
            return result;

          }
        } else {
          throw new IllegalArgumentException("This is not this player's turn");
        }
      } else {
        throw new IllegalArgumentException("This player does not exist");
      }
    } else {
      throw new IllegalArgumentException("player name can not be null or blank");
    }

  }

  private String displayPlayer(String playerName) {
    final Player temp;
    List<String> playerdetails = new ArrayList();
    if (playerName != null && !("").equals(playerName)) {
      if (playermap.containsKey(playerName)) {
        temp = playermap.get(playerName);
        // String player = temp.getPlayerName();
        // playerType type = temp.getPlayerType();
        // int position = temp.getPlayerPosition();
        // List<String> item = temp.getItems();
        // playerdetails.add(0,player);
        // playerdetails.add(1,type.toString());
        // playerdetails.add(2,Integer.toString(position));
        // playerdetails.add(3,item.toString());
        // return playerdetails;
        final String s = temp.toString();
        int position = temp.getPlayerPosition();
        String roomInfo = list.get(position).toString();

        String result = String.format("%s", s);
        return result;
      } else {
        throw new IllegalArgumentException("This player does not exist");
      }
    } else {
      throw new IllegalArgumentException("player name can not be null or blank");
    }

  }

  @Override
  public String lookaround(String playerName) {

    if (playerName != null && !("").equals(playerName)) {
      if (playermap.containsKey(playerName)) {
        if (playerName.equals(this.turn)) {
          this.isLookaround = false;

          final PlayerImpModel temp = (PlayerImpModel) playermap.get(playerName);
          List<String> neighborNames = new ArrayList();
          final int currentRoom = temp.getPlayerPosition();
          final String roomName = list.get(currentRoom).getRoomName();
          final List<Space> templist = this.neighbor.get(currentRoom);
          String roomInfo = displayRoomInfo(currentRoom);
          String playerInfo = displayPlayer(playerName);
          StringBuffer neighborDetails = new StringBuffer();
          for (int i = 0; i < templist.size(); i++) {
            if (!templist.get(i).getHasPet()) {
              neighborNames.add(templist.get(i).getRoomName());
              neighborDetails.append(
                  String.format("Below is the neighbor details\n%s\n", templist.get(i).toString()));
            }
          }
          if (neighborNames.size() != 0) {
            final String s = String.format(
                "Current roomdetails =\n%s\nCurrent playerdetails = \n%s\n" + "neighbors = %s\n"
                    + "%s\n",
                roomInfo, playerInfo, neighborNames.toString(), neighborDetails);
            temp.setLookAround();
            this.isLookaround = temp.isLookAround();
            changeTurn();
            return s;
          } else {
            final String s = String.format("Current roomsetails:- %s\nCurrent playerdetails = %s\n"
                + "neighbors = no neighbors\n", roomInfo, playerInfo);
            temp.setLookAround();
            this.isLookaround = temp.isLookAround();
            changeTurn();
            return s;
          }
        } else {
          throw new IllegalArgumentException("This is not this player's turn");
        }
      } else {
        throw new IllegalArgumentException("This player name does not exist");
      }
    } else {
      throw new IllegalArgumentException("Input can not be null");
    }

  }

  @Override
  public boolean isGameOver() {

    if (this.noOfTurns >= this.maxTurns || target.getTargetHealth() == 0) {
      return true;
    } else {
      return false;
    }

  }

  @Override

  public int getMaxTurns() {
    return this.maxTurns;
  }

  @Override
  public String getPathforPetDfS(int root) {
    if (root < 0) {
      throw new IllegalArgumentException("Invalid root");
    }
    List<Integer> path = new ArrayList<>();
    path = movePetDfS(root);
    return path.toString();
  }

  @Override
  public boolean isComputer(String playerName) {
    if (playerName != null && !("").equals(playerName)) {
      if (playermap.containsKey(playerName)) {
        final Player temp = playermap.get(playerName);
        if (temp.getPlayerType() == PlayerType.COMPUTER) {
          return true;
        } else {
          return false;
        }
      } else {
        throw new IllegalArgumentException("This player name does not exist");
      }
    } else {
      throw new IllegalArgumentException("Input can not be null");
    }

  }

  @Override
  public String moveplayer(String playerName, String roomname) {
    this.ismoveplayer = false;
    int roomNum = -1;
    String message = "Move not successful";
    String currentplayerInfo = "";

    if (playerName != null && roomname != null && !("").equals(roomname)
        && !("").equals(playerName)) {
      if (playermap.containsKey(playerName)) {
        if (playerName.equals(this.turn)) {
          String playerInfo = displayPlayer(playerName);
          final PlayerImpModel temp = (PlayerImpModel) playermap.get(playerName);
          final int currentpos = temp.getPlayerPosition();
          String roomInfo = displayRoomInfo(currentpos);
          // String roomName = list.get(19).getRoomName();
          for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRoomName().equals(roomname)) {
              roomNum = i;
            }
          }
          if (roomNum == -1) {
            throw new IllegalArgumentException("The room does not exist");
          }
          final List<Space> tempList = this.neighbor.get(currentpos);
          for (int j = 0; j < tempList.size(); j++) {
            if (tempList.get(j).getRoomName().equals(roomname)) {
              if (!tempList.get(j).getHasPet()) {

                temp.movePlayer(playerName, roomNum);
                Space s = list.get(roomNum);
                Space s1 = list.get(currentpos);
                s.setPlayers(playerName);
                s1.removePlayers(playerName);
                this.ismoveplayer = temp.isMovePlayer();
                message = String.format("Move successful to room %d", roomNum);
                currentplayerInfo = temp.toString();

                changeTurn();
              }

            }
          }

          if (this.ismoveplayer != true) {
            throw new IllegalArgumentException("The room is not a neighbor");
          } else {
            String result = String.format("%s\n%s\n%s\ncurrent player info\n%s\n", playerInfo,
                roomInfo, message, currentplayerInfo);
            return result;
          }
        } else {
          throw new IllegalArgumentException("This is not this player's turn");
        }
      } else {
        throw new IllegalArgumentException("This player name does not exist");
      }
    } else {
      throw new IllegalArgumentException("The Inputs can not be null or blank");
    }
  }

  @Override

  public String showPet() {
    String s = pet.toString();
    return s;
  }

  @Override

  public String getTurn() {
    return this.turn;
  }

  private void changeTurn() {
    int x = playerturn.indexOf(this.turn);
    List<Integer> path = new ArrayList<>();
    final Player temp = playermap.get(this.turn);
    for (int i = x; i <= playerturn.size(); i++) {
      if (playerturn.size() == i && (temp.isLookAround() == true || temp.isMovePlayer() == true
          || temp.isPickItem() == true || temp.isAttackTarget() || temp.isMovePet())) {
        moveTargetChar();
        boolean s = temp.isMovePet();
        if (temp.isMovePet() == false) {
          path = movePetDfS(0);
        } else {
          path = movePetDfS(pet.getPetPosition());
        }
        int index = path.indexOf(pet.getPetPosition());
        pet.movePet(path.get(index + 1));

        this.turn = playerturn.get(0);
        this.noOfTurns = this.noOfTurns + 1;

      } else {
        if (i + 1 < playerturn.size() && (temp.isLookAround() == true || temp.isMovePlayer() == true
            || temp.isPickItem() == true || temp.isAttackTarget() || temp.isMovePet())) {
          moveTargetChar();
          if (temp.isMovePet() == false) {
            path = movePetDfS(0);
          } else {
            path = movePetDfS(pet.getPetPosition());
          }
          int index = path.indexOf(pet.getPetPosition());
          pet.movePet(path.get(index + 1));
          this.turn = playerturn.get(i + 1);
          this.noOfTurns = this.noOfTurns + 1;
          break;
          // movetarget
        }
      }

      // else
      // {
      // throw new IllegalStateException("The current turn is not completed ");
      // }
    }
  }

  @Override

  public String pickItemsPlayer(String playerName, String itemName) {
    this.ispickitem = false;
    String message = "";
    if (playerName != null && itemName != null && !("").equals(itemName)
        && !("").equals(playerName)) {
      if (playerName.equals(this.turn)) {
        final PlayerImpModel temp;
        temp = (PlayerImpModel) playermap.get(playerName);
        final int roomNum = temp.getPlayerPosition();
        String playerInfo = displayPlayer(playerName);
        String roomInfo = displayRoomInfo(roomNum);

        final Space space = list.get(roomNum);
        final List<List<Item>> items = space.getItems();
        final List<Item> item = items.get(roomNum);
        for (int i = 0; i < item.size(); i++) {
          if (item.get(i).getItemName().equals(itemName)) {
            int itemDam = item.get(i).getItemDamage();
            temp.pickitems(playerName, itemName, itemDam);
            space.removeItem(itemName);
            this.ispickitem = temp.isPickItem();
            changeTurn();
            message = "Pick item is successful";
          }
        }

        if (this.ispickitem != true) {
          throw new IllegalArgumentException("The item does not exist in player's room");
        } else {
          String result = String.format("%s\n%s\n%s\n", playerInfo, roomInfo, message);
          return result;
        }

      } else {
        throw new IllegalArgumentException("This is not this player's turn");
      }

    } else {
      throw new IllegalArgumentException("The item name or player name can not be null");
    }
  }

  @Override
  public void displayMap(BufferedImage image) {

    if (image == null) {
      throw new IllegalArgumentException("input can not be null");
    }
    Graphics2D g = (Graphics2D) image.createGraphics();

    // List<Space> roomList = getRoomObject();
    for (int i = 0; i < list.size(); i++) {

      g.setBackground(Color.blue);
      g.setColor(Color.white);
      int[] a = list.get(i).getRoomDimension();
      // int b[] = list2.get(j).getRoomDimension();
      int x1a = a[0];
      int y1a = a[1];
      int x2a = a[2] + 1;
      int y2a = a[3] + 1;
      // int x1b = b[0];
      // int y1b = b[1];
      // int x2b = b[2]+1;
      // int y2b = b[3]+1;

      g.drawLine(y1a * 15, x1a * 15, y2a * 15, x1a * 15);
      g.drawLine(y1a * 15, x1a * 15, y1a * 15, x2a * 15);
      g.drawLine(y1a * 15, x2a * 15, y2a * 15, x2a * 15);
      g.drawLine(y2a * 15, x2a * 15, y2a * 15, x1a * 15);

      g.setColor(Color.YELLOW);
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
      String str = list.get(i).getRoomName();

      g.drawString(str, y1a * 15, x1a * 15 + 20);
      // g.drawImage(image, 40, 40, Color.WHITE, null);
      // g.drawRect(leftx, lefty, height, width);
    }
  }

  @Override
  public String toString() {

    return String.format(
        "World (world name = %s, target char = %s, "
            + "target health = %s, currentPosition = %s, totalRoom = %s )",
        this.worldName, this.targetChar, this.targetHealth, this.currentPosition, this.totalRoom);
  }

  @Override
  public String movePetForPlayers(String playerName, int nextPos) {
    this.movePet = false;
    String result = "";

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (nextPos < 0 || nextPos >= totalRoom) {
      throw new IllegalArgumentException("Invalid position to move");
    }
    if (!playermap.containsKey(playerName)) {
      throw new IllegalArgumentException("playeName does not exist");

    }
    if (!playerName.equals(this.turn)) {
      throw new IllegalArgumentException("This is not this player's turn");

    }
    if (nextPos == pet.getPetPosition()) {
      throw new IllegalArgumentException("Pet already exists in that position");
    }
    final PlayerImpModel temp = (PlayerImpModel) playermap.get(playerName);
    final int roomNum = temp.getPlayerPosition();
    String playerInfo = displayPlayer(playerName);
    String roomInfo = displayRoomInfo(roomNum);
    Space s2 = list.get(pet.getPetPosition());
    pet.movePet(nextPos);
    Space s1 = list.get(nextPos);
    s1.addPet(petName);
    s2.removePet(petName);
    this.movePet = true;
    if (this.movePet == true) {
      result = String.format("%s\n%s\nmove successful to room = %d\n", playerInfo, roomInfo,
          nextPos);
      temp.setMovePet();
      changeTurn();
    } else {
      result = "Move was unsuccessful";
    }

    return result;
  }

  @Override
  public String attackTargetForPlayers(String playerName, String itemName) {
    this.isVisible = false;
    this.isAttack = false;
    Evidence e1;
    String result = "";
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    if (("").equals(itemName) || itemName == null) {
      throw new IllegalArgumentException("Invalid Item name");
    }
    if (!playermap.containsKey(playerName)) {
      throw new IllegalArgumentException("playeName does not exist");

    }
    if (!playerName.equals(this.turn)) {
      throw new IllegalArgumentException("This is not this player's turn");

    }
    final PlayerImpModel temp = (PlayerImpModel) playermap.get(playerName);
    final int roomNum = temp.getPlayerPosition();
    String playerInfo = displayPlayer(playerName);
    String roomInfo = displayRoomInfo(roomNum);
    Space s1 = list.get(roomNum);
    if (s1.getTarget()) {
      String players = s1.getPlayers();
      players = players.substring(1, players.length() - 1);
      String[] players1 = players.split(",");
      if (!players.equals(temp.getPlayerName())) {
        isVisible = true;
      }
      final List<Space> tempList = this.neighbor.get(roomNum);
      for (int j = 0; j < tempList.size(); j++) {

        String p = tempList.get(j).getPlayers();
        p = p.substring(1, p.length() - 1);
        String[] p1 = p.split(",");
        if (!p.isEmpty()) {
          isVisible = true;
        }
      }
      if (s1.getHasPet()) {
        isVisible = false;
      }

      if (isVisible == true) {
        result = "Attack not successful as seen by other player";
        final String playerItems = temp.getItems();
        boolean itemmatch = false;
        String item3 = "";
        String curItemname = "";
        String damage = "";
        int curItemDamage = 0;
        List<String> itemList = new ArrayList<String>(Arrays.asList(playerItems.split(",")));
        for (int i = 0; i < itemList.size(); i++) {
          String[] curItem = itemList.get(i).split(":");
          item3 = itemList.get(i);
          if (!item3.endsWith("]")) {
            item3 = item3 + "]";
          }
          curItemname = curItem[0].substring(1);
          damage = curItem[1];
          if (damage.endsWith("]")) {
            damage = curItem[1].substring(0, curItem[1].length() - 1);
          }
          curItemDamage = Integer.parseInt(damage);
          if (curItemname.equals(itemName)) {

            itemmatch = true;
            break;

          }
        }
        if (itemmatch == true) {

          temp.removeItem(item3);
          e1 = new EvidenceImpl(itemName, curItemDamage, playerName);
          this.evidenceList.add(e1);
          this.evidences.add(e1.getEvidenceName() + ":" + e1.getEvidenceDamage());
          // s1.removeItem(itemName);
          this.isAttack = true;
          changeTurn();
        }

      } else {
        boolean itemMatch = false;

        final Space space = list.get(roomNum);
        final List<List<Item>> items = space.getItems();
        final List<Item> item = items.get(roomNum);
        final List<Item> item1 = this.itemList.get(roomNum);
        final String playerItems = temp.getItems();
        String item3 = "";
        String curItemname = "";
        String damage = "";
        int curItemDamage = 0;
        List<String> itemList = new ArrayList<String>(Arrays.asList(playerItems.split(",")));
        if (itemList.get(0).equals("[]")) {
          throw new IllegalArgumentException(
              "The player does not have any items,but player can poke the target in the eye");
        } else {
          for (int i = 0; i < itemList.size(); i++) {
            String[] curItem = itemList.get(i).split(":");
            item3 = itemList.get(i);
            curItemname = curItem[0].substring(1);
            damage = curItem[1];
            if (damage.endsWith("]")) {
              damage = curItem[1].substring(0, curItem[1].length() - 1);
            }
            curItemDamage = Integer.parseInt(damage);
            if (curItemname.equals(itemName)) {

              itemMatch = true;
              break;

            }
          }

          if (itemMatch == true) {

            target.setTargetHealth(curItemDamage);

            // remove item from game.
            temp.removeItem(item3);
            e1 = new EvidenceImpl(itemName, curItemDamage, playerName);
            this.evidenceList.add(e1);
            this.evidences.add(e1.getEvidenceName() + ":" + e1.getEvidenceDamage());
            // space.removeItem(itemName);
            this.isAttack = true;
            temp.setAttackTarget();
            changeTurn();
            // create evidence

            if (target.getTargetHealth() == 0) {
              this.winner = playerName;
            }
            result = String.format("Attack successful with damage = %d", curItemDamage);
          } else {
            throw new IllegalArgumentException(
                "Player does not have the mentioned item with him/her");
          }

        }

      }
      return result;
    } else {
      throw new IllegalArgumentException(
          "Player can not attack target as target not in player's room");
    }

  }

  @Override
  public String getWinner() {

    return this.winner;
  }

  @Override
  public String showTarget() {

    String info = target.toString();
    return info;
  }

  @Override
  public String getEvidenceList() {
    return this.evidences.toString();
  }

  // Took help and reference from online sources for implementing this algo
  // as mentioned under Citations(readME.md:-Citations:-3).
  private List<Integer> movePetDfS(int root) {
    this.vertices = new ArrayList<>();
    List<Space> spaceList = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    List<Integer> neighbors = new ArrayList<>();
    int rootPos = root;
    int nextPos = 0;
    List<Integer> path = new ArrayList<>();
    List<Boolean> visitedNode = new ArrayList<>();
    for (int i = 0; i < this.neighbor.size(); i++) {
      spaceList = this.neighbor.get(i);
      for (int j = 0; j < spaceList.size(); j++) {
        int num = spaceList.get(j).getRoomNum();
        temp.add(num);
      }
      this.vertices.add(i, temp);

      temp = new ArrayList<>();
    }

    for (int i = 0; i < totalRoom; i++) {
      visitedNode.add(false);
    }

    Stack<Integer> newStack = new Stack<>();

    Stack<Integer> newStacky = new Stack<>();
    newStack.push(rootPos);
    while (newStack.empty() == false) {

      int s = newStack.peek();
      neighbors = this.vertices.get(s);
      Collections.sort(neighbors);
      // Collections.reverse(adjacent);

      // if (visitedNode.get(s) == false ||
      // ) {

      visitedNode.set(s, true);

      // if (path.isEmpty() || path.get(path.size() - 1) != s) {
      path.add(s);

      // }

      // }

      int ct = 0;
      for (int i = 0; i < neighbors.size(); i++) {
        if (visitedNode.get(neighbors.get(i))) {
          ct++;
        }
      }

      if (neighbors.size() == ct) {
        visitedNode.set(newStack.peek(), true);
        newStacky.push(newStack.pop());

      } else {
        for (int i = 0; i < neighbors.size(); i++) {
          int e = neighbors.get(i);
          if (!visitedNode.get(e)) {
            // if (i != 0)
            // newStack.push(s);
            newStack.push(e);
            break;
          }
        }
      }

      // Iterator<Integer> i1 = path.listIterator(); if(i1.hasNext()) {
      // pet.movePet(i1.next()); }

    }

    return path;
  }

  @Override
  public String attackComputerPlayers(String playerName) {
    String result = "";
    Evidence e1;
    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (!playermap.containsKey(playerName)) {

      throw new IllegalArgumentException("Player does not exist");
    }

    if (!playerName.equals(this.turn)) {
      throw new IllegalArgumentException("This is not this player's turn");
    }
    final ComputerPlayerModel temp = (ComputerPlayerModel) playermap.get(playerName);
    if (temp.getPlayerType() != PlayerType.COMPUTER) {
      throw new IllegalArgumentException("Player is not a computer");
    }
    int roomNum = temp.getPlayerPosition();
    String playerInfo = displayPlayer(playerName);
    String roomInfo = displayRoomInfo(roomNum);
    Space s1 = list.get(roomNum);
    if (s1.getTarget()) {
      String players = s1.getPlayers();
      players = players.substring(1, players.length() - 1);
      String[] players1 = players.split(",");
      if (!players.equals(temp.getPlayerName())) {
        isVisible = true;
      }
      final List<Space> tempList = this.neighbor.get(roomNum);
      for (int j = 0; j < tempList.size(); j++) {

        String p = tempList.get(j).getPlayers();
        p = p.substring(1, p.length() - 1);
        String[] p1 = p.split(",");
        if (!p.isEmpty()) {
          isVisible = true;
        }
      }
      if (s1.getHasPet()) {
        isVisible = false;
      }

      if (isVisible == true) {
        result = "VISIBLE";
      } else {
        boolean itemMatch = false;

        final Space space = list.get(roomNum);
        final List<List<Item>> items = space.getItems();
        final List<Item> item = items.get(roomNum);
        final List<Item> item1 = this.itemList.get(roomNum);
        final String playerItems = temp.getItems();
        List<String> computerItems = new ArrayList<>();
        List<Integer> itemDamages = new ArrayList<>();
        List<String> itemList = new ArrayList<String>(Arrays.asList(playerItems.split(",")));
        if (itemList.get(0).equals("[]")) {
          target.setTargetHealth(1);
          this.isAttack = true;
          temp.setAttackTarget();
          changeTurn();
          if (target.getTargetHealth() == 0) {
            this.winner = playerName;
          }
          result = "Computer player chose to poke in the eye of target with damage 1";
        } else {
          String curItemname = "";

          for (int i = 0; i < itemList.size(); i++) {
            String[] curItem = itemList.get(i).split(":");
            String item3 = itemList.get(i);
            if (!item3.endsWith("]")) {
              item3 = item3 + "]";
            }
            curItemname = curItem[0].substring(1);
            int curItemDamage = 0;
            String damage = curItem[1];
            if (damage.endsWith("]")) {
              damage = curItem[1].substring(0, curItem[1].length() - 1);
            }
            curItemDamage = Integer.parseInt(damage);
            computerItems.add(i, item3);
            itemDamages.add(i, curItemDamage);

          }

          int maxDamage = Collections.max(itemDamages);
          int index = itemDamages.indexOf(maxDamage);
          String item3 = computerItems.get(index);
          target.setTargetHealth(maxDamage);

          // remove item from game.
          temp.removeItem(item3);
          e1 = new EvidenceImpl(curItemname, maxDamage, playerName);
          this.evidenceList.add(e1);
          this.evidences.add(e1.getEvidenceName() + ":" + e1.getEvidenceDamage());
          // space.removeItem(itemName);
          this.isAttack = true;
          temp.setAttackTarget();
          changeTurn();
          // create evidence

          if (target.getTargetHealth() == 0) {
            this.winner = playerName;
          }
          result = String.format("Attack successful with item:damage = %s", item3);
        }
      }
      return result;
    } else {
      throw new IllegalArgumentException(
          "Computer can not attack as target is not there in same room");
    }
  }

  @Override
  public String pokeInEye(String playerName) {
    String result = "";

    if (("").equals(playerName) || playerName == null) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (!playermap.containsKey(playerName)) {
      throw new IllegalArgumentException("Player does not exist");

    }
    if (!playerName.equals(this.turn)) {
      throw new IllegalArgumentException("This is not this player's turn");
    }
    final PlayerImpModel temp = (PlayerImpModel) playermap.get(playerName);
    final int roomNum = temp.getPlayerPosition();
    String playerInfo = displayPlayer(playerName);
    String roomInfo = displayRoomInfo(roomNum);
    Space s1 = list.get(roomNum);
    if (s1.getTarget()) {
      String players = s1.getPlayers();
      players = players.substring(1, players.length() - 1);
      String[] players1 = players.split(",");
      if (!players.equals(temp.getPlayerName())) {
        isVisible = true;
      }
      final List<Space> tempList = this.neighbor.get(roomNum);
      for (int j = 0; j < tempList.size(); j++) {

        String p = tempList.get(j).getPlayers();
        p = p.substring(1, p.length() - 1);
        String[] p1 = p.split(",");
        if (!p.isEmpty()) {
          isVisible = true;
        }
      }
      if (s1.getHasPet()) {
        isVisible = false;
      }

      if (isVisible == true) {
        result = "poke In The Eye not successful as seen by other player";
        this.isAttack = true;
        changeTurn();
      } else {
        target.setTargetHealth(1);
        this.isAttack = true;
        temp.setAttackTarget();
        changeTurn();
        if (target.getTargetHealth() == 0) {
          this.winner = playerName;
        }
        result = String.format("Poke in the eye successful with damage = %d", 1);
      }

      return result;
    } else {
      throw new IllegalArgumentException(
          "Poke in the eye can not be done as target is not present in player's room");
    }
  }

  @Override
  public int getTotalCurrentPlayers() {
    return playerturn.size();
  }

  private void addPlayerRestriction() {
    if (playerturn.size() == 10) {
      throw new IllegalArgumentException("can not add more players to the game");
    }
  }

  @Override
  public List<Space> getRoomDetails() {

    return new ArrayList<Space>(list);

  }

  @Override
  public int getTargetLocation() {
    return target.getTargetPos();
  }

  @Override
  public int getCurrentLocationPlayer() {
    String playername = getTurn();
    final Player temp = playermap.get(playername);
    final int roomNum = temp.getPlayerPosition();
    return roomNum;

  }

  @Override
  public PlayerType getPlayerTypeCurrentTurn() {
    String playername = getTurn();
    final Player temp = playermap.get(playername);
    PlayerType type = temp.getPlayerType();

    return type;
  }

  @Override
  public String movePlayerWithCoordinates(String playerName, int row, int col) {

    this.result = "";
    int nextRoom = 0;
    if (playerName == null || ("").equals(playerName)) {
      throw new IllegalArgumentException("Invalid playername");
    }

    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("Invalid position to move");
    }
    String playername = getTurn();
    if (!(" ").equals(playername)) {
      int pos = getCurrentLocationPlayer();
      List<Space> list = getRoomDetails();
      for (int i = 0; i < list.size(); i++) {
        int[] arr = list.get(i).getRoomDimension();
        int x1 = arr[0];
        int y1 = arr[1];
        int x2 = arr[2];
        int y2 = arr[3];
        if (row >= x1 && row <= x2 && col >= y1 && col <= y2) {
          nextRoom = i;
          break;
        }
      }

      String roomName = list.get(nextRoom).getRoomName();
      this.result = moveplayer(playername, roomName);
    }
    return this.result;
  }

  @Override
  public String displayPlayerForController(String playerName) {
    String info = "";
    String result = "";
    if (playerName == null || ("").equals(playerName)) {

      throw new IllegalArgumentException("Invalid playerName");
    }

    if (playermap.containsKey(playerName)) {
      Player temp = playermap.get(playerName);
      final String s = temp.toString();
      int position = temp.getPlayerPosition();
      String roomInfo = list.get(position).toString();
      info = displayPlayer(playerName);
      result = String.format("PlayerInfo:-%s%sRoom Info:-%s%s", "\n", info, "\n", roomInfo);
    }

    return result;
  }

  @Override
  public int getTargetHealth() {
    return target.getTargetHealth();
  }
}
