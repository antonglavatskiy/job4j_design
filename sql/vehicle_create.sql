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