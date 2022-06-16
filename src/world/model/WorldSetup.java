/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * This is World Implementation Class which extends from the World Interface.
 * It creates the Target object, Spaces Objects and Items Objects.
 * It also creates the graphical representation of the world.
 */
public final class WorldSetup implements World {

  private final Scanner scan;
  private final int totalRow;
  private final int totalCol;
  private String world;
  private final int roomCount;
  private final int itemCount;
  private String[][] rooms;
  private int health;
  private String target;
  private String pet;
  private final int gameMaxTurn;
  private int turn;
  private int turnCount;
  private int winner;
  private Target targetObj;
  private Pet petObj;
  private final Map<Integer, ItemList> itemObjList;  
  private final Map<Integer, Room> roomObjList;
  private final Map<Integer, List<Room>> neighborTable;
  private final Map<Integer, Player> playerList;
  private final Graph worldGraph;
  private List<Integer> petTraversalOrder;
  private int petTraversalIndex;
  private boolean reset;
  

  /**
   * This is the Constructor for the WorldSetup Class.
   * It initializes takes the file content as string to derive the
    required parameter values. These values are used to create the Spaces, Items and Target.
   * @param fileInput gives the file content in a Readable format.
   * @param maxTurn The integer value for the maximum number of turns for which the game runs.
    Should be greater than 0.
   * @throws IllegalArgumentException If the file content does not have values that 
    satisfy the requirements or max turn is less than 1, exceptions are thrown.
   */
  public WorldSetup(Readable fileInput, int maxTurn) throws IllegalArgumentException {
    if (fileInput == null) {
      throw new IllegalArgumentException("World creation input file content is not correct.");
    }
    if (maxTurn < 1) {
      throw new IllegalArgumentException("To paly the game Max turn should be atleast 1.");
    }
    this.turn = 1;
    this.gameMaxTurn = maxTurn;
    this.turnCount = 1;
    this.winner = -2; // - 2 is for game in progress.
    this.itemObjList = new HashMap<Integer, ItemList>();  
    this.roomObjList = new HashMap<Integer, Room>();
    this.neighborTable = new HashMap<Integer, List<Room>>();
    this.playerList = new HashMap<Integer, Player>();
    this.scan = new Scanner(fileInput);
        
    String fileContent = "";
    scan.useDelimiter("");
    while (this.scan.hasNext()) {
      fileContent = fileContent + scan.next();
    }
    String[] lines = fileContent.split("\n");
    String[] element = null;
    
    element = lines[0].trim().split("\\s+");
    
    //Get World Details. 
    this.world = "";
    this.totalRow = Integer.parseInt(element[0].trim());
    this.totalCol = Integer.parseInt(element[1].trim());
    if (this.totalRow <= 0 || this.totalCol <= 0) {
      throw new IllegalArgumentException("Enter correct dimensions for World.");
    }
    for (int j = 2; j < element.length; j++) {
      this.world = this.world + " " + element[j];
    }
        
    //Get Target Details. 
    try {
      element = lines[1].trim().split("\\s+");
      this.health = Integer.parseInt(element[0].trim());
  
      if (this.health <= 0) {
        throw new IllegalArgumentException("Initial Health of Target must be a Positive value.");
      }
      this.target = "";
      for (int j = 1; j < element.length; j++) {
        this.target = this.target + " " + element[j];
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Health should be an integer");
    }
    
    //Get Pet Details. 
    element = lines[2].trim().split("\\s+");
    this.pet = "";
    for (int j = 0; j < element.length; j++) {
      this.pet = this.pet + " " + element[j];
    }
    this.petTraversalOrder = new ArrayList<Integer>();
    this.petTraversalIndex = 1;
    this.reset = false;
 
    //Get Room Details.
    try { 
      Integer.parseInt(lines[3].trim());
    } catch (NumberFormatException e)  { 
      throw new IllegalArgumentException("Number of Spaces should be an Integer"); 
    } 
    
    this.roomCount = Integer.parseInt(lines[3].trim());
    if (this.roomCount <= 1) {
      throw new IllegalArgumentException("There should atleast be two rooms present.");
    }
    
    //Initialize a graph to represent connected spaces in the world.
    this.worldGraph = new Graph(roomCount);
    
    //Get Item Details. 
    try { 
      Integer.parseInt(lines[4 + this.roomCount].trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Number of Items should be an Integer. "
          + "Or check if correct Number of Spaces are provided.");
    }

    this.itemCount = Integer.parseInt(lines[4 + this.roomCount].trim());
    if (this.itemCount < 1) {
      throw new IllegalArgumentException("There should atleast be 1 item.");
    }
     
    createWorldComponents(lines);
  }
  
 
  //
  // Private Methods of the Class - exception handling needed?
  //
  private void createWorldComponents(String[] lines) {
    //creating 2D array for rooms
    String[] element = null;
    this.rooms = new String[this.roomCount][6];
    if (lines.length < 4 + this.roomCount + 1) {
      throw new IllegalArgumentException("Correct number of Spaces and Items not given");
    }
    for (int i = 4; i < 4 + this.roomCount; i++) {
      element = null;
      String roomName = "";
      element = lines[i].trim().split("\\s+");
      if (element.length < 5) {
        throw new IllegalArgumentException("Room Details Not Given Correctly.");
      }
      rooms[i - 4][0] = element[0].trim();
      rooms[i - 4][1] = element[1].trim();
      rooms[i - 4][2] = element[2].trim();
      rooms[i - 4][3] = element[3].trim();
      try {
        if (Integer.parseInt(rooms[i - 4][0]) < 0 || Integer.parseInt(rooms[i - 4][1]) < 0 
            || Integer.parseInt(rooms[i - 4][2]) < 0 || Integer.parseInt(rooms[i - 4][3]) < 0 
            || Integer.parseInt(rooms[i - 4][0]) > this.totalRow 
            || Integer.parseInt(rooms[i - 4][1]) > this.totalCol
            || Integer.parseInt(rooms[i - 4][2]) > this.totalRow 
            || Integer.parseInt(rooms[i - 4][3]) > this.totalCol) {
          throw new IllegalArgumentException("Room Column or Row value cannot be negative."
              + " They can also not be greater then the room dimensions.");
        }
      } catch (NumberFormatException nfe) {
        throw new IllegalArgumentException("Room Details not Correct.");
      }
      for (int j = 4; j < element.length; j++) {
        roomName = roomName + " " + element[j];
      }
      rooms[i - 4][4] = roomName.trim();
    }
    
    //creating 2D array for Items 
    String[][] items = new String[this.itemCount][3];
    if (lines.length < 5 + this.roomCount + this.itemCount) {
      throw new IllegalArgumentException("Correct number of Spaces and Items not given");
    }
    for (int i = 5 + this.roomCount; i < 5 + this.roomCount + this.itemCount; i++) {
      element = null;
      String itemName = "";
      element = lines[i].trim().split("\\s+");
      if (element.length < 3) {
        throw new IllegalArgumentException("Item Details Not Given Correctly.");
      }
      items[i - 5 - this.roomCount][0] = element[0].trim();
      try {
        if (Integer.parseInt(items[i - 5 - this.roomCount][0]) < 0 
            || Integer.parseInt(items[i - 5 - this.roomCount][0]) > this.roomCount - 1) {
          throw new IllegalArgumentException("Item is set to a room that does not exist.");
        }
      } catch (NumberFormatException nfe) {
        throw new IllegalArgumentException("Item Details not Correct.");
      }
      items[i - 5 - this.roomCount][1] = element[1].trim();
      if (Integer.parseInt(items[i - 5 - this.roomCount][1]) < 1) {
        throw new IllegalArgumentException("Damage of an item should be positive.");
      }
      for (int j = 2; j < element.length; j++) {
        itemName = itemName + " " + element[j];
      }
      items[i - 5 - this.roomCount][2] = itemName.trim();
    }

    // call function to create Target
    createTarget();

    // create Pet
    createPet();
    
    //create items
    createItems(items);
 
    //create rooms
    createRooms(rooms, items);
         
    //check neighbors of rooms
    for (int i = 1; i <= this.roomCount; i++) {
      List<Room> neighborList = new ArrayList<Room>();
      this.neighborTable.put(i, neighborList);
    }
    for (int i = 1; i <= this.roomCount; i++) {
      findNeighbor(i, rooms);      
    }
    
    // set the neighbors to the rooms
    setNeighbors(neighborTable);

    //Create a graph to represent connected spaces in the world.
    createGraph();
  }
  
  private void createTarget() {
    this.targetObj = new Target(this.target, this.health, 1, this.roomCount);
  }
  
  private void createPet() {
    this.petObj = new TargetPet(this.pet);
  }
  
  private void createItems(String[][] items) {
    
    for (int i = 1; i <= this.itemCount; i++) {
      this.itemObjList.put(i, new ItemList(i, Integer.parseInt(items[i - 1][0]) + 1,
          items[i - 1][2], Integer.parseInt(items[i - 1][1])));
    }
  }
  
  private void setNeighbors(Map<Integer, List<Room>> neighborTable2) {
    for (int i = 1; i <= this.roomCount; i++) {
      roomObjList.get(i).setNeighbor(neighborTable2.get(i));
    }
  }
  
  private void createRooms(String[][] rooms, String[][] items) {
    for (int i = 1; i <= this.roomCount; i++) {
      List<ItemList> itemList = new ArrayList<ItemList>();
      for (int j = 1; j <= this.itemCount; j++) {
        if (Integer.parseInt(items[j - 1][0]) + 1 == i) {
          itemList.add(this.itemObjList.get(j));
        }
      }
      int[] coordinates = new int[] {Integer.parseInt(rooms[i - 1][1]), 
          Integer.parseInt(rooms[i - 1][0]), Integer.parseInt(rooms[i - 1][3]) + 1,
          Integer.parseInt(rooms[i - 1][2]) + 1};
      this.roomObjList.put(i, new Room(i, rooms[i - 1][4], itemList, coordinates, 
          targetObj, petObj));

    }
    this.roomObjList.get(1).setHasPet(true);
    this.roomObjList.get(1).setHasTarget(true);
  }
  
  private void findNeighbor(int roomNum, String[][] rooms) {
    //set coordinate 1
    int[] coord1 = new int[] {Integer.parseInt(rooms[roomNum - 1][0]), 
        Integer.parseInt(rooms[roomNum - 1][1]), Integer.parseInt(rooms[roomNum - 1][2]) + 1, 
        Integer.parseInt(rooms[roomNum - 1][3]) + 1}; 
    
    
    //set coordinate 2
    for (int k = 0; k < this.roomCount; k++) {
      if (k != roomNum - 1) {
        int [] coord2 = new int[] {Integer.parseInt(rooms[k][0]), Integer.parseInt(rooms[k][1]), 
            Integer.parseInt(rooms[k][2]) + 1, Integer.parseInt(rooms[k][3]) + 1};
      
        int[][] x = new int[][] {{coord1[0], coord1[1]}, {coord1[2], coord1[1]}, 
          {coord1[2], coord1[3]}, {coord1[0], coord1[3]}};
        int[][] y = new int[][] {{coord2[0], coord2[1]}, {coord2[2], coord2[1]}, 
          {coord2[2], coord2[3]}, {coord2[0], coord2[3]}};
      
        //call fns
        // for every side on j, check if point i lies on it.
        for (int i = 0; i < 4; i++) {
          for (int j = 0; j < 4; j++) {
            if (j < 2) {
              if (checkWithinLine(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], x[(j + 1) % 4][1],
                  y[i][0], y[i][1])) {
                                
                if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                  neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                }
                if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                  neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                }              
              } else if (i == 3) {
                //check corner case where the two rectangles have only 1 point in common
                if (checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                    x[(j + 1) % 4][1], y[0][0], y[0][1], y[i][0], y[i][1]) 
                      || checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                          x[(j + 1) % 4][1], y[i][0], y[i][1], y[i - 1][0], y[i - 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }
                }
              } else if (i == 0) {
                if (checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                    x[(j + 1) % 4][1], y[i][0], y[i][1], y[3][0], y[3][1]) 
                    || checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                        x[(j + 1) % 4][1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {  
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }                   
                }
              } else if (i == 1) {
                if (checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0],
                    x[(j + 1) % 4][1], y[i - 1][0], y[i - 1][1], y[i][0], y[i][1]) 
                    || checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                        x[(j + 1) % 4][1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }
                }
              } else if (i == 2) {
                if (checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0],
                    x[(j + 1) % 4][1], y[i - 1][0], y[i - 1][1], y[i][0], y[i][1]) 
                    || checkTwoCommonPoints(x[j % 4][0], x[j % 4][1], x[(j + 1) % 4][0], 
                        x[(j + 1) % 4][1], y[i + 1][0], y[i + 1][1], y[i][0], y[i][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  } 
                }
              }
            } else {
              if (checkWithinLine(x[(j + 1) % 4][0], x[(j + 1) % 4][1], 
                  x[j % 4][0], x[j % 4][1], y[i][0], y[i][1])) {
                if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                  neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                }
                if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                  neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                }
              } else if (i == 3) {
                //check corner case
                if (checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], 
                    x[j % 4][0], x[j % 4][1], y[0][0], y[0][1], y[i][0], y[i][1]) 
                    || checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], x[j % 4][0], 
                        x[j % 4][1], y[i][0], y[i][1], y[i - 1][0], y[i - 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }
                }
              } else if (i == 0) {
                if (checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], 
                    x[j % 4][0], x[j % 4][1], y[i][0], y[i][1], y[3][0], y[3][1]) 
                    || checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], x[j % 4][0], 
                        x[j % 4][1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }
                }
              } else if (i == 1) {
                if (checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], x[j % 4][0], 
                    x[j % 4][1], y[i - 1][0], y[i - 1][1], y[i][0], y[i][1]) 
                    || checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], x[j % 4][0], 
                        x[j % 4][1], y[i][0], y[i][1], y[i + 1][0], y[i + 1][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  }
                }
              } else if (i == 2) {
                if (checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], 
                    x[j % 4][0], x[j % 4][1], y[i - 1][0], y[i - 1][1], y[i][0], y[i][1]) 
                    || checkTwoCommonPoints(x[(j + 1) % 4][0], x[(j + 1) % 4][1], x[j % 4][0], 
                        x[j % 4][1], y[i + 1][0], y[i + 1][1], y[i][0], y[i][1])) {
                  if (!neighborTable.get(roomNum).contains(this.roomObjList.get(k + 1))) {
                    neighborTable.get(roomNum).add(this.roomObjList.get(k + 1));
                  }
                  if (!neighborTable.get(k + 1).contains(this.roomObjList.get(roomNum))) {
                    neighborTable.get(k + 1).add(this.roomObjList.get(roomNum));
                  } 
                }
              }
            }
          }
        }
      }
    }
  }

  private boolean checkTwoCommonPoints(int x1, int y1, int x2, int y2,
      int x3, int y3, int x4, int y4) {
    if ((x1 == x3) && (x2 == x4) && (y1 == y3) && (y2 == y4)) {
      return true;
    }
    return false;  
  }
  
  private boolean checkWithinLine(int x1, int y1, int x2, int y2, int x, int y) {
    if ((x == x1) && (x == x2) && (y > y1) && (y < y2)) {
      return true;
    } else if ((y == y1) && (y == y2) && (x > x1) && (x < x2)) {
      return true;
    } else {
      return false;
    }  
  }

  private boolean hasWinner() {
    if (this.targetObj.getHealth() <= 0) {
      return true;
    }
    return false;
  }
  
  private void updateTurn() {
    if (hasWinner() == true) {
      this.winner = turn;
    }
    if (this.turn == this.playerList.size() - 1) {
      this.turn = 0;
    } else {
      this.turn++;
    }
    this.turnCount++;
    if (hasWinner() == false) {
      moveTarget();
    }
    movePetDfs();
  }
   
  private void moveTarget() {
    this.roomObjList.get(this.targetObj.getLocation()).setHasTarget(false);
    this.targetObj.move();
    this.roomObjList.get(this.targetObj.getLocation()).setHasTarget(true);
  }
   
  private void removeItemFromGame(int playerNum, int itemNum) {
    playerList.get(playerNum).usedItem(itemObjList.get(itemNum));
  }
  
  private void createGraph() {
    for (int i = 1; i <= roomCount; i++) {
      List<Integer> sortedNeigh = new ArrayList<Integer>();
      for (int j = 0; j < neighborTable.get(i).size(); j++) {
        sortedNeigh.add(neighborTable.get(i).get(j).getRoomNum());
      }
      Collections.sort(sortedNeigh);
      for (int j = 0; j < sortedNeigh.size(); j++) {
        worldGraph.addEdge(i - 1, sortedNeigh.get(j) - 1);
      }
    }
    worldGraph.dfs(0);
    this.petTraversalOrder = null;
    this.petTraversalOrder = worldGraph.getTraversal();
  }
  
  private void movePetDfs() {
    if (reset == true) {
      worldGraph.dfs(this.petObj.getLocation() - 1);
      this.petTraversalOrder = null;
      this.petTraversalOrder = worldGraph.getTraversal();
      this.reset = false;
      this.petTraversalIndex = 0;
      movePetDfs();
    }
    this.roomObjList.get(this.petObj.getLocation()).setHasPet(false);
    this.petObj.move(petTraversalOrder.get(petTraversalIndex));
    this.roomObjList.get(this.petObj.getLocation()).setHasPet(true);
    if (this.petTraversalIndex == this.petTraversalOrder.size() - 1) {
      this.petTraversalIndex = 0; 
    } else {
      this.petTraversalIndex++; 
    }
  }
  
  
  /*public void drawPanel() {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
          f.add(new MyPanel(this));
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        f.pack();
        f.setVisible(true);
      }
    });
  }*/
  
  // 
  //Public Methods from Interface and needed for controller commands
  //
  @Override
  public void drawWorld() throws IllegalArgumentException {
    int width = totalCol * 25 + 25;
    int height = totalRow * 25 + 25;

    BufferedImage bufferInstance = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    Graphics graphicsObj = bufferInstance.createGraphics();
    
    graphicsObj.setColor(Color.gray);
    graphicsObj.fillRect(0, 0, width, height);
    for (int i = 0; i < rooms.length; i++) {
      graphicsObj.setColor(Color.white);
      graphicsObj.fillRect(Integer.parseInt(rooms[i][1]) * 25 + 8, 
          Integer.parseInt(rooms[i][0]) * 25 + 8, (Integer.parseInt(rooms[i][3])
              - Integer.parseInt(rooms[i][1])) * 25 + 25, 
          (Integer.parseInt(rooms[i][2]) - Integer.parseInt(rooms[i][0])) * 25 + 25);
      graphicsObj.setColor(Color.DARK_GRAY);
      graphicsObj.drawRect(Integer.parseInt(rooms[i][1]) * 25 + 8,
          Integer.parseInt(rooms[i][0]) * 25 + 8, (Integer.parseInt(rooms[i][3])
              - Integer.parseInt(rooms[i][1])) * 25 + 25, (Integer.parseInt(rooms[i][2])
                  - Integer.parseInt(rooms[i][0])) * 25 + 25);
      graphicsObj.drawString(String.format("%s", rooms[i][4]), 
          Integer.parseInt(rooms[i][1]) * 25 + 13, Integer.parseInt(rooms[i][0]) * 25 + 20);
    }
   
    graphicsObj.dispose();
    try {
      ImageIO.write(bufferInstance, "png", new File("world.png"));
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Unable to draw world image.");
    }
  }
  
  @Override
  public String createPlayer(int playerNum, String playerName, int maxItem, int curRoom) 
      throws IllegalArgumentException {
    if (playerNum < 0 || playerName == null || "".equals(playerName) || maxItem < 0
        || playerList.containsKey(playerNum)) {
      throw new IllegalArgumentException("Player Details are not correct");
    }
    if (curRoom < 1 || curRoom > roomObjList.size()) {
      throw new IllegalArgumentException("Room does not exist");
    }
    Player player1 = new Player(playerNum, playerName, maxItem, roomObjList.get(curRoom));
    this.playerList.put(playerNum, player1);
    return "Player Created";
  }
  
  @Override
  public String movePlayer(int player, int roomNum) throws IllegalArgumentException {
    if (player < 0 || !playerList.containsKey(player)) {
      throw new IllegalArgumentException("Incorrect Player");
    }
    if (roomNum < 1 || roomNum > roomCount) {
      throw new IllegalArgumentException("Room does not Exist");
    }
    int playerRoom = playerList.get(player).getLocation();
    if (neighborTable.get(playerRoom).contains(roomObjList.get(roomNum))) {
      playerList.get(player).move(roomObjList.get(roomNum));
      updateTurn();
      return "Move Completed";
    }
    updateTurn();
    return "Room not a neighbor. Lose your turn";
  }
  
  @Override
  public String pickItem(int playerNum, int itemNum) throws IllegalArgumentException {
    if (playerNum < 0 || !playerList.containsKey(playerNum)) {
      throw new IllegalArgumentException("Incorrect Player");
    }
    if (itemNum < 1 || itemNum > itemCount) {
      throw new IllegalArgumentException("Item does not Exist");
    }
    if (playerList.get(playerNum).getMaxItems() 
        == playerList.get(playerNum).getItemCount()) {
      updateTurn();
      return "Max item count already reached! Lose your turn!";
    }
    int roomNum = playerList.get(playerNum).getLocation();
    if (roomObjList.get(roomNum).hasItem(itemObjList.get(itemNum))) {
      roomObjList.get(roomNum).removeItem(itemObjList.get(itemNum));
      playerList.get(playerNum).pickItem(itemObjList.get(itemNum));
      itemObjList.get(itemNum).updateRoom();
      updateTurn();
      return "Item Picked";
    }
    updateTurn();
    return "Item Not Present";
  }
  
  @Override
  public String lookAround(int player) throws IllegalArgumentException {
    if (player < 0 || !playerList.containsKey(player)) {
      throw new IllegalArgumentException("Incorrect Player");
    }
    String info = playerList.get(player).lookAround();
    updateTurn();
    return info;
  }

  @Override
  public String getSpecificPlayerDetails(int specificPlayerNum) throws IllegalArgumentException {
    if (specificPlayerNum < 0 || !playerList.containsKey(specificPlayerNum)) {
      throw new IllegalArgumentException("Incorrect Player");
    }
    return playerList.get(specificPlayerNum).toString();
  }
 
  @Override
  public String getSpecificRoomDetails(int specificRoomNum) throws IllegalArgumentException {
    if (specificRoomNum < 0 || specificRoomNum > roomObjList.size()) {
      throw new IllegalArgumentException("Incorrect Room Number");
    }
    return roomObjList.get(specificRoomNum).displaySpaceInfo();
  }
    
  @Override
  public String movePet(int nextRoom) throws IllegalArgumentException {
    if (nextRoom < 1 || nextRoom > roomCount) {
      throw new IllegalArgumentException("Room does not Exist");
    }
    if (nextRoom == this.petObj.getLocation()) {
      return "You are trying to move the pet within the same room! "
          + "Try again to move it to another room.";
    }
    this.roomObjList.get(this.petObj.getLocation()).setHasPet(false);
    this.petObj.move(nextRoom);
    this.roomObjList.get(this.petObj.getLocation()).setHasPet(true);
    this.reset = true;
    int loc = this.petObj.getLocation();
    updateTurn();
    return String.format("Pet Moved to Room %d", loc);
  }
  
  @Override
  public String killAttempt(int playerNum, int itemNum) throws IllegalArgumentException {
    if (playerNum < 0 || !playerList.containsKey(playerNum)) {
      throw new IllegalArgumentException("Incorrect Player");
    }
    if (itemNum < 0 || itemNum > itemCount) {
      throw new IllegalArgumentException("Item does not Exist");
    }
    //check target in same room
    if (playerList.get(playerNum).getLocation() != this.targetObj.getLocation()) {
      if (itemNum > 0 || playerList.get(playerNum).hasItem(this.itemObjList.get(itemNum)) == true) {
        removeItemFromGame(playerNum, itemNum);
      }
      updateTurn();
      return "Target not in same room. Cannot attempt to kill. Lose your turn.";
    }
    
    //check player has the item
    int itemDamage;
    if (itemNum == 0) {
      itemDamage = 1;
    } else {
      if (playerList.get(playerNum).hasItem(this.itemObjList.get(itemNum)) == false) {
        updateTurn();
        return "You do not have this Item to use for attack. Lose your turn.";
      }
      itemDamage = this.itemObjList.get(itemNum).getItemDamage();
    }
    
    //check if seen by any other player
    int roomNum = playerList.get(playerNum).getLocation();
    if (roomObjList.get(roomNum).seen() == true) {
      if (itemNum != 0) {
        removeItemFromGame(playerNum, itemNum);
      }
      updateTurn();
      return "You were seen during the attack! Attack unsuccessful!";
    }
    
    //if attack is successful, remove item from player and update target health.
    targetObj.changeHealth(itemDamage);
    if (itemNum != 0) {
      removeItemFromGame(playerNum, itemNum);
    }
    updateTurn();
    return "Attack successful!!";
  }
  
  @Override
  public String computerAction(List<Integer> rand) {
    if (rand == null) {
      throw new IllegalArgumentException("Random List can't be null");
    }
    
    //try kill
    if (this.playerList.get(0).getLocation() == this.targetObj.getLocation() 
        && roomObjList.get(playerList.get(0).getLocation()).see() == false) {
      int item = this.playerList.get(0).getMaxDamageItem();
      return killAttempt(0, item);
    }
    
    //choose a random action
    //choose pickup only if maxItemCount is not reached and there are items in current space.
    //choose move only if neighbors are present.
    List<String> actions = new ArrayList<String>();
    if (playerList.get(0).getMaxItems() > playerList.get(0).getItemCount() 
        && roomObjList.get(playerList.get(0).getLocation()).getItemNums().size() > 0) {
      actions.add("pick");  
    }
    if (roomObjList.get(playerList.get(0).getLocation()).getNeighborNums().size() > 0) {
      actions.add("move");
    }
    actions.add("movePet");
    actions.add("look");
    
    Random random = new RandomNumber(rand);
    String action = actions.get(random.nextInt(actions.size()));

    int r = 0;
    switch (action) {
      case "move":
        r = random.nextInt(roomObjList.get(playerList.get(0).getLocation()).getNeighborNums()
            .size());
        int roomNum = roomObjList.get(playerList.get(0).getLocation()).getNeighborNums().get(r);
        return movePlayer(0, roomNum);
      case "pick":
        r = random.nextInt(roomObjList.get(playerList.get(0).getLocation()).getItemNums().size());
        int itemNum = roomObjList.get(playerList.get(0).getLocation()).getItemNums().get(r);
        return pickItem(0, itemNum);
      case "movePet":
        int room = random.nextInt((roomCount + 1) - 1) + 1;
        return movePet(room);
      case "look":
        return lookAround(0);
      default:
        updateTurn();
        return "Unable to Perform action for Computer Player.";
    }
  }
    
  @Override
  public List<String> getTurn() {
    List<String> playerTurn = new ArrayList<String>();
    playerTurn.add(String.format("%s", this.turn));
    playerTurn.add(playerList.get(this.turn).getPlayerName());
    return playerTurn;
  }
   
  @Override
  public boolean gameOver() {
    if (hasWinner() == true) {
      return true;
    }
    if (this.turnCount == this.gameMaxTurn + 1) {
      this.winner = -1;
      return true;
    }
    return false;
  }
  
  @Override
  public List<String> getWinner() {
    List<String> win = new ArrayList<String>();
    if (this.winner == -2) {
      win.add("Game in progress");
    } else if (this.winner == -1) {
      win.add("draw");
    } else {
      win.add(String.format("%s", this.winner));
      win.add(playerList.get(this.winner).getPlayerName());
    }
    return win;
  }
  
  @Override
  public String getTarget() {
    return this.targetObj.toString();
  }
    
  @Override
  public String getAllRoomNames() {
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= roomCount; i++) {
      sb.append(i);
      sb.append(" ");
      sb.append(this.roomObjList.get(i).getRoomName());
      sb.append("\n");
    }
    return sb.toString();
  }
  
  /**
   * This function returns the complete specifications of the world like
    world dimensions, room count and details, item count and details, target details.
   * @return returns a String containing world specifications.
   */
  @Override
  public String toString() {
    return String.format("total rows = %s, total cols = %d, WorldName = %s,\nroomCount = %d,"
        + " itemCount = %d,\nTarget = %s,\nItems = %s,\nRooms = %s\n", 
        this.totalRow, this.totalCol, this.world, this.roomCount, this.itemCount,
        this.targetObj, this.itemObjList, this.roomObjList);
  }
  
  public int[] getCoordinate(int roomNum) {
    // TODO: send coordinates to the panel to draw.
    return null;
  }
  
}