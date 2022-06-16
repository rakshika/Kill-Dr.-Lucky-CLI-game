/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import world.control.playercommand.CreatePlayer;
import world.control.playercommand.CreateWorldGraph;
import world.control.playercommand.DisplayPlayerInfo;
import world.control.playercommand.DisplaySpaceInfo;
import world.control.playercommand.KillAttempt;
import world.control.playercommand.LookAround;
import world.control.playercommand.MovePet;
import world.control.playercommand.MovePlayer;
import world.control.playercommand.PickItem;
import world.model.World;


/**
 * This is the controller for the Kill Dr. Lucky game. 
 * It implements the GameControllerInterface interface.
 * It uses the command design pattern to execute the user specified commands.
 * The controller takes input from the user and decides which methods need to be executed.
 */
public class GameController implements GameControllerInterface {
    
  private final Appendable out;
  private final Scanner scan;
  private List<Integer> rand;

  /**
   * Constructor for the controller, which takes Readable input and Appendable output.
   * @param in  the source to read from
   * @param out the target to print to
   * @param rand an array containing the values to substitute random number generator.
   * @throws IllegalArgumentException thrown if input or output stream is not valid.
   */
  public GameController(Readable in, Appendable out, List<Integer> rand) {
    if (in == null || out == null || rand == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    this.out = out;
    this.scan = new Scanner(in);
    this.rand = rand;
  } 
  
  @Override
  public void playGame(World worldSetup) throws IllegalArgumentException {
    if (worldSetup == null) {
      throw new IllegalArgumentException("Not a valid Model.");
    }
    
    CommandController cmd = null;
    try {
      //Create Computer Controlled player.
      int computerItemCount = 1;
      boolean flag = false;
      do {
        try {
          out.append("\nWelcome to the Game!!\nThis Game needs one Computer Controlled Player\n"
              + "Adding Computer Controlled Player...\n"
              + "Enter the Maximum number of items the computer player is allowed to pick\n");
          computerItemCount = Integer.parseInt(scan.next());
          if (computerItemCount < 1 || computerItemCount > 5) {
            throw new NumberFormatException("Enter a number between 1 and 5.\n");
          }
          flag = false;
        } catch (NumberFormatException nfe) {
          out.append("Enter a number between 1 and 5.\n");
          flag = true;
        }
      } while (flag);  

      int compRoom = 1;
      flag = false;
      do {
        try {
          out.append("\nFrom which Room do you want the computer player to Start? "
              + "Choose room Number from below options:\n");
          out.append(worldSetup.getAllRoomNames());
          compRoom = Integer.parseInt(scan.next());
          cmd = new CreatePlayer(0, "Computer", computerItemCount, compRoom);
          if ("Player Created".equals(cmd.go(worldSetup))) {
            out.append("Computer Controlled Player Created\n");
          }
          flag = false;
        } catch (NumberFormatException nfe) {
          out.append("Enter correct room Number to start.\n");
          flag = true;
        } catch (IllegalArgumentException iae) {
          out.append("Enter correct room Number to start.\n");
          flag = true;
        }
      } while (flag);
       
      String[] element = worldSetup.toString().split("\\s+");
      String[] remove2 = element[15].split(",");
      int roomCount = Integer.parseInt(remove2[0]);
      String[] remove = element[18].split(",");
      int itemCount = Integer.parseInt(remove[0]); 
      
      //user input create players
      int playerCount = 0;
      flag = false;
      do {
        try {
          out.append("\nEnter Total Number of Human Players\n");
          playerCount = Integer.parseInt(scan.next());
          if (playerCount < 1) {
            out.append("Player count should be atleast 1.\n");
            throw new NumberFormatException("Player count should be atleast 1.\n");
          }
          flag = false;
        } catch (NumberFormatException nfe) {
          out.append("Enter Integer for Number of Players.\n");
          flag = true;
        }
      } while (flag);
      
      //create the human players. 
      cmd = null;
      int i = 1;
      while (i <= playerCount) {
        out.append("\nEnter Player " + i + " name\n");
        String playerNameInput = scan.next();
        
        int maxItem = 0;
        flag = false;
        do {
          try {
            out.append("\nEnter Player " + i + "'s count for Max Items allowed"
                + "(between 1 to 5)\n");
            maxItem = Integer.parseInt(scan.next());
            if (maxItem < 1 || maxItem > 5) {
              throw new NumberFormatException("Enter a number between 1 and 5.\n");
            }
            flag = false;
          } catch (NumberFormatException nfe) {
            out.append("Enter a number between 1 and 5.\n");
            flag = true;
          }
        } while (flag);
        
        int firstRoom = 1;
        flag = false;
        do {
          try {
            out.append("\n" + playerNameInput + ", which Room do you want to enter first? "
                + "Choose room Number from below options:\n");
            out.append(worldSetup.getAllRoomNames());
            firstRoom = Integer.parseInt(scan.next());
            cmd = new CreatePlayer(i, playerNameInput, maxItem, firstRoom);
            if ("Player Created".equals(cmd.go(worldSetup))) {
              i++;
            }
            flag = false;
          } catch (NumberFormatException nfe) {
            out.append("Enter correct room Number to start.\n");
            flag = true;
          } catch (IllegalArgumentException iae) {
            out.append("Enter correct room Number to start.\n");
            flag = true;
          }
        } while (flag);
        
        cmd = null;
      }
      
      int turn = 0;
      int roomNum = 1;
      int itemNum = 1;
      int petNextRoom = 1;
      int attackItem = 0;
      
      //Start the game
      out.append("\nLET's PLAY!!");
      
      
      while (!worldSetup.gameOver()) {
        List<String> turnList = worldSetup.getTurn();
        turn = Integer.parseInt(turnList.get(0));
        String turnName = turnList.get(1);
        flag = false;
        String in = null;
        do {
          out.append("\n\nPlayer " + turn + ". " + turnName + "'s Turn:\n");
          
          out.append("\nYour details:");
          cmd = new DisplayPlayerInfo(turn);
          out.append(cmd.go(worldSetup));
          out.append("\nYour space details:");
          cmd = new DisplaySpaceInfo(turn);
          out.append(cmd.go(worldSetup));
          cmd = null;
          
          out.append("\nChoose one of the following options: \n");
          out.append("1. Draw World\n2. Move Pet\n"
              + "3. Attempt Kill\n4. Move\n5. Pick Item\n6. Look Around\n7. quit\n");
          
          if (turn == 0) {
            out.append(worldSetup.computerAction(rand));
          } else {
            try {
              in = scan.next();
              if ("q".equalsIgnoreCase(in) || "quit".equalsIgnoreCase(in) || "7".equals(in)) {
                out.append("Game Quit");
                return;
              }
              flag = false;
              if (!("1".equals(in) || "2".equals(in) || "3".equals(in) || "4".equals(in) 
                  || "5".equals(in) || "6".equals(in))) {
                flag = true; 
                throw new NumberFormatException("Invalid option. Try again.");
              }
            } catch (NumberFormatException nfe) {
              out.append("Invalid option. Try again.\n");
              flag = true;
            }
          }
        } while (flag);
        
        // Take valid input for space details when player wants to move pet.
        if (turn != 0 && Integer.parseInt(in) == 2) {
          flag = false;
          do {
            try {
              out.append("\nEnter room number to which you want to move the pet:\n");
              petNextRoom = Integer.parseInt(scan.next());
              flag = false;
            } catch (NumberFormatException nfe) {
              out.append("Enter a room number between 1 and " + roomCount + "\n");
              flag = true;
            } 
          } while (flag);
        }
        
        // Take valid input for item details used to attack Target.
        if (turn != 0 && Integer.parseInt(in) == 3) {
          flag = false;
          do {
            try {
              out.append("\nEnter Item number that you want to use to attack the Target.\nYou can "
                  + "choose to poke in the eye for damage of 1 by selecting item number as 0.\n");
              attackItem = Integer.parseInt(scan.next());
              flag = false;
            } catch (NumberFormatException nfe) {
              out.append("Enter a item number between 0 and " + itemCount + "\n");
              flag = true;
            } 
          } while (flag);
        }
        
        // Take valid input for space details when player wants to move.
        if (turn != 0 && Integer.parseInt(in) == 4) {
          flag = false;
          do {
            try {
              out.append("\nEnter room number to which you want to move:\n");
              roomNum = Integer.parseInt(scan.next());
              flag = false;
            } catch (NumberFormatException nfe) {
              out.append("Enter a room number between 1 and " + roomCount + "\n");
              flag = true;
            } 
          } while (flag);
        }
          
        //Take valid input for item when player wants to pick item.
        if (turn != 0 && Integer.parseInt(in) == 5) {
          flag = false;
          do {
            try {
              out.append("\nEnter Item number that you want to pick:\n");
              itemNum = Integer.parseInt(scan.next());
              flag = false;
            } catch (NumberFormatException nfe) {
              out.append("Enter a item number between 1 and " + itemCount + "\n");
              flag = true;
            }
          } while (flag);
        }
         
        if (turn != 0) {
          Map<String, CommandController> gameCommands = new HashMap<>();
          gameCommands.put("1", new CreateWorldGraph());
          gameCommands.put("2", new MovePet(petNextRoom));
          gameCommands.put("3", new KillAttempt(turn, attackItem));
          gameCommands.put("4", new MovePlayer(turn, roomNum));
          gameCommands.put("5", new PickItem(turn, itemNum));
          gameCommands.put("6", new LookAround(turn)); 
            
          cmd = gameCommands.getOrDefault(in, null);
          if (cmd == null) {
            out.append("Numeric Expected\n");
          } else {
            try {
              out.append(cmd.go(worldSetup));
              cmd = null;
            } catch (IllegalArgumentException iae) {
              out.append("Unable to complete operation.");
            }
          }
        }
      }
      scan.close();
      
      if (worldSetup.getWinner().get(0).equals("draw")) {
        out.append("\nTarget has Escaped!! No winner!");
      } else {
        out.append("\nPlayer " + worldSetup.getWinner().get(0) + ". " 
            + worldSetup.getWinner().get(1) + " is the winner!!!\n");
      }
      
    } catch (IOException ioe) {
      throw new IllegalArgumentException("Append failed", ioe);
    }
  }
}
