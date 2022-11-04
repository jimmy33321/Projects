/* 
 * ------ Filename: main.c ------ 
 * Description: Basic C program to print out hello world! 
 * Author: Jimmy Yu
*/

#include <stdio.h>
#include <unistd.h>

int main(void) {
	int myNumber = 0;
	printf("\"Hello: Welcome to Spring 2018 \\C291!/\"\n");
	printf("Please enter a user ID# and press ENTER: ");
	scanf("%d", &myNumber);
	printf("User ID: %d\n", myNumber);
	while (1) {
  		printf("*\t");
		fflush(stdout);
		sleep(1);
	}
	return(0); 
}


