import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import world.model.World;
import world.model.WorldSetup;

/**
 * This Test class has unit tests to test the methods of the player class.
 * @author Rakshika Raju
 */
public class TestPlayer {

  private World world;
  
  /**
   * Sets the initial requirements before running the tests.
   */
  @Before
  public void setUp() {
    String content = "30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n25 20 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick";
    Readable fileContent = new StringReader(content);
    world = new WorldSetup(fileContent, 8);
  }
  
  //CHECK ALL METHODS AND FUNCTIONALITY OF PLAYER
  @Test
  public void testPlayerCreation() {
    //Using toString() to check if player is created correctly and values are set correctly.
    world.createPlayer(1, "rakshika", 2, 5);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\nPlayer in Room: Num: 5 Name: Guest Room\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n", 
        world.getSpecificPlayerDetails(1));
  }

  @Test
  public void testPlayerStartLocation() {
    //Using toString() to check if player is created correctly and values are set correctly.
    world.createPlayer(1, "rakshika", 2, 5);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\nPlayer in Room: Num: 5 Name: Guest Room\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n", 
        world.getSpecificPlayerDetails(1));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationIfIllegalArgument() {
    world.createPlayer(1, "rakshika", -2, 5);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationIfIllegalArgument2() {
    world.createPlayer(1, "", 2, -2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerCreationIfIllegalArgument3() {
    world.createPlayer(1, "rakshika", 2, 30);
  }
  
  @Test
  public void testValidMovePlayer() {
    world.createPlayer(1, "rakshika", 2, 5);
    assertEquals("Move Completed", world.movePlayer(1, 20));
  }
  
  @Test
  public void testInvalidMovePlayer() {
    world.createPlayer(1, "rakshika", 2, 5);
    assertEquals("Room not a neighbor. Lose your turn", world.movePlayer(1, 8));
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testMovePlayerException() {
    world.createPlayer(1, "rakshika", 2, 5);
    world.movePlayer(1, 30);
  }
  
  @Test
  public void testPickItem() {
    world.createPlayer(1, "rakshika", 1, 6);
    assertEquals("Item Picked", world.pickItem(1, 14));
  }
  
  @Test
  public void testPickItemOverflow() {
    world.createPlayer(1, "rakshika", 2, 6);
    world.pickItem(1, 14);
    world.pickItem(1, 21);
    world.movePlayer(1, 1);
    assertEquals("Max item count already reached! Lose your turn!", world.pickItem(1, 13));
  }
  
  @Test
  public void testInvalidPickItem() {
    world.createPlayer(1, "rakshika", 1, 6);
    assertEquals("Item Not Present", world.pickItem(1, 5));
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testPickItemException() {
    world.createPlayer(1, "rakshika", 1, 5);
    world.pickItem(1, 30);
  }
  
  @Test 
  public void testPlayerDetailsAfterMoveAndItemPick() {
    world.createPlayer(1, "rakshika", 2, 6);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n",
        world.getSpecificPlayerDetails(1));
    world.pickItem(1, 21);
    world.pickItem(1, 21);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n"
        + "21. Hair Comb, Item Damage: 2\n", world.getSpecificPlayerDetails(1));
    world.movePlayer(1, 1);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 1 Name: Trophy Room\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n"
        + "21. Hair Comb, Item Damage: 2\n", world.getSpecificPlayerDetails(1));
  }
  
  // TODO: Do you have at least one test that verifies that the looking 
  //around command works correctly when there are players in the same space?
  
  @Test
  public void testLookAround() {
    world.createPlayer(1, "rakshika", 2, 6);
    assertEquals("Current Room Num = 6, Room Name = Bathroom,\n\n"
        + "Items in Room:\n14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\n"
        + "Players in Room:\n1 : rakshika\n\nNEIGHBOR DETAILS:\nRoom Num = 4, "
        + "Room Name = Dining Hall,\n\nItems in Room:\n\nNeighbors of Room:\n"
        + "3 : Foyer\n6 : Bathroom\n7 : Drawing Room\n13 : Kitchen\n\nPlayers in Room:\n\n"
        + "Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\nNeighbors of Room:\n"
        + "6 : Bathroom\n20 : Closet\n\nPlayers in Room:\n\nRoom Num = 7, Room Name = "
        + "Drawing Room,\n\nItems in Room:\n\nNeighbors of Room:\n3 : Foyer\n4 : Dining Hall\n"
        + "6 : Bathroom\n15 : Hedge Maze\n\nPlayers in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testLookAroundWhenNoItems() {
    world.createPlayer(1, "rakshika", 2, 5);
    assertEquals("Current Room Num = 5, Room Name = Guest Room,\n\n"
        + "Items in Room:\n\n"
        + "Neighbors of Room:\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\n1 : rakshika\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\n"
        + "Players in Room:\n\nRoom Num = 20, Room Name = Closet,\n\n"
        + "Items in Room:\n18. Piece of Rope, Item Damage: 2\n\n"
        + "Neighbors of Room:\n5 : Guest Room\n10 : Master Suite\n12 : Armory\n\n"
        + "Players in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testLookAroundWithTarget() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Current Room Num = 1, Room Name = Trophy Room,\n\n"
        + "Items in Room:\n13. Pinking Shears, Item Damage: 2\n\n"
        + "Neighbors of Room:\n5 : Guest Room\n6 : Bathroom\n10 : Master Suite\n"
        + "20 : Closet\n8 : Lanchester Room\n\n"
        + "Players in Room:\n1 : rakshika\nPet =  Cooper the Husky, Current Location = 1\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 5, Room Name = Guest Room,\n\n"
        + "Items in Room:\n\nNeighbors of Room:\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\n\nRoom Num = 6, Room Name = Bathroom,\n\n"
        + "Items in Room:\n14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\n"
        + "Players in Room:\n\nRoom Num = 10, Room Name = Master Suite,\n\n"
        + "Items in Room:\n19. Silken Cord, Item Damage: 3\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n9 : Ball Room\n11 : Nursery\n20 : Closet\n\n"
        + "Players in Room:\n\nRoom Num = 20, Room Name = Closet,\n\n"
        + "Items in Room:\n18. Piece of Rope, Item Damage: 2\n\n"
        + "Neighbors of Room:\n5 : Guest Room\n10 : Master Suite\n12 : Armory\n\n"
        + "Players in Room:\n\nRoom Num = 8, Room Name = Lanchester Room,\n\n"
        + "Items in Room:\n17. Tight Hat, Item Damage: 2\n20. Loud Noise, Item Damage: 1\n\n"
        + "Neighbors of Room:\n2 : Pantry\n10 : Master Suite\n13 : Kitchen\n18 : Gallery\n\n"
        + "Players in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testOtherPlayerWhenActiveTurnPlayerMoves() {
    world.createPlayer(1, "rakshika", 1, 16);
    world.createPlayer(2, "raju", 2, 13);
    String player21 = world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13); 
    world.createPlayer(3, "computer", 1, 9);
    String player31 = world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9); 
    world.movePlayer(1, 2);
    assertEquals(player21, world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13));
    assertEquals(player31, world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9));
  }
  
  @Test
  public void testOtherPlayerWhenActiveTurnPlayerPicksItem() {
    world.createPlayer(1, "rakshika", 1, 16);
    world.createPlayer(2, "raju", 2, 13);
    String player21 = world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13); 
    world.createPlayer(3, "computer", 1, 9);
    String player31 = world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9); 
    world.pickItem(1, 13);
    assertEquals(player21, world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13));
    assertEquals(player31, world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9));
  }
  
  @Test
  public void testOtherPlayerWhenActiveTurnPlayerLooksAround() {
    world.createPlayer(1, "rakshika", 1, 16);
    world.createPlayer(2, "raju", 2, 13);
    String player21 = world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13); 
    world.createPlayer(3, "computer", 1, 9);
    String player31 = world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9); 
    world.lookAround(1);
    assertEquals(player21, world.getSpecificPlayerDetails(2) + world.getSpecificRoomDetails(13));
    assertEquals(player31, world.getSpecificPlayerDetails(3) + world.getSpecificRoomDetails(9));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testCreatingPlayerWithSameIndex() {
    world.createPlayer(1, "rakshika", 1, 16);
    world.createPlayer(1, "raju", 2, 13);
  }
  
  @Test
  public void testTargetAttackWithPokeInEye() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    assertEquals("Attack successful!!", world.killAttempt(1, 0));
    assertEquals("Target =  Doctor Lucky, Health = 49, Current Location = 2", world.getTarget());
  }
  
  @Test
  public void testTargetAttackWithPokeInEyeWhenSeen() {
    world.createPlayer(1, "rakshika", 1, 3);
    world.createPlayer(2, "raju", 2, 4);
    world.lookAround(2);
    world.lookAround(1);
    assertEquals(
        "Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
            + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
            + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n"
            + "4 : Dining Hall\n7 : Drawing Room\n13 : Kitchen\n"
            + "14 : Piazza\n\nPlayers in Room:\n1 : rakshika\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 3\n",
        world.getSpecificRoomDetails(3));
    assertEquals("You were seen during the attack! Attack unsuccessful!", world.killAttempt(1, 0));
  }

  @Test
  public void testTargetAttackWithInvalidItem() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    assertEquals("You do not have this Item to use for attack. Lose your turn.", 
        world.killAttempt(1, 4));
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 2", world.getTarget());
  }
  
  @Test
  public void testTargetAttackWhenNotInSameSpace() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 2", world.getTarget());
    assertEquals("Target not in same room. Cannot attempt to kill. Lose your turn.", 
        world.killAttempt(1, 4));
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 3", world.getTarget());
  }
  
  @Test
  public void testTargetAttackWhenNotInSameSpaceCheckEvidence() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    world.pickItem(1, 13);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\nPlayer in Room: Num: 1 Name: Trophy Room"
        + "\nMax Number of Items allowed to pick: 2\nItems Picked:\n"
        + "13. Pinking Shears, Item Damage: 2\n", world.getSpecificPlayerDetails(1));
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 2", world.getTarget());
    assertEquals("Target not in same room. Cannot attempt to kill. Lose your turn.", 
        world.killAttempt(1, 13));
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 3", world.getTarget());
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\nPlayer in Room: Num: 1 Name: Trophy Room"
        + "\nMax Number of Items allowed to pick: 2\nItems Picked:\n", 
        world.getSpecificPlayerDetails(1));
  }
  
  @Test
  public void testPlayerMovedtoSpaceWhichIsNeighborWithPet() {
    //Room 1 is actually a neighbor of room 6. But since Pet is present in Room 1, Player
    //cannot see that room in neighbor list, but can still move to that space as per design.
    world.createPlayer(1, "rakshika", 2, 6);
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\n"
        + "Items in Room:\n13. Pinking Shears, Item Damage: 2\n\n"
        + "Neighbors of Room:\n5 : Guest Room\n6 : Bathroom\n10 : Master Suite\n"
        + "20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n"
        + "Pet =  Cooper the Husky, Current Location = 1\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n", 
        world.getSpecificRoomDetails(1));
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n4 : Dining Hall\n5 : Guest Room\n"
        + "7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n",
        world.getSpecificRoomDetails(6));
    assertEquals("Move Completed", world.movePlayer(1, 1));
  }
}
