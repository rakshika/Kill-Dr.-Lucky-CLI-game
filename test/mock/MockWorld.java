package mock;

import java.util.ArrayList;
import java.util.List;
import world.model.World;

/**
 * This is the mock world model abstract class which implements the World interface to facilitate
  testing the controller in isolation. It allows other classes to re-use the code in this class.
 * @author Rakshika Raju
 */
public abstract class MockWorld implements World {
  protected StringBuilder log;
  protected final int uniqueCode;
  
  /**
   * Constructor to set the output log and unique code.
   * @param log output log used to append the output.
   * @param uniqueCode code to identify the model.
   */
  public MockWorld(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void drawWorld() throws IllegalArgumentException {
    log.append("Input Received for Drawing World\n");
    //return this.uniqueCode;
  }

  @Override
  public String createPlayer(int playerNum, String playerName, int maxItem, int curRoom)
      throws IllegalArgumentException {
    log.append(String.format("Creating Player %d. %s, Max Items = %d, first room = %d\n", 
        playerNum, playerName, maxItem, curRoom));
    return "Player Created";
  }

  @Override
  public String movePlayer(int player, int roomNum) throws IllegalArgumentException {
    log.append(String.format("Moving Player %d to room = %d\n", player, roomNum));
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String pickItem(int playerNum, int itemNum) throws IllegalArgumentException {
    log.append(String.format("Player %d picking item %d\n", playerNum, itemNum));
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String lookAround(int player) throws IllegalArgumentException {
    log.append("Letting Player " + player + " Look Around\n");
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String getSpecificPlayerDetails(int specificPlayerNum) throws IllegalArgumentException {
    log.append("Displaying Player " + specificPlayerNum + " details\n");
    return String.format("At the start of turn, displaying Player details for player "
        + "number %d and unique code %s", specificPlayerNum, this.uniqueCode);
  }
  
  @Override
  public String getSpecificRoomDetails(int specificRoomNum) throws IllegalArgumentException {
    log.append("Displaying details of Room " + specificRoomNum + "\n");
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String movePet(int nextRoom) {
    log.append(String.format("Moving Pet to room = %d\n", nextRoom));
    return String.format("%s", this.uniqueCode);
  }
  
  @Override
  public String killAttempt(int playerNum, int itemNum) throws IllegalArgumentException {
    log.append(String.format("Player %d attempting to kill target with item %d\n", 
        playerNum, itemNum));
    return String.format("%s", this.uniqueCode);
  }
  
  @Override
  public String computerAction(List<Integer> rand) {
    log.append("Computer Controlled Player taking action.\n");
    return String.format("%s", this.uniqueCode);
  }
  
  @Override
  public List<String> getTurn() {
    log.append("Returning the turn\n");
    List<String> returnStringList = new ArrayList<String>(); 
    returnStringList.add("1");
    returnStringList.add("player1");
    //returnStringList.add(String.format("%s", this.uniqueCode));
    return returnStringList;
  }

  @Override
  public boolean gameOver() {
    return false;
  }
  
  @Override
  public List<String> getWinner() {
    List<String> list = new ArrayList<String>();
    list.add("1");
    list.add("player1");
    return list;
  }

  @Override
  public String getTarget() {
    log.append("Target Details\n");
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String getAllRoomNames() {
    log.append("Room Number and Names\n");
    return String.format("%s", this.uniqueCode);
  }

  @Override
  public String toString() {
    return "total rows = 30, total cols = 23, WorldName =  Doctor Lucky's Mansion,\r\n"
        + "roomCount = 21, itemCount = 22,\r\n"
        + "Target = Target =  Doctor Lucky, Health = 50, Current Location = 1,"
        + "Pet = Cooper the Husky, Current Location = 1";
  }
}

