/***********************************************************************************************
*
*  class BFSTester contains a main method that reads in a graph, performs breadth-first search
*  from a given source vertex, and prints out the shortest path to every vertex reachable
*  from the source.
*
*  @author James W Benham
*
************************************************************************************************/
import java.io.*; // for FileNotFoundException

class BFSTester {
	
 public static void main (String[] args) {
 
    // Read in a file and build graph
	
    // String inputFileName = args[0];
	 
	String inputFileName = "unweightedGraphData-1.txt";
	 
	Graph myGraph = null;
    
    try {
      myGraph = GraphMaker.makeGraph(inputFileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File " + inputFileName + " not found.");
      System.exit(1);
    }


    // Perform breadth-first search
    BreadthFirstSearch bfs;
    int sourceVertex = 0;
    if (args.length > 1)
      sourceVertex = Integer.parseInt(args[1]);
      
    bfs = new BreadthFirstSearch(myGraph,sourceVertex);
    
    bfs.doBFS();
    
    // Print out BFS tree
    System.out.println("The breadth-first search tree:\n");
    System.out.println(bfs.bfsTreeToString());
    
    // Print out shortest paths
    System.out.println("Shortest paths to the vertices from vertex " + sourceVertex + ": \n");
    for(int vertex = 0; vertex < myGraph.getNumberOfVertices(); vertex++) {
      System.out.print("Vertex " + vertex + ": ");
      System.out.println(bfs.getPathFromSource(vertex));
    }
  }
}    
