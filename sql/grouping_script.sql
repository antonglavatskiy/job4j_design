create table devices (
    id serial primary key,
    name varchar(255),
    price float
);

create table people (
    id serial primary key,
    name varchar(255)
);

create table devices_people (
    id serial primary key,
    people_id int references people(id),
    device_id int references devices(id)
);

insert into devices (name, price)values ('laptop', 75340);
insert into devices (name, price)values ('smartphone', 48350);
insert into devices (name, price)values ('book reader', 13250);
insert into devices (name, price)values ('smartwatch', 32450);
insert into devices (name, price) values ('headphone', 9750);

insert into people (name) values ('Александр Лебедев');
insert into people (name) values ('Ярослав Шаповалов');
insert into people (name) values ('Валерия Чернова');
insert into people (name) values ('Ярослав Андреев');
insert into people (name) values ('Роберт Федотов');
insert into people (name) values ('Амина Власова');
insert into people (name) values ('Константин Васильев');
insert into people (name) values ('Алексей Дроздов');
insert into people (name) values ('София Овсянникова');
insert into people (name) values ('Михаил Левин');

insert into devices_people (people_id, device_id) values (1, 2);
insert into devices_people (people_id, device_id) values (1, 4);
insert into devices_people (people_id, device_id) values (2, 1);
insert into devices_people (people_id, device_id) values (2, 2);
insert into devices_people (people_id, device_id) values (2, 5);
insert into devices_people (people_id, device_id) values (3, 1);
insert into devices_people (people_id, device_id) values (3, 2);
insert into devices_people (people_id, device_id) values (3, 4);
insert into devices_people (people_id, device_id) values (4, 1);
insert into devices_people (people_id, device_id) values (4, 2);
insert into devices_people (people_id, device_id) values (4, 3);
insert into devices_people (people_id, device_id) values (4, 4);
insert into devices_people (people_id, device_id) values (5, 2);
insert into devices_people (people_id, device_id) values (5, 4);
insert into devices_people (people_id, device_id) values (6, 1);
insert into devices_people (people_id, device_id) values (6, 2);
insert into devices_people (people_id, device_id) values (7, 2);
insert into devices_people (people_id, device_id) values (7, 3);
insert into devices_people (people_id, device_id) values (7, 5);
insert into devices_people (people_id, device_id) values (8, 2);
insert into devices_people (people_id, device_id) values (8, 4);
insert into devices_people (people_id, device_id) values (8, 5);
insert into devices_people (people_id, device_id) values (9, 1);
insert into devices_people (people_id, device_id) values (9, 2);
insert into devices_people (people_id, device_id) values (9, 3);
insert into devices_people (people_id, device_id) values (9, 4);
insert into devices_people (people_id, device_id) values (9, 5);
insert into devices_people (people_id, device_id) values (10, 2);
insert into devices_people (people_id, device_id) values (10, 3);
insert into devices_people (people_id, device_id) values (10, 4);

select avg (price) as "Средняя цена" from devices;

select people.name, avg (devices.price)
from people
join devices_people
on people.id = devices_people.people_id 
join devices 
on devices_people.device_id = devices.id 
group by people.name;

select people.name, avg (devices.price)
from people
join devices_people
on people.id = devices_people.people_id 
join devices 
on devices_people.device_id = devices.id 
group by people.name
having avg (devices.price) > 5000;

