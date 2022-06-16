import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import world.model.World;
import world.model.WorldSetup;

/**
 * This Test class has unit tests to test the methods of the player class.
 * 
 * @author Rakshika Raju
 */
public class TestComputerPlayer {

  private World world;
  private List<Integer> rand;

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
    rand = new ArrayList<Integer>();
  }

  @Test
  public void testComputerPlayerKillAttemptWithoutWeapon() {
    world.createPlayer(0, "computer", 2, 2);
    world.createPlayer(1, "player1", 2, 12);
    world.pickItem(1, 5);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 2", world.getTarget());
    assertEquals("Attack successful!!", world.computerAction(rand));
    assertEquals("Target =  Doctor Lucky, Health = 49, Current Location = 3", world.getTarget());
  }

  @Test
  public void testComputerPlayerKillAttemptWithOneItem() {
    rand.add(0);
    rand.add(1);
    world.createPlayer(0, "computer", 2, 6);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("Item Picked", world.computerAction(rand));
    rand.remove(0);
    rand.remove(0);
    world.movePlayer(1, 2);
    rand.add(2);
    rand.add(0);
    assertEquals("Pet Moved to Room 1", world.computerAction(rand));
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 6", world.getTarget());
    assertEquals("Attack successful!!", world.computerAction(rand));
    assertEquals("Target =  Doctor Lucky, Health = 48, Current Location = 7", world.getTarget());
  }

  @Test
  public void testComputerPlayerKillAttemptWhenTheyHaveMoreThanOneItem() {
    rand.add(0);
    rand.add(0);
    world.createPlayer(0, "computer", 2, 6);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("Item Picked", world.computerAction(rand));
    world.movePlayer(1, 2);
    assertEquals("Item Picked", world.computerAction(rand));
    assertEquals("\nPlayer Num: 0\nPlayer Name: computer\nPlayer in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n14. Duck Decoy, Item Damage: 3\n"
        + "21. Hair Comb, Item Damage: 2\n", world.getSpecificPlayerDetails(0));
    world.lookAround(1);
    assertEquals("Target =  Doctor Lucky, Health = 50, Current Location = 6", world.getTarget());
    assertEquals("Attack successful!!", world.computerAction(rand));
    //Attacked with item of highest damage.
    assertEquals("Target =  Doctor Lucky, Health = 47, Current Location = 7", world.getTarget());
    assertEquals("\nPlayer Num: 0\nPlayer Name: computer\nPlayer in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n"
        + "21. Hair Comb, Item Damage: 2\n", world.getSpecificPlayerDetails(0));
  }

  @Test
  public void testComputerPlayerPickItem() {
    rand.add(0);
    rand.add(0);
    world.createPlayer(0, "computer", 2, 6);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("Item Picked", world.computerAction(rand));
    assertEquals("\nPlayer Num: 0\nPlayer Name: computer\nPlayer in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n"
        + "14. Duck Decoy, Item Damage: 3\n", world.getSpecificPlayerDetails(0));
  }
  
  @Test
  public void testComputerPlayerMove() {
    rand.add(1);
    rand.add(0);
    world.createPlayer(0, "computer", 2, 6);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("\nPlayer Num: 0\nPlayer Name: computer\nPlayer in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 2\nItems Picked:\n", 
        world.getSpecificPlayerDetails(0));
    assertEquals("Move Completed", world.computerAction(rand));
    assertEquals("\nPlayer Num: 0\nPlayer Name: computer\nPlayer in Room: Num: 1 Name: "
        + "Trophy Room\nMax Number of Items allowed to pick: 2\n"
        + "Items Picked:\n", world.getSpecificPlayerDetails(0));
  }
  
  @Test
  public void testComputerPlayerMovePet() {
    rand.add(2);
    rand.add(8);
    world.createPlayer(0, "computer", 2, 8);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("Room Num = 9, Room Name = Ball Room,\n\n"
        + "Items in Room:\n"
        + "4. Sharp Knife, Item Damage: 3\n\n"
        + "Neighbors of Room:\n"
        + "10 : Master Suite\n"
        + "11 : Nursery\n"
        + "12 : Armory\n"
        + "19 : Library\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(9));
    assertEquals("Room Num = 10, Room Name = Master Suite,\n\nItems in Room:\n"
        + "19. Silken Cord, Item Damage: 3\n\nNeighbors of Room:\n"
        + "1 : Trophy Room\n"
        + "8 : Lanchester Room\n"
        + "9 : Ball Room\n"
        + "11 : Nursery\n"
        + "20 : Closet\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(10));
    //Pet is moved to 9 and since turn is updated, DFS is called with root as 9, 
    //so next location gets updated to 10.
    assertEquals("Pet Moved to Room 9", world.computerAction(rand));
    assertEquals("Room Num = 10, Room Name = Master Suite,\n\nItems in Room:\n"
        + "19. Silken Cord, Item Damage: 3\n\nNeighbors of Room:\n"
        + "1 : Trophy Room\n"
        + "8 : Lanchester Room\n"
        + "9 : Ball Room\n"
        + "11 : Nursery\n"
        + "20 : Closet\n\n"
        + "Players in Room:\n"
        + "Pet =  Cooper the Husky, Current Location = 10\n", world.getSpecificRoomDetails(10));
  }
  
  @Test
  public void testComputerPlayerLookAround() {
    rand.add(3);
    world.createPlayer(0, "computer", 2, 17);
    world.createPlayer(1, "player1", 2, 16);
    world.pickItem(1, 6);
    assertEquals("Current Room Num = 17, Room Name = Garage,\n\nItems in Room:\n"
        + "8. Broom Stick, Item Damage: 2\n11. Trowel, Item Damage: 2\n\n"
        + "Neighbors of Room:\n14 : Piazza\n\n"
        + "Players in Room:\n0 : computer\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 14, Room Name = Piazza,\n\n"
        + "Items in Room:\n16. Monkey Hand, Item Damage: 2\n\n"
        + "Neighbors of Room:\n3 : Foyer\n15 : Hedge Maze\n17 : Garage\n\n"
        + "Players in Room:\n\n", world.computerAction(rand));
  }
  
  @Test
  public void testComputerPlayerAttacksOnlyIfTargetInSameRoom() {
    world.createPlayer(0, "computer", 2, 2);
    world.createPlayer(1, "player1", 2, 21);
    rand.add(3);
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n"
        + "9. Billiard Cue, Item Damage: 2\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n"
        + "16 : Wine Cellar\n"
        + "13 : Kitchen\n\n"
        + "Players in Room:\n"
        + "0 : computer\n", world.getSpecificRoomDetails(2));
    world.computerAction(rand);
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n"
        + "9. Billiard Cue, Item Damage: 2\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n"
        + "16 : Wine Cellar\n"
        + "13 : Kitchen\n\n"
        + "Players in Room:\n0 : computer\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 2\n", 
        world.getSpecificRoomDetails(2));
    assertEquals("Attack successful!!", world.computerAction(rand));
  }
  
  @Test
  public void testComputerPlayerNotAttackSeenNeighbor() {
    world.createPlayer(0, "computer", 1, 2);
    world.createPlayer(1, "player1", 2, 13);
    rand.add(0);
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n"
        + "9. Billiard Cue, Item Damage: 2\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n"
        + "16 : Wine Cellar\n"
        + "13 : Kitchen\n\n"
        + "Players in Room:\n"
        + "0 : computer\n", world.getSpecificRoomDetails(2));
    world.computerAction(rand);
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n"
        + "16 : Wine Cellar\n"
        + "13 : Kitchen\n\n"
        + "Players in Room:\n0 : computer\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 2\n", 
        world.getSpecificRoomDetails(2));
    //Not attacked, since player present in neighboring room and can see the attack.
    assertEquals("Move Completed", world.computerAction(rand));
  }
  
  @Test
  public void testComputerPlayerAttackSeenNeighborWithPet() {
    world.createPlayer(0, "computer", 1, 6);
    world.createPlayer(1, "player1", 2, 5);
    rand.add(0);
    rand.add(0);
    assertEquals("Item Picked", world.computerAction(rand));
    world.lookAround(1);
    world.lookAround(0);
    rand.remove(0);
    rand.remove(0);
    rand.add(2);
    world.computerAction(rand);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "0 : computer\n", world.getSpecificRoomDetails(6));
    world.movePet(1);
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n6 : Bathroom\n"
        + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(1));
    //Attacked, since computer cannot see neighboring player which has pet in same room.
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "0 : computer\nTarget =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("You were seen during the attack! Attack unsuccessful!", 
        world.computerAction(rand));
   
  }
  
  @Test
  public void testComputerAttackWhenPetInSameRoom() {
    world.createPlayer(0, "computer", 1, 6);
    world.createPlayer(1, "player1", 2, 5);
    rand.add(0);
    rand.add(0);
    assertEquals("Item Picked", world.computerAction(rand));
    world.lookAround(1);
    world.lookAround(0);
    rand.remove(0);
    rand.remove(0);
    rand.add(2);
    world.movePet(5);
    world.computerAction(rand);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "0 : computer\nPet =  Cooper the Husky, Current Location = 6\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 6\n", 
        world.getSpecificRoomDetails(6));
    assertEquals("Room Num = 1, Room Name = Trophy Room,\n\nItems in Room:\n"
        + "13. Pinking Shears, Item Damage: 2\n\nNeighbors of Room:\n5 : Guest Room\n"
        + "10 : Master Suite\n20 : Closet\n8 : Lanchester Room\n\nPlayers in Room:\n",
        world.getSpecificRoomDetails(1));
    //Attacked, since computer cannot see neighboring player which has pet in same room.
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n"
        + "0 : computer\nPet =  Cooper the Husky, Current Location = 6\n"
        + "Target =  Doctor Lucky, Health = 50, Current Location = 6\n",
        world.getSpecificRoomDetails(6));
    assertEquals("Attack successful!!", world.computerAction(rand));
  }

}
