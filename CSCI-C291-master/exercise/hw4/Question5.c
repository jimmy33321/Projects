/* Name: Jimmy Yu
 * Username: jimmyu
 * Date Created: 4/9/14
 * Assignment: Assignment 4 Question 5
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
	//my six chosen topics
	char * topics[6] =
		{"Environment", "Healthcare", "Taxes", "Military", 
		"Gun Reform", "US Debt"};
	//matrix used to store data
	//I polled 9 people and am initializing responses with the results
	int responses[10][6] = {
		{10,8,5,1,7,6}, {9,10,8,6,2,8}, {3,1,10,9,1,7},
		{10,10,4,2,9,3}, {8,9,3,1,7,10}, {1,1,10,9,3,10},
		{8,1,5,3,10,8}, {8,8,1,10,2,5}, {6,10,3,5,3,8},
		{0,0,0,0,0,0} };
	//poll user on these issues
	printf("Please rate these political issues regarding importance from 1 to 10\n");
	int input;
	
	printf("Environment: ");
	scanf("%d",&input);
	responses[9][0] = input;
	
	printf("Healthcare: ");
	scanf("%d",&input);
	responses[9][1] = input;
	
	printf("Taxes: ");
        scanf("%d",&input);
        responses[9][2] = input;
	
	printf("Military: ");
        scanf("%d",&input);
        responses[9][3] = input;
	
	printf("Gun Reform: ");
        scanf("%d",&input);
        responses[9][4] = input;
	
	printf("US Debt: ");
        scanf("%d",&input);
        responses[9][5] = input;
	
	//print the data
	printf("\nResults\n\n");
	int i,j;
	printf("%10s %10s %10s %10s %10s %10s\n",topics[0],topics[1],topics[2],topics[3],topics[4],topics[5]);
	for (i = 0; i < 10; i++) {
		for (j = 0; j < 6; j++) {
			printf("%10d ",responses[i][j]);
		}
		printf("\n");
	}
	printf("Averages\n");
	double avg;
	for (i = 0; i < 6; i++) {
		//find average for each issue and display
		avg = responses[0][i] + responses[1][i] + responses[2][i] + responses[3][i] + 
		responses[4][i] + responses[5][i] + responses[6][i] + responses[7][i] +
		responses[8][i] + responses[9][i];
		avg = avg / 10;
		printf("%10.2lf ",avg);
	}
	printf("\n");
	//find issue with the highest point total
	input = 0; //used here to keep track of largest total
	int issue = 0; //used to keep track of the issue with largest total
	int temp = 0; //used to calculating sums
	for (i = 0; i < 6; i++) {
		temp = responses[0][i] + responses[1][i] + responses[2][i] + responses[3][i] +
                responses[4][i] + responses[5][i] + responses[6][i] + responses[7][i] +
                responses[8][i] + responses[9][i];
		if (temp > input) {
			input = temp;
			issue = i;
		}
	}
	printf("The issue with the highest point total was %s with %d points.\n",topics[issue],input);
	//find issue with the lowest point total
	input = 200;
	issue = 0;
	temp = 0;
	for (i = 0; i < 6; i++) {
		temp = responses[0][i] + responses[1][i] + responses[2][i] + responses[3][i] +
                responses[4][i] + responses[5][i] + responses[6][i] + responses[7][i] +
                responses[8][i] + responses[9][i];
		if (temp < input) {
			input = temp;
			issue = i;
		}
	}
	printf("The issue with the lowest point total was %s with %d points.\n",topics[issue],input);
	return (0);
}
