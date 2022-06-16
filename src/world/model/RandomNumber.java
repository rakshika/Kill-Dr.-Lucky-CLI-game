/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.util.List;
import java.util.Random;

/**
 * Overridden implementation of Random Number generation for the computer
  controller player so make the tests predictable.
 * If the parameter rand is empty, truly random numbers are generated. Otherwise random number
  are returned as per the list: rand.
 */
public class RandomNumber extends Random {

  private boolean state; // false - default implementation of random
  private List<Integer> rand;
  private int randIndex;
  
  /**
   * Constructor which determines based on the input if numbers need to be truly random or
    predictable for the tests.
   * @param rand List of integers that will used as input for the random number generation.
   */
  public RandomNumber(List<Integer> rand) {
    if (rand == null) {
      throw new IllegalArgumentException("List of Random Number cannot be Null.");
    }
    if (rand.size() == 0) {
      this.state  = false;
    } else {
      this.state = true;
    }
    this.rand = rand;
    this.randIndex = 0;
  }
  
  /**
   * If state is false, default implementation of the random class is performed. If state is true,
    elements from rand list are returned in order. In the end of rand list is reached, state is set
    to false and default implementation of the random class is performed.
   */
  @Override
  public int nextInt(int num) {
    if (!this.state) {
      return super.nextInt(num);
    } else {
      if (this.randIndex == rand.size()) {
        this.state = false;
        return super.nextInt(num);
      }
      return this.rand.get(this.randIndex++);
    }
  }
 
}
