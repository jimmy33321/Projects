/*
* Name: Jimmy Yu
* Date: 3/25/14
* Username: jimmyu
* Assignment 2 Part 2
*/

#include <stdio.h>

void box();
void oval();
void star();
void triangle();
void hut();

int main() {
	char input;
	int check = 1;
	while (check) {
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
		//use switch statement to call appropriate
		//function for printing a shape
		switch(input) {
			case 'B':
				//print a box
				box();
				break;
			case 'b':
				//print a box
				box();
				break;
			case 'O':
				//print an oval
				oval();
				break;
			case 'o':
				//print an oval
				oval();
				break;
			case 'S':
				//print a star
				star();
				break;
			case 's':
				//print a star
				star();
				break;
			case 'T':
				//print a triangle
				triangle();
				break;
			case 't':
				//print a triangle
				triangle();
				break;
			case 'H':
				//print a hut
				hut();
				break;
			case 'h':
				//print a hut
				hut();
				break;
			case 'Q':
				//quit
				check = 0;
				break;
			case 'q':
				//quit
				check = 0;
				break;
			default:
				//invalid input
				printf("Invalid input.\n");
				break;
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
