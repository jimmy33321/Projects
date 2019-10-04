//a simple interface for graph
//C343 2015
public interface Graph {
	public void init();
	public int length();
	public void setVertex(String[] nodes);
	public void addVertex(String node);
	public void setEdge(int v1, int v2, int weight);
	public void setEdge(String v1, String v2, int weight);
	public void setVisited(int v);
	public boolean ifVisited(int v);
	public void clearWalk();
	public void walk(String order);
	public void DFS(int s); //depth first
	public void BFS(int s); //breath first
	public void display(); //display basic info
	public void displayEnum(); //display enumeration of the vertices
	public void topSort(); //topological sorting: to-be-implemented
}
