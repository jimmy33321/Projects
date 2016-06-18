//Jimmy Yu
//Working alone because of odd number of students in class
//lab 3
//dump.c
#include <stdio.h>

void dump_memory(void *p, int len)
{
  int i;
  printf("address  char  hexCH  short      integer        float      doubleFloat");
  for (i = 0; i < len; i++) {
    printf("\n %8p %c  %02x\n %0hd %d %f %+e ", p + i, *(unsigned char *)(p + i),*(unsigned char *)(p + i), *(unsigned short *)(p + i),*(unsigned int *)(p + i), *(float *)(p + i), *(double *)(p + i));    // p is pointer, c is char, x is hex, hd is short, d is integer, f is float, e is doublefloat 
  }
}
