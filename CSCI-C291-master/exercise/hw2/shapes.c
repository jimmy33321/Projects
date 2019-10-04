/*
* Name: Jimmy Yu
* Date: 3/25/14
* Username: jimmyu
* Assignment 2 Part 1
*/

/*
* a. Pseudocode
* Will need to use a while loop and get input from user. Based on
* user input, program will print a particular shape using 
* asterisks. Will quit if user enters Q.
*/

#include <stdio.h>

void box();
void oval();
void star();
void triangle();
void hut();

int main() {
	char input;
	while (1) {
		//scan for input
		printf("Enter a single character\n");
		printf("\t'B' to print a Box.\n");
		printf("\t'O' to print an Oval.\n");
		printf("\t'S' to print a Star.\n");
		printf("\t'T' to print a Triangle.\n");
		printf("\t'H' to print an inverted Hut.\n");
		printf("\t'Q' to Quit.\n");
		scanf("%c",&input);
		getchar();
		//use if-else statements to call appropriate
		//function for printing a shape
		if (input == 'B' || input == 'b') {
			//print a box
			box();
		} else if (input == 'O' || input == 'o') {
			//print an oval
			oval();
		} else if (input == 'S' || input == 's') {
			//print a star
			star();
		} else if (input == 'T' || input == 't') {
			//print a triangle
			triangle();
		} else if (input == 'H' || input == 'h') {
			//print an inverted hut
			hut();
		} else if (input == 'Q' || input == 'q') {
			//quit
			break;
			printf("Quiting...\n");
		} else {
			//invalid input
			printf("Invalid input.\n");
		}
	}
	return (0);
}

void box() {
	//print a box
	printf("********\n");
	printf("*      *\n");
	printf("*      *\n");
	printf("*      *\n");
	printf("*      *\n");
	printf("*      *\n");
	printf("*      *\n");
	printf("********\n");
}

void oval() {
	//print an oval
	printf("   ***\n");
	printf(" *     *\n");
	printf("*       *\n");
	printf("*       *\n");
	printf("*       *\n");
	printf("*       *\n");
	printf("*       *\n");
	printf(" *     *\n");
	printf("   ***\n");
}

void star() {
	//print a star
	printf("   *   \n");
	printf("* * * *\n");
	printf(" * * * \n");
	printf("*     *\n");
}

void triangle() {
	//print a triangle
	printf("***************\n");
	printf(" *           *\n");
	printf("  *         *\n");
	printf("   *       *\n");
	printf("    *     *\n");
	printf("     *   *\n");
	printf("      * *\n");
	printf("       *\n");
}

void hut() {
	//print a hut
	printf("***   ***\n");
	printf("***   ***\n");
	printf("***   ***\n");
	printf("*********\n");
	printf(" *******\n");
	printf("  *****\n");
	printf("   ***\n");
	printf("    *\n");
}
