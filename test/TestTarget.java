import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import world.model.World;
import world.model.WorldSetup;

/**
 * This Test class has unit tests to test the methods of the Target class.
 * 
 * @author Rakshika Raju
 */
public class TestTarget {

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

  @Test
  public void testWorldbyGettingTargetObject() {
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
  }

  @Test
  public void testTargetMoveAndTargetLocation() {
    // Target is moved after every turn. So create a player and make them look
    // around.
    world.createPlayer(1, "rakshika", 2, 5);
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 2", world.getTarget());
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 10", world.getTarget());
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 21", world.getTarget());
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
  }

  @Test
  public void testTargetChangedHealth() {
    world.createPlayer(1, "rakshika", 2, 2);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    world.pickItem(1, 9);
    assertEquals("Attack successful!!", world.killAttempt(1, 9));
    assertEquals("Target =  Doctor Lucky, Health = 48, Current Location = 3", world.getTarget());
  }
  
  @Test
  public void testKillTargetSuccessful() {
    String content = "30 23 Doctor Lucky's Mansion\n2 Doctor Lucky\n"
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
        + "1 3 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick";
    Readable fileContent = new StringReader(content);
    world = new WorldSetup(fileContent, 8);

    assertEquals(false, world.gameOver());
    world.createPlayer(1, "rakshika", 2, 2);
    assertEquals("Target =  Doctor Lucky, Health = 2, Current Location = 1", world.getTarget());
    world.pickItem(1, 9);
    assertEquals("Attack successful!!", world.killAttempt(1, 9));
    assertEquals("Target =  Doctor Lucky, Health = -1, Current Location = 2", world.getTarget());
    assertEquals(true, world.gameOver());
  }

  // TODO Do you have at least one test that verifies that player A can see player B if 
  //they are in the same space without the pet?
  
  
  @Test
  public void testKillAttemptSuccessful() {
    world.createPlayer(1, "rakshika", 1, 2);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 1", world.getTarget());
    world.pickItem(1, 9);
    assertEquals("Attack successful!!", world.killAttempt(1, 9));
    assertEquals("Target =  Doctor Lucky, Health = 48, Current Location = 3", world.getTarget());
  }

  @Test
  public void testKillAttemptSeenByPlayerInSameSpaceWithoutPet() {
    world.createPlayer(1, "rakshika", 1, 2);
    world.createPlayer(2, "raju", 2, 2);
    world.pickItem(1, 9);
    assertEquals(
        "Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n\nNeighbors of Room:\n"
            + "8 : Lanchester Room\n16 : Wine Cellar\n13 : Kitchen\n\nPlayers in Room:\n"
            + "1 : rakshika\n2 : raju\nTarget =  Doctor Lucky, Health = 50, Current Location = 2\n",
        world.getSpecificRoomDetails(2));
    assertEquals("You were seen during the attack! Attack unsuccessful!", world.killAttempt(1, 9));
  }

  @Test
  public void testKillAttemptSeenByPlayerInSameSpaceWithPet() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 6);
    world.pickItem(1, 14);
    world.lookAround(1);
    world.lookAround(2);
    world.movePet(1);
    world.movePlayer(1, 18);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "1 : rakshika\n2 : raju\nPet =  Cooper the Husky, Current Location = 6\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("You were seen during the attack! Attack unsuccessful!", world.killAttempt(1, 14));
  }

  @Test
  public void testKillAttemptSeenByPlayerInNeighborWithoutPetInNeighbor() {
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
  public void testKillAttemptSeenByPlayerInNeighborWithPetInNeighbor() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 5);
    world.pickItem(1, 14);
    world.lookAround(1);
    world.lookAround(2);
    world.movePlayer(1, 18);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "1 : rakshika\n", world.getSpecificRoomDetails(6));
    world.movePet(1);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "1 : rakshika\nTarget =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("You were seen during the attack! Attack unsuccessful!", world.killAttempt(1, 14));
  }

  @Test
  public void testKillAttemptWithPetInSameSpaceNoNeighborPlayer() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 17);
    world.pickItem(1, 14);
    world.lookAround(1);
    world.lookAround(2);
    world.movePet(1);
    world.movePlayer(2, 18);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "1 : rakshika\nPet =  Cooper the Husky, Current Location = 6\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("Attack successful!!", world.killAttempt(1, 14));
  }

  @Test
  public void testKillAttemptWithPetInSameSpaceWithNeighborPlayer() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 7);
    world.pickItem(1, 14);
    world.lookAround(1);
    world.lookAround(2);
    world.movePet(1);
    world.movePlayer(2, 18);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "1 : rakshika\nPet =  Cooper the Husky, Current Location = 6\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("Attack successful!!", world.killAttempt(1, 14));
  }
  
  @Test
  public void testTargetDoesNotMoveAfterbeingKilled() {
    String content = "30 23 Doctor Lucky's Mansion\n2 Doctor Lucky\n"
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
    World world2 = new WorldSetup(fileContent, 8);
    
    List<Integer> rand = new ArrayList<Integer>();
    rand.add(0);
    rand.add(0);
    world2.createPlayer(0, "computer", 2, 21);
    world2.createPlayer(1, "player1", 2, 3);
    world2.pickItem(1, 7);
    assertEquals("Item Picked", world2.computerAction(rand));
    assertEquals(false, world2.gameOver());;
    assertEquals("Target =  Doctor Lucky, Health = 2, Current Location = 3", world2.getTarget());
    assertEquals("[Game in progress]", world2.getWinner().toString());
    assertEquals("Attack successful!!", world2.killAttempt(1, 7));
    assertEquals("Target =  Doctor Lucky, Health = -2, Current Location = 3", world2.getTarget());
    assertEquals("[1, player1]", world2.getWinner().toString());
    assertEquals(true, world2.gameOver());
  }
}
