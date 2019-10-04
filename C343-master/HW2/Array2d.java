public interface Array2d{


	
	//Insert an integer into the array located in the array (a 2d array).
		public void insertInteger(int indexOfRow, int indexOfColumn, int add);

	//Reinitialize the 2d array
		public void reinitialize();

	//Return the number of arrays in the array (number of columns).
		public int  countColumns();

	//Return the total number of integers.
		public int  countElements();


	//Delete an integer at a specified location.
		public void deleteInt(int indexOfColumn, int indexOfRow);

	//Return true if the array empty.
		public boolean isEmpty();
	
	//Prints
		public void print2D(); 




}
