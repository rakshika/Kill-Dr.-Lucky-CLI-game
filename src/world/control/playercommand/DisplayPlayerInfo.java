/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control.playercommand;

import world.control.CommandController;
import world.model.World;

/**
 * This class implements the Command Controller interface to display the player details 
  like name, room that the player is present in, and the items that they carry. 
 */
public class DisplayPlayerInfo implements CommandController {
  
  private final int playerNum;
  
  /**
   * This is the constructor for the display player information command.
   * @param playerNum player number of the player whose details need to be displayed.
   * @throws IllegalArgumentException thrown if invalid player number is passed.
   */
  public DisplayPlayerInfo(int playerNum) throws IllegalArgumentException {
    if (playerNum < 0) {
      throw new IllegalArgumentException("Player does not exist");
    }
    this.playerNum = playerNum;
  }

  @Override 
  public String go(World m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Not correct Model");
    }
    return String.format("\n%s\n%s\n", m.getSpecificPlayerDetails(this.playerNum), m.getTarget());
  }
}
