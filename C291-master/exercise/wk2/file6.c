//Automatic dialing machine
//Delivers a sales message
/*
Needs to print all possible combinations of areacode and exchangecode with the last 4 digits
Example : If areacode = 812, exchange = 369
Output : 
812-369-0001
812-369-0002
812-369-0003
.
.
812-369-9998
812-369-9999
*/

#include<stdio.h>

int main() {
	int digit1, digit2, digit3, digit4;
	int areaCode, exchange;
	printf("I can dial every phone number in an exchange and deliver a telemarketing message\n");
	printf("Enter a three-digit area code: ");
	scanf("%d", &areaCode);
	printf("Enter a three-digit exchange to call ");
	scanf("%d", &exchange);
	
	for(digit1 = 0; digit1 <= 9; digit1++) {
		for(digit2 = 0; digit2 <= 9; digit2++) {
			for(digit3 = 0; digit3 <= 9; digit3++) {
				for(digit4 = 0; digit4 <= 9; digit4++) {
					printf("Dialing %d-%d-%d%d%d%d\n",areaCode,exchange,digit1,digit2,digit3,digit4);
					printf("I am calling to tell you to buy Acme Insurance!\n");
				}
			}
		}
    }
	printf("Calls completed\n");
	return 0;
}
