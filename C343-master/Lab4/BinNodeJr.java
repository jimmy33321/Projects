public class BinNodeJr <E extends Comparable<?super E>>{ 
    private E value;
    private BinNodeJr<E> left;
    private BinNodeJr<E> right;
    public BinNodeJr(E e) {
        value = e;
        left = right = null;
    }
    public void setLeft(BinNodeJr<E> node) {
          left = node;
          }
    public void setRight(BinNodeJr<E> node) {
          right = node;
          }
    public boolean find(E q) {
        boolean result = false;
	if(value.compareTo(q) == 0){
    	 	return true;}
     	else{
	 	if(left != null){ //left is not null
			 if (left.find(q) == true){ return true;}
			 	else {result = left.find(q);} //recur
		}
		if(right != null){ //right is not null
			if (right.find(q) == true){ return true;}
				else{ result = right.find(q);}
		}
		return result;
	} 	
    }
    public static void main(String[] argv) {
        BinNodeJr<Integer> root = new BinNodeJr<Integer>(10);
        BinNodeJr<Integer> node1 = new BinNodeJr<Integer>(30);
    	BinNodeJr<Integer> node2 = new BinNodeJr<Integer>(40);
	BinNodeJr<Integer> node3 = new BinNodeJr<Integer>(22);
	BinNodeJr<Integer> node4 = new BinNodeJr<Integer>(29);
	node2.setLeft(node3);
	node2.setRight(node4);
        root.setLeft(node1);
        root.setRight(node2);
	System.out.println( root.find(10) );
	System.out.println( root.find(30) );
	System.out.println( root.find(22) );
	System.out.println( root.find(29) );
        System.out.println( root.find(49) );
	//find() is to be implemented
	System.out.println("40 is found in the tree: " + root.find(40));
	//find(40) shall return true
	System.out.println("100 is found in the tree: " + root.find(100));
	//find(100) shall return false
	                                    }
	                                    
}	
