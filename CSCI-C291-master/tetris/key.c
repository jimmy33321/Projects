/* ---- key.c --- 
 * 
 * Filename: key.c
 * Description: Controls all key inputs 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Thu Sep 15 16:35:07 2017
 * Last-Updated: Mon Feb 12 11:28:59 2018
 *           By: 
 *     Update #: 0
 * Keywords: 
 * Compatibility: 
 * 
 */

/* Commentary: 
 * 
 * 
 * 
 */

/* Change log:
 * 
 * 
 */

/* Copyright (c) 2016-18 The Trustees of Indiana University and 
 * Indiana University Research and Technology Corporation.  
 * 
 * All rights reserved. 
 * 
 * Additional copyrights may follow 
 */

/* Code: */
#include <ncurses.h> 
#include "key.h"

int read_escape(int *read_char) {
  int c; 
  if ((c = getch()) == ERR) {
    return (NOCHAR);
  }
  else if (c==0x1b) {
    if ((c = getch()) == '[') {
      c=getch();
      switch (c) {
      case 'A':
	return (UP);
	break;
      case 'B':
	return (DOWN);
	break;
      case 'C':
	return (RIGHT);
	break;
      case 'D':
	return (LEFT);
	break;
      default: 
	return (BADESC);
      }
    }
  } else if (c == 0x20) {
	return (SPACE);
  } else if (c == 0x2b) {
	return (PLUS);
  } else if (c == 0x2d) {
	return (MINUS);
  }
  else {
    *read_char = c;
    return (REGCHAR);
  }
}

/* key.c ends here */
