-- Problem 1
select A.x AS x, 
       sqrt(A.x) AS square_root_x, 
       power(A.x,2) AS x_squared, 
       power(2,A.x) AS two_to_the_power_x, 
       (A.x)! AS x_factorial, 
       ln(A.x) AS logarithm_x from A A;



/-- Problem 2
select 
    not exists(select * from A except select * from B) as empty_A_minus_B, 
    exists(select * from A except select * from B
           union
           select * from B except select * from A) as not_empty_symmetric_difference,
    not exists(select * from A intersect select * from B) as empty_A_intersection_B;

/* Problem 3
*/

select p1.x as x1, p1.y as y1, p2.x as x2, p2.y as y2
from Pair p1, Pair p2
where p1.x+p1.y = p2.x + p2.y and not(p1.x = p2.x and p2.y = p2.y);

-- Problem 4.a
select exists((select a.x from A a) INTERSECT (select b.x from B b)) as answer;


select exists(select a.x from A a
              where  a.x in (select b.x from B b)) as answer;

-- Problem 4.b
select not exists((select a.x from A a) EXCEPT (select b.x from B b)) as answer;

select not exists(select a.x from A a 
                  where  a.x not in (select b.x from B b)) as answer;

-- Problem 4.c
select not exists((select b.x from B b) EXCEPT (select a.x from A a)) as answer;


select not exists(select b.x from B b
                  where  b.x not in (select a.x from A a)) as answer;


-- Problem 4.d
select  exists((select a.x from A a EXCEPT select b.x from B b)
               union
               (select b.x from B b EXCEPT select a.x from A a)) as value;


select  exists(select a.x from A a where a.x not in (select b.x from B b)) or
        exists(select b.x from B b where b.x not in (select a.x from A a)) as value;

-- Problem 4.e		
with A_intersection_B as (select * from a intersect select * from b)
select not exists(select 1
                  from   A_intersection_B a1, A_intersection_B a2
                  where  a1.x <> a2.x) as answer;


with A_intersection_B as (select a.x from a where a.x in (select b.x from b))
select not exists(select 1
                  from   A_intersection_B a1, A_intersection_B a2
                  where  a1.x <> a2.x) as answer;

-- Problem 4.f
with A_union_B as (select * from a union select * from b)
select not exists((select e.x from A_union_B e) EXCEPT (select c.x from C c)) as answer;                  

with A_union_B as (select * from a union select * from b)
select not exists(select e.x from A_union_B e where e.x not in (select c.x from C c)) as answer;                  

-- Problem 4.g

with A_minus_B_union_B_minus_C as ((select * from a except select * from B) union
                                          (select * from b except select * from C))
select exists(select 1 from A_minus_B_union_B_minus_C) and
       not exists (select 1 from A_minus_B_union_B_minus_C e1, 
                                 A_minus_B_union_B_minus_C e2
                   where   e1.x <> e2.x) as answer;

with A_minus_B_union_B_minus_C as (select a.x from a where a.x not in (select b.x from b) union
                                          select b.x from b where b.x not in (select c.x from C))
select exists(select 1 from A_minus_B_union_B_minus_C) and
       not exists (select 1 from A_minus_B_union_B_minus_C e1, 
                                 A_minus_B_union_B_minus_C e2
                   where   e1.x <> e2.x) as answer;


-- Problem 5.a
select (select count(1) from
         ((select a.x from A a) INTERSECT (select b.x from B b)) q) >= 1 as answer;

-- Problem 5.b

select (select count(1) from
        ((select a.x from A a) EXCEPT (select b.x from B b)) q) = 0 as answer;

-- Problem 5.c

select (select count(1) from
         ((select b.x from B b) EXCEPT (select a.x from A a)) q) = 0 as answer;


-- Problem 5.d
select (select count(1) from
           ((select a.x from A a EXCEPT select b.x from B b)
             union
            (select b.x from B b EXCEPT select a.x from A a)) q) >= 1 as value;


-- Problem 5.e
with A_intersection_B as (select * from a intersect select * from b)
select (select count(1) from A_intersection_B) < 2 as value;

-- Problem 5.f
with A_union_B as (select * from a union select * from b)
select (select count(1) from
         ((select e.x from A_union_B e) EXCEPT (select c.x from C c)) q) = 0 as answer;                  

-- Problem 5.g
with A_minus_B_union_B_minus_C as ((select * from a except select * from B) union
                                          (select * from b except select * from C))
select (select count(1) from A_minus_B_union_B_minus_C) = 1 as value;


-- Problem 6.a.i
/* 
Write a function {\tt booksBoughtbyStudent(sid int, out bookno int,
out title VARCHAR(30), out price integer)} that takes a student sid as
input and returns the book information of books bought by that
student.
*/

create or replace function booksBoughtbyStudent(student integer) 
     returns table(bookno integer, title varchar(30), price integer) as
$$
select b.bookno, b.title, b.price
from   buys t, book b
where  t.sid = student and t.bookno = b.bookno
order by bookno;
$$ language sql;

-- Problem 6.a.ii

select * from booksBoughtbyStudent(1001);

select * from booksBoughtbyStudent(1015);

-- Problem 6.a.iii.A

select s.sid, s.sname
from   student s
where  (select count(1)
        from   (select * from booksBoughtbyStudent(s.sid)
                intersect
                select * from book where price < 50) q) = 1;

-- Problem 6.a.iii.B

select s1.sid as s1, s2.sid as s1
from   student s1, student s2
where  (select count(1)
        from   (select * from booksBoughtbyStudent(s1.sid)
                except
                select * from booksBoughtbyStudent(s2.sid)) q) = 0 and
       (select count(1)
        from   (select * from booksBoughtbyStudent(s2.sid)
                except
                select * from booksBoughtbyStudent(s1.sid)) q) = 0 and
        s1.sid <> s2.sid;


-- Problem 6.b.i
create or replace function studentsWhoBoughtBook(book integer) 
     returns table(sid integer, sname varchar(15)) as
$$
select s.sid, s.sname 
from   buys t, student s
where  t.bookno = book and t.sid = s.sid
order by sid;
$$ language sql;

-- Problem 6.b.ii

select * from studentsWhoBoughtBook(2001);

select * from studentsWhoBoughtBook(2010);

-- Problem 6.b.iii

  (select sid, sname from major m where m.major = 'CS'
   intersect
   select sid, sname from studentsWhoBoughtBook(b.bookno)) q,
   buys t, book b
   where 


create view CS_studentsWhoBoughtBookAbove30 as
select s.sid, s.sname
from   student s
where  s.sid in (select m.sid
                 from   major m, buys t, book b
                 where  m.major = 'CS' and
                        m.sid = t.sid and t.bookno = b.bookno and
                        b.price > 30);



select b.bookno
from   book b
where  (select count(1)
        from   (select * from CS_studentsWhoBoughtBookAbove30
                intersect
                select * from studentsWhoBoughtBook(b.bookno)) q) >= 2;



--Problem 6.c.i

/* Find the sid and major of each student who
bought at least 4 books that cost more than \$30
*/

create or replace function books_that_cost_more_than(price integer) 
     returns table(bookno integer, title varchar(30), price integer) as
$$
select b.bookno, b.title, b.price
from   book b
where  b.price > books_that_cost_more_than.price
order by bookno;
$$ language sql;

select m.sid, m.major
from   major m
where  (select count(1)
        from   (select * from booksBoughtbyStudent(m.sid)
                intersect 
                select * from books_that_cost_more_than(30)) b) >= 4;

/*
sid  |  major  
------+---------
 1002 | CS
 1002 | Math
 1004 | CS
 1006 | CS
 1007 | CS
 1007 | Physics
 1009 | Biology
 1010 | Biology
(8 rows)
*/


--Problem 6.c.ii
/*  Find the pairs $(s_1,s_2)$ of different students who spent the same
amount of money on the books they bought.
*/

select s1.sid as s1, s2.sid as s2
from   student s1, student s2
where  (select sum(price)
        from   booksBoughtbyStudent(s1.sid)) =
       (select sum(price)
        from   booksBoughtbyStudent(s2.sid)) and
       s1.sid <> s2.sid;

--Problem 6.c.iii
/*
Find the sid and name of each student who spent more money on
the books he or she bought than the average cost that was spent on books by
students who major in `CS'.
*/


create or replace function cost_spent_on_books_by(student integer)
       returns bigint as
$$
select sum(price)
from   booksBoughtbyStudent(student);
$$ language SQL;

select avg(cost_spent_on_books_by(m.sid))
from   major m
where  m.major = 'CS';

select s.sid, s.sname
from   student s
where  cost_spent_on_books_by(s.sid) >
       (select avg(cost_spent_on_books_by(m.sid))
        from   major m
        where  m.major = 'CS');

/*
 sid  |   sname   
------+-----------
 1002 | Maria
 1003 | Anna
 1004 | Chin
 1006 | Ryan
 1007 | Catherine
 1009 | Jan
 1010 | Linda
 1017 | Ellen
(8 rows)
*/


--Problem 6.c.iv       
/*
Find the booknos and titles of the third most expensive books.
*/

select q.bookno, q.title
from   (select b.bookno, b.title, (select count(1)
                                   from   book  b1
                                   where  b1.price > b.price) + 1 as rank
        from   book b) q
where q.rank = 3;

/*
 bookno |    title     
--------+--------------
   2008 | DataScience
   2011 | Anthropology
(2 rows)
*/

--Problem 6.c.v

/* Find the bookno and title of each book that is only bought by students 
who major in `CS'.  (Make sure to use the COUNT aggregate function instead of
the EXISTS predicate.)
*/

/*
select b.bookno, b.title
from   book b
where  not exists (select t.sid
                   from   buys t
                   where  t.bookno = b.bookno
                   except
                   select m.sid
                   from   major m
                   where  m.major = 'CS');
*/


select b.bookno, b.title
from   book b
where  (select count (1)
          from (select sid
                from   studentsWhoBoughtBook(b.bookno)
                except
                select m.sid
                from   major m
                where  m.major = 'CS') q) = 0;

/*
 bookno |        title        
--------+---------------------
   2004 | AI
   2005 | DiscreteMathematics
   2006 | SQL
   2010 | Philosophy
(4 rows)
*/


--Problem 6.c.vi

/* Find the sid and name of each student who not only bought books that
were bought by at least two 'CS' students.
(Make sure to use the COUNT aggregate function instead of
the EXISTS predicate.)
*/

select s.sid, s.sname 
from   student s 
where (select count(1) 
       from (select b.bookno from 
             booksBoughtbyStudent(s.sid) b 
             except 
             select t1.bookno
             from buys t1, buys t2, major m1, major m2
             where m1.sid <> m2.sid and m1.major = 'CS' and m2.major = 'CS' and 
                   t1.sid = m1.sid and t2.sid = m2.sid and t1.bookno = t2.bookno) q) >= 1;



select s.sid, s.sname
from   student s
where  (select count(1)
        from   (select b.bookno
                from   booksBoughtbyStudent(s.sid) b
                except
                select bookno
                from   book b
                where  (select count(1)
                        from   studentsWhoBoughtBook(b.bookno) s, major m
                        where  s.sid = m.sid and m.major = 'CS') >= 2) q) >= 1;

 sid  |   sname   
------+-----------
 1001 | Jean
 1007 | Catherine
 1010 | Linda
 1017 | Ellen
 1022 | Qin
 1023 | Melanie
(6 rows)


--Problem 6.c.vii

/* Find each $(s,b)$ pair where $s$ is the sid of a student and where $b$
is the bookno of a book bought that student whose price is strictly below the average
price of the books bought by that student.
*/

select s.sid, b.bookno
from   student s, lateral booksBoughtbyStudent(s.sid) b
where  b.price < (select avg(price)
                  from   booksBoughtbyStudent(s.sid));

/* The answer has 44 rows */


--Problem 6.c.viii
/* Find each pair $(s_1,s_2)$ where $s_1$ and $s_2$ are the sids of
different students who have a common major and who bought the same
number of books.
*/

select m1.sid as s1, m2.sid as s2
from   major m1, major m2
where  m1.sid <> m2.sid and m1.major = m2.major and
       (select count(1)
        from   booksBoughtbyStudent(m1.sid)) =
       (select count(1)
        from   booksBoughtbyStudent(m2.sid));

/*
  s1  |  s2  
------+------
 1001 | 1003
 1002 | 1006
 1002 | 1004
 1003 | 1001
 1004 | 1006
 1004 | 1002
 1006 | 1004
 1006 | 1002
 1011 | 1013
 1013 | 1011
(10 rows)
*/

--Problem 6.c.ix
/* Find the triples $(s_1,s_2,n)$ where $s_1$ and $s_2$ are the sids of
students who share a major and where $n$ is the number of books that was bought by
student $s_1$ but not by student $s_2$.
*/

select m1.sid as s1, m2.sid as s2, (select count(1)
                                    from   (select b.bookno
                                            from   booksBoughtbyStudent(m1.sid) b
                                            except 
                                            select b.bookno
                                            from   booksBoughtbyStudent(m2.sid) b) q) as n
from   major m1, major m2
where  m1.major = m2.major and
       m1.sid <> m2.sid order by s1, s2, n;

/* number of triples is 76
*/


--Problem 6.c.x
/* Find the bookno of each book that was bought buy all-but-one student who majors in 'CS'.
*/

select b.bookno
from   book b
where  (select count (1)
        from   (select m.sid
                from   major m
                where  m.major = 'CS'
                except
                select s.sid
                from   studentsWhoBoughtBook(b.bookno) s) q) = 1;
/*
 bookno 
--------
   2012
   2011
(2 rows)
*/
