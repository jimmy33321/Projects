
// KD tree with insert and exact match functions
// getNeighbors(Key[] key, int r) is going to be implemented by the students

public class KDtree<Key extends Comparable<?super Key>, E> {
	private BinNode<Key, E> root;
	private int totalNode;
	private BinNode<Key, E> curr;   //works with find()
	private String enumStr;         //for enumeration
	private int dim;   //dimension of the key
	private int level; //which level; important for insertion & search
	public KDtree(int d) {
		root = curr = null;
		totalNode = 0;
		dim = d;
		level = 0;
	}
	public BinNode<Key, E> find(Key[] k) {
		if(root == null) return null;
		else {
			return find(root, 0, k);
		}
	}
	public BinNode<Key, E> find(BinNode<Key, E> entry, int thislevel, Key[] k) {
		if(entry == null) return null;
		curr = entry;
		level = thislevel; //update level
		if(entry.getKey() == k) {
			return curr;
		}
		else {
			if(entry.isLeaf()) return null;
			Key[] key2 = entry.getKey();
			if (k[level % dim].compareTo(key2[level % dim]) >= 0) { //make sure the "right" key is used
				return find(entry.getRight(), thislevel + 1, k); //note thislevel + 1
			}
			else {
				return find(entry.getLeft(), thislevel + 1, k);
			}
		}
	}
	public void insert(Key[] k, E v) {
		BinNode<Key, E> node = new BinNode <Key, E>(k, v);
		insert(node);
		//insert(root, node);
	}
	public void insert(BinNode<Key, E> node) {
		find(node.getKey());
		if(curr == null) {
			root = node;
		}
		else {
			Key[] key1 = node.getKey();
			Key[] key2 = curr.getKey();
			if (key1[level % dim].compareTo(key2[level % dim]) >= 0) {
				if(curr.getRight() != null) node.setRight(curr.getRight());
				curr.setRight(node);
			}
			else {
				if(curr.getLeft() != null) node.setLeft(curr.getLeft());
				curr.setLeft(node);
			}
		}
		totalNode ++;
	}
	public void preorder() {
		enumStr = "";
		System.out.println("Total node = " + totalNode);
		if(root != null) preorder(root);
		System.out.println("Preorder enumeration: " + enumStr);
	}
	private void preorder(BinNode<Key, E> node) {	
		if(node != null) System.out.println("root " + node.toString());
		if(node.getLeft() != null) System.out.println("   left " + node.getLeft().toString());
		if(node.getRight() != null) System.out.println("   right " + node.getRight().toString());
		
		
		if(node != null) {
			enumStr += node.toString();
		}
 		if(node.getLeft() != null) preorder(node.getLeft());
		if(node.getRight() != null) preorder(node.getRight());
	}
	//to be implemented 
	public void getNeighbors(Key[] key, int r) 
	{

	  //$$$$
	  //	Codes are not complete, but you can hopefully see a framework for my plan to solve the problem. 
	  //
	  //
	  //
	  //
	 //$$$$$$$$$$$$$
	//	BinNode<Integer, String> current = find(key);
		System.out.println(key.toString() );


//		BinNode<Integer, String> current = find(key);

		  Key[] minAway = key;//location - r, location range it could be and still be close enough	
		Key[] maxAway = key;//location + r, location range it could be and not be too far

		//Makes some new arrays whose values are not either the max or minimum distances.
		for(Key k: minAway){
	//		 k.setValue(k -r);
		}	  
		for(Key k: maxAway){
	//	  	k.setValue(k +r);
		}


//Now that we have an arry with the max and min coord, loop through every node in this tree, and if both of its coordinates are within
//	max and min, it is a close neighbor. 


		//How to get an array of nodes in the tree?
	}

	//design your own examples to test the program!!
	public static void main(String[] args) {
		
		KDtree <Integer, String> kdt = new KDtree<Integer, String>(2);
		Integer[] dataA = {40, 45};
		kdt.insert(dataA, "A");
		Integer[] dataB = {15, 70};
		kdt.insert(dataB, "B");
		Integer[] dataC = {70, 10};
		kdt.insert(dataC, "C");
		Integer[] dataD = {69, 50};
		kdt.insert(dataD, "D");
		Integer[] dataE = {66, 85};
		kdt.insert(dataE, "E");
		Integer[] dataF = {85, 95};
		kdt.insert(dataF, "F");
		
		kdt.preorder();
		
		Integer[] dataG = {85, 93}; //G, close to F
		System.out.println("Query location: " + dataG[0] + " " + dataG[1]);
		BinNode<Integer, String> node = kdt.find(dataG); //exact match
		if(node == null) {
			System.out.println("point not found");
		}
		else {
			System.out.println("point found: " + node.toString());
		}
	

	//Test cases and expected answers below	
		int r = 3;
		kdt.getNeighbors(dataG, r); //get close neighbors
		//Should return G
		kdt.getNeighbors(dataE, 1); //should return none
		kdt.getNeighbors(dataA, 29292); //should return all of them
	}
}
