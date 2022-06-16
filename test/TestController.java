import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import mock.MockComputerTurn;
import mock.MockModel;
import org.junit.Before;
import org.junit.Test;
import world.control.GameController;
import world.control.GameControllerInterface;
import world.model.World;

/**
 * This Test class has unit tests to test the functionality of the controller.
 * These tests use a mock model so that the tests are independent of the model.
 * @author Rakshika Raju
 */
public class TestController {
    
  private List<Integer> rand;
  
  /**
   * Sets the initial requirements before running the tests.
   */
  @Before
  public void setUp() {
    rand = new ArrayList<Integer>();
  }
  
  @Test
  public void testComputerPlayerCreated() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. "
        + "Computer, Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\n"
        + "Your details:\nAt the start of turn, displaying Player details for player number 1 "
        + "and unique code 123321\n123321\n\nYour space details:\n"
        + "123321\nChoose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
   
  @Test
  public void testOneHumanPlayerCreated() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\n"
        + "Creating Player 0. Computer, Max Items = 1, first room = 1\n"
        + "Room Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n\n"
        + "From which Room do you want the computer player to Start? Choose room Number "
        + "from below options:\n123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\n"
        + "Your details:\nAt the start of turn, displaying Player details for player number 1 "
        + "and unique code 123321\n123321\n\nYour space details:\n"
        + "123321\nChoose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString());  
  }
  
  @Test
  public void testTwoHumanPlayerCreated() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 2 player1 2 12 player2 1 4 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Room Number and Names\n"
        + "Creating Player 2. player2, Max Items = 1, first room = 4\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nEnter Player 2 name\n\n"
        + "Enter Player 2's count for Max Items allowed(between 1 to 5)\n\n"
        + "player2, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\n"
        + "Your details:\nAt the start of turn, displaying Player details for player number 1 "
        + "and unique code 123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }

  @Test
  public void testDrawWorldCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 1 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n"
        + "Input Received for Drawing World\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "World PNG created\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test
  public void testMovePlayerCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 4 10 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\n"
        + "Displaying Player 1 details\n"
        + "Target Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n"
        + "Moving Player 1 to room = 10\n"
        + "Returning the turn\n"
        + "Displaying Player 1 details\n"
        + "Target Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\n"
        + "Enter room number to which you want to move:\n123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test
  public void testPickItemCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 5 5 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Player 1 picking item 5\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\n"
        + "Enter Item number that you want to pick:\n123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }

  @Test
  public void testLookAroundCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 6 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\n"
        + "Displaying details of Room 1\n"
        + "Letting Player 1 Look Around\nReturning the turn\n"
        + "Displaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\n"
        + "123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test
  public void testMovePetCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 2 5 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Moving Pet to room = 5\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\n"
        + "Enter room number to which you want to move the pet:\n123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test
  public void testKillAttemptCommand() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 3 8 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Player 1 attempting to kill target with item 8\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\n"
        + "Enter Item number that you want to use to attack the Target.\n"
        + "You can choose to poke in the eye for damage of 1 by selecting item number as 0.\n"
        + "123321\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputOutputStream() {
    StringBuffer out = null;
    Readable in = new StringReader("1 1 1 player1 2 12 q");
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInputOutputStream2() {
    StringBuffer out = new StringBuffer();;
    Readable in = null;
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidModel() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 q");
    World mockWorld = null;
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
  }
  
  @Test
  public void testInvalidHumanControlInput() {
    //The program runs till correct input is given.
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 test 1 rakshika g 2 room5 5 q");
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, "
        + "Max Items = 1, first room = 1\nRoom Number and Names\nRoom Number and Names\n"
        + "Creating Player 1. rakshika, Max Items = 2, first room = 5\nReturning the turn\n"
        + "Displaying Player 1 details\nTarget Details\nDisplaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n"
        + "Enter Integer for Number of Players.\n\n"
        + "Enter Total Number of Human Players\n\n"
        + "Enter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n"
        + "Enter a number between 1 and 5.\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "rakshika, which Room do you want to enter first? "
        + "Choose room Number from below options:\n"
        + "123321Enter correct room Number to start.\n\n"
        + "rakshika, which Room do you want to enter first? "
        + "Choose room Number from below options:\n123321\n"
        + "LET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testGameEndsOnQ() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 Q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\nCreating Player 1. player1,"
        + " Max Items = 2, first room = 12\nReturning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\n"
        + "Displaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
  @Test
  public void testInvalidOptionSelection() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 move 8 1 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Displaying Player 1 details\nTarget Details\nDisplaying Player 1 details\n"
        + "Displaying details of Room 1\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\nInput Received "
        + "for Drawing World\nReturning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nInvalid option. Try again.\n\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Invalid option. Try again.\n\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nWorld PNG created\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testInvalidMoveLocation() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 4 30 4 9 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n"
        + "Moving Player 1 to room = 9\nReturning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of "
        + "Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\nEnter room number to which you want to move:\n"
        + "Not a valid room Number\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "\nEnter room number to which you want to move:\n"
        + "123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testInvalidPickItem() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 5 30 5 13 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n"
        + "Player 1 picking item 13\nReturning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of "
        + "Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\nEnter Item number that you want to pick:\n"
        + "Item number not in correct range\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "\nEnter Item number that you want to pick:\n"
        + "123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testInvalidMovePetLocation() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 2 30 4 9 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n"
        + "Moving Player 1 to room = 9\nReturning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of "
        + "Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\nEnter room number to which you want to move the pet:\n"
        + "Not a valid room Number\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "\nEnter room number to which you want to move:\n"
        + "123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testInvalidItemToAttack() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 3 30 5 13 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, "
        + "first room = 1\nRoom Number and Names\n"
        + "Creating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of Room 1\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n"
        + "Player 1 picking item 13\nReturning the turn\nDisplaying Player 1 details\n"
        + "Target Details\nDisplaying Player 1 details\nDisplaying details of "
        + "Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\nEnter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n\nEnter Item number that you want to use to attack the Target.\n"
        + "You can choose to poke in the eye for damage of 1 by selecting item number as 0.\n"
        + "Item number not in correct range\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "\nEnter Item number that you want to pick:\n"
        + "123321\n\n"
        + "Player 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "Game Quit", out.toString()); 
  }
  
  @Test
  public void testComputerTurn() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("1 1 1 player1 2 12 q");
    
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockComputerTurn(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 1, first room = 1"
        + "\nRoom Number and Names\nCreating Player 1. player1, Max Items = 2, first room = 12\n"
        + "Returning the turn\nDisplaying Player 0 details\nTarget Details\nDisplaying Player "
        + "0 details\nDisplaying details of Room 0\nComputer Controlled Player "
        + "taking action.\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\nThis Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\n"
        + "Enter Player 1 name\n\nEnter Player 1's count for Max Items allowed(between 1 to 5)\n"
        + "\nplayer1, which Room do you want to enter first? Choose room Number from below "
        + "options:\n123321\nLET's PLAY!!\n\nPlayer 0. computer's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 0 and "
        + "unique code 123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\n"
        + "123321\n"
        + "Target has Escaped!! No winner!", out.toString()); 
  }
  
  @Test
  public void testInvalidComputerControlInput() {
    StringBuffer out = new StringBuffer();
    Readable in = new StringReader("6 f 2 1 1 player1 1 12 q");
    StringBuilder log = new StringBuilder();
    World mockWorld = new MockModel(log, 123321);
    GameControllerInterface c = new GameController(in, out, rand);
    c.playGame(mockWorld);
    // Expected output is that game runs till valid input is given.
    // input reaches model correctly
    assertEquals("Room Number and Names\nCreating Player 0. Computer, Max Items = 2, first room = 1"
        + "\nRoom Number and Names\nCreating Player 1. player1, Max Items = 1, first room = 12\n"
        + "Returning the turn\nDisplaying Player 1 details\nTarget Details\n"
        + "Displaying Player 1 details\nDisplaying details of Room 1\n", log.toString()); 
    
    // output from model received correctly
    assertEquals("\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "Enter a number between 1 and 5.\n\n"
        + "Welcome to the Game!!\nThis Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "Enter a number between 1 and 5.\n\nWelcome to the Game!!\n"
        + "This Game needs one Computer Controlled Player\n"
        + "Adding Computer Controlled Player...\n"
        + "Enter the Maximum number of items the computer player is allowed to pick\n"
        + "\nFrom which Room do you want the computer player to Start? "
        + "Choose room Number from below options:\n"
        + "123321Computer Controlled Player Created\n\n"
        + "Enter Total Number of Human Players\n\n"
        + "Enter Player 1 name\n\n"
        + "Enter Player 1's count for Max Items allowed(between 1 to 5)\n\n"
        + "player1, which Room do you want to enter first? Choose room Number from below options:\n"
        + "123321\nLET's PLAY!!\n\nPlayer 1. player1's Turn:\n\nYour details:\n"
        + "At the start of turn, displaying Player details for player number 1 and unique code "
        + "123321\n123321\n\nYour space details:\n123321\n"
        + "Choose one of the following options: \n"
        + "1. Draw World\n"
        + "2. Move Pet\n"
        + "3. Attempt Kill\n"
        + "4. Move\n"
        + "5. Pick Item\n"
        + "6. Look Around\n"
        + "7. quit\nGame Quit", out.toString()); 
  }
  
}
