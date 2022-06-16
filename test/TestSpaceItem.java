import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import world.model.ItemList;
import world.model.Player;
import world.model.Room;
import world.model.Target;
import world.model.TargetPet;
import world.model.World;
import world.model.WorldSetup;

/**
 * This Test class has unit tests to test the methods of the Space class.
 * @author Rakshika Raju 
 */
public class TestSpaceItem {

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
  public void testSpaceCreationAndDetails() {
    int[] coordinates = {0, 0, 10, 10};
    Room room1 = new Room(2, "Piazza", List.of(new ItemList(5, 2, "knife", 3), 
        new ItemList(9, 2, "punch", 2)), coordinates, new Target("Dr. Lucky", 50, 1, 21),
        new TargetPet("bruno"));
    assertEquals("Room Num = 2, Room Name = Piazza,\n\nItems in Room:\n"
        + "5. knife, Item Damage: 3\n9. punch, Item Damage: 2\n\n"
        + "Neighbors of Room:\n\nPlayers in Room:\n", room1.toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testRoomCoord() {
    Readable content = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 40  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n27 21 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick");
    World world2 = new WorldSetup(content, 8);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRoomForItem() {
    Readable content = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n27 21 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n23 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick");
    World world2 = new WorldSetup(content, 8);
  }
  
  @Test
  public void testPlayerViewOfSpace() {
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n"
        + "9. Billiard Cue, Item Damage: 2\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n16 : Wine Cellar\n13 : Kitchen\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(2));
  }
  
  @Test
  public void testPlayerAddandRemovedInSpace() {
    int[] coordinates = {0, 0, 10, 10};
    Room room2 = new Room(2, "Piazza", List.of(new ItemList(5, 2, "knife", 3), 
        new ItemList(9, 2, "punch", 2)), coordinates, new Target("Dr. Lucky", 50, 1, 21),
        new TargetPet("bruno"));
    Player player1 = new Player(1, "rakshika", 2, room2);
    assertEquals("Room Num = 2, Room Name = Piazza,\n\nItems in Room:\n"
        + "5. knife, Item Damage: 3\n9. punch, Item Damage: 2\n\n"
        + "Neighbors of Room:\n\nPlayers in Room:\n1 : rakshika\n", room2.toString());
    int[] coordinates2 = {10, 10, 20, 20};
    Room room8 = new Room(8, "Kitchen", List.of(new ItemList(9, 8, "stick", 2)), 
        coordinates2, new Target("Dr. Lucky", 50, 1, 21), new TargetPet("bruno"));
    player1.move(room8);
    assertEquals("Room Num = 2, Room Name = Piazza,\n\nItems in Room:\n"
        + "5. knife, Item Damage: 3\n9. punch, Item Damage: 2\n\n"
        + "Neighbors of Room:\n\nPlayers in Room:\n", room2.toString());
    assertEquals("Room Num = 8, Room Name = Kitchen,\n\nItems in Room:\n"
        + "9. stick, Item Damage: 2\n\nNeighbors of Room:\n\n"
        + "Players in Room:\n1 : rakshika\n", room8.toString());
  }
  
  @Test
  public void testPickItemFromSpace() {
    world.createPlayer(1, "rakshika", 2, 6);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\n"
        + "Neighbors of Room:\n4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n"
        + "\nPlayers in Room:\n1 : rakshika\n", world.getSpecificRoomDetails(6));
    world.pickItem(1, 14);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n"
        + "1 : Trophy Room\n4 : Dining Hall\n7 : Drawing Room\n\n"
        + "Players in Room:\n1 : rakshika\n", world.getSpecificRoomDetails(6));
  }
  
  @Test
  public void testNeighborsNoNeighbors() {    
    Readable content = new StringReader("30 23 Doctor Lucky's Mansion\n50 Doctor Lucky\n"
        + "Cooper the Husky\n21\n15 8 17 13 Trophy Room\n"
        + "18 17 21 20 Pantry\n24  9 26 13 Foyer\n20 9 23 13 Dining Hall \n"
        + "15  3 17 7 Guest Room \n18 4 20  8 Bathroom  \n"
        + "21  3 24  8 Drawing Room\n12 14 17 18 Lanchester Room\n"
        + " 4  7 9  12 Ball Room \n10  9 14 13 Master Suite\n 5 13  9 15 Nursery\n"
        + " 5  4 11  6 Armory\n18 14 24 16 Kitchen \n27  5 28 19 Piazza\n"
        + "25 2 28  4 Hedge Maze\n22 17 24 19 Wine Cellar\n27 21 28 21 Garage\n"
        + " 6 16 11 19 Gallery\n 2  2  4  6 Library\n12  5 14  8 Closet\n"
        + " 2 16  5 21 Green House\n22\n12 3 Crepe Pan\n18 2 Letter Opener\n"
        + "2 2 Shoe Horn\n8 3 Sharp Knife\n11 4 Revolver\n"
        + "15 3 Civil War Cannon\n2 4 Chain Saw\n16 2 Broom Stick\n"
        + "1 2 Billiard Cue\n14 2 Rat Poison\n16 2 Trowel\n"
        + "2 4 Big Red Hammer\n0 2 Pinking Shears\n5 3 Duck Decoy\n"
        + "10 1 Bad Cream\n13 2 Monkey Hand\n7 2 Tight Hat\n"
        + "19 2 Piece of Rope\n9 3 Silken Cord\n7 1 Loud Noise\n"
        + "5 2 Hair Comb\n20 3 Wooden Stick");
    World world2 = new WorldSetup(content, 8);
    world2.createPlayer(1, "rakshika", 2, 17);
    assertEquals("Current Room Num = 17, Room Name = Garage,\n\nItems in Room:\n"
        + "8. Broom Stick, Item Damage: 2\n11. Trowel, Item Damage: 2\n\n"
        + "Neighbors of Room:\n\nPlayers in Room:\n1 : rakshika\n\nNEIGHBOR DETAILS:\n",
        world2.lookAround(1));
  }
  
  @Test
  public void testNeighborsOneNeighbors() {
    world.createPlayer(1, "rakshika", 2, 17);
    assertEquals("Current Room Num = 17, Room Name = Garage,\n\nItems in Room:\n"
        + "8. Broom Stick, Item Damage: 2\n11. Trowel, Item Damage: 2\n\n"
        + "Neighbors of Room:\n14 : Piazza\n\nPlayers in Room:\n1 : rakshika\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 14, Room Name = Piazza,\n\nItems in Room:\n"
        + "16. Monkey Hand, Item Damage: 2\n\nNeighbors of Room:\n3 : Foyer\n"
        + "15 : Hedge Maze\n17 : Garage\n\nPlayers in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testNeighborsTwoNeighbors() {
    world.createPlayer(1, "rakshika", 2, 21);
    assertEquals("Current Room Num = 21, Room Name = Green House,\n\nItems in Room:\n"
        + "22. Wooden Stick, Item Damage: 3\n\nNeighbors of Room:\n11 : Nursery\n"
        + "18 : Gallery\n\nPlayers in Room:\n1 : rakshika\n\nNEIGHBOR DETAILS:\n"
        + "Room Num = 11, Room Name = Nursery,\n\nItems in Room:\n15. Bad Cream, Item Damage: 1\n"
        + "\nNeighbors of Room:\n9 : Ball Room\n10 : Master Suite\n18 : Gallery\n21 : Green House\n"
        + "\nPlayers in Room:\n\nRoom Num = 18, Room Name = Gallery,\n\nItems in Room:\n\n"
        + "Neighbors of Room:\n8 : Lanchester Room\n11 : Nursery\n21 : Green House\n\n"
        + "Players in Room:\n\n", world.lookAround(1));
  }
      
  @Test
  public void testNeighborsMultipleNeighbor() {
    world.createPlayer(1, "rakshika", 2, 14);
    assertEquals("Current Room Num = 14, Room Name = Piazza,\n\nItems in Room:\n"
        + "16. Monkey Hand, Item Damage: 2\n\nNeighbors of Room:\n3 : Foyer\n"
        + "15 : Hedge Maze\n17 : Garage\n\nPlayers in Room:\n1 : rakshika\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
        + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
        + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n4 : Dining Hall\n"
        + "7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\nPlayers in Room:\n\n"
        + "Room Num = 15, Room Name = Hedge Maze,\n\nItems in Room:\n"
        + "10. Rat Poison, Item Damage: 2\n\nNeighbors of Room:\n7 : Drawing Room\n"
        + "14 : Piazza\n\nPlayers in Room:\n\nRoom Num = 17, Room Name = Garage,\n\n"
        + "Items in Room:\n8. Broom Stick, Item Damage: 2\n11. Trowel, Item Damage: 2\n\n"
        + "Neighbors of Room:\n14 : Piazza\n\nPlayers in Room:\n\n", world.lookAround(1));
  }
        
  @Test
  public void testOneItemsInSpace() {
    assertEquals("Room Num = 2, Room Name = Pantry,\n\nItems in Room:\n"
        + "9. Billiard Cue, Item Damage: 2\n\nNeighbors of Room:\n"
        + "8 : Lanchester Room\n16 : Wine Cellar\n13 : Kitchen\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(2));
  }
  
  @Test
  public void testMultipleItemsInSpace() {
    assertEquals("Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
        + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
        + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n"
        + "4 : Dining Hall\n7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\n"
        + "Players in Room:\n", world.getSpecificRoomDetails(3));
  }
  
  @Test
  public void testNoItemsInSpace() {
    assertEquals("Room Num = 4, Room Name = Dining Hall,\n\n"
        + "Items in Room:\n\nNeighbors of Room:\n3 : Foyer\n6 : Bathroom\n"
        + "7 : Drawing Room\n13 : Kitchen\n\nPlayers in Room:\n", world.getSpecificRoomDetails(4));
  }
  
  @Test
  public void testAllItemDetails() {    
    assertEquals("Items = {1=1. Crepe Pan, Item Damage: 3, Item Room: 13, 2=2. Letter Opener, "
        + "Item Damage: 2, Item Room: 19, 3=3. Shoe Horn, Item Damage: 2, Item Room: 3, 4=4. "
        + "Sharp Knife, Item Damage: 3, Item Room: 9, 5=5. Revolver, Item Damage: 4, Item Room: 12,"
        + " 6=6. Civil War Cannon, Item Damage: 3, Item Room: 16, 7=7. Chain Saw, Item Damage: 4, "
        + "Item Room: 3, 8=8. Broom Stick, Item Damage: 2, Item Room: 17, 9=9. Billiard Cue, "
        + "Item Damage: 2, Item Room: 2, 10=10. Rat Poison, Item Damage: 2, Item Room: 15, "
        + "11=11. Trowel, Item Damage: 2, Item Room: 17, 12=12. Big Red Hammer, Item Damage: 4, "
        + "Item Room: 3, 13=13. Pinking Shears, Item Damage: 2, Item Room: 1, 14=14. Duck Decoy, "
        + "Item Damage: 3, Item Room: 6, 15=15. Bad Cream, Item Damage: 1, Item Room: 11, 16=16. "
        + "Monkey Hand, Item Damage: 2, Item Room: 14, 17=17. Tight Hat, Item Damage: 2, "
        + "Item Room: 8, 18=18. Piece of Rope, Item Damage: 2, Item Room: 20, 19=19. Silken Cord,"
        + " Item Damage: 3, Item Room: 10, 20=20. Loud Noise, Item Damage: 1, Item Room: 8, 21=21."
        + " Hair Comb, Item Damage: 2, Item Room: 6, 22=22. Wooden Stick, Item Damage: 3,"
        + " Item Room: 21},", world.toString().split("\n")[3]);
  }
   
  @Test
  public void testItemRoomAfterPicked() {
    String[] items = world.toString().split("\n")[3].split("\\d+=");
    world.createPlayer(1, "rakshika", 2, 6);
    world.pickItem(1, 14);
    assertEquals("14. Duck Decoy, Item Damage: 3, Item Room: 6, ", items[14]);
    items = world.toString().split("\n")[3].split("\\d+=");
    //After Picking an Item, the room number changes to 0.
    assertEquals("14. Duck Decoy, Item Damage: 3, Item Room: 0, ", items[14]);
  }
   
  @Test
  public void testTwoPlayersCannotPickSameItem() {
    world.createPlayer(1, "rakshika", 2, 6);
    world.createPlayer(2, "raju", 2, 6);
    world.pickItem(1, 14);
    assertEquals("Item Not Present", world.pickItem(2, 14));
  }
  
  @Test
  public void testNeighborWithPet() {
    world.createPlayer(1, "rakshika", 2, 6);
    assertEquals("Current Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n"
        + "4 : Dining Hall\n5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n\n"
        + "NEIGHBOR DETAILS:\nRoom Num = 4, Room Name = Dining Hall,\n\nItems in Room:\n\nNeighbors"
        + " of Room:\n3 : Foyer\n6 : Bathroom\n7 : Drawing Room\n13 : Kitchen\n\nPlayers in Room:"
        + "\n\nRoom Num = 5, Room Name = Guest Room,\n\nItems in Room:\n\nNeighbors of Room:\n"
        + "6 : Bathroom\n20 : Closet\n\nPlayers in Room:\n\nRoom Num = 7, Room Name = Drawing Room,"
        + "\n\nItems in Room:\n\nNeighbors of Room:\n3 : Foyer\n4 : Dining Hall\n6 : Bathroom\n"
        + "15 : Hedge Maze\n\nPlayers in Room:\n\n", world.lookAround(1));
    assertEquals("Room Num = 5, Room Name = Guest Room,\n\n"
        + "Items in Room:\n\n"
        + "Neighbors of Room:\n"
        + "1 : Trophy Room\n"
        + "6 : Bathroom\n"
        + "20 : Closet\n"
        + "\nPlayers in Room:\n"
        + "Pet =  Cooper the Husky, Current Location = 5\n", world.getSpecificRoomDetails(5));
  }
  
  @Test
  public void testNeighborWithoutPet() {
    world.createPlayer(1, "rakshika", 2, 14);
    world.movePet(12);
    assertEquals("Current Room Num = 14, Room Name = Piazza,\n\n"
        + "Items in Room:\n16. Monkey Hand, Item Damage: 2\n\n"
        + "Neighbors of Room:\n3 : Foyer\n15 : Hedge Maze\n17 : Garage\n\n"
        + "Players in Room:\n1 : rakshika\n\nNEIGHBOR DETAILS:\n"
        + "Room Num = 3, Room Name = Foyer,\n\nItems in Room:\n"
        + "3. Shoe Horn, Item Damage: 2\n7. Chain Saw, Item Damage: 4\n"
        + "12. Big Red Hammer, Item Damage: 4\n\nNeighbors of Room:\n"
        + "4 : Dining Hall\n7 : Drawing Room\n13 : Kitchen\n14 : Piazza\n\n"
        + "Players in Room:\n\nRoom Num = 15, Room Name = Hedge Maze,\n\nItems in Room:\n"
        + "10. Rat Poison, Item Damage: 2\n\nNeighbors of Room:\n"
        + "7 : Drawing Room\n14 : Piazza\n\nPlayers in Room:\n\n"
        + "Room Num = 17, Room Name = Garage,\n\nItems in Room:\n"
        + "8. Broom Stick, Item Damage: 2\n11. Trowel, Item Damage: 2\n\n"
        + "Neighbors of Room:\n14 : Piazza\n\nPlayers in Room:\n\n", world.lookAround(1));
  }
  
  @Test
  public void testEvidenceWhenAttackSuccessful() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 7);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n4 : Dining Hall\n"
        + "5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n",
        world.getSpecificRoomDetails(6));
    world.pickItem(1, 14);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n",
        world.getSpecificRoomDetails(6));
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
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 1\nItems Picked:\n"
        + "14. Duck Decoy, Item Damage: 3\n", world.getSpecificPlayerDetails(1));
    assertEquals("Attack successful!!", world.killAttempt(1, 14));
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 1\nItems Picked:\n", 
        world.getSpecificPlayerDetails(1));
  }
  
  @Test
  public void testEvidenceWhenAttackUnsuccessful() {
    world.createPlayer(1, "rakshika", 1, 6);
    world.createPlayer(2, "raju", 2, 5);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "14. Duck Decoy, Item Damage: 3\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n4 : Dining Hall\n"
        + "5 : Guest Room\n7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n",
        world.getSpecificRoomDetails(6));
    world.pickItem(1, 14);
    assertEquals("Room Num = 6, Room Name = Bathroom,\n\nItems in Room:\n"
        + "21. Hair Comb, Item Damage: 2\n\nNeighbors of Room:\n1 : Trophy Room\n"
        + "4 : Dining Hall\n7 : Drawing Room\n\nPlayers in Room:\n1 : rakshika\n",
        world.getSpecificRoomDetails(6));
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
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 1\nItems Picked:\n"
        + "14. Duck Decoy, Item Damage: 3\n", world.getSpecificPlayerDetails(1));
    assertEquals("You were seen during the attack! Attack unsuccessful!", world.killAttempt(1, 14));
    assertEquals("\nPlayer Num: 1\nPlayer Name: rakshika\n"
        + "Player in Room: Num: 6 Name: Bathroom\n"
        + "Max Number of Items allowed to pick: 1\nItems Picked:\n", 
        world.getSpecificPlayerDetails(1));
  }
}
