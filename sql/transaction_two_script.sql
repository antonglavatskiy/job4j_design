\c transaction_db;

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

--после изменений таблицы в первой транзакции

select * from students;

/*
 id |  name  |  city  | age 
----+--------+--------+-----
  1 | Andrey | Moscow |  18
  2 | Anna   | Tula   |  19
  3 | Ivan   | Kazan  |  21
(3 rows)
*/

-- после коммита первой транзакции

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

delete from students where id = 1;

--lock
-- после коммита первой транзакции не удалось сериализовать доступ 
-- из-за одновременного удаления
-- could not serialize access due to concurrent delete

commit;
-- rollback транзакция не исполнилась

--SERIALIZABLE

begin transaction isolation level serializable;
select avg(age) from students;
update students set age = 22 where id = 3;
commit;
