create table region(
    id serial primary key,
    name varchar(255));
create table supervisor(
    id serial primary key,
    name varchar(255));
create table region_supervisor(
    id serial primary key,
    region_id int references region(id) unique,
    supervisor_id int references supervisor(id) unique);

insert into region(name) values ('Московская область');
insert into region(name) values ('Ленинградская область');
insert into supervisor (name) values ('Иванов');
insert into supervisor (name) values ('Николаев');
insert into region_supervisor (region_id, supervisor_id) values (1, 2);
insert into region_supervisor (region_id, supervisor_id) values (2, 1);
