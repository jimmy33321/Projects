\qecho -- Solutions 2018 Assignment 7

\qecho Problem  1
create or replace function memberof(x anyelement, S anyarray) returns boolean as
$$
select x = SOME(S)
$$ language sql;

create or replace function setunion(A anyarray, B anyarray) returns anyarray as
$$
select array(select UNNEST(A) union select UNNEST(B) order by 1);
$$ language sql;

\qecho Problem  1.a

create or replace function setunion(A anyarray, B anyarray) returns anyarray as
$$
select array(select UNNEST(A) intersect select UNNEST(B) order by 1);
$$ language sql;

select setintersection ('{1,2,3,3}'::int[],'{1,1,3,5,6}'::int[]);  


\qecho Problem  1.b

create or replace function setdifference(A anyarray, B anyarray) returns anyarray as
$$
select array(select UNNEST(A) except select UNNEST(B) order by 1);
$$ language sql;

select setdifference ('{1,2,3,3}'::int[],'{1,1,3,5,6}'::int[]);  


\qecho Problem  2

create or replace view student_books as
   select distinct s.sid, array(select t.bookno 
                                from   buys t
                                where  t.sid = s.sid order by bookno) as books
   from   student s order by sid;

select * from student_books;

\qecho Problem  2.a

create or replace view book_students as
   select b.bookno, array(select t.sid
                          from   buys t 
                          where  t.bookno = b.bookno order by sid) as students
   from   book b order by bookno;

select * from book_students;

\qecho Problem  2.b 

create or replace view book_citedbooks as
   select b.bookno, array(select c.citedbookno
                          from   cites c
                          where  c.bookno = b.bookno order by citedbookno) as citedbooks
   from   book b order by bookno;

select * from book_citedbooks;

\qecho Problem  2.c

create or replace view book_citingbooks as
   select b.bookno as bookno, array(select c.bookno
                                    from   cites c
                                    where  c.citedbookno = b.bookno order by bookno) as citingbooks
   from   book b order by bookno;

select * from book_citingbooks;

\qecho Problem  2.d 

create or replace view major_students as
    select distinct m.major, array(select m1.sid 
                                   from major m1 
                                   where m1.major = m.major) as students
    from   major m order by major;

select * from major_students;

\qecho Problem 2.e 

create or replace view student_majors as
    select s.sid, array(select m.major from major m where m.sid = s.sid) as majors
    from   student s order by sid;

select * from student_majors;


\qecho Problem 3.a
--  Find the sid of each student who bought precisely 2 books.

select sid
from   student_books
where  cardinality(books) = 2;

\qecho Problem 3.b
-- Find the sid of each student who bought all the books 
-- bought by the student with sid 1001.

select  sid
from    student_books s, 
        (select books
         from   student_books
         where  sid = 1001) s_1001
where   s_1001.books <@ s.books;

\qecho  Problem 3.c
--  Find the bookno of each book that cites fewer than 2 books 
--  that each cost more than $30

select bookno
from   book
where  bookno in (select bookno
                  from   book_citedbooks bc,
                         (select array(select bookno
                                       from   book
                                       where  price > 30) as above_thirty) q
                  where  cardinality(setintersection(bc.citedbooks,q.above_thirty)) < 2)
order by bookno;



\qecho  Problem 3.d
--  Find the bookno and title of each book that was not only bought 
--  by students who majors in `CS' and in `Math'.

create or replace function studentsInMajor(m text) returns int[] as
$$
select students 
from   major_students 
where  major = m;
$$ language sql;


select  bookno, title
from    book b
where   bookno in (select bs.bookno
                   from   book_students bs
                   where  not(bs.students <@ setintersection(studentsInMajor('CS'),studentsInMajor('Math'))))
order by bookno, title;

\qecho Problem 3.e
-- Find the sid-bookno pairs $(s,b)$ pairs such student $s$ bought book 
-- $b$ and such that book $b$ is cited by at least two books that cost less 
-- than $50.

select unnest(students) as sid, bookno
from   book_students bs
where  (select count(1)
        from   (select  bookno from book b where price < 50) b
                inner join book_citedbooks bc on (b.bookno = bc.bookno)
        where  memberof(bs.bookno, bc.citedbooks)) >= 2
order by sid, bookno;


\qecho Problem 3.f
-- Find the tuple $(students)$ where students is the set of students
-- who major in `CS' and in `Math'.

select setintersection(studentsInMajor('CS'),studentsInMajor('Math')) as students;

\qecho Problem 3.g
-- Find each pair (s, majors) where $s$ is the sid of a student who
-- bought none of the books bought by student with sid 1001 and where
-- majors is the set of majors of the student $s$.

create or replace function booksBoughtByStudent(s int)
returns int[] as
$$
select books
from   student_books
where  sid = s;
$$ language sql;

select sid, majors
from   student_majors
where  not(booksBoughtByStudent(sid) && booksBoughtByStudent(1001))
order by sid;


\qecho Problem 3.h 
-- Find the tuple (books) where books is the set of books bought by œôòøCSœôòù students.

select array(select distinct bookno
             from   (select sb.sid, UNNEST(sb.books) as	bookno
      	             from   student_books sb
      		     where  memberof(sb.sid, studentsInMajor('CS'))) t order by bookno) as books;




\qecho Problem 3.i
-- Find the tuple (students) where students is the set of students who
-- bought books that cites at least two books.

select array(select  distinct sb.sid
             from    (select bookno
                      from   book_citedbooks
                      where  cardinality(citedbooks) >= 2) b,
                      student_books sb
             where  memberof(b.bookno, sb.books) order by sid);


\qecho Problem 3.j 
-- Find the pairs (b, students) where b is a bookno and students is the
-- set CS students who bought that book.

select  b.bookno, array( select distinct t.sid
                         from   (select sb.sid, UNNEST(sb.books) as bookno
                                 from   student_books sb
                                 where  memberof(sb.sid, studentsInMajor('CS'))) t
                         where  t.bookno = b.bookno order by sid)
from    book b order by bookno;

