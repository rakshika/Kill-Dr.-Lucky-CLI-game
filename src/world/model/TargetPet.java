/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

/**
 * This is the implementation class for the Target's Pet.
 * The Pet moves from one room to another following DFS pattern.
 * The Pet can also be moved manually by the Players to any space.
 * The Pet makes the space that it occupies virtually invisible to the surrounding players.
 */
public class TargetPet implements Pet {
  
  private final String petName;
  private int currentLocation;
  
  /**
   * This is the constructor for the the TargetPet Class.
   * @param petName the name to identify the pet
   * @throws IllegalArgumentException thrown if the name is not in valid format.
   */
  public TargetPet(String petName) throws IllegalArgumentException {
    if (petName == null || "".equals(petName)) {
      throw new IllegalArgumentException("Invalid parameters passed for creating Target's Pet.");
    }
    this.petName = petName;
    this.currentLocation = 1;
  }

  @Override
  public int getLocation() {
    return this.currentLocation;
  }

  @Override
  public void move(int nextRoom) throws IllegalArgumentException {
    if (nextRoom < 1) {
      throw new IllegalArgumentException("Room number does not exist.");
    }
    this.currentLocation = nextRoom;
    //System.out.println("Pet location " + nextRoom);
  }

  @Override
  public String toString() {
    return String.format("Pet = %s, Current Location = %d", this.petName, this.currentLocation);
  }
}
