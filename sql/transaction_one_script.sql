create database transaction_db;
\c transaction_db;

create table students (
    id serial primary key,
    name varchar(50),
    city varchar(50),
    age int
);

insert into students(name, city, age) values ('Andrey', 'Moscow', 18);
insert into students(name, city, age) values ('Anna', 'Tula', 19);
insert into students(name, city, age) values ('Ivan', 'Kazan', 21);

--READ COMMITTED

begin;
select * from students;

/*
 id |  name  |  city  | age 
----+--------+--------+-----
  1 | Andrey | Moscow |  18
  2 | Anna   | Tula   |  19
  3 | Ivan   | Kazan  |  21
(3 rows)
*/

insert into students(name, city, age) values ('Sveta', 'Moscow', 18);
update students set name = 'Andrey Petrov' where name = 'Andrey';

select * from students;

/*
 id |     name      |  city  | age 
----+---------------+--------+-----
  2 | Anna          | Tula   |  19
  3 | Ivan          | Kazan  |  21
  4 | Sveta         | Moscow |  18
  1 | Andrey Petrov | Moscow |  18
(4 rows)
*/

commit;

--REPEATABLE READ

begin transaction isolation level repeatable read;

insert into students(name, city, age) values ('Roman', 'Samara', 17);
delete from students where id = 1;

commit;

--SERIALIZABLE

begin transaction isolation level serializable;
select avg(age) from students;
update students set age = 100 where id = 6;
commit;

/*
не удалось сериализовать доступ из-за зависимостей чтения/записи между транзакциями

ERROR:  could not serialize access due to read/write dependencies among transactions
DETAIL:  Reason code: Canceled on identification as a pivot, during commit attempt.
HINT:  The transaction might succeed if retried.
*/
