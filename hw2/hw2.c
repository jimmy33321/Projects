// hw2pl.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdio.h>
#include <string>

  void reverse_in_place(char *s);
  
  int main(int argc, char *argv[]) {
    int i;
    
    /* reverse all the command-line arguments */
    for (i = 1; i < argc; i++) {
       reverse_in_place(argv[i]);
    }
    
    /* print out the reversed arguments, in reverse order */
    for (i = argc - 1; i >= 1; i--) {
       if (i != argc - 1)
          putchar(' '); /* space between args */
       printf("%s", argv[i]);
    }
    putchar('\n');
    
    return 0;
  }

void reverse_in_place(char *s)
  {
	  int w = strlen(*s);
	  int p = 0;
	  int v = w - 1;
	  string temp = '';
	  temp = strcat(temp, *s);
	  while (p < w)
	  {
	  s*[p]=temp[v];
	  p = p + 1;
	  v = v - 1;
	  }
	  
  }
