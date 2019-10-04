/*Debugging quiz - File No: 4 */
/* This program should perform the duties of a basic calculator */
// Check for possible logical errors and rectify them 

#include<stdio.h>

int main(void){
	char input;
	double num1 = 0;
	double num2 = 0;
	double result = 0;
	printf("Welcome to the Calculator\nOperation choices:\tAddition(A)\n\t\t\tSubtraction(S)\n\t\t\tMultiplication(M)\n\t\t\tDivision(D)\nEnter choice: ");
	input = getchar();
	
	if (input == 'A' || input == 'S' || input == 'M' || input == 'D') {
		printf("Enter both numbers in required sequence: ");
		scanf("%lf %lf",&num1,&num2);
		switch(input) {
			case 'A': 
				result = num1 + num2;
          			break;
			case 'S': 
				result = num1 - num2;
          			break;
			case 'M': 
				result = num1 * num2;
          			break;
			case 'D':
				if (num2 != 0) { 
					result = num1 / num2;
				} else {
					printf("Error: Divide by zero.\n");
				}
	 			break;        			  
			default:
				break;
		}
		printf("The final result = %lf\n", result);
	} else {
		printf("Please choose a valid operation\n");
	}
	return(0);
}
