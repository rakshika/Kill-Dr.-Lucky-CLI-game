/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

/**
 * This is the interface to represent Target character's Pet.
 * The pet enters the game in the same space as the target.
 * It provides details of the Pet like its name and which space it is currently occupies.
 */
public interface Pet {

  /**
   * Gives the Current location in terms of the room number of the pet.
   * @return returns the integer value of the room number (room numbers start from 1)
    where the pet is present. 
   */
  public int getLocation();
  
  /**
   * This method is used to move the pet to a different room.
   * Does not return a value after moving the pet.
   * @param nextRoom room number where the pet needs to be moved (room numbers start from 1)
   */
  public void move(int nextRoom);
  
  /**
   * Gives the details of the Pet like Name and current location.
   * @return returns the String representation of the pet details.
   */
  public String toString();
}
