//Jimmy Yu c335

//C343 HW10
//CowMarathon

import java.util.*;

public class CowMarathon {
  int greatestDistance, vertices, edges = 0;
  int maxIndex = 0; //contains the index of the max distance value
  
  public CowMarathon(int vert, int edge){ //Constructor
    vertices = vert;
    edges = edge;
    greatestDistance = 0; 
    maxIndex = 0; 
  }
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String[] input = in.nextLine().split("\\s+");
    CowMarathon cm = new CowMarathon(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
    
    //add some nodes to an arraylist
    List<List<Node>> nodes = new ArrayList<List<Node>>();
    for (int i = 0; i <= cm.vertices; ++i) {
      nodes.add(new ArrayList<Node>());}
    
    //prompt user for input of individual verticie/edge information
    for (int i = 1; i <= cm.edges; ++i) {
      input = in.nextLine().split("\\s+");
      int v1 = Integer.parseInt(input[0]);//first vertex
      int v2 = Integer.parseInt(input[1]);//second vertex
      int distance = Integer.parseInt(input[2]); //ie the weight
      //input[3] = Distance, which we do not need for this problem, but user still gives us it! We ignore it now!
      nodes.get(v1).add(new Node(v2, distance));  //make edge v1=>v2
      nodes.get(v2).add(new Node(v1, distance));  //make edge v2=>v1
    }
    cm.maxIndex = cm.bfs(1, nodes);
    cm.greatestDistance = 0; //reset value to 0 as bfs method mutates it
    cm.bfs(cm.maxIndex, nodes);
    System.out.println(cm.greatestDistance);//This is the answer!
  }
  
 public int bfs(int index, List<List<Node>> nodes) {
   int edges = nodes.size();//number of edges
   int[] distance = new int[edges];//array where value at index i is the distance of edge i
   boolean[] visited = new boolean[edges];//array where value at index i is true if i has been visited, false otherwise
   Queue<Integer> queue = new LinkedList<Integer>();//other things to visit
  
   //visit the given index
   queue.offer(index);
   visited[index] = true;
   
  //visit the rest of the queue
   while (!queue.isEmpty()) {
     int i = queue.poll();
     if (distance[i] > greatestDistance) {
      greatestDistance = distance[i];
      maxIndex = i;
     }
     for (int j = 0; j < nodes.get(i).size(); ++j) {
       Node temp = nodes.get(i).get(j);
       if (!visited[temp.getIndex()]) {
         visited[temp.getIndex()] = true;
         distance[temp.getIndex()] = distance[i] + temp.getDistance();
         queue.offer(temp.getIndex());
       }
     }
  }
  return maxIndex;
 }
}