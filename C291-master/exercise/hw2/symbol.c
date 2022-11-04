/*
* Name: Jimmy Yu
* Date: 3/25/14
* Username: jimmyu
* Assignment 2 Part 3
*/

/*
* a. Pseudocode
* Will need to first get input, then use a while loop to print the necessary
* number of lines. Within that while loop, two more while loops will need
* to be used in order to print the necessary number of increasing tabs
* for each line, then to print the necessary number of symbols per line.
*/

#include <stdio.h>

int main() {
	int num_lines = 0; //number of lines to print
	char symbol; //symbol to print
	int num_symbols = 0; //number of symbols per line
	
	//get user input
	while (1) {
		printf("Enter the number of lines to print: ");
		scanf("%d",&num_lines);
		if (num_lines > 4 && num_lines < 12) {
			break;
		} else {
			printf("Number of lines must be between eleven inclusively.\n");
		}
	}
	
	getchar();
	printf("Enter the symbol to print: ");
	scanf("%c",&symbol);
	
	while (1) {
		printf("Enter the number of symbols to print per line: ");
		scanf("%d",&num_symbols);
		if (num_symbols > 7 && num_symbols < 21) {
			break;
		} else {
			//use default 15
			num_symbols = 15;
			break;
		}
	}
	
	int tabs = 1; //number to keep track of indentations for printing
	int temp;
	while (num_lines > 0) {
		temp = tabs;
		while (temp > 0) {
			printf("\t");
			temp--;
		}
		temp = num_symbols;
		while (temp > 0) {
			printf("%c",symbol);
			temp--;
		}
		printf("\n");
		tabs += 2;
		num_lines--;
	}
	return (0);
}
