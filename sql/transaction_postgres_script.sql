\c transaction_db;
create table accounts(
    name varchar(50),
    count float,
    created timestamp
);
alter table accounts add column id serial primary key;

begin;
set transaction isolation level repeatable read;

insert into accounts (name, count, created) values ('Vasiliev', 100.50, now());
insert into accounts (name, count, created) values ('Ivanov', 500.70, now());
insert into accounts (name, count, created) values ('Sergeev', 1300.00, now());
insert into accounts (name, count, created) values ('Andreev', 1200.55, now());
insert into accounts (name, count, created) values ('Petrov', 50.25, now());

savepoint insert_acc;
select * from accounts;

/*
   name   |  count  |          created           | id 
----------+---------+----------------------------+----
 Vasiliev |   100.5 | 2022-08-30 18:47:26.768233 |  1
 Ivanov   |   500.7 | 2022-08-30 18:47:26.768233 |  2
 Sergeev  |    1300 | 2022-08-30 18:47:26.768233 |  3
 Andreev  | 1200.55 | 2022-08-30 18:47:26.768233 |  4
 Petrov   |   50.25 | 2022-08-30 18:47:26.768233 |  5
(5 rows)
*/

update accounts set count = count + 100 where name = 'Vasiliev';
update accounts set count = count - 100 where name = 'Sergeev';

savepoint trans_serg_to_vas;
select * from accounts;

/*
   name   |  count  |          created           | id 
----------+---------+----------------------------+----
 Ivanov   |   500.7 | 2022-08-30 18:47:26.768233 |  2
 Andreev  | 1200.55 | 2022-08-30 18:47:26.768233 |  4
 Petrov   |   50.25 | 2022-08-30 18:47:26.768233 |  5
 Vasiliev |   200.5 | 2022-08-30 18:47:26.768233 |  1
 Sergeev  |    1200 | 2022-08-30 18:47:26.768233 |  3
(5 rows)
*/

insert into accounts (name, count, created) values ('Listieva', 100.50, now());
insert into accounts (name, count, created) values ('Medvedeva', 500.70, now());

select count(*) from accounts;

/*
 count 
-------
     7
(1 row)
*/

rollback to savepoint trans_serg_to_vas;
select count(*) from accounts;

/*
 count 
-------
     5
(1 row)
*/

select name, count from accounts where id = 3;

/*
  name   | count 
---------+-------
 Sergeev |  1200
(1 row)
*/

rollback to savepoint insert_acc;
select name, count from accounts where id = 3;

/*
  name   | count 
---------+-------
 Sergeev |  1300
(1 row)
*/

update accounts set count = count + 505.30 where name = 'Petrov';
commit;

select * from accounts;

begin;
insert into accounts (name, count, created) values ('Listieva', 12.50, now());
insert into accounts (name, count, created) values ('Medvedeva', 37.70, now());
select * from accounts;

/*
   name    |  count  |          created           | id 
-----------+---------+----------------------------+----
 Vasiliev  |   100.5 | 2022-08-30 19:45:30.145668 |  1
 Ivanov    |   500.7 | 2022-08-30 19:45:30.145668 |  2
 Sergeev   |    1300 | 2022-08-30 19:45:30.145668 |  3
 Andreev   | 1200.55 | 2022-08-30 19:45:30.145668 |  4
 Petrov    |  555.55 | 2022-08-30 19:45:30.145668 |  5
 Listieva  |    12.5 | 2022-08-30 19:56:37.684435 |  8
 Medvedeva |    37.7 | 2022-08-30 19:56:37.684435 |  9
(7 rows)
*/
rollback;

select * from accounts;

/*
   name   |  count  |          created           | id 
----------+---------+----------------------------+----
 Vasiliev |   100.5 | 2022-08-30 19:45:30.145668 |  1
 Ivanov   |   500.7 | 2022-08-30 19:45:30.145668 |  2
 Sergeev  |    1300 | 2022-08-30 19:45:30.145668 |  3
 Andreev  | 1200.55 | 2022-08-30 19:45:30.145668 |  4
 Petrov   |  555.55 | 2022-08-30 19:45:30.145668 |  5
(5 rows)
*/
