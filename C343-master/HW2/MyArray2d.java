import java.util.*;

public class MyArray2d implements Array2d{

	int[][]ddarray = new int[1][1];

	public MyArray2d(int row, int column){
		ddarray = new int[row][column];
	
	}

  
           //Insert an integer into the array located in the array (a 2d array).
                  public void insertInteger(int indexOfRow, int indexOfColumn, int added){ 
			ddarray[indexOfRow][indexOfRow] = added;
  		}
          //Reinitialize the 2d array
                  public void reinitialize(){	
			ddarray = new int[ddarray.length][ddarray[0].length];
		
		} 
          //Return the number of arrays in the array (number of columns).
                  public int  countColumns(){
			return ddarray.length;
		}
 
          //Return the total number of integers.
                  public int  countElements(){
			return ddarray.length * ddarray[0].length;
		}
 
 
          //Delete an integer at a specified location.
                  public void deleteInt(int indexOfRow, int indexOfColumn){
			ddarray[indexOfRow][indexOfColumn] = 0;
		}
 
          //Return true if the array empty.
                  public boolean isEmpty(){
			if(ddarray.length < 2){
				 return true;}
			  else{ return false;}
		}


		public void print2D(){
			System.out.println( Arrays.deepToString(ddarray) //Uses a java.util.* method to print out multi-d array. This could be done manually with some nested loops, but why reinvent the wheel, when java provides this to you?
	);	}
		
	public static void main(String[] args){
		MyArray2d m = new MyArray2d(2, 2);
		System.out.println( m.isEmpty() );
		System.out.println( m.countColumns() );
		m.print2D();
		m.insertInteger(1, 1, 2) ;
		m.print2D();
		m.reinitialize();
		m.print2D();
}
}
