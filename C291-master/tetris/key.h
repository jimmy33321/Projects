/* ---- key.h --- 
 * 
 * Filename: key.h
 * Description: 
 * Author: Bryce Himebaugh
 * Maintainer: 
 * Created: Thu Sep 15 16:36:21 2017
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
enum {NOCHAR, REGCHAR, UP, DOWN, LEFT, RIGHT, BADESC, PLUS, MINUS, SPACE, RESET}; 

int read_escape(int *);

/* key.h ends here */
