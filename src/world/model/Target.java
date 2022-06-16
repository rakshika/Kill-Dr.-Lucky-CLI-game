/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

/**
 * This is the Implementation class of the Target object which is 
  derived from Player Interface. This class represents the details
  of the target character in the game, like target's location, health,
  and has method to move the target sequentially through the rooms.
 */
public final class Target implements TargetInterface {
  
  private final String targetName;
  private int health;
  private int currentLocation;
  private final int roomCount;

  /**
   * This is the constructor for initializing Target object's parameters.
   * @param name This is name of the target.
   * @param health This is the initial health of the target. Should be greater than 0.
   * @param startLocation This is the initial start location of the target. Room Number
    should be greater than 0 or less than total room count.
   * @param roomCount this is the total number of rooms that the target can traverse.
   * @throws IllegalArgumentException throws exception if correct arguments are not passed.
   */
  public Target(String name, int health, int startLocation, int roomCount) 
      throws IllegalArgumentException {
    if (name == null || "".equals(name) || health < 0 || startLocation < 1 
        || startLocation > roomCount || roomCount < 0) {
      throw new IllegalArgumentException("Invalid parameters passed for creating Target.");
    }
    this.targetName = name;
    this.health = health;
    this.currentLocation = startLocation;
    this.roomCount = roomCount;
  }
  
  @Override
  public int getLocation() {
    return this.currentLocation;
  }
  
  @Override
  public int getHealth() {
    return this.health;
  }
  
  @Override
  public void changeHealth(int damage) throws IllegalArgumentException {
    if (damage <= 0) {
      throw new IllegalArgumentException("Damage should be positive.");
    }
    this.health = this.health - damage;
  }
  
  @Override
  public void move() {
    if (this.currentLocation == this.roomCount) {
      this.currentLocation = 1; 
    } else {
      this.currentLocation = this.currentLocation + 1; 
    }
  } 

  @Override
  public String toString() {
    return String.format("Target = %s, Health = %d, Current Location = %d", 
        this.targetName, this.health, this.currentLocation);    
  }
}
