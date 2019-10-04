//factorial of a number is the product of the number with all
// the numbers below it.
//eg: Factorial of 5 = 5 * 4 * 3 * 2 * 1 = 120

#include <stdio.h>

int factorial (int num);

int main() {
	int n; //input for finding factorial
    	printf("Debugging Practice 1 - Quiz 3, Q3\n\n");
        printf("Please enter the number whose factorial you wish to find: ");
        scanf("%d", &n);
        int fact = factorial(n);
        printf("The factorial of %d is %d\n",n,fact);
	return 0;
}

int factorial (int num) {
	int fact = num;
	int i = 0;
	num--;
	while (num > 0) {
		
		fact = fact * num;
		num--;
	}
	return fact;
}
