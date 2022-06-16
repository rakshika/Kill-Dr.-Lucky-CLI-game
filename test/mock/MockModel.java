package mock;

/**
 * This is the mock model which implements the Abstract MockModel for 
  testing the controller in isolation.
 * @author Rakshika Raju
 */
public class MockModel extends MockWorld {

  /**
   * Constructor to set the output log and unique code.
   * @param log output log used to append the output.
   * @param uniqueCode code to identify the model.
   */
  public MockModel(StringBuilder log, int uniqueCode) {
    super(log, uniqueCode);
  }
}
