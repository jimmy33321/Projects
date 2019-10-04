/* 
The following code has a segmentation fault.
Figure out what it does, debug the file, find the error 
and fix it before preparing a demo for the AI's. 
*/

#include <stdio.h>

#define MAX_LETTERS 26

char alphabet[MAX_LETTERS];

void initialize_alphabet(char *a) {
  char *p;
  char current_letter = 'a';
  for (p=a;p<(a+MAX_LETTERS);p++) {
    *p = current_letter++;
  }
}  

void reverse_print_alpha(char *b) {
  static var = 0;
  char *p;

  var?(p=NULL):(p=b);
  var ^= 1;
								 
  initialize_alphabet(p);
  for (p=(b+MAX_LETTERS-1);p>=b;p--) {
   printf("%c",*p);
  }
  printf("\n");
}

int main(void) {
  int i;
  while (i++<5) {
    reverse_print_alpha(alphabet);
  }
}
