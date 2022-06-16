/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.util.List;

/**
 * This class is used to create the graph data structure to represent the spaces and their 
  neighbors as a graph.
 */
public interface GraphInterface {

  /**
   * Add the edges of the graph.
   * @param source source node of the graph.
   * @param dest destination node of the graph.
   */
  public void addEdge(int source, int dest) throws IllegalArgumentException;
  
  /**
   * This method is used to get the order of the DFS traversal.
   * @return a copy of the traversal order list.
   */
  public List<Integer> getTraversal();
  
  /**
   * This method finds the DFS traversal of the graph.
   * @param v root vertex of the graph to start the traversal with.
   */
  public void dfs(int v) throws IllegalArgumentException;
}
