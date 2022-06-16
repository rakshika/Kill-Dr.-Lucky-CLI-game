/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control.playercommand;

import world.control.CommandController;
import world.model.World;

/**
 * This class implements the CommandController interface which allows the player
  to pick an item. 
 * An item can be picked only if it is present in the space that the player is currently present in.
 */
public class PickItem implements CommandController {

  private final int playerNum;
  private final int itemNum;
  
  /**
   * This is the constructor for the pick item command which allows the player
    to pick an item which is in the space they are currently in.
   * @param playerNum player number of the player who wants to pick an item.
   * @param itemNum item number of the item which the player wants to pick.
   * @throws IllegalArgumentException thrown if invalid parameters are passed.
   */
  public PickItem(int playerNum, int itemNum) throws IllegalArgumentException {
    if (playerNum < 0 || itemNum < 1) {
      throw new IllegalArgumentException("Not a valid item Number");
    }
    this.playerNum = playerNum;
    this.itemNum = itemNum;
  }
 
  @Override
  public String go(World m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Not correct Model");
    }
    String[] element = m.toString().split("\\s+");
    String[] remove = element[18].split(",");
    int itemCount = Integer.parseInt(remove[0]);
    if (this.itemNum > itemCount) {
      return "Item number not in correct range";
    }
    return m.pickItem(this.playerNum, this.itemNum);
  }
}
