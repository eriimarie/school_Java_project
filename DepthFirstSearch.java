public class DepthFirstSearch 
{
	private int sourceVertex;
	private Graph g;

	DepthFirstSearch(Graph g, int sourceVertex)
	{
		this.g= g;
		this.sourceVertex = sourceVertex;
	}
	
	DepthFirstSearch(Graph g)
	{
		this.g=g;
		this.sourceVertex= 0;
	}
	
}
