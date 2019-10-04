/*
* Name: Jimmy Yu	
* Username: jimmyu
* Date: 3/29/14
* Part of: Assignment 3
*/

//This program is for Part B

#include <stdio.h>

//function prototypes
void move(int num);

//global variables
int pen = 0; //1 = pen down, 0 = pen up
int turtleX = 0; //x coordinate of turtle
int turtleY = 0; //y coordinate of turtle
int dir = 90; //90 is north
int grid[100][60]; //matrix that represents turtle's floor

//function moves turtle by given amount in its current direction
//if turtle attempts to move outside grid it will turn around and keep going
void move(int num) {
	while (num > 0) {
		//check if moving turtle would move it off the grid
		//if so, reverse its direction
		if (dir == 90 && turtleY == 0) {
			dir = 270;
		} else if (dir == 45 && (turtleY == 0 || turtleX == 59)) {
			dir = 225;
		} else if (dir == 0 && turtleX == 59) {
			dir = 180;
		} else if (dir == 315 && (turtleY == 99 || turtleX == 59)) {
			dir = 135;
		} else if (dir == 270 && turtleY == 99) {
			dir = 90;
		} else if (dir == 225 && (turtleY == 99 || turtleX == 0)) {
			dir = 45;
		} else if (dir == 180 && turtleX == 0) {
			dir = 0;
		} else if (dir == 135 && (turtleY == 0 || turtleX == 0)) {
			dir = 315;
		}
		
		//check if pen is down
		if (pen == 1) {
			//mark this part of grid
			grid[turtleY][turtleX] = 1;
		}
		
		//move the turtle based on its direction
		if (dir == 90) {
			turtleY--;
		} else if (dir == 45) {
			turtleY--;
			turtleX++;
		} else if (dir == 0) {
			turtleX++;
		} else if (dir == 315) {
			turtleY++;
			turtleX++;
		} else if (dir == 270) {
			turtleY++;
		} else if (dir == 225) {
			turtleY++;
			turtleX--;
		} else if (dir == 180) {
			turtleX--;
		} else if (dir == 135) {
			turtleY--;
			turtleX--;
		}
		num--;
	}
}

int main() {
	//initialize grid to all zeroes
	int i, j;
	for (i = 0; i < 100; i++) {
		for (j = 0; j < 60; j++) {
			grid[i][j] = 0;
		}
	}
	//start command loop
	int input;
	printf("The turtle is at coordinate (%d,%d) and is turned to direction %d\n",turtleX,turtleY,dir);
	while (1) {
		printf("Enter command (9 to end input): ");
		scanf("%d", &input);
		getchar();
		if (input == 1) {
			//pen up
			pen = 0;
		} else if (input == 2) {
			//pen down
			pen = 1;
		} else if (input == 3) {
			//turn right 90 degrees
			if (dir == 0) {
				dir = 270;
			} else if (dir == 45) {
				dir = 315;
			} else {
				dir -= 90;
			}
		} else if (input == 4) {
			//turn left 90 degrees
			if (dir == 270) {
				dir = 0;
			} else if (dir == 315) {
				dir = 45;
			} else {
				dir += 90;
			}
		} else if (input == 5) {
			//move
			scanf("%d",&input);
			getchar();
			move(input);
		} else if (input == 6) {
			//print portion of grid around turtle
			printf("\nThe drawing is:\n\n");
			for (i = 0; i < 100; i++) {
                		for (j = 0; j < 60; j++) {
                        		if (grid[i][j] == 1) {
						printf("*");
					} else {
						printf(" ");
					}
                		}
				printf("\n");
        		}
		} else if (input == 7) {
			//turn right 45 degrees
			if (dir == 0) {
				dir = 315;
			} else {
				dir -= 45;
			}
		} else if (input == 8) {
			//turn left 45 degrees
			if (dir == 315) {
				dir = 0;
			} else {
				dir += 45;
			}
		} else if (input = 9) {
			//end
			break;
		} else {
			printf("Invalid command.\n");
		}
	}
	
	//draw a shape using commands
	//start by resetting grid, coordinates, dir and pen
	printf("\nPrinting first five letters of my name\n\nThe drawing is: \n\n");
	turtleX = 0;
	turtleY = 0;
	dir = 270;
	pen = 1;
	for (i = 0; i < 100; i++) {
                for (j = 0; j < 60; j++) {
                        grid[i][j] = 0;
                }
        }
	
	move(1);
	pen = 0;
	move(1);
	pen = 1;
	move(2);
	dir -= 270;
	move(1);
	pen = 0;
	move (1);
	//draw the a
	pen = 1;
	dir += 90;
	move(4);
	dir -= 90;
	move(2);
	dir += 270;
	move(2);
	dir -= 90;
	move(1);
	dir -= 180;
	move(1);
	dir += 270;
	move(2);
	dir -= 270;
	move(1);
	pen = 0;
	move(1);
	//draw the n
	pen = 1;
	dir += 90;
	move(4);
	dir += 225;
	move(4);
	dir -= 225;
	move(4);
	dir -= 90;
	move (1);
	pen = 0;
	move(1);
	pen = 1;
	//draw the p
	dir += 270;
	move(4);
	dir -= 180;
	move(4);
	dir -= 90;
	move(2);
	dir += 270;
	move(2);
	dir -= 90;
	move(2);
	pen = 0;
	dir -= 180;
	move(4);
	dir += 90;
	move(2);
	dir -= 90;
	pen = 1;
	//finally draw o
	move(2);
	dir += 270;
	move(4);
	dir -= 90;
	move(2);
	dir -= 90;
	move(4);
	
	//print grid to show shape
	for (i = 0; i < 100; i++) {
        	for (j = 0; j < 60; j++) {
                	if (grid[i][j] == 1) {
                        	printf("*");
                        } else {
                                printf(" ");
                        }
                }
                printf("\n");
        }
	return (0);
}
