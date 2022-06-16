/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control.playercommand;

import world.control.CommandController;
import world.model.World;

/**
 * This class implements the CommandController interface and allows the controller
  to create a player. 
 * The player can be user controller to computer controlled.
 */
public class CreatePlayer implements CommandController {
  
  private final String playerName;
  private final int playerNum;
  private int currentLocation;
  private final int maxItem;
  
  /**
   * This is the constructor for the command which creates the user controlled 
    and computer controlled player.
   * @param playerNum player number of the player to be created.
   * @param playerName player name for the player to be created.
   * @param maxItem maximum number of items that the player is allowed to carry.
   * @param cur room number of the room that the player wants to enter first.
   * @throws IllegalArgumentException thrown if invalid parameters are passed.
   */
  public CreatePlayer(int playerNum, String playerName, int maxItem, int cur) 
      throws IllegalArgumentException {
    if (playerNum < 0 || playerName == null || "".equals(playerName) || maxItem < 0 || cur < 0) {
      throw new IllegalArgumentException("Player Details are not correct");
    }
    this.playerName = playerName;
    this.playerNum = playerNum;
    this.maxItem = maxItem;
    this.currentLocation = cur;
  }
 
  @Override
  public String go(World m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Not correct Model");
    }
    return m.createPlayer(this.playerNum, this.playerName, this.maxItem, this.currentLocation);
  }
}
