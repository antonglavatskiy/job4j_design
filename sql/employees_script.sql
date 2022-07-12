--1
create table departments (
    id serial primary key,
    name varchar(255)
);
create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);
insert into departments (name) values ('IT');
insert into departments (name) values ('HR');
insert into departments (name) values ('SALES');
insert into departments (name) values ('MARKETING');
insert into departments (name) values ('MANAGEMENT');
insert into departments (name) values ('OTHER');

insert into employees (name, department_id) values ('Даниил Беляев', 1);
insert into employees (name, department_id) values ('Захар Яковлев', 1);
insert into employees (name, department_id) values ('Злата Васильева', 2);
insert into employees (name, department_id) values ('Макар Рыжов', 3);
insert into employees (name, department_id) values ('Александр Новиков', 1);
insert into employees (name, department_id) values ('Михаил Кузнецов', 1);
insert into employees (name, department_id) values ('Алиса Воробьева', 2);
insert into employees (name, department_id) values ('Марк Филатов', 3);
insert into employees (name, department_id) values ('Сергей Селезнев', 3);
insert into employees (name, department_id) values ('Арина Пименова', 3);
insert into employees (name, department_id) values ('Вера Козлова', 4);
insert into employees (name, department_id) values ('Полина Кравцова', 4);
insert into employees (name, department_id) values ('Владислав Носов', 4);
insert into employees (name, department_id) values ('Адам Никольский', 5);
insert into employees (name, department_id) values ('Давид Крюков', 5);
insert into employees (name) values ('Милана Савельева');
insert into employees (name) values ('Дарина Ефимова');
insert into employees (name) values ('Марк Вавилов');

--2
select * from employees
left join departments
on employees.department_id = departments.id;

select * from employees
right join departments
on employees.department_id = departments.id;

select * from employees
full join departments
on employees.department_id = departments.id;

select * from employees
cross join departments;

--3
select departments.name Отдел, 
count(employees.department_id) as "Количество работников" 
from departments
left join employees
on departments.id = employees.department_id
group by departments.name
having count(employees.department_id) = 0;


--4
select * from employees
left join departments
on employees.department_id = departments.id;

select employees.id, employees.name,
employees.department_id, departments.id,
departments.name
from departments 
right join employees 
on employees.department_id = departments.id;
--
select * from departments
left join employees
on employees.department_id = departments.id;

select departments.id, departments.name,
employees.id, employees.name,
employees.department_id
from employees
right join departments
on employees.department_id = departments.id;

--5
create table teens(
    id serial primary key,
    name varchar(255),
    sex boolean
);
insert into teens (name, sex) values ('Дмитрий', true);
insert into teens (name, sex) values ('Иван', true);
insert into teens (name, sex) values ('Михаил', true);
insert into teens (name, sex) values ('Василиса', false);
insert into teens (name, sex) values ('Виктория', false);
insert into teens (name, sex) values ('Валерия', false);
insert into teens (name, sex) values ('Екатерина', false);
insert into teens (name, sex) values ('Мария', false);

select t1.name as Boy, t2.name as Girl
from teens t1 cross join teens t2
where t1.sex = true and (t1.sex != t2.sex) = true;