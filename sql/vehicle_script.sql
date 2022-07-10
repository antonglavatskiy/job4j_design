create table vehicle_type (
    id serial primary key,
    name varchar(255)
);
create table vehicle (
    id serial primary key,
    name varchar(255),
    date_of_birth date,
    vehicle_type_id int references vehicle_type(id)
);

insert into vehicle_type (name) values ('car');
insert into vehicle_type (name) values ('truck');
insert into vehicle_type (name) values ('tractor');

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('Moskvich 412', '1975.05.05', 1);

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('LADA Samara', '1990.06.12', 1);

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('KAMAZ 65115', '2005.01.07', 2);

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('ZIL 157', '1978.01.02', 2);

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('K-700', '1984.08.09', 3);

insert into vehicle (name, date_of_birth, vehicle_type_id) 
values ('T-40', '1979.02.01', 3);

insert into vehicle (name, date_of_birth) 
values ('Bicycle', '2000.03.06');

select * from vehicle as v 
join vehicle_type as vt 
on v.vehicle_type_id = vt.id;

select v.name, vt.name 
from vehicle as v 
join vehicle_type as vt 
on v.vehicle_type_id = vt.id;

select v.name as "Транспортное средство", 
vt.name as "Тип ТС", 
v.date_of_birth as "Дата выпуска" 
from vehicle v 
join vehicle_type vt 
on v.vehicle_type_id = vt.id;

select v.name ТС, vt.name Тип, 
v.date_of_birth as "Дата выпуска" 
from vehicle v 
join vehicle_type vt 
on v.vehicle_type_id = vt.id;