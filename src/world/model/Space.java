/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.util.List;

/**
 * This is the Space Interface.
 * The space has a name, coordinates, list of items present in it, 
 * and the list of other neighboring spaces.
 */
public interface Space {
  
  /**
   * Gets the name of the particular space.
   * @return a String with value equal to the space name.
   */
  public String getRoomName();

  /**
   * Gets the room number of the particular space.
   * @return a integer room number (starts from 1).
   */
  public int getRoomNum();
 
  /**
   * Sets the list of neighbors of this Space. It sets the list of objects of type space
    as well as sets the arrayList with just the neighbor's room number. 
   * @param neighborList Contains the list of objects of type Room which are neighbors of this Room.
   */
  public void setNeighbor(List<Room> neighborList) throws IllegalArgumentException;
   
  /**
   * This method gives details of just the room numbers of the neighbors.
   * @return a list of integer numbers of the neighbors.
   */
  public List<Integer> getNeighborNums();
  
  /**
   * This method gives the list of item numbers of the items present in the space.
   * @return a list of item numbers.
   */
  public List<Integer> getItemNums();
  
  /**
   * Removes the player from the space.
   * @param playerNum the index of player who has to move out of the space.
   * @throws IllegalArgumentException if invalid player is passed or Player is not 
    present in the space.
   */ 
  public void removePlayer(int playerNum) throws IllegalArgumentException;
  
  /**
   * Adds a valid player to the space.
   * @param playerNum index of the player who needs to enter the space.
   * @param playerName name of the player.
   * @throws IllegalArgumentException thrown if invalid player is passed.
   */
  public void addPlayer(int playerNum, String playerName) throws IllegalArgumentException;
  
  /**
   * Checks if a specific space is present in the space.
   * @param item object reference for the item to check if its present in that space.
   * @return if item so present in space, returns true, otherwise returns false.
   * @throws IllegalArgumentException thrown if item is null or not valid.
   */
  public boolean hasItem(ItemList item) throws IllegalArgumentException;

  /**
   * Removes the item from the space.
   * @param item object representing the item that needs to be removed from the space.
   * @throws IllegalArgumentException thrown when invalid item is passed or item not present
    in the space.
   */
  public void removeItem(ItemList item) throws IllegalArgumentException;
    
  /**
   * Displays all the neighbor details of the space along with items in them.
    Does not list the neighbor if Pet in present in that neighboring space.
   * @return a string with details of all the neighbors of the space 
    along with items present in them.
   */
  public String displayNeighborDetails();
 
  /**
   * Displays only the details that need to be shown to the player.
    Does not list the neighbor if Pet in present in that neighboring space.
   * @return a string with details of space number and name in which player is present,
    along with the items present in that space.
   */
  public String displaySpaceInfo();

  /**
   * This method is used to set the state of hasPet field.
   * @param set value for setting or un-setting the hasPet boolean field.
   */
  public void setHasPet(boolean set);
  
  /**
   * This method is used to set the state of hasTarget field.
   * @param set value for setting or un-setting the hasTarget boolean field.
   */
  public void setHasTarget(boolean set);

  /**
   * This method is used to find if the space contains the Pet.
   * @return returns true if pet is present otherwise returns false.
   */
  public boolean getHasPet();
  
  /**
   * This method is used to find if the space contains the Target.
   * @return returns true if Target is present otherwise returns false.
   */
  public boolean getHasTarget();
  
  /**
   * This method is used to check if any players are present in neighboring rooms.
   * @return true if player present in neighboring room, false otherwise.
   */
  public boolean seen();
  
  /**
   * This method is used to check if a player can see any players in neighboring spaces.
   * If the neighboring space has the pet, the players in those spaces are not seen.
   * @return true if players are seen, false otherwise.
   */
  public boolean see();

}
