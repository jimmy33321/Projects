import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class HuffTree<Key, E> {
	private MinHeap<Integer, String> heap; //for building the tree
	private BinNode<Integer, String> root; //for traversal
	private Dictionary<String, String> codeTable; //huffman code table
	private Dictionary<String, Integer> codeFreq;
	public HuffTree(String letters, int[] weights) {
		init(letters, weights);
		buildTree();

		codeTable = new Hashtable<String, String>();
		getCode();
		
		summary();
	}
	private void init(String letters, int[] weights) {
		codeFreq = new Hashtable<String, Integer>();
		for(int i = 0; i < letters.length(); i ++) 
			codeFreq.put(letters.substring(i, i + 1), weights[i]);
		int maxNum = letters.length();
		//BinNode<Integer, String>[] nodes = (BinNode<Integer, String>[]) new Object[maxNum];
		@SuppressWarnings("unchecked")
		BinNode<Integer, String>[] nodes = new BinNode[maxNum];
		for(int i = 0; i < maxNum; i ++) {
			nodes[i] = new BinNode<Integer, String>(weights[i], letters.substring(i, i + 1)); 
		}
		heap = new MinHeap<Integer, String>(maxNum, maxNum, nodes);
		heap.display();
	}
	private void buildTree() {
		while(heap.length() > 1) {
			BinNode<Integer, String> node1 = heap.removeMin();
			BinNode<Integer, String> node2 = heap.removeMin();
			BinNode<Integer, String> newnode = new BinNode<Integer, String>(node1.getKey() + node2.getKey(), " ");
			newnode.setLeft(node1);
			newnode.setRight(node2);
			heap.insert(newnode);
			heap.display();
		}
		root = heap.removeMin();
		System.out.println("root of the hufftree: weight " + root.getKey());
	}
	public void summary() {
		if(codeTable.isEmpty()) {
			System.out.println("codes not found");
			return;
		}
		//display the code & compute the sum of weighted path lengths
		Enumeration<String> keys = codeFreq.keys();
		int sumOfWeightedPath = 0;
		while(keys.hasMoreElements()) {
			String key = keys.nextElement();
		    System.out.println("Letter: " + key + " " + codeTable.get(key));
		    sumOfWeightedPath += codeTable.get(key).length() * codeFreq.get(key); 
		}
		System.out.println("Total letters: " + root.getKey());
		System.out.println("Sum of weighted path lengths: " + sumOfWeightedPath);
		System.out.println("Ave-Code-Length: " + sumOfWeightedPath * 1.0 / root.getKey());
	}
	//function 1: get the codes by walking in the tree
	//each leaf node is a letter, and the corresponding path is the code
	//for simplicity, the codes are represented using strings of "0" and "1", not bits
	//to be implemented by students
	private void getCode() {
		getCodeACC(root, ""); 
	}
	private void getCodeACC(BinNode focus, String path){
		if(focus.isLeaf()){ // if not a leaf, we recur into the tree, storing the path.  If it is a leaf, we put it with its path into dictionary.
			  codeTable.put(focus.getValue() + "", path);}
		else{ 
			if(focus.getRight() != null){
				getCodeACC(focus.getRight(), path + "1");}
			if(focus.getLeft() != null) {
				getCodeACC(focus.getLeft(), path + "0");}
		}}

	//function 2: encode a message
	public String encode(String instr) {
		if(codeTable.isEmpty()) { System.out.println("codes not found"); return ""; }
		String code = "";
		for(int i = 0; i < instr.length(); i ++) {
			String letter = instr.substring(i, i+1);
			code += codeTable.get(letter);
		}
		return code;
	}
	//function 3: decode a message
	public String decode(String coded) {
		String ori = "";
		BinNode<Integer, String> curr = root; 
		assert curr.isLeaf() == false : "empty Huffman tree";
		for(int i = 0; i < coded.length(); i ++) {
			char tmp = coded.charAt(i);
			if(tmp == '0') curr = curr.getLeft();
			else curr = curr.getRight();
			if(curr.isLeaf()) { 
				ori += curr.getValue();
				curr = root;
			}
		}
		return ori;
	}
	public static void main(String[] argv) {
		int[] weights = {2, 7, 24, 32, 37, 42, 42, 120};
		String letters = "ZKMCUDLE";
		HuffTree<Integer, String> tree = new HuffTree<Integer, String>(letters, weights);
		System.out.println("DEED" + " coded as " + tree.encode("DEED"));
	}
}
