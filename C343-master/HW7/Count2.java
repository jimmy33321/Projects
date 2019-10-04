import java.util.*;

public class Count2
{
  int[] arrayToSort;
  int[] sortedArray;
   public static void main(String[] args)
    {
	  Count2 c = new Count2();
	  c.arrayToSort = new int[] { 0, 3, 1, 0, 5, 2, 4, 5, 2 };
	  c.sortedArray = c.sort(); //arrayToSort);
	   
	System.out.println("The array unsorted: " + Arrays.toString(c.arrayToSort) + " vs it sorted: " + Arrays.toString(c.sortedArray));	    
     }
   
  
 public int[] sort() //int[] arrayToSort) 
       {
   int maxValue = getMaxVal();
   int[] fin = new int[arrayToSort.length];
   int[] temp = new int[maxValue + 1];
   for (int i = 0; i < arrayToSort.length; i++)
     {
          temp[arrayToSort[i]] = temp[arrayToSort[i]] + 1;
     }
   for (int i = 1; i < maxValue + 1; i++)
     {
	  temp[i] = temp[i] + temp[i - 1];
     }
     for (int i = (arrayToSort.length - 1); i >= 0; i--)
     {
	  fin[temp[arrayToSort[i]] - 1] = arrayToSort[i];
	  temp[arrayToSort[i]] = temp[arrayToSort[i]] - 1;
     }
  return fin;
 }

	public  int getMaxVal()
	{
		int max = arrayToSort[0];
		for(int i = 1; i<arrayToSort.length; i++){
			if(arrayToSort[i]>max){
				max = arrayToSort[i];
			}
		}
		return max;	
	}
}
