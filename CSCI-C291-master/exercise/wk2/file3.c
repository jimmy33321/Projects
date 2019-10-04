/*Debugging quiz - File No: 3 */
/* The program should give out the appropriate message, given the grade as input */

#include<stdio.h>

int main() {
	char input;
	while(1) {
		printf("Enter the grade(A/B/C/D/F): (Q to Quit)\n");
		scanf("%c", &input);
		getchar();
		if ((input == 'A') || (input == 'B') || (input == 'C')) {
			printf("You have passed!\n");
		} else if ((input == 'D') || (input == 'F')) {
			printf("You have not passed the course.\n");
		} else if (input == 'Q') {
			break;
		} else {
			
		}
	}
	printf("Quit the menu.\n");
	return (0);
}
