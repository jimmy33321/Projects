/* Problem 1 

Find the sid and major of each student who bought a book that cost
 less than \$20.

*/


select 'Problem 1';


select m.sid, m.major
from   major m
where  m.sid in (select t.sid
                 from   buys t, book b
                 where  t.bookno = b.bookno and b.price < 20) order by sid, major;



/* Problem 2

Find the bookno and title of each book that cost between \$20 and \$40
and that is cited by another book.

*/


select 'Problem 2';


select b.bookno, b.title
from   book b
where  20 <= b.price and b.price <= 40 and
       b.bookno in (select c.citedbookno
                    from   cites c) order by bookno, title;
                 

/* Problem 3

Find the sid and name of each student who majors in `CS' and who
bought a book that is cited by a lower priced book.

*/

select 'Problem 3';

select s.sid, s.sname
from   student s
where  s.sid in (select m.sid from major m where m.major = 'CS') AND
       exists (select 1
               from   buys t, cites c, book b1, book b2
               where  s.sid = t.sid and t.bookno = c.citedbookno and
                      c.citedbookno = b1.bookno and c.bookno = b2.bookno and
                      b1.price > b2.price) order by sid;


/* Problem 4

Find the bookno and title of each cited book that is itself cited by
another book.

Find the bookno and title of each book that is cited by a book that 
itself is also a cited book.

*/

select 'Problem 4';

select b.bookno, b.title
from   book b
where  b.bookno in (select c2.citedbookno
                    from   cites c1, cites c2
                    where  c1.citedbookno = c2.bookno) order by bookno, title;


/* Problem 5

Use the SQL {\tt ALL} predicate to find the booknos of the cheapest
books.

*/

select 'Problem 5';

select b.bookno
from   book b
where  b.price <= ALL (select b1.price
                       from   book b1) order by bookno, title;


/*  Problem 6

Without using the SQL {\tt ALL} or {\tt SOME} predicates, find the
booknos and titles of the most expensive books.

*/

select 'Problem 6';

select b.bookno, b.title
from   book b
where  not exists (select 1
                   from   book b1
                   where  b1.price > b.price) order by bookno, title;


/*  Problem 7

Find the booknos and titles of the second most expensive books.

*/

select 'Problem 7';


with not_most_expensive_prices as 
   ((select b1.price 
    from   book b1)
   except 
   (select b2.price 
    from   book b2 
    where  b2.price >= all(select b.price 
                           from book b)))
select b.bookno, b.title
from   book b
where  b.price in (select b.price
                   from   not_most_expensive_prices b
                   where  b.price >= ALL (select price 
                                          from not_most_expensive_prices)) order by bookno, title;




/* Problem 8

Find the bookno and price of each book which, if it is cited by
another book, cites a book that cost more than \$20.  (Note that each
book which is not cited also satisfies the condition of the query and
must therefore also be included in the answer.)

*/

select 'Problem 8';

with CitedBooks as
(  select  b.bookno, b.title, b.price
   from    book b
   where   b.bookno in (select c.citedbookno
                        from   cites c))

(select b.bookno, b.price
 from   book b
 where  b.bookno not in (select b1.bookno
                         from   citedbooks b1))
union
(select b.bookno, b.price
 from   Citedbooks b, cites c, book b1
 where  b.bookno = c.bookno and
        c.citedbookno = b1.bookno and
        b1.price > 20) order by bookno, price;

/* Problem 9

Find the bookno and title of each book that is bought by a student
who majors in `Biology' or in `Psychology'.

*/


select 'Problem 9';

select b.bookno, b.title
from   book b
where  exists (select 1
               from   buys t, major m
               where  t.bookno = b.bookno and 
                      t.sid = m.sid and
                      (m.major = 'Biology' or m.major = 'Psychology')) order by bookno, title;;

/* Problem 10 


Find the bookno and title of each book that is not bought by all
students who major in `CS'.


*/


select 'Problem 10';

select b.bookno, b.title
from   book b
where  exists (select m.sid
               from   major m
               where  m.major = 'CS'
               EXCEPT
               select t.sid
               from   buys t
               where  t.bookno = b.bookno) order by bookno, title;

/* Alternative solution 
*/

select b.bookno, b.title
from   book b
where  exists (select m.sid
               from   major m
               where  m.major = 'CS' and
                      m.sid not in(select t.sid
                                   from   buys t
                                   where  t.bookno = b.bookno)) order by bookno, title;






/* Problem 11


Find the bookno of each book that was only bought by students who
major in `Biology'.

*/

select 'Problem 11';

select b.bookno
from   book b
where  not exists (select t.sid
                   from   buys t 
                   where  t.bookno = b.bookno 
                   except
                   select m.sid
                   from   major m
                   where  m.major = 'Biology') order by bookno;

/* Alternative solution
*/



select b.bookno
from   book b
where  not exists (select t.sid
                   from   buys t 
                   where  t.bookno = b.bookno and
                          t.sid not in (select m.sid
                                        from   major m
                                        where  m.major = 'Biology')) order by bookno;

/* Problem 12

Find the bookno and title of each book that is bought by all students
who major in both `CS' and in `Math'.

*/

select 'Problem 12';

select b.bookno, b.title
from   book b
where not exists ((select s.sid
                   from   student s, major m
                   where  s.sid = m.sid and m.major = 'CS'
                   INTERSECT
                   select s.sid
                   from   student s, major m
                   where  s.sid = m.sid and m.major = 'Math')
                    EXCEPT
                    select t.sid
                    from   buys t
                    where  t.bookno = b.bookno) order by bookno, title;

/* Alternative solution */

select b.bookno, b.title
from   book b
where  not exists (select s.sid
                   from   student s
                   where  s.sid in (select m.sid from major m where m.major = 'CS') and
                          s.sid in (select m.sid from major m where m.major = 'Math') and
                          s.sid not in (select t.sid
                                        from   buys t
                                        where  t.bookno = b.bookno)) order by bookno, title;;

/* Problem 13

Find the sid and name of each student who 
not only bought books that were bought by at least two `CS' students.



*/


select 'Problem 13';

select s.sid, s.sname
from   student s
where  exists (select t.bookno
               from   buys t
               where  t.sid = s.sid and
                      t.bookno not in (select t1.bookno
                                       from   buys t1, buys t2
                                       where  t1.bookno = t2.bookno and
                                              t1.sid <> t2.sid and
                                              t1.sid in (select m.sid
                                                         from   major m
                                                         where  m.major = 'CS') and
                                              t2.sid in (select m.sid
                                                         from   major m
                                                         where  m.major = 'CS'))) order by sid, sname;

/* Problem 14

Find the sid and name of each student who bought at most one book that
cost more than \$20.

*/


select 'Problem 14';

select s.sid, s.sname
from   student s
where  s.sid not in (select distinct t1.sid
                     from   buys t1, buys t2
                     where  t1.sid = t2.sid and t1.bookno <> t2.bookno and
                            t1.bookno in (select b.bookno from book b where b.price > 20) and
                            t2.bookno in (select b.bookno from book b where b.price > 20)) order by sid, sname;



/* Problem 15

Find each $(s,b)$ pair where $s$ is the sid of a student and where $b$
is the bookno of a book whose price is the cheapest among the books
bought by that student.

*/


select 'Problem 15';

select distinct t.sid, b.bookno
from   buys t, book b
where  t.bookno = b.bookno and
       b.price <= ALL (select b1.price
                       from   buys t1, book b1
                       where t1.bookno = b1.bookno and t1.sid = t.sid) order by sid, bookno;

/* Alternative solution for Problem 15*/

select distinct t.sid, b.bookno
from   buys t, book b
where  t.bookno = b.bookno and
       not exists (select 1 
                   from book b1, buys t1 
                   where t1.bookno = b1.bookno and t1.sid = t.sid and b1.price < b.price) order by sid, bookno;;


