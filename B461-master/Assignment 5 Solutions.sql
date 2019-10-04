-- Problem 1

select distinct s.sid,s.sname, b.bookno, b.title
from   student s
       cross join book b
       inner join buys t on ((s.sname = 'Eric' or s.sname = 'Anna') and
                              s.sid = t.sid and 
                              b.price > 20 and 
                              t.bookno = b.bookno);


--> Push selections over joins

select distinct s.sid,s.sname, b.bookno, b.title
from   (select s.sid, s.sname from student s where (s.sname = 'Eric' or s.sname = 'Anna')) s
       cross join (select b.* from book b where  b.price > 20) b
       inner join buys t on (s.sid = t.sid and t.bookno = b.bookno);

--> rearanging joins and combining cross join with selection so that
--  it becomes an inner join

select distinct s.sid,s.sname, b.bookno, b.title
from   (select s.sid, s.sname from student s where (s.sname = 'Eric' or s.sname = 'Anna')) s
       inner join buys t on (s.sid = t.sid)
       inner join (select b.* from book b where  b.price > 20) b on (t.bookno = b.bookno);

--> introducing natural joins where appropriate

select distinct s.sid,s.sname, b.bookno, b.title
from   (select s.sid, s.sname from student s where (s.sname = 'Eric' or s.sname = 'Anna')) s
       natural join buys t
       natural join (select b.* from book b where  b.price > 20) b;


--> pushing down projections over joins which gets rid off b.price

select distinct s.sid,s.sname, b.bookno, b.title
from   (select s.sid, s.sname from student s where (s.sname = 'Eric' or s.sname = 'Anna')) s
       natural join buys t
       natural join (select b.bookno, b.title from book b where  b.price > 20) b;


-- Problem 2

select distinct s.sid
from   student s
       cross join book b
       inner join buys t on ((s.sname = 'Eric' or s.sname = 'Anna') and
                              s.sid = t.sid and 
                              b.price > 20 and 
                              t.bookno = b.bookno);

--> same optimization as before 

select distinct s.sid
from   (select s.sid, s.sname from student s where (s.sname = 'Eric' or s.sname = 'Anna')) s
       natural join buys t
       natural join (select b.bookno, b.title from book b where  b.price > 20) b;

--> pushing projection down since now we only need s.sid

select distinct q.sid
from   (select s.sid from student s where sname = 'Eric' or s.sname = 'Anna') s
        natural join (select sid 
                      from   buys t 
                             natural join (select b.bookno 
                                           from book b 
                                           where  b.price > 20) b) q;

-->  introducing intersection

(select sid 
from  student 
where sname = 'Eric' or sname = 'Anna')
intersect
(select sid 
from   buys t 
       natural join (select b.bookno 
                     from   book b 
                     where  price > 20) q);


-- Problem 3

select distinct s.sid, b1.price as b1_price, b2.price as b2_price
from   (select s.sid from student s where s.sname <> 'Eric') s
        cross join book b2 
        inner join book b1 on (b1.bookno <> b2.bookno and b1.price > 60 and b2.price >= 50)
        inner join buys t1 on (t1.bookno = b1.bookno and t1.sid = s.sid)
        inner join buys t2 on (t2.bookno = b2.bookno and t2.sid = s.sid);

--> pushing down selections and removing book titles (application of functional constraint)

select distinct s.sid, b1.price as b1_price, b2.price as b2_price
from   (select s.sid from student s where s.sname <> 'Eric') s
        cross join (select b2.bookno, b2.price 
                    from book b2 where b2.price >= 50) b2
        inner join (select b1.bookno, b1.price 
                    from book b1 where b1.price > 60) b1 on (b1.bookno <> b2.bookno)
        inner join buys t1 on (t1.bookno = b1.bookno and t1.sid = s.sid)
        inner join buys t2 on (t2.bookno = b2.bookno and t2.sid = s.sid);

--> pushing down more projections

with  E as 
(select s.sid, b1.bookno as b1bno, b1.price as b1price, b2.bookno as b2bno, b2.price as b2price
 from (select s.sid from student s where s.sname <> 'Eric') s
       cross join (select b2.bookno, b2.price from book b2 where b2.price >= 50) b2 
       inner join (select b1.bookno, b1.price from book b1 where b1.price > 60) b1 
                on (b1.bookno <> b2.bookno)),

      F as (select  e.sid, e.b1price, e.b2bno, e.b2price
            from    E e
                    inner join buys t1 on (t1.bookno = e.b1bno and t1.sid = e.sid))

select distinct f.sid, f.b2price as b2_price, f.b1price as b1_price
from   F f
       inner join buys t2 on (t2.bookno = f.b2bno and t2.sid = f.sid);

--> introducing natural joins


with  E as 
(select s.sid, b1.bookno as bookno, b1.price as b1price, b2.bookno as b2bno, b2.price as b2price
 from (select sid from student s where s.sname <> 'Eric') s
       cross join (select b2.bookno, b2.price from book b2 where b2.price >= 50) b2 
       inner join (select b1.bookno, b1.price from book b1 where b1.price > 60) b1 
                on (b1.bookno <> b2.bookno)),

      F as (select  e.sid, e.b1price, e.b2bno as bookno, e.b2price
            from    E e
                    natural join buys t)

select distinct f.sid, f.b2price as b2_price, f.b1price as b1_price
from   F f
       natural join buys t;



-- Problem 4

select q.sid
from   (select s.sid, s.sname
        from   student s
        except
        select s.sid, s.sname
        from   student s
               inner join buys t on (s.sid = t.sid)
               inner join book b on (t.bookno = b.bookno and b.price > 50)) q;

-->  since sid is a key for student, \pi_sid distributes over except

select s.sid
from   student s
except
select s.sid
from   student s
       inner join buys t on (s.sid = t.sid)
       inner join book b on (t.bookno = b.bookno and b.price > 50);


--> since sid is a foreign key referencing student, we can remove the first inner join

select s.sid
from   student s
except
select t.sid
from   buys t 
       inner join book b on (t.bookno = b.bookno and b.price > 50);

--> pushing donw the b.price condition and using the fact that bookno is primary key

select s.sid
from   student s
except
select t.sid
from   buys t 
       inner join (select b.bookno from book b where b.price > 50) b on (t.bookno = b.bookno);

-- introduce natural join

select s.sid
from   student s
except
select t.sid
from   buys t 
       natural join (select b.bookno from book b where b.price > 50) b;





-- Problem 5

select distinct q.sid, q.sname
from   (select s.sid, s.sname, 2007 as bookno
        from   student s 
               cross join book b
        intersect
        select s.sid, s.sname, b.bookno
        from   student s 
               cross join book b
               inner join buys t on (s.sid = t.sid and t.bookno = b.bookno and b.price <25)) q;

-->  We first deal with the 2007 as bookno constant (this works since bookno is a primary key for book)
-->  we can also push the condition b.price < 25 into the book query 

select distinct q.sid, q.sname
from   (select s.sid, s.sname, b.bookno
        from   student s
               cross join (select bookno from book where bookno = 2007) b
        intersect
        select s.sid, s.sname, b.bookno 
        from   student s
               cross join (select bookno from book where (bookno = 2007 and price < 25)) b
               inner join buys t on (s.sid = t.sid and t.bookno = b.bookno)) q;

--> we can turn a cross join into an inner join 


select distinct q.sid, q.sname
from   (select s.sid, s.sname, b.bookno
        from   student s
               cross join (select bookno from book where bookno = 2007) b
        intersect
        select s.sid, s.sname, b.bookno 
        from   student s
               inner join buys t on (s.sid=t.sid)
               inner join (select bookno from book where (bookno = 2007 and price < 25)) b on (t.bookno = b.bookno)) q;

-->  we can push the book=2007 over the intersection and apply it to the buys
--> relation

select distinct q.sid, q.sname
from   (select s.sid, s.sname, b.bookno
        from   student s
               cross join (select bookno from book where bookno = 2007) b
        intersect
        select s.sid, s.sname, b.bookno 
        from   student s
               inner join buys t on (s.sid=t.sid)
               inner join (select bookno from book where (bookno = 2007 and price < 25)) b on (t.bookno = b.bookno)) q;





-- introduce natural joins

select distinct q.sid, q.sname
from   (select sid, sname, bookno
        from   student s
               cross join (select bookno from book where bookno = 2007) b
        intersect
        select sid, sname, bookno 
        from   student s
               natural join (select sid, bookno from buys where bookno = 2007) t
               natural join (select bookno from book where (bookno = 2007 and price < 25)) b) q;

-->  When we compary the inner select sid, sname, bookno query, we realize
-->  that it produce a subset of the out select sid, sname, book no query.
-->  There, since we apply an intersection between these queries, we get that 
-->  can eliminate the outer query, so we get

select distinct q.sid, q.sname
from   (select s.sid, s.sname, b.bookno 
        from   student s
               inner join buys t on (s.sid=t.sid)
               inner join (select bookno from book where (bookno = 2007 and price < 25)) b on (t.bookno = b.bookno)) q;

-->  We now have a project q.sid, q.sname followong an select s.sid, s.name, b.bookno
--> projection, so we can further optimize to


select s.sid, s.sname
from   student s
       inner join (select t.sid, t.bookno from buys t where bookno = 2007) t on (s.sid=t.sid)
       inner join (select bookno from book where (bookno = 2007 and price < 25)) b 
           on (t.bookno = b.bookno);

--> we can introduce natural joins

select s.sid, s.sname
from   student s
       natural join (select t.sid, t.bookno from buys t where bookno = 2007) t
       natural join (select bookno from book where (bookno = 2007 and price < 25)) b;

--> we then observe apply associate of natural join

select s.sid, s.sname
from   student s
       natural join (select t.sid, t.bookno 
                     from   select t.sid buys t where bookno = 2007) t
                                    natural join (select bookno 
                                                  from book 
                                                  where (bookno = 2007 and price < 25)));

select s.sid, s.sname
from   student s
       natural join (select t.sid, t.bookno
                     from   (select t.sid, t.bookno
                             from   buys t where bookno = 2007) t
                                    natural join (select bookno
                                                  from book
                                                  where (bookno = 2007 and price < 25)) b) t;

-->  we then observe that we really only need a semi-join

select s.sid, s.sname
from   student s
       natural join (select t.sid
                     from   (select t.sid from buys t where bookno = 2007) t
                             natural join (select bookno
                                           from   book
                                           where  (bookno = 2007 and price < 25)) b) t;




-- Problem 6

select distinct q.bookno
from   (select s.sid, s.sname, b.bookno, b.title
        from   student s
      	       cross join book b
        except
        select s.sid, s.sname, b.bookno, b.title
        from   student s
      	       cross join book b
               inner join buys t on (s.sid = t.sid and t.bookno = b.bookno and b.price <20)) q;


--> we can eliminate the title column and push the b.price condition to book
--> and we can replace the cross join with a inner join
--> we can also project out title and price 

select distinct q.bookno
from   (select s.sid, s.sname, b.bookno
        from   student s
      	       cross join (select bookno from book) b
        except
        select s.sid, s.sname, b.bookno
        from   student s
               inner join buys t on (s.sid = t.sid)
      	       inner join (select bookno from book b where price < 20) b on t.bookno = b.bookno) q;

--> introduce natural joins

select distinct q.bookno
from   (select s.sid, s.sname, b.bookno
        from   student s
      	       cross join (select bookno from book) b
        except
        select s.sid, s.sname, b.bookno
        from   student s
               natural join buys t
      	       natural join (select bookno from book b where price < 20) b) q;



