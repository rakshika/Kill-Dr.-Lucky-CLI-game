import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import world.model.World;
import world.model.WorldSetup;

/**
 * This is the test class created for testing all the methods of the
  classes in this project.
 * @author Rakshika Raju
 */
public class TestWorld {
  
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
  
  //Check All exceptions
  @Test(expected = IllegalArgumentException.class)
  public void testWorldCoordinates() {
    Readable fileContent = new StringReader("30.0 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky"); 
    World world2 = new WorldSetup(fileContent, 8);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoomCoord() {
    Readable fileContent = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "-15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n25 20 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan"); 
    World world2 = new WorldSetup(fileContent, 8);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRoomForItem() {
    Readable fileContent = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
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
        + "25 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick"); 
    World world2 = new WorldSetup(fileContent, 8);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testDamage() {
    Readable fileContent = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
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
        + "25 3 Civil War Cannon\n2 4 Chain Saw\n16 0 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat"); 
    World world2 = new WorldSetup(fileContent, 8);
  }
  
  @Test
  public void testPlayerCreated() {
    assertEquals("Player Created", world.createPlayer(1, "rakshika", 2, 10));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerCreated() {
    assertEquals("Room does not exist", world.createPlayer(1, "rakshika", 2, 30));
  }
  
  @Test
  public void testMovePlayer() {
    world.createPlayer(1, "rakshika", 2, 10);
    assertEquals("Move Completed", world.movePlayer(1, 8));
  }
  
  @Test
  public void testWrongMoveByPlayer() {
    world.createPlayer(1, "rakshika", 2, 10);
    assertEquals("Room not a neighbor. Lose your turn", world.movePlayer(1, 5));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMovePlayer() {
    world.createPlayer(10, "rakshika", 2, 10);
    world.movePlayer(1, 5);
  }
  
  @Test
  public void testPickItem() {
    world.createPlayer(1, "rakshika", 2, 10);
    assertEquals("Item Not Present", world.pickItem(1, 5));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickItem() {
    world.createPlayer(1, "rakshika", 2, 10);
    world.pickItem(1, 30);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLookAround() {
    world.createPlayer(1, "rakshika", 2, 10);
    world.lookAround(20);
  }
  
  @Test
  public void testNoPlayerOnePlayerInSpace() {
    world.createPlayer(1, "rakshika", 2, 4);
    assertEquals("Room Num = 4, Room Name = Dining Hall,\n\nItems in Room:\n"
        + "\nNeighbors of Room:\n3 : Foyer\n6 : Bathroom\n7 : Drawing Room\n13 : Kitchen\n"
        + "\nPlayers in Room:\n1 : rakshika\n", world.getSpecificRoomDetails(4));
    assertEquals("Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
        + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
        + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n4 : Dining Hall\n"
        + "7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(3));
  }
   
  @Test
  public void testTwoPlayersAddedToSameSpace() {
    world.createPlayer(1, "rakshika", 2, 7);
    world.createPlayer(2, "raju", 1, 13);
    world.movePlayer(1, 4);
    world.movePlayer(2, 4);
    assertEquals("Room Num = 4, Room Name = Dining Hall,\n\nItems in Room:\n"
        + "\nNeighbors of Room:\n3 : Foyer\n7 : Drawing Room\n13 : Kitchen\n\n"
        + "Players in Room:\n1 : rakshika\n2 : raju\n", world.getSpecificRoomDetails(4));
  }
  
  @Test
  public void testGetTurn() {
    world.createPlayer(0, "computer", 2, 7);
    world.createPlayer(1, "raju", 1, 13);
    world.createPlayer(2, "rakshika", 3, 12);
    assertEquals("[1, raju]", world.getTurn().toString());
    world.lookAround(1);
    assertEquals("[2, rakshika]", world.getTurn().toString());
    world.movePlayer(2, 20);
    assertEquals("[0, computer]", world.getTurn().toString());
    world.movePlayer(0, 12);
    assertEquals(false, world.gameOver());
    assertEquals("[1, raju]", world.getTurn().toString());
    world.pickItem(1, 1);
    assertEquals("[2, rakshika]", world.getTurn().toString());
    world.lookAround(2);
    assertEquals("[0, computer]", world.getTurn().toString());
    world.movePlayer(0, 1);
  }
  
  @Test
  public void testGameOver() {
    //Max turns is defined as 8 for this test.
    world.createPlayer(1, "rakshika", 2, 7);
    world.movePlayer(1, 4);
    world.createPlayer(2, "raju", 1, 13);
    world.movePlayer(2, 4);
    world.createPlayer(3, "computer", 3, 12);
    world.movePlayer(3, 20);
    assertEquals(false, world.gameOver());
    world.lookAround(1);
    world.lookAround(2);
    world.lookAround(3);
    world.createPlayer(4, "player4", 3, 19);
    world.movePlayer(4, 1);
    world.lookAround(4);
    assertEquals(true, world.gameOver());
  }
  
  @Test 
  public void testWorldGraphCreated() {
    try {
      world.drawWorld();
    } catch (IllegalArgumentException e) {
      System.out.println("could not draw world");
    }
    File tempFile = new File("world.png");
    boolean exists = tempFile.exists();
    assertEquals(true, exists);
  }
  
  @Test
  public void testComputerWinner() {
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
    world2.createPlayer(0, "computer", 2, 2);
    world2.createPlayer(1, "player1", 2, 21);
    rand.add(0);
    rand.add(0);
    world2.computerAction(rand);
    assertEquals(false, world2.gameOver());
    assertEquals("[Game in progress]", world2.getWinner().toString());
    assertEquals("Attack successful!!", world2.computerAction(rand));
    assertEquals("Target =  Doctor Lucky, Health = 0, Current Location = 2", world2.getTarget());
    assertEquals("[0, computer]", world2.getWinner().toString());
    assertEquals(true, world2.gameOver());
  }
  
  @Test
  public void testHumanWinner() {
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
    assertEquals("[Game in progress]", world2.getWinner().toString());
    assertEquals("Attack successful!!", world2.killAttempt(1, 7));
    assertEquals("Target =  Doctor Lucky, Health = -2, Current Location = 3", world2.getTarget());
    assertEquals("[1, player1]", world2.getWinner().toString());
    assertEquals(true, world2.gameOver());
  }
  
  @Test
  public void testMultipleAttacksWithWinner() {
    String content = "30 23 Doctor Lucky's Mansion\n4 Doctor Lucky\n"
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
    World world2 = new WorldSetup(fileContent, 20);
    
    List<Integer> rand = new ArrayList<Integer>();
    rand.add(3);
    world2.createPlayer(0, "computer", 2, 6);
    world2.createPlayer(1, "player1", 3, 21);
    world2.pickItem(1, 22);
    world2.computerAction(rand);
    world2.movePlayer(1, 11);
    rand.remove(0);
    rand.add(2);
    rand.add(5);
    world2.computerAction(rand);
    world2.pickItem(1, 15);
    assertEquals("Attack successful!!", world2.computerAction(rand));
    assertEquals("Target =  Doctor Lucky, Health = 3, Current Location = 7", world2.getTarget());
    assertEquals(false, world2.gameOver());
    assertEquals("[Game in progress]", world2.getWinner().toString());
    world2.movePlayer(1, 9);
    rand.remove(0);
    rand.remove(0);
    rand.add(0);
    rand.add(1);
    world2.computerAction(rand);
    assertEquals("Target =  Doctor Lucky, Health = 3, Current Location = 9", world2.getTarget());
    assertEquals("Attack successful!!", world2.killAttempt(1, 22));
    assertEquals("Target =  Doctor Lucky, Health = 0, Current Location = 9", world2.getTarget());
    assertEquals(true, world2.gameOver());
    assertEquals("[1, player1]", world2.getWinner().toString());
  }
  
  @Test
  public void testGameDrawTargetEscaped() {
    String content = "30 23 Doctor Lucky's Mansion\n10 Doctor Lucky\n"
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
    World world2 = new WorldSetup(fileContent, 6);
    
    List<Integer> rand = new ArrayList<Integer>();
    rand.add(0);
    rand.add(0);
    world2.createPlayer(0, "computer", 2, 21);
    world2.createPlayer(1, "player1", 2, 3);
    world2.pickItem(1, 7);
    assertEquals("Item Picked", world2.computerAction(rand));
    assertEquals("Attack successful!!", world2.killAttempt(1, 7));
    assertEquals("Target =  Doctor Lucky, Health = 6, Current Location = 4", world2.getTarget());
    world2.computerAction(rand);
    world2.movePlayer(1, 14);
    assertEquals(false, world2.gameOver());
    assertEquals("[Game in progress]", world2.getWinner().toString());
    world2.computerAction(rand);
    assertEquals(true, world2.gameOver());    
    assertEquals("Target =  Doctor Lucky, Health = 6, Current Location = 7", world2.getTarget());
    assertEquals("[draw]", world2.getWinner().toString());
  }
}