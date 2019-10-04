-- Problem 1  Supersets of a set X
create or replace function make_singleton(x int) returns int[] AS
$$
   select array[x];
$$ language sql;

create or replace function make_union(S int[], T int[]) returns int[] as
$$
   select array( select UNNEST(S) union select UNNEST(T) order by 1);
$$ language sql;

create or replace function new_sets()
returns table (set int[]) AS
$$
  (select   make_union(S, make_singleton(x))
   from     Powerset S, A x)
  except
  (select   S
   from     Powerset);
$$ language sql;

create or replace function Powerset()
returns void as 
$$
begin
   drop table if exists Powerset;
   create table Powerset(S int[]);

   insert into Powerset select array[]::int[];

   while exists(select * from new_sets()) 
   loop
        insert into Powerset select * from new_sets();
   end loop;
end;
$$ language plpgsql;

create or replace function superSetsOfSet(X int[]) 
returns table(superset int[]) as
$$
   select S from Powerset where X <@ S;
$$ language sql;


--  Problem 2  Even and odd paths

create or replace function new_Odd_pairs()
returns table (source integer, target integer) AS
$$
(select   p.source, edge.target
 from     Even p, Graph edge
 where    p.target = edge.source)
except
(select   source, target
 from     Odd p);
$$ LANGUAGE SQL;


create or replace function new_Even_pairs()
returns table (source integer, target integer) AS
$$
(select   p.source, edge.target
 from     Odd p, Graph edge
 where    p.target = edge.source)
except
(select   source, target
 from     Even p);
$$ LANGUAGE SQL;


create or replace function EvenAndOddPairs()
returns void as
$$
begin
   drop table if exists Even;
   drop table if exists Odd;

   create table Even(source integer, target integer);
   create table Odd(source integer, target integer);

   insert into Even
       select e.source as source, e.source as target from Graph e
       union
       select e.target as source, e.target as target from Graph e;

   while exists(select * from new_Odd_pairs())
   loop
        insert into Odd  select * from new_Odd_pairs();
        insert into Even select * from new_Even_pairs();
   end loop;
end;
$$ language plpgsql;

create or replace function connectedByEvenLengthPath() 
returns table (source integer, target integer) as
$$
  select source, target from even order by 1,2;
$$ language sql;


create or replace function connectedByOddLengthPath() 
returns table (source integer, target integer) as
$$
  select source, target from odd order by 1,2;
$$ language sql;


-- Problem 3   Topological sort of a acyclic graph

create table temp_graph(source integer, target integer);

create table vertices(n integer);

create table ordering(index integer, vertex integer);

create or replace function degreeZeroVertex() returns integer as
$$
   select e.source 
   from   temp_graph e 
   where not exists (select 1 
                     from   temp_graph e1 where e1.target = e.source);
$$ language sql;


create or replace function degreeZeroVertex() returns integer as
$$
   select n
   from   vertices 
   where not exists (select 1 
                     from   temp_graph e where e.target = n);
$$ language sql;

create or replace function topologicalSort() 
returns table(index integer, node integer) as
$$
declare i integer;
        v integer;
begin
   delete from ordering;
   
   delete from temp_graph;
   insert into temp_graph select * from graph;

   insert into vertices (select e.source
                         from   graph e 
                         union  
                         select e.target
                         from   graph e);
                

    i := 0;
    while exists (select 1 from vertices)
    loop
      i := i+1;
      select * into v from (select degreeZeroVertex()) q;
      insert into ordering values(i, v);
      delete from temp_graph where source = v;   
      delete from vertices where n = v;   
    end loop;
    
return query (select * from ordering order by 1);
end;
$$ language plpgsql;


create or replace function topologicalSort() 
returns table(index integer, node integer) as
$$
declare i integer;
        v integer;
begin
   delete from ordering;
   
   delete from temp_graph;
   insert into temp_graph select * from graph;

   insert into vertices (select e.source
                         from   graph e 
                         union  
                         select e.target
                         from   graph e);
                

    i := 0;
    while exists (select 1 from temp_graph)
    loop
      i := i+1;
      select * into v from (select degreeZeroVertex()) q;
      insert into ordering values(i, v);
      delete from temp_graph where source = v;   
    end loop;
    
    delete from vertices where n in (select vertex from ordering);
 
    while exists (select 1 from vertices)
    loop
      i := i+1;
      select * into v from (select n from vertices) q;
      insert into ordering values(i, v);
      delete from vertices where n = v;   
    end loop;


return query (select * from ordering order by 1);
end;
$$ language plpgsql;

-- Problem 4  Frequent itemsets

create table document (doc text,  words text[]);

create table word(w text);

insert into word
 select distinct unnest(d.words)
 from   document d order by 1;

drop table if exists Candidates;
create table Candidates(C text[]);

drop table if exists FrequentSets;
create table FrequentSets(F text[]);

create or replace function make_union_words(S text[], T text[]) returns text[] as
$$
   select array( select UNNEST(S) union select UNNEST(T) order by 1);
$$ language sql;

create or replace function difference(S text[], T text[]) returns text[] as
$$
   select array( select UNNEST(S) except select UNNEST(T) order by 1);
$$ language sql;


create or replace function isCandidate(C text[]) returns boolean as
$$
select not exists(select  1
                  from    UNNEST(C) w
                  where   difference(C,array[w]) not in (select F from FrequentSets)) 
$$ language sql;

create or replace function new_Candidates()
returns table (C text[]) AS
$$
  select   make_union_words(F, array[w])
  from     Frequentsets F, word 
  where    not (array[w] <@ F) 
           and isCandidate(make_union_words(F, array[w]))
  except
  (select C from candidates);
$$ language sql;


create or replace function isFrequent(X text[], t integer) 
returns boolean as
$$
   select (select count(1)
           from   document d
           where  X <@ d.words) >= t;
$$ language sql;


create or replace function FrequentSets(t integer)
returns void as 
$$
begin
   drop table if exists Candidates;
   create table Candidates(C text[]);

   drop table if exists Frequentsets;
   create table Frequentsets(F text[]);

   insert into Candidates select array[]::text[];
   insert into frequentsets select array[]::text[];

   while exists(select 1 from new_Candidates())
   loop
      insert into Candidates select * from new_Candidates() where C not in (select * from candidates);
      insert into frequentsets select * from candidates where isfrequent(C,t) and C not in (select * from frequentsets);
   end loop;

   delete from frequentsets where F = '{}' and not(isFrequent('{}',t));

end;
$$ language plpgsql;

-- Problem 5  Bill of materials problem
CREATE TABLE partSubPart(pid INTEGER, sid INTEGER, quantity INTEGER);

CREATE TABLE basicPart(pid INTEGER, weight INTEGER);

-- The solution is based on the following rules
-- The ps_triples relation provides for a part pid
-- the sid of each subpart along with the number of
-- times that subpart occurs in part pid

-- ps_triples(p,s,q) :- partsubpart(p,s,q)
-- ps_triples(p,s,q*q1) :- anc(p,s1,q1), partsubpart(s1,s,q)

create or replace function new_ps_triples() 
returns table (pid integer, sid integer, quantity integer) as
$$
  select  t.pid, ps.sid, t.quantity*ps.quantity
  from    ps_triples t, partSubpart ps
  where   t.sid = ps.pid
  except  
  select  * from ps_triples;
$$ language sql;


-- the function ps_triples computes for
-- each part each of its basic subpart along
-- with the number of times that that subpart
-- occurs

create or replace function ps_triples() 
returns table (pid integer, sid integer, quantity integer) as
$$
begin
   drop table ps_triples;
   create table ps_triples(pid integer, sid integer, quantity integer);

   insert into ps_triples select * from partSubPart;

   while exists( select * from new_ps_triples())
   loop
     insert into ps_triples select * from new_ps_triples();
   end loop;

   return query (select * from ps_triples ps 
                 where  ps.sid in (select p.pid from basicPart p) order by 1,2);
end;
$$ language plpgsql;

-- the function aggregatedWeight return for each part (including basic parts),
-- the aggregated cost of that part

create or replace function aggregatedWeight(p integer) returns bigint as
$$
   select  sum(q.quantity*bp.weight)
   from    (select * from ps_triples() part where part.pid=p) q, basicPart bp
   where   q.sid = bp.pid
   group   by (q.pid)
   union   
   select weight
   from   basicpart
   where  pid=p;
$$ language sql;



-- Problem 6  Dijkstra's algorithm (Solution by Catherine Metcalf (Former B561 student))


-- Create the table that will be used to implement Dijkstra's algorithm
CREATE TABLE weightedGraph (Source INTEGER, Target INTEGER, Weight INTEGER);
-- This table tracks the distance between a source node and each other node in
-- the graph
CREATE TABLE Paths (Node INTEGER, Dist INTEGER);
-- Populate the weightedGraph
INSERT INTO weightedGraph VALUES
  (0, 1, 2),
  (1, 0, 2),
  (0, 4, 10),
  (4, 0, 10),
  (1, 3, 3),
  (3, 1, 3),
  (1, 4, 7),
  (4, 1, 7),
  (2, 3, 4),
  (3, 2, 4),
  (3, 4, 5),
  (4, 3, 5),
  (4, 2, 6),
  (2, 4, 6);
 

-- This function returns a table representing those that are children of nodes
-- in the distance table, but have not yet had their distances from the start
-- node computed
CREATE FUNCTION Stop_Condition () RETURNS TABLE (Val INTEGER) AS
$$
  SELECT 1
  FROM weightedGraph g, Paths p
  WHERE g.Source = p.Node AND
        NOT EXISTS (SELECT 1
                    FROM Paths p_one
                    WHERE g.Target = p_one.Node AND
                          p_one.Dist <= (p.Dist+g.Weight));
$$ LANGUAGE SQL;

SELECT * FROM weightedGraph;

-- This function executes Dijkstra's algorithm on the weightedGraph table defined above
CREATE FUNCTION Dijkstra (start INTEGER) RETURNS TABLE (Target INTEGER, Distance INTEGER) AS
$$
  BEGIN
  -- Initially populate the paths table with the distance between those points
  -- with a direct path from the given starting point
  INSERT INTO Paths (SELECT g.Target, g.Weight
                     FROM weightedGraph g
                     WHERE g.Source = start);
  INSERT INTO Paths VALUES (0, 0);
  -- Keep adding nodes to the table of paths and distances until all nodes that
  -- can be reached from the sources have been added
  WHILE (EXISTS(SELECT * FROM Stop_Condition())) LOOP
    -- Add nodes that are immediate neighors of the nodes that have already
    -- been added to the path's table
    INSERT INTO Paths (SELECT g.Target AS Node, p.dist+g.Weight AS Dist
                       FROM weightedGraph g, Paths p
                       WHERE g.Source = p.Node AND
                             NOT EXISTS (SELECT 1
                                         FROM Paths p_one
                                         WHERE g.Target = p_one.Node AND
                                               p_one.Dist <= (p.Dist+g.Weight)));
  END LOOP;
  RETURN QUERY (SELECT p.Node AS Target, p.dist AS Distance
                FROM Paths p
                WHERE NOT EXISTS (SELECT 1
                                  FROM Paths p_two
                                  WHERE p.Node = p_two.Node AND
                                        p.Dist > p_two.Dist));
  END;
$$ LANGUAGE PLPGSQL;

SELECT * FROM Dijkstra(0);

-- The computations are done, so drop the tables
DROP TABLE IF EXISTS Paths;
DROP TABLE IF EXISTS weightedGraph;

-- The computations are done, so from the functions
DROP FUNCTION IF EXISTS Dijkstra (INTEGER);
DROP FUNCTION IF EXISTS Stop_Condition ();

