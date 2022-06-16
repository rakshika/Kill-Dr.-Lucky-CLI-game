/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control.playercommand;

import world.control.CommandController;
import world.model.World;

/**
 * This class implements the CommandController interface and allows the controller
  to create the graphical representation of the world.
 * The image is stored in the /res directly from the current working directory.
 */
public class CreateWorldGraph implements CommandController {

  @Override
  public String go(World m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Not correct Model");
    }
    try {
      m.drawWorld();
      return "World PNG created";
    } catch (IllegalArgumentException ioe) {
      throw new IllegalArgumentException("Unable to draw world image.");
    }
  }
}
