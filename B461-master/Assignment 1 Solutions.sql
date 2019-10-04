/* Consider the following relation schemas for a database that maintains
sailors, boats, and reservations of boats by sailors.


  Sailor(Sid INTEGER, Sname VARCHAR(20), Rating INTEGER, Age INTEGER) 
  Boat(Bid INTEGER, Bname VARCHAR(15), Color VARCHAR(15)) 
  Reserves(Sid INTEGER, Bid INTEGER, Day VARCHAR(10))

You should assume that Sid in Reserves is a foreign key that references
the primary key Sid in Sailor, and that Bid in Reserves is a foreign
key that references the primary key Bid in Boat.

Note the attached text files sailor.txt, boat.txt, and reserves.txt
that contain the relation instances for the Sailor, Boat, and Reserves
relations, respectively.



1. Create a database in PostgreSQL that stores these relations.
  Make sure to specify primary and foreign keys.
*/

/*
Problem 1
*/

DROP TABLE reserves;
DROP TABLE sailor;
DROP TABLE boat;


CREATE TABLE sailor(sid	integer, 
                    sname VARCHAR(20), 
                    rating integer, 
                    age integer,
                    PRIMARY KEY(sid));

CREATE TABLE boat (bid integer, 
                   bname VARCHAR(15), 
                   color VARCHAR(15), 
                   PRIMARY KEY(bid));

CREATE TABLE reserves(sid integer, bid integer, day varchar(10), 
                      PRIMARY KEY(sid, bid),
                      FOREIGN KEY (sid) REFERENCES sailor(sid),
                      FOREIGN KEY (bid) REFERENCES boat(bid));

INSERT INTO sailor VALUES(22,   'Dustin',       7,      45);
INSERT INTO sailor VALUES(29,   'Brutus',       1,      33);
INSERT INTO sailor VALUES(31,   'Lubber',       8,      55);
INSERT INTO sailor VALUES(32,   'Andy',         8,      25);
INSERT INTO sailor VALUES(58,   'Rusty',        10,     35);
INSERT INTO sailor VALUES(64,   'Horatio',      7,      35);
INSERT INTO sailor VALUES(71,   'Zorba',        10,     16);
INSERT INTO sailor VALUES(74,   'Horatio',      9,      35);
INSERT INTO sailor VALUES(85,   'Art',          3,      25);
INSERT INTO sailor VALUES(95,   'Bob',          3,      63);


INSERT INTO boat VALUES(101,	'Interlake',	'blue');
INSERT INTO boat VALUES(102,	'Sunset',	'red');
INSERT INTO boat VALUES(103,	'Clipper',	'green');
INSERT INTO boat VALUES(104,	'Marine',	'red');


INSERT INTO reserves VALUES(22,	101,	'Monday');
INSERT INTO reserves VALUES(22,	102,	'Tuesday');
INSERT INTO reserves VALUES(22,	103,	'Wednesday');
INSERT INTO reserves VALUES(31,	102,	'Thursday');
INSERT INTO reserves VALUES(31,	103,	'Friday');
INSERT INTO reserves VALUES(31, 104,	'Saturday');
INSERT INTO reserves VALUES(64,	101,	'Sunday');
INSERT INTO reserves VALUES(64,	102,	'Monday');
INSERT INTO reserves VALUES(74,	102,	'Saturday');

SELECT * FROM sailor;
SELECT * FROM boat;
SELECT * FROM reserves;


/*
Problem 2: Provide examples that illustrate how the presence or absence of primary and
  foreign keys affects insert and deletes in these relations.   To solve this problem,
  you will need to experiment with the relation schemas.   For example, 
  you should consider altering primary keys and foreign key constraints and
  then consider various sequences of insert and delete operations.
  Certain inserts and deletes should succeed but other should create error conditions.
  (Consider the lecture notes about keys, foreign keys, and inserts and deletes.)
*/

/* Suppose we insert a sailor whose primary key value already exists in the
sailor relation
*/

INSERT INTO Sailor VALUES(22, 'John', 20, 20);

/* We get the following error because we get a violation of the primary key
constraint in the sailor relation
ERROR:  duplicate key value violates unique constraint "sailor_pkey"
DETAIL:  Key (sid)=(22) already exists.
*/

/* Suppose we insert the following tuple in reserves */

INSERT INTO reserves(200, 101, 'Saturday');

/* We get the following error message since the does not a sailor with sid 100
and we get this error due to the foreign key constraint in Reserves.
ERROR:  syntax error at or near "200"
LINE 1: INSERT INTO reserves(200, 101, 'Saturday');
*/

/* Suppose we delete sailors from the sailor relation*/

DELETE FROM sailor WHERE sid = 22;

/*
We get the following error message since sailor 22 is referenced in the
reserves relation and we therefore would loose a reference.

ERROR:  syntax error at or near "200"
LINE 1: INSERT INTO reserves(200, 101, 'Saturday');
ERROR:  update or delete on table "sailor" violates foreign key constraint "reserves_sid_fkey" on table "reserves"
DETAIL:  Key (sid)=(22) is still referenced from table "reserves".
*/

/* However we can delete any tuple in reserves as illustrated next;
This delete does not violate any of the constraints */

DELETE FROM reserves WHERE sid = 22;

/*Suppose we drop the foreign key constraints in reserves and attempt to insert
a tuple in reserves and then subsequently delete it;  This will not cause
an error since no constraints are violated*/

DROP TABLE reserves;
CREATE TABLE reserves(sid integer, bid integer, day varchar(10), 
                      PRIMARY KEY(sid, bid));


INSERT INTO reserves VALUES (101, 34, 'Saturday');
DELETE FROM reserves WHERE sid = 101;


/*We now begin with Problem 3 and recreate the tables*/


DROP TABLE reserves;
DROP TABLE sailor;
DROP TABLE boat;


CREATE TABLE sailor(sid	integer, 
                    sname VARCHAR(20), 
                    rating integer, 
                    age integer,
                    PRIMARY KEY(sid));

CREATE TABLE boat (bid integer, 
                   bname VARCHAR(15), 
                   color VARCHAR(15), 
                   PRIMARY KEY(bid));

CREATE TABLE reserves(sid integer, bid integer, day varchar(10), 
                      PRIMARY KEY(sid, bid),
                      FOREIGN KEY (sid) REFERENCES sailor(sid),
                      FOREIGN KEY (bid) REFERENCES boat(bid));
INSERT INTO sailor VALUES(22,   'Dustin',       7,      45);
INSERT INTO sailor VALUES(29,   'Brutus',       1,      33);
INSERT INTO sailor VALUES(31,   'Lubber',       8,      55);
INSERT INTO sailor VALUES(32,   'Andy',         8,      25);
INSERT INTO sailor VALUES(58,   'Rusty',        10,     35);
INSERT INTO sailor VALUES(64,   'Horatio',      7,      35);
INSERT INTO sailor VALUES(71,   'Zorba',        10,     16);
INSERT INTO sailor VALUES(74,   'Horatio',      9,      35);
INSERT INTO sailor VALUES(85,   'Art',          3,      25);
INSERT INTO sailor VALUES(95,   'Bob',          3,      63);


INSERT INTO boat VALUES(101,	'Interlake',	'blue');
INSERT INTO boat VALUES(102,	'Sunset',	'red');
INSERT INTO boat VALUES(103,	'Clipper',	'green');
INSERT INTO boat VALUES(104,	'Marine',	'red');


INSERT INTO reserves VALUES(22,	101,	'Monday');
INSERT INTO reserves VALUES(22,	102,	'Tuesday');
INSERT INTO reserves VALUES(22,	103,	'Wednesday');
INSERT INTO reserves VALUES(31,	102,	'Thursday');
INSERT INTO reserves VALUES(31,	103,	'Friday');
INSERT INTO reserves VALUES(31, 104,	'Saturday');
INSERT INTO reserves VALUES(64,	101,	'Sunday');
INSERT INTO reserves VALUES(64,	102,	'Monday');
INSERT INTO reserves VALUES(74,	102,	'Saturday');

SELECT * FROM sailor;
SELECT * FROM boat;
SELECT * FROM reserves;



/* Write SQL statements for the following queries:


Problem 3.a. Find the rating of each sailor.

*/

SELECT DISTINCT S.rating
FROM   Sailor S;


/*
 rating 
--------
      1
      3
      7
      8
      9
     10
(6 rows)
*/



/*
Problem 3.b  Find the bid and color of each boat.
*/
SELECT B.bid, B.color
FROM   Boat B;


/*
 bid | color 
-----+-------
 101 | blue
 102 | red
 103 | green
 104 | red
(4 rows)
*/


/*
Problem 3.c  Find the name of each sailor whose age is in the range [15,30].
*/

SELECT S.sname
FROM   Sailor S
WHERE  15 <= S.age AND S.age <= 30;

/*
 sname 
-------
 Andy
 Art
 Zorba
(3 rows)
*/

/*
Problem 3.d Find the name of each boat that was reserved during a weekend (i.e., Saturday or Sunday).
*/


SELECT B.bname
FROM   Boat B, Reserves R
WHERE  B.bid = R.bid AND 
       (R.day = 'Saturday' OR R.day = 'Sunday');


/*
   bname   
-----------
 Interlake
 Sunset
 Marine
(3 rows)
*/

/*
Problem 3.e Find the name of each sailor who reserved both a red boat and a green boat.
*/


SELECT   S.sname
FROM     Sailor S
WHERE    S.sid IN (
            (SELECT  S.sid
             FROM    Sailor S, Reserves R, Boat B
             WHERE   S.sid = R.sid AND
                     R.bid = B.bid AND
                     B.color = 'red')
             INTERSECT
            (SELECT  S.sid
             FROM    Sailor S, Reserves R, Boat B
             WHERE   S.sid = R.sid AND
                     R.bid = B.bid AND
                     B.color = 'green'));

/*
 sname  
--------
 Dustin
 Lubber
(2 rows)
*/


/*
Problem 3.f Find the name of each sailor who reserved a red boat but neither a green nor a blue boat.
*/


SELECT   S.sname
FROM     Sailor S
WHERE    S.sid IN (
            (SELECT  S.sid
             FROM    Sailor S, Reserves R, Boat B
             WHERE   S.sid = R.sid AND
                     R.bid = B.bid AND
                     B.color = 'red')
             EXCEPT
            (SELECT  S.sid
             FROM    Sailor S, Reserves R, Boat B
             WHERE   S.sid = R.sid AND
                     R.bid = B.bid AND
                     B.color = 'green'
             UNION
             SELECT  S.Sid
             FROM    Sailor S, Reserves R, Boat B
             WHERE   S.sid = R.sid AND
                     R.bid = B.bid AND
                     B.color = 'blue'));

/*
  sname  
---------
 Horatio
(1 row)
*/


/*
Problem 3.g Find the name of each sailor who reserved two different boats.
*/

SELECT S.Sname
FROM   Sailor S
WHERE  S.sid IN
           (SELECT  R1.sid
            FROM    Reserves R1, Reserves R2
            WHERE   R1.sid = R2.sid AND
                    R1.bid <> R2.bid);


/*
  sname  
---------
 Dustin
 Lubber
 Horatio
(3 rows)
*/


/*
Problem 3.h Find the sid of each sailor who did not reserve any boats.
*/


SELECT S.sid
FROM   Sailor S
EXCEPT
SELECT R.sid
FROM   Reserves R;

/*
 sid 
-----
  71
  85
  32
  95
  29
  58
(6 rows)
*/

/*
Problem 3.i Find the pairs of sids $(s_1,s_2)$ of different sailors who both reserved a boat on a Saturday.
*/

SELECT  S1.sid AS s1, S2.sid AS s2
FROM    Sailor S1, Sailor S2
WHERE   S1.sid <> S2.sid AND
        S1.sid IN (SELECT R.sid
                   FROM   Reserves R
                   WHERE  R.day = 'Saturday') AND
        S2.sid IN (SELECT R.sid
                   FROM   Reserves R
                   WHERE  R.day = 'Saturday');

/*
 s1 | s2 
----+----
 31 | 74
 74 | 31
(2 rows)
*/




/*
Problem 3.j  Find the bids of boats that where reserved by only one sailor. 
*/

SELECT  R.bid
FROM    Reserves R
EXCEPT
SELECT  R1.bid
FROM    Reserves R1, Reserves R2
WHERE   R1.bid = R2.bid AND
        R1.sid <> R2.sid;



