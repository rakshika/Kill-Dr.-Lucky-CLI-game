/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import world.model.World;
import world.model.WorldSetup;

/**
 * This is the Driver class used to run and test the model. 
 * It creates the model and the controller and gives the control to the controller.
 * It has the code for example runs to check the behavior of the model. 
 */
public class Main {
  
  /** 
   * This is the main method from where the program starts execution.
   * @param args accepts 1 command line argument specifying the path to the file
    containing the world specifications.
   * @throws FileNotFoundException If the specified file is not found, this exception is thrown.
   */
  public static void main(String[] args) {   
    Path  filePath = Paths.get(args[0]);
    int maxTurn = Integer.parseInt(args[1]);
    
    String fileContent = "";
    try {
      fileContent = Files.readString(filePath, StandardCharsets.US_ASCII);
    } catch (IOException e) {
      System.out.println("File Path is not correct. "
          + "Please enter correct path and try again.");
      return;
    }
    
    World worldSetup = null;
    Readable fileInput = new StringReader(fileContent);
    try {
      worldSetup = new WorldSetup(fileInput, maxTurn);
    } catch (IllegalArgumentException iae) {
      System.out.println("Illegal Argument/File Passed.");
      return;
    }
    
    Readable input = new InputStreamReader(System.in);
    Appendable output = System.out;
    List<Integer> rand = new ArrayList<Integer>();

    try {
      new GameController(input, output, rand).playGame(worldSetup);
    } catch (IllegalArgumentException iae) {
      System.out.println("Incorrect Inputs given to play game.");
      return;
    }
        
  }   
}