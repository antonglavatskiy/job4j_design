create table mountain_range(
    id serial primary key,
    name varchar(255));
create table mountain(
    id serial primary key,
    name varchar(255),
    height int,
    range_id int references mountain_range(id));

insert into mountain_range (name) values ('Кавказские горы');
insert into mountain_range (name) values ('Тянь-Шань');
insert into mountain (name, height, range_id) values ('Эльбрус', 5642, 1);
insert into mountain (name, height, range_id) values ('Казбек', 5033, 1);
insert into mountain (name, height, range_id) values ('Джигит', 5170, 2);
insert into mountain (name, height, range_id) values ('Пик Победы', 7439, 2);

select * from mountain;