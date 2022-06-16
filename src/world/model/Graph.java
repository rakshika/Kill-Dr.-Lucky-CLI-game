/**
 * PDP project.
 * @author Rakshika Raju
 */

package world.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to create the graph data structure to represent the spaces and their 
  neighbors as a graph.
 */
public final class Graph implements GraphInterface { 
  private int vertexCount; 
  private List<List<Integer>> adjList;
  private List<Integer> traversalOrder;
  
  /**
   * This is the constructor for the Graph class. 
   * It sets the total number of vertices in the graph.
   * It also initializes the Adjacency list used to represent the graph.
   * @param totalVertices total number of vertices in the graph.
   */
  public Graph(int totalVertices) throws IllegalArgumentException { 
    if (totalVertices < 1) {
      throw new IllegalArgumentException("There needs to be atleast one vertex to create a graph.");
    }
    this.vertexCount = totalVertices;
    adjList = new ArrayList<>();
    traversalOrder = new ArrayList<Integer>();
    for (int i = 0; i < totalVertices; i++) {
      adjList.add(new ArrayList<Integer>());
    } 
  } 
 
  private void dfsHelper(int v, boolean[] visited) { 
    //current node is set to visited. 
    visited[v] = true;
    traversalOrder.add(v + 1); 
 
    //process all adjacent vertices 
    Iterator<Integer> node = adjList.get(v).listIterator(); 
    while (node.hasNext()) { 
      int next = node.next(); 
      if (!visited[next]) {
        dfsHelper(next, visited); 
        traversalOrder.add(v + 1);
      }
    }
  }
  
  @Override
  public void addEdge(int source, int dest) throws IllegalArgumentException {
    if (source < 0 || dest < 0) {
      throw new IllegalArgumentException("vertex cannot be negative.");
    }
    adjList.get(source).add(dest);
  } 

  @Override
  public List<Integer> getTraversal() {
    List<Integer> copy = new ArrayList<Integer>();
    for (int i = 0; i < traversalOrder.size(); i++) {
      copy.add(traversalOrder.get(i));
    }
    return copy;
  }
  
  @Override
  public void dfs(int v) throws IllegalArgumentException { 
    if (v < 0) {
      throw new IllegalArgumentException("vertex cannot be negative.");
    }
    this.traversalOrder = new ArrayList<Integer>();
    //initially none of the vertices are visited 
    boolean[] visited = new boolean [vertexCount]; 
 
    //call recursive dfsHelper function for DFS traversal. 
    dfsHelper(v, visited); 
  } 
}
