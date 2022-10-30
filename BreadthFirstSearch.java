/************************************************************************************
*
*  The class BreadthFirstSearch contains methods to perform breadth-first search
*   on a graph from a given source vertex, and to produce the shortest path 
*   (ignoring weights) from the source to any vertex.
*  It contains an inner class VertexData that maintains its distance from
*   the source vertex and its parent in the BFS tree
*
* @author James Benham
*
************************************************************************************/
import java.util.*;

class BreadthFirstSearch {

  // An enumeration class for colors
  enum Color  {
    WHITE,     // color for vertices that has not yet been discovered by BBS 
    GRAY,      // color for verrtices that has been discovered but not fully processed
    BLACK;     // color for vertices that have been fully processed
  }  

  // An inner class for vertex data
  class VertexData {
    
    private Color color;
    private int vertexIndex, 
                parentIndex;    // Index of parent vertex in BFS tree
    private double distance;    // Distance to source vertex in BFS tree
    
    // Constructor creates a vertex with parent initialized to -1 (no parent),
    //  distance initialized to infinity, and color initialized to white
    public VertexData(int vertexIndex) {
      this.vertexIndex = vertexIndex;
      parentIndex = -1;    // no parent
      distance = Double.POSITIVE_INFINITY;
      color = Color.WHITE;
    }
    
    // get and set methods
    public int getVertexIndex()  { return vertexIndex; }
    public int  getParentIndex() { return parentIndex; }
    public double getDistance()  { return distance; }
    public Color getColor()      { return color; }
    
    public void setParent(int parentIndex)   { this.parentIndex = parentIndex; }
    public void setDistance(double distance) { this.distance = distance; }
    public void setColor(Color color)        { this.color = color; }
  } // inner class Vertex data
  
  private Graph theGraph;
  private int sourceIndex;  // Index of source vertex
  private VertexData[] vertexData;    
  
  // Constructor sets graph and index of source vertex
  public BreadthFirstSearch(Graph theGraph, int sourceIndex) {
    this.theGraph = theGraph;
    this.sourceIndex = sourceIndex;
    vertexData = new VertexData[theGraph.getNumberOfVertices()];
    
    for (int i = 0; i < theGraph.getNumberOfVertices(); i++)
      vertexData[i] = new VertexData(i);
  }
  
  // Constructor sets graph and default source index of 0
  public BreadthFirstSearch (Graph theGraph) {
    this(theGraph, 0);
  }
  
  public void doBFS() {
    Queue<Integer> queue = new ArrayQueue<Integer>(theGraph.getNumberOfVertices());
    
    int currentVertexIndex,      // current vertex being processed;
        adjacentVertexIndex;     // a vertex adjacent to current vertex
    Edge currentEdge = null;
    
    // Initialize queue with source vertex
    vertexData[sourceIndex].setColor(Color.GRAY);
    vertexData[sourceIndex].setDistance(0);
    queue.enqueue(sourceIndex);
    
    // Process vertices until all vertices (reachable from source) have been processed
    while(!queue.isEmpty()) {
      currentVertexIndex = queue.dequeue();
      Iterator<Edge> edgeIterator = theGraph.getVertexEdgeIterator(currentVertexIndex);
      
      // Begin processing vertices that are adjacent to current vertex
      while (edgeIterator.hasNext()) {
        currentEdge = edgeIterator.next();
        adjacentVertexIndex = currentEdge.getEndVertex();
        if (vertexData[adjacentVertexIndex].getColor() == Color.WHITE) {
          // Vertex has not yet been discovered
          vertexData[adjacentVertexIndex].setParent(currentVertexIndex);
          vertexData[adjacentVertexIndex].setDistance(vertexData[currentVertexIndex].getDistance() + 1);
          vertexData[adjacentVertexIndex].setColor(Color.GRAY);
          queue.enqueue(adjacentVertexIndex);
        }
      
        vertexData[currentVertexIndex].setColor(Color.BLACK);
      }
    }
  }
  
  // Returns the path from the source to a given vertex along BFS tree edges
  public String getPathFromSource(int vertexIndex) {
    if(vertexData[vertexIndex].getDistance() == Double.POSITIVE_INFINITY)
      return ("No path from source vertex to vertex " + vertexIndex + ".");
    else
      return ("(" + getPathFromSourceHelper(vertexIndex) + ")" );
  }
  
  // Returns the sequence of vertices along the path
  public String getPathFromSourceHelper(int vertexIndex) {
    if (vertexData[vertexIndex].getParentIndex() == -1) 
      // at source vertex
      return Integer.toString(vertexIndex);
    else
      return getPathFromSourceHelper(vertexData[vertexIndex].getParentIndex()) 
                                      + " " + Integer.toString(vertexIndex);
  }
  
  /* Creates a string of the breadth-first search tree in parenthisized format
     This format is (sourceVertex (child[1] subtree) (child[2] subtree)...(child[n] subtree))
  */
  public String bfsTreeToString() {
    return bfsTreeToStringHelper(sourceIndex);
  }
  
  // Builds the string for the subtree rooted at the given vertex
  public String bfsTreeToStringHelper(int vertexIndex) {
    StringBuilder treeString = new StringBuilder();
    treeString.append("( " + vertexIndex + " "); 
    
    Iterator<Edge> adjacentEdgeIterator = theGraph.getVertexEdgeIterator(vertexIndex);
    
    while(adjacentEdgeIterator.hasNext()) {
      Edge adjacentEdge = adjacentEdgeIterator.next();
      int adjacentVertexIndex = adjacentEdge.getEndVertex();
      if (vertexData[adjacentVertexIndex].getParentIndex() == vertexIndex)
        treeString.append(bfsTreeToStringHelper(adjacentVertexIndex));
    }
    
    treeString.append(") ");
    return treeString.toString();
  }

}
    
    