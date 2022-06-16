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
public class MovePet implements CommandController {

  private final int roomNum;
  
  /**
   * This is the constructor for the Move Pet command which moves the pet from 
    current room to the destination room.
   * @param petNextRoom the room number of the room where the pet has to be moved.
   * @throws IllegalArgumentException thrown if invalid parameters are passed.
   */
  public MovePet(int petNextRoom) throws IllegalArgumentException {
    if (petNextRoom < 1) {
      throw new IllegalArgumentException("Not a valid room Number");
    }
    this.roomNum = petNextRoom;
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
    return m.movePet(this.roomNum);
  }
}