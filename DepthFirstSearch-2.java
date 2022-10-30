/****************************
*
*  The class DepththFirstSearch contains methods to perform depth-first search
*   on a graph from a given source vertex, and to produce the shortest path 
*   (ignoring weights) from the source to any vertex.
*  It contains an inner class VertexData that maintains its distance from
*   the source vertex and its parent in the DFS tree
*
*
****************************/
package erii_algo;
import java.util.*;

public class DepthFirstSearch {

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
    }
  
	private Graph theGraph;
	private int sourceIndex; // Index of source vertex
	private VertexData[] vertexData;
	private Date[] startTime; //holds the startTimes for each vertex. matches by index to vertexData
	private Date[] endTime; //holds the endTimes for each vertex. matches by index to vertexData
	

	public Date[] getStartTime()
	{
		return startTime;
	}

	public Date[] getEndTime()
	{
		return endTime;
	}
	
	public void setStartTime(Date[] startTime)
	{
		this.startTime = startTime;
	}
	
	public void setEndTime(Date[] endTime)
	{
		this.endTime = endTime;
	}
	
	// Constructor sets graph and index of source vertex
	public DepthFirstSearch(Graph theGraph, int sourceIndex) {
		this.theGraph = theGraph;
		this.sourceIndex = sourceIndex;
		vertexData = new VertexData[theGraph.getNumberOfVertices()];
		this.startTime = new Date[theGraph.getNumberOfVertices()];
		this.endTime = new Date[theGraph.getNumberOfVertices()];
		
		for (int i = 0; i < theGraph.getNumberOfVertices(); i++) {
			vertexData[i] = new VertexData(i);
		}
	}

	// Constructor sets graph and default source index of 0
	public DepthFirstSearch(Graph theGraph) {
		this(theGraph, 0);
	}

	//iterates to next tree edge
	public Iterator<Edge> getTreeEdgeIterator() {
		Iterator<Edge> edgeIterator = theGraph.getVertexEdgeIterator(sourceIndex);
		return edgeIterator;
	}
	
	public void doDFS() {
		StackImplementation<Integer> stack = new StackImplementation<Integer>(theGraph.getNumberOfVertices()); //instantiate inner class
		//Queue<Integer> queue = new ArrayQueue<Integer>(theGraph.getNumberOfVertices());
		 
		int currentVertexIndex, // current vertex being processed;
				adjacentVertexIndex; // a vertex adjacent to current vertex
		Edge currentEdge = null;

		// Initialize stack with source vertex
		vertexData[sourceIndex].setColor(Color.GRAY);
		vertexData[sourceIndex].setDistance(0);
		startTime[sourceIndex] = new Date(System.currentTimeMillis());
		stack.push(sourceIndex);
		//queue.enqueue(sourceIndex);
		
		// Process vertices until all vertices (reachable from source) have been
		// processed
		while (!stack.isEmpty()) {
		 //while(!queue.isEmpty()) {
			//currentVertexIndex = stack.peek();
			currentVertexIndex = stack.pop();
			//currentVertexIndex = queue.dequeue();
			endTime[currentVertexIndex] = new Date(System.currentTimeMillis()); //set endTime when you pop the vertex
			
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
					startTime[adjacentVertexIndex] = new Date(System.currentTimeMillis());
					stack.push(adjacentVertexIndex);
					//queue.enqueue(adjacentVertexIndex);

				}
				vertexData[currentVertexIndex].setColor(Color.BLACK);
			}
		}
	/*	while(!queue.isEmpty())
		{
		System.out.print(queue.dequeue());
		} */
	}

	// Returns the path from the source to a given vertex along DFS tree edges
	public String getPathFromSource(int vertexIndex) {
		if (vertexData[vertexIndex].getDistance() == Double.POSITIVE_INFINITY)
			return ("No path from source vertex to vertex " + vertexIndex + ".");
		else
			return ("(" + getPathFromSourceHelper(vertexIndex) + ")");
	}

	// Returns the sequence of vertices along the path
	public String getPathFromSourceHelper(int vertexIndex) {
		if (vertexData[vertexIndex].getParentIndex() == -1)
			// at source vertex
			return Integer.toString(vertexIndex);
		else
			return getPathFromSourceHelper(vertexData[vertexIndex].getParentIndex()) + " "
					+ Integer.toString(vertexIndex);
	}

	/*
	 * Creates a string of the breadth-first search tree in parenthisized format
	 * This format is (sourceVertex (child[1] subtree) (child[2]
	 * subtree)...(child[n] subtree))
	 */
	 public String dfsTreeToString() {
		return dfsTreeToStringHelper(sourceIndex);
	}

   // Builds the string for the subtree rooted at the given vertex 
	public String dfsTreeToStringHelper(int vertexIndex) {
		StringBuilder treeString = new StringBuilder();
		treeString.append("( " + vertexIndex + " ");

		Iterator<Edge> adjacentEdgeIterator = theGraph.getVertexEdgeIterator(vertexIndex);

		while (adjacentEdgeIterator.hasNext()) {
			Edge adjacentEdge = adjacentEdgeIterator.next();
			int adjacentVertexIndex = adjacentEdge.getEndVertex();
			if (vertexData[adjacentVertexIndex].getParentIndex() == vertexIndex)
				treeString.append(dfsTreeToStringHelper(adjacentVertexIndex));
		}
		      
		treeString.append(") ");
		return treeString.toString();
	}
}
