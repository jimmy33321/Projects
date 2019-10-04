//a simple implementation of Graph using adjacency list
//C343 2015

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Arrays;

public class AdjGraph implements Graph {
	private boolean digraph;
	private int totalNode;
	private Vector<String> nodeList;
	private int totalEdge;
	private Vector<LinkedList<Integer>>  adjList; //Adjacency list
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
			visited.add(false);
			totalNode ++;
		}
	}
	//add a vertex
	public void addVertex(String label) {
		nodeList.add(label);
		visited.add(false);
		adjList.add(new LinkedList<Integer>());
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
	public void clearWalk() {
		//clean up before traverse
		nodeEnum.clear();
		for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
	}

	public static void main(String[] args){
		AdjGraph a = new AdjGraph();
		String[] v = {"A", "B", "C", "D", "E"}; 
		a.setVertex(v);
		a.setEdge("A", "B", 1);
		a.setEdge("B", "C", 1);
		a.setEdge("D", "E", 1); //This graph will have 2 components and 5 nodes. A-B-C | D-E
		a.myFloyd();
	}

	public void print2d(int[][] array){ //uses java.util.Arrays to print out 2d arrays easily
		for(int i = 0; i < array.length; i++)
		{
	    		for(int j = 0; j < array[i].length; j++)
        		{
        			System.out.print(array[i][j]);
        			if(j < array[i].length - 1) System.out.print(" ");
    			}
    		System.out.println();
		}    
		System.out.println();
	
	}

	public void myFloyd(){ //myFloyd Function for Lab Week 13
		int[][] table = init2dArray(); //initialize the 2d representation of the graph
		int[][] floydTable = floydAlgo(table); //the loop/important part
		printShortestPath(floydTable);
		//print2d(table);	
		//print2d(floydTable);
	}

	public void printShortestPath(int[][] table){
	  System.out.println("A path length of 1000 means there is no shortest path!");
	  for(int i=0; i<table.length; i++){
	    for(int j=0; j<table.length; j++)
	      System.out.println("The Shortest path between: " + i + " and " + j + " is " + table[i][j]);
	    System.out.println();
	}


	}
	public int[][] init2dArray(){ //AI said it was fine to hardcode table
		//A-1 B-2 C-3 D-4 E-5
	  	//Graph is: 1-2-3 | 4-5
		int m = 1000; //m will be used as infinity
	 //		     1  2  3  4  5
          int[][] array = { {0, 1, 2, m, m},  //1
		  	    {1, 0, 1, m, m},  //2
			    {2, 1, 0, m, m},  //3
			    {m, m, m, 0, 1},  //4
			    {m, m, m, 1, 0}}; //5
		return array;
	}

	public int[][] floydAlgo (int[][] matrix){
		for(int k=0; k<matrix.length; k++){
		  for(int i=0; i<matrix.length; i++){
		    for(int j=0; j<matrix.length; j++){
		 	if(matrix[i][j] > matrix[i][k] + matrix[k][j]){
			 	matrix[i][j] = matrix[i][k] + matrix[k][j];}} 
		    }
		}		
		return matrix;
	}


	public void walk(String method) {
		clearWalk();
		//traverse the graph 
		for(int i = 0; i < nodeList.size(); i ++) {
			if(ifVisited(i) == false) {
				if(method.equals("BFS")){ BFS(i);}  //i as the start node
				else if(method.equals("DFS")){ DFS(i);} //i as the start node
				else {
					System.out.println("unrecognized traversal order: " + method);
					System.exit(0);
				}
			}
		}
		System.out.println(method + ":");
		displayEnum();
	}

	int compCount;
	int compSize;

	public void walk2(String method) {//walk method for mini-assignment Lab week 12
		clearWalk();
		//traverse the graph 
		for(int i = 0; i < nodeList.size(); i ++) {
			if(ifVisited(i) == false) {
				compCount++;	  
				if(compSize != 0) System.out.println("One of the components has " + compSize + " nodes.");//The Will cove the size of all component encountered, except the last one.
				if(method.equals("BFS")){compSize = 0; BFS(i); } //i as the start node
				else if(method.equals("DFS")){compSize = 0; DFS(i);} //i as the start node
				else {
					System.out.println("unrecognized traversal order: " + method);
					System.exit(0);
				}
			}
		}
			
		displayEnum();
	}


	public void DFS(int v) {
		setVisited(v); 
		compSize++;
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
			compSize++;
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
}
