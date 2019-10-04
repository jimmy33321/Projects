//a simple implementation of Graph using adjacency list
//C343 2015, with Prim's algorithm (mstPrim) to be implemented by students

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Arrays; //used for testing
public class AdjGraph implements Graph {
	private boolean digraph;
	private int totalNode;
	private Vector<String> nodeList;
	private int totalEdge;
	private Vector<LinkedList<Integer>> adjList; //Adjacency list
	private Vector<LinkedList<Integer>> adjWeight; //Weight of the edges
	private Vector<Boolean> visited;
	private Vector<Integer> nodeEnum; //list of nodes pre visit
	public AdjGraph() {
		init();
	}
	public AdjGraph(boolean ifdigraph) {
		init();
		digraph =ifdigraph;
	}
	public void init() {
		nodeList = new Vector<String>(); 
		adjList = new Vector<LinkedList<Integer>>(); 
		adjWeight = new Vector<LinkedList<Integer>>();
		visited = new Vector<Boolean>();
		nodeEnum = new Vector<Integer>();
		totalNode = totalEdge = 0;
		digraph = false;
	}
	//set vertices
	public void setVertex(String[] nodes) {
		for(int i = 0; i < nodes.length; i ++) {
			nodeList.add(nodes[i]);
			adjList.add(new LinkedList<Integer>());
			adjWeight.add(new LinkedList<Integer>());
			visited.add(false);
			totalNode ++;
		}
	}
	//add a vertex
	public void addVertex(String label) {
		nodeList.add(label);
		visited.add(false);
		adjList.add(new LinkedList<Integer>());
		adjWeight.add(new LinkedList<Integer>());
		totalNode ++;
	}
	public int getNode(String node) {
		for(int i = 0; i < nodeList.size(); i ++) {
			if(nodeList.elementAt(i).equals(node)) return i;
		}
		return -1;
	}
	//return the number of vertices
	public int length() {
		return nodeList.size();
	}
	//add edge from v1 to v2
	public void setEdge(int v1, int v2, int weight) {
		LinkedList<Integer> tmp = adjList.elementAt(v1);
		if(adjList.elementAt(v1).contains(v2) == false) {
			tmp.add(v2);
			adjList.set(v1,  tmp);
			totalEdge ++;
			LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
			tmp2.add(weight);
			adjWeight.set(v1,  tmp2);
		}
	}
	public void setEdge(String v1, String v2, int weight) {
		if((getNode(v1) != -1) && (getNode(v2) != -1)) {
			//add edge from v1 to v2
			setEdge(getNode(v1), getNode(v2), weight);
			//for digraph, add edge from v2 to v1 as well
			if(digraph == false) setEdge(getNode(v2), getNode(v1), weight);
		}
	}

	//it is important to keep track if a vertex is visited or not (for traversal)
	public void setVisited(int v) {
		visited.set(v, true);
		nodeEnum.add(v);
	}
	public boolean ifVisited(int v) {
		return visited.get(v);
	}
	public LinkedList<Integer> getNeighbors(int v) {
		return adjList.get(v);
	}
	public int getWeight(int v, int u) {
    		LinkedList<Integer> tmp = getNeighbors(v);
    		LinkedList<Integer> weight = adjWeight.get(v);
    		if(tmp.contains(u)) return weight.get(tmp.indexOf(u));
    		else return Integer.MAX_VALUE;
    	}
	public void clearWalk() {
		//clean up before traverse
		nodeEnum.clear();
		for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
	}
	public void walk(String method) {
		clearWalk();
		//traverse the graph 
		for(int i = 0; i < nodeList.size(); i ++) {
			if(ifVisited(i) == false) {
				if(method.equals("BFS")) BFS(i); //i as the start node
				else if(method.equals("DFS")) DFS(i); //i as the start node
				else {
					System.out.println("unrecognized traversal order: " + method);
					System.exit(0);
				}
			}
		}
		System.out.println(method + ":");
		displayEnum();
	}
	public void DFS(int v) {
		setVisited(v);
		LinkedList<Integer> neighbors = adjList.elementAt(v);
		for(int i = 0; i < neighbors.size(); i ++) {
			int v1 = neighbors.get(i);
			if(ifVisited(v1) == false) DFS(v1); 
		}
	}
	public void BFS(int s) {
		ArrayList<Integer> toVisit = new ArrayList<Integer>();
		toVisit.add(s);
		while(toVisit.size() > 0) {
			int v = toVisit.remove(0); //first-in, first-visit
			setVisited(v);
			LinkedList<Integer> neighbors = adjList.elementAt(v);
			for(int i = 0; i < neighbors.size(); i ++) {
				int v1 = neighbors.get(i);
				if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
					toVisit.add(v1);
				}
			}
		}
	}
	public void display() {
		System.out.println("total nodes: " + totalNode);
		System.out.println("total edges: " + totalEdge);
	}
	public void displayEnum() {
		for(int i = 0; i < nodeEnum.size(); i ++) {
			System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
		}
		System.out.println();
	}
	public int mstPrim() {
		//Vector<String> s = nodeList; //s is var from pseudo code
		Vector<String> s = new Vector<>();
		for(final String str:nodeList) {
		s.add(str);
		}
//Initialize
		int[] dist = new int[s.size()];
		dist[0] = 0;
		for(int v = 1; v<s.size(); v++){ //loop starts at v=1 since v=0 has already been set to 0
		  dist[v] = Integer.MAX_VALUE;
		}
		int cost = 0;
//Repeat
		while(s.size() > 0){
		//Used in for loop
			int minSoFar = 1000;
			int index = -1; //index will be our u outside of for loop, as u is scoped too narrowly
		//Find vertex with smallest dist[u]
			for(String node : s){
			  int u = nodeList.indexOf(node); //u is the index of the string(node) being processed
//			  System.out.println("node is " + node);
//			    System.out.println("dist is " + dist[u]);
			  if(dist[u] < minSoFar){
//			    System.out.println("smaller");
			    minSoFar = dist[u];
			    index = u;}
			}
			s.remove(nodeList.get(index));
			cost += dist[index];
			LinkedList neighbors = getNeighbors(index);
			for(int c = 0; c<neighbors.size(); c++){ //c is loop counter
			  int v = (Integer) neighbors.get(c);//v is v from pseudo code
			  if(getWeight(index,v) < dist[v]) {
			    dist[v] = getWeight(index, v);} //index is u from pseudo code
			}
	//		for(int i:dist) {
//			System.out.print(i);
//			System.out.print(" ");}
//			System.out.println();
		}
//Return
		return cost;	
	}

	public static void main(String argv[]) {
		AdjGraph a = new AdjGraph();
		String[] v = {"A", "B", "C", "D", "E"};
		a.setVertex(v);
		a.setEdge("A", "B", 3);
		a.setEdge("B", "C", 2);
		a.setEdge("C", "D", 1);
		a.setEdge("D", "E", 10);
		a.walk("BFS");
		System.out.println("The cost from Prim's Algorithm is: " + a.mstPrim());
	}
}
