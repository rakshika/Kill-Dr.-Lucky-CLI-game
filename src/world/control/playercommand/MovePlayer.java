/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control.playercommand;

import world.control.CommandController;
import world.model.World;

/**
 * This class implements the CommandController interface to move the player
  from current location to the specified room.
 * Player can move to the room only if its is a neighbor to the current room.
 */
public class MovePlayer implements CommandController {

  private final int playerNum;
  private final int roomNum;
  
  /**
   * This is the constructor for the Move Player command which moves the player from 
    current room to the target room.
   * @param playerNum player number of the player who wants to move to different room.
   * @param roomNum room number of the room to which the player wants to move.
   * @throws IllegalArgumentException thrown if invalid parameters are passed.
   */
  public MovePlayer(int playerNum, int roomNum) throws IllegalArgumentException {
    if (playerNum < 0 || roomNum < 1) {
      throw new IllegalArgumentException("Not a valid room Number");
    }
    this.playerNum = playerNum;
    this.roomNum = roomNum;
  }
  
  
  @Override
  public String go(World m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Not correct Model");
    }
    String[] element = m.toString().split("\\s+");
    String[] remove2 = element[15].split(",");
    int roomCount = Integer.parseInt(remove2[0]);
    if (this.roomNum > roomCount) {
      return "Not a valid room Number";
    }
    return m.movePlayer(this.playerNum, this.roomNum);
  }
}
