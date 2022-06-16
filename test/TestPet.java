import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;
import world.model.Graph;
import world.model.TargetPet;
import world.model.World;
import world.model.WorldSetup;

/**
 * This Test class has unit tests to test the methods of the player class.
 * 
 * @author Rakshika Raju
 */
public class TestPet {

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
  public void testPetCreatedSuccessfully() {
    TargetPet pet1 = new TargetPet("Cooper the Husky");
    assertEquals("Pet = Cooper the Husky, Current Location = 1", pet1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPetInvalidInFile() {
    String content = "30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n";
    Readable fileContent = new StringReader(content);
    World world2 = new WorldSetup(fileContent, 8);
  }
  
  @Test
  public void testPetInitialLocationInGame() {
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
            + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
  }

  @Test
  public void testPetValidMove() {
    TargetPet pet2 = new TargetPet("Cooper the Husky");
    pet2.move(20);
    assertEquals(20, pet2.getLocation());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPetInvalidMove() {
    TargetPet pet2 = new TargetPet("Cooper the Husky");
    pet2.move(-3);
  }

  @Test
  public void testPetMovedAsTurn() {
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
            + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
    // After moving to 7, DFS is called with root as 7, so next node in DFS after 7 is 3.
    assertEquals("Pet Moved to Room 7", world.movePet(7));
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
        + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(1));
    assertEquals("Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
        + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
        + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n4 : Dining Hall\n"
        + "7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\nPlayers in Room:\n"
        + "Pet =  Cooper the Husky, Current Location = 3\n", world.getSpecificRoomDetails(3));
  }

  @Test
  public void testPetMovedByDfsOnce() {
    world.createPlayer(1, "rakshika", 1, 16);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
            + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
    world.lookAround(1);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n6 : Bathroom\n"
            + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(1));
    assertEquals(
        "Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
            + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n",
        world.getSpecificRoomDetails(5));
  }

  @Test
  public void testPetMovedByDfsTwice() {
    world.createPlayer(1, "rakshika", 1, 16);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
            + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
    world.lookAround(1);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n6 : Bathroom\n"
            + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(1));
    assertEquals(
        "Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
            + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n",
        world.getSpecificRoomDetails(5));
    world.lookAround(1);
    // Same traversal order is maintained.
    assertEquals(
        "Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
            + "Neighbors of Room:\n1 : Trophy Room\n20 : Closet\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(5));
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n4 : Dining Hall\n5 : Guest Room\n"
        + "7 : Drawing Room\n\nPlayers in Room:\nPet =  Cooper the Husky, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
  }

  @Test
  public void testPetChangedDfsTraversalAsMovedCalledByPlayer() {
    world.createPlayer(1, "rakshika", 1, 16);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
            + "6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
    world.lookAround(1);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n6 : Bathroom\n"
            + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(1));
    assertEquals(
        "Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
            + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n",
        world.getSpecificRoomDetails(5));
    // After moving Pet to 14, DFS is called with root as 14, so next node in DFS after 14 is 3.
    assertEquals("Pet Moved to Room 14", world.movePet(14));
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(5));
    assertEquals(
        "Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
            + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
            + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n"
            + "4 : Dining Hall\n7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\n"
            + "Players in Room:\nPet =  Cooper the Husky, Current Location = 3\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 3\n",
        world.getSpecificRoomDetails(3));
  }

  @Test
  public void testSpaceWhenPetInCurrentRoom() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals(
        "Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
            + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n"
            + "5 : Guest Room\n"
            + "6 : Bathroom\n"
            + "10 : Master Suite\n"
            + "20 : Closet\n"
            + "8 : Lanchester Room\n\n"
            + "Players in Room:\n"
            + "1 : rakshika\nPet =  Cooper the Husky, Current Location = 1\n"
            + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n",
        world.getSpecificRoomDetails(1));
  }

  @Test
  public void testSpaceWhenPetInNeighbor() {
    world.createPlayer(1, "rakshika", 2, 6);
    //Room 1 is neighbor but not seen since pet is in room 1.
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n"
        + "4 : Dining Hall\n"
        + "5 : Guest Room\n"
        + "7 : Drawing Room\n"
        + "\nPlayers in Room:\n1 : rakshika\n", world.getSpecificRoomDetails(6));
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n"
        + "5 : Guest Room\n"
        + "6 : Bathroom\n"
        + "10 : Master Suite\n"
        + "20 : Closet\n"
        + "8 : Lanchester Room\n"
        + "\nPlayers in Room:\n"
        + "Pet =  Cooper the Husky, Current Location = 1\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n", 
        world.getSpecificRoomDetails(1));
  }

  @Test
  public void testSpaceWhenPetNotInNeighbor() {
    world.createPlayer(1, "rakshika", 2, 6);
    world.movePet(19);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n14. Duck Decoy, "
        + "Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n"
        + "1 : Trophy Room\n"
        + "4 : Dining Hall\n"
        + "5 : Guest Room\n"
        + "7 : Drawing Room\n"
        + "\nPlayers in Room:\n1 : rakshika\n", world.getSpecificRoomDetails(6));
  }
  
  @Test
  public void testPickItemWhenPetInRoom() {
    world.createPlayer(1, "rakshika", 2, 6);
    world.lookAround(1);
    world.lookAround(1);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n4 : Dining Hall\n5 : Guest Room\n"
        + "7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n"
        + "Pet =  Cooper the Husky, Current Location = 6\n", world.getSpecificRoomDetails(6));
    world.pickItem(1, 14);
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\nMax Number of Items allowed to pick: 2\n"
        + "Items Picked:\n14. Duck Decoy, Item Damage: 3\n", world.getSpecificPlayerDetails(1));
  }
  
  @Test
  public void testMovePlayerWhenPetInRoom() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n"
        + "5 : Guest Room\n6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n"
        + "\nPlayers in Room:\n1 : rakshika\nPet =  Cooper the Husky, Current Location = 1\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n", 
        world.getSpecificRoomDetails(1));
    assertEquals("Move Completed", world.movePlayer(1, 5));
  }
   
  @Test
  public void testLookAroundWhenPetInRoom() {
    world.createPlayer(1, "rakshika", 2, 1);
    world.movePlayer(1, 5);
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\n1 : rakshika\n"
        + "Pet =  Cooper the Husky, Current Location = 5\n", 
        world.getSpecificRoomDetails(5));
    assertEquals("Current Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\n1 : rakshika\nPet =  Cooper the Husky, Current Location = 5\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n6 : Bathroom\n"
        + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n\n"
        + "Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n4 : Dining Hall\n7 : Drawing Room\n\n"
        + "Players in Room:\n\nRoom Num = 20, Room Name = Closet,\n\nItems in Room:\n"
        + "18. Piece of Rope, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "10 : Master Suite\n12 : Armory\n\nPlayers in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testMovePetWhenPetInNeighborRoom() {
    world.createPlayer(1, "rakshika", 2, 1);
    world.pickItem(1, 14);
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n", 
        world.getSpecificRoomDetails(5));
    assertEquals("Pet Moved to Room 20", world.movePet(20));
  }
  
  @Test
  public void testMovePetWhenPetInNonNeighborRoom() {
    world.createPlayer(1, "rakshika", 2, 21);
    world.pickItem(1, 22);
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n", 
        world.getSpecificRoomDetails(5));
    assertEquals("Pet Moved to Room 20", world.movePet(20));
  }
  
  @Test
  public void testPetMovedtoCurrentRoom() {
    world.createPlayer(1, "rakshika", 2, 21);
    world.pickItem(1, 22);
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n", 
        world.getSpecificRoomDetails(5));
    assertEquals("Pet Moved to Room 21", world.movePet(21));
    assertEquals("Room Num = 21, Room Name = Green House,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n18 : Gallery\n\nPlayers in Room:\n1 : rakshika\n", 
        world.getSpecificRoomDetails(21));
  }
  
  @Test
  public void testPetMovedSourceAndDestinationSame() {
    world.createPlayer(1, "rakshika", 2, 21);
    world.pickItem(1, 22);
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n1 : Trophy Room\n6 : Bathroom\n20 : Closet\n\n"
        + "Players in Room:\nPet =  Cooper the Husky, Current Location = 5\n", 
        world.getSpecificRoomDetails(5));
    assertEquals("You are trying to move the pet within the same room! "
        + "Try again to move it to another room.", world.movePet(5));
  }
  
  @Test
  public void testMovePetWhenPetInSameRoom() {
    world.createPlayer(1, "rakshika", 2, 1);
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n"
        + "5 : Guest Room\n6 : Bathroom\n10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n"
        + "\nPlayers in Room:\n1 : rakshika\nPet =  Cooper the Husky, Current Location = 1\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 1\n", 
        world.getSpecificRoomDetails(1));
    assertEquals("Pet Moved to Room 10", world.movePet(10));
  }
    
  @Test
  public void testDfsTraversal() {
    Graph worldGraph = new Graph(5);
    worldGraph.addEdge(0, 1);
    worldGraph.addEdge(0, 2);
    worldGraph.addEdge(0, 3);
    worldGraph.addEdge(0, 4);
    worldGraph.addEdge(1, 3);
    worldGraph.dfs(0);
    assertEquals("[1, 2, 4, 2, 1, 3, 1, 5, 1]", worldGraph.getTraversal().toString());
  }
}
