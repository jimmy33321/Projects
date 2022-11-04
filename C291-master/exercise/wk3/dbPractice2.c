//Program to read elements into a 3X3 matrix and display them

#include <stdio.h>

void display(int matrix[][3]);

int main() {
	int matrix[3][3];	
	printf("Enter 9 elements for the matrix.\n");
	int i;
	int j;
	for (i = 0; i < 3; i++) {
		for (j = 0; j < 3; j++) {
			scanf("%d",&matrix[i][j]);
		}
	}
	display(matrix);
    	return 0;
}

void display(int matrix[][3]) {
	int i;
	int j;
	for (i = 0; i < 3; i++) {
		for (j = 0; j < 3; j++) {
			if (j == 2) {
				printf("%d",matrix[i][j]);
			} else {
				printf("%d, ",matrix[i][j]);
			}
		}
		printf("\n");
        }
}
