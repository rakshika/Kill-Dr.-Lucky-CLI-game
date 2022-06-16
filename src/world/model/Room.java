/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This is the implementation of the Space Interface.
 * The space has a name, coordinates, list of items present in it, 
 * and the list of other neighboring spaces.
 */
public final class Room implements Space {  
  
  private final int roomNum;
  private final String roomName;
  private final int[] coordinate;
  private final List<ItemList> itemList;
  private List<Room> neighborList;
  private final Map<Integer, String> roomNumOfNeighbors;
  private final Map<Integer, String> playerList;
  private boolean hasPet;
  private boolean hasTarget;
  private final Target targetObj;
  private final Pet petObj;
  
  /**
   * Constructor for Room Class to initialize the parameters. 
   * Need not throw an exception because the input fields are already validated and passed. 
   * @param roomNum Room number of the room. The rooms are numbered from 1 to n.
   * @param name Contains the Room name.
   * @param itemList Contains a list of the items object which are present in this room.
   * @param coordinates first two values in this list are the row and column value for 
    top left corner of the room. Next two values are the row and column value for 
    bottom right corner of the room.
   * @param targetObj Target Object of the game which moves around the rooms.
   * @param petObj Pet Object of the game which moves around the rooms.
   * @throws IllegalArgumentException if invalid parameters are passed.
   */
  public Room(int roomNum, String name, List<ItemList> itemList, int[] coordinates, 
      Target targetObj, Pet petObj) throws IllegalArgumentException {
    if (roomNum <= 0 || name == null || "".equals(name) || itemList == null 
        || coordinates == null || targetObj == null || petObj == null) {
      throw new IllegalArgumentException("Invalid Inputs");
    }
    this.roomNum = roomNum;
    this.roomName = name;
    this.itemList = itemList;
    this.coordinate = coordinates;
    this.playerList = new HashMap<Integer, String>();
    this.roomNumOfNeighbors = new HashMap<Integer, String>();
    this.targetObj = targetObj;
    this.petObj = petObj;
    this.hasPet = false;
    this.hasTarget = false;
  }
  
  @Override
  public void setNeighbor(List<Room> neighborList) throws IllegalArgumentException {
    if (neighborList == null) {
      throw new IllegalArgumentException("Neighbors are null. Check correctness of input file.");
    } else {
      this.neighborList = neighborList;
      for (int i = 0; i < this.neighborList.size(); i++) {
        this.roomNumOfNeighbors.put(this.neighborList.get(i).getRoomNum(), 
            this.neighborList.get(i).getRoomName());
      }
    }
  }
  
  @Override
  public String getRoomName() {
    return this.roomName;
  }

  @Override
  public int getRoomNum() {
    return this.roomNum;
  }
  
  @Override
  public List<Integer> getNeighborNums() {
    return new ArrayList<Integer>(roomNumOfNeighbors.keySet());
  }
  
  @Override
  public List<Integer> getItemNums() {
    return itemList.stream().map(i -> i.getItemNum()).collect(Collectors.toList());
  }
  
  @Override
  public void removePlayer(int playerNum) throws IllegalArgumentException {
    if (playerNum < 0 || !playerList.containsKey(playerNum)) {
      throw new IllegalArgumentException("Not a valid player.");
    }
    this.playerList.remove(playerNum);
  }
  
  @Override
  public void addPlayer(int playerNum, String playerName) throws IllegalArgumentException {
    if (playerNum < 0 || playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("Not a valid player.");
    }
    this.playerList.put(playerNum, playerName);
  }
  
  @Override
  public boolean hasItem(ItemList item) throws IllegalArgumentException {
    if (item == null) {
      throw new IllegalArgumentException("Not a valid item.");
    }
    if (itemList.contains(item)) {
      return true;
    }
    return false;
  }

  @Override
  public void removeItem(ItemList item) throws IllegalArgumentException {
    if (item == null || !itemList.contains(item)) {
      throw new IllegalArgumentException("Not a valid item.");
    }
    itemList.remove(item);
  }
    
  @Override
  public void setHasPet(boolean set) {
    this.hasPet = set;
  }
  
  @Override
  public void setHasTarget(boolean set) {
    this.hasTarget = set;
  }
  
  @Override
  public boolean getHasPet() {
    return this.hasPet;
  }
  
  @Override
  public boolean getHasTarget() {
    return this.hasTarget;
  }
  
  @Override
  public boolean seen() {
    if (playerList.size() > 1) {
      return true;
    }
    if (hasPet == true) {
      return false;
    }
    for (int i = 0; i < neighborList.size(); i++) {
      if (neighborList.get(i).playerList.size() > 0) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public boolean see() {
    if (playerList.size() > 1) {
      return true;
    }
    if (hasPet == true) {
      return false;
    }
    for (int i = 0; i < neighborList.size(); i++) {
      if (neighborList.get(i).playerList.size() > 0 && neighborList.get(i).hasPet == false) {
        return true;
      }
    }
    return false;
  }
  
  @Override
  public String displayNeighborDetails() {
    StringBuilder neighborDetails = new StringBuilder();
    for (int i = 0; i < neighborList.size(); i++) {
      if (neighborList.get(i).hasPet == false) {
        neighborDetails.append(neighborList.get(i).displaySpaceInfo());
        neighborDetails.append("\n");
      }
    }
    return neighborDetails.toString();
  } 
 
  @Override
  public String displaySpaceInfo() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Room Num = %d, Room Name = %s,\n\nItems in Room:\n", 
        this.roomNum, this.roomName));
    for (int i = 0; i < itemList.size(); i++) {
      sb.append(itemList.get(i).toString().split(", Item Room:")[0]);
      sb.append("\n");
    }
    sb.append("\nNeighbors of Room:\n");
    for (int i = 0; i < neighborList.size(); i++) {
      if (neighborList.get(i).hasPet == false) {
        sb.append(this.neighborList.get(i).getRoomNum());
        sb.append(" : ");
        sb.append(this.neighborList.get(i).getRoomName());
        sb.append("\n");
      }
    }
    sb.append("\nPlayers in Room:\n");
    this.playerList.forEach((k, v) -> 
        sb.append(k + " : " + v + "\n"));
    
    if (this.hasPet) {
      sb.append(this.petObj.toString());
      sb.append("\n");
    }
    if (this.hasTarget) {
      sb.append(this.targetObj.toString());
      sb.append("\n");
    }
    return sb.toString();  
  }
  
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(String.format("Room Num = %d, Room Name = %s,\n\nItems in Room:\n", 
        this.roomNum, this.roomName));
    for (int i = 0; i < itemList.size(); i++) {
      sb.append(itemList.get(i).toString().split(", Item Room:")[0]);
      sb.append("\n");
    }
    sb.append("\nNeighbors of Room:\n");
    this.roomNumOfNeighbors.forEach((k, v) -> 
        sb.append(k + " : " + v + "\n"));
    
    sb.append("\nPlayers in Room:\n");
    this.playerList.forEach((k, v) -> 
        sb.append(k + " : " + v + "\n"));
    
    if (this.hasPet) {
      sb.append(this.petObj.toString());
      sb.append("\n");
    }
    if (this.hasTarget) {
      sb.append(this.targetObj.toString());
      sb.append("\n");
    }
    return sb.toString();    
  }
  
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Room)) {
      return false;
    }
    Room other = (Room) obj;
    return Arrays.equals(coordinate, other.coordinate) && Objects.equals(itemList, other.itemList)
        && Objects.equals(roomName, other.roomName) && roomNum == other.roomNum;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(coordinate, itemList, roomName, roomNum);
  }
}
