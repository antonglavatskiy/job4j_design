create table car_bodies (
    id serial primary key,
    name varchar(255)
);
create table car_engines (
    id serial primary key,
    name varchar(255)
);
create table car_transmissions (
    id serial primary key,
    name varchar(255)
);
create table cars (
    id serial primary key,
    name varchar(255),
    body_id int references car_bodies(id), 
    engine_id int references car_engines (id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies (name) values ('sedan');
insert into car_bodies (name) values ('coupe');
insert into car_bodies (name) values ('hatchback');
insert into car_bodies (name) values ('cabriolet');
insert into car_bodies (name) values ('wagon');

insert into car_engines (name) values ('I4 SOHC');
insert into car_engines (name) values ('I4 DOHC');
insert into car_engines (name) values ('I6 SOHC');
insert into car_engines (name) values ('I6 DOHC');
insert into car_engines (name) values ('V6 DOHC');
insert into car_engines (name) values ('V8 DOHC');
insert into car_engines (name) values ('V12 Supercharger');

insert into car_transmissions (name) values ('5MT');
insert into car_transmissions (name) values ('6MT');
insert into car_transmissions (name) values ('4AMT');
insert into car_transmissions (name) values ('5AMT');
insert into car_transmissions (name) values ('6AMT');
insert into car_transmissions (name) values ('5CVT');
insert into car_transmissions (name) values ('6CVT');

insert into cars (name, body_id, engine_id, transmission_id)
values ('Lada Granta', 1, 1, 1);

insert into cars (name, body_id, engine_id, transmission_id)
values ('BMW 325i', 1, 4, 4);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Mercedes-Benz C 200 ', 2, 2, 5);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Toyota Camry', 1, 5, 4);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Toyota Corolla', 1, 2, 6);

insert into cars (name, body_id, engine_id, transmission_id)
values ('VW Golf', 3, 2, 4);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Lada Samara', 3, null, 1);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Dodge', 2, 7, null);

insert into cars (name, body_id, engine_id, transmission_id)
values ('Fiat 124', 1, null, null);

select cars.id, cars.name Машина, 
car_bodies.name Кузов, 
car_engines.name Двигатель, 
car_transmissions.name КПП
from cars
left join car_bodies
on car_bodies.id = cars.body_id
left join car_engines
on car_engines.id = cars.engine_id
left join car_transmissions
on car_transmissions.id = cars.transmission_id;

select car_bodies.name
from car_bodies
left join cars
on car_bodies.id = cars.body_id
where cars.body_id is null;

select car_engines.name
from car_engines
left join cars
on car_engines.id = cars.engine_id
where cars.engine_id is null;

select car_transmissions.name
from car_transmissions
left join cars
on car_transmissions.id = cars.transmission_id
where cars.transmission_id is null;
