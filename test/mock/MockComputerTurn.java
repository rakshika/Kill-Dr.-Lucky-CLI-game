package mock;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the mock model which extends the abstract mockWorld to be able to 
 * display correct space details for unit testing.
 * @author Rakshika Raju
 */
public class MockComputerTurn extends MockWorld {
  
  private int turn;
  
  /**
   * Constructor to set the output log and unique code.
   * @param log output log used to append the output.
   * @param uniqueCode code to identify the model.
   */
  public MockComputerTurn(StringBuilder log, int uniqueCode) {
    super(log, uniqueCode);
    this.turn = 0;
  }

  @Override
  public List<String> getTurn() {
    super.log.append("Returning the turn\n");
    List<String> returnStringList = new ArrayList<String>(); 
    returnStringList.add("0");
    returnStringList.add("computer");
    //returnStringList.add(String.format("%s", this.uniqueCode));
    return returnStringList;
  }
  
  @Override
  public boolean gameOver() {
    if (turn == 0) {
      turn++;
      return false;
    }
    return true;
  }
  
  @Override
  public List<String> getWinner() {
    List<String> list = new ArrayList<String>();
    list.add("draw");
    return list;
  }
  
}