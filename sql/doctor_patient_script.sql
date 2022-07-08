create table doctor(
    id serial primary key,
    name varchar(255),
    speciality varchar(255));
create table patient(
    id serial primary key,
    name varchar(255),
    age int);
create table doctors_patients(
    id serial primary key,
    doctor_id int references doctor(id),
    patient_id int references patient(id));

insert into doctor (name, speciality) values ('Иван Иванович', 'Окулист');
insert into doctor (name, speciality) values ('Николай Николаевич', 'Отоларинголог');
insert into doctor (name, speciality) values ('Михаил Михайлович', 'Психиатр');
insert into patient (name, age) values ('Сергей', 23);
insert into patient (name, age) values ('Виктор', 27);
insert into patient (name, age) values ('Юрий', 21);
insert into doctors_patients (doctor_id, patient_id) values (1, 1);
insert into doctors_patients (doctor_id, patient_id) values (1, 3);
insert into doctors_patients (doctor_id, patient_id) values (2, 2);
insert into doctors_patients (doctor_id, patient_id) values (3, 2);
insert into doctors_patients (doctor_id, patient_id) values (3, 3);
