import java.util.*;

public class BinarySearch <E extends Comparable<?super E>>{


  public static void main(String[] args){
	Integer[] a = new Integer[] {2, 3, 4, 5, 6, 7, 8, 9};
	String[] s = {"a", "b", "c", "d"};
	BinarySearch bs = new BinarySearch();	
	
	System.out.println("Searching for 4 in :" + Arrays.toString(a) + " " + bs.searchAndReturnIndex(a, 4) );
	System.out.println("Searching for 12 in :" + Arrays.toString(a) + " " + bs.searchAndReturnIndex(a, 12) );
	System.out.println("Searching for b in :" + Arrays.toString(s) + " " + bs.searchAndReturnIndex(s, "b") );
	System.out.println("Searching for w in :" + Arrays.toString(s) + " " + bs.searchAndReturnIndex(s, "w") );


  }

  public int searchAndReturnIndex(E[] a, E k){
	int l = -1;
	int r = a.length;
	while(l+1 != r){
		int i = (l+r)/2;
		if (k.compareTo(a[i]) ==0) return i;
		else if (k.compareTo(a[i]) < 0 ) r = i;
		else l = i;
	}
	return a.length;
  }




}
