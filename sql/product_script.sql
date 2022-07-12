create table type (
    id serial primary key,
    name varchar(255)
);
create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into type (name) values ('ХЛЕБ');
insert into type (name) values ('КРУПЫ');
insert into type (name) values ('СЫР');
insert into type (name) values ('МОЛОКО');
insert into type (name) values ('ОВОЩИ');

insert into product (name, type_id, expired_date, price)
values ('Булочка', 1, '2022.07.10', 23.50);

insert into product (name, type_id, expired_date, price)
values ('Пирожок', 1, '2022.07.09', 25.50);

insert into product (name, type_id, expired_date, price)
values ('Батон', 1, '2022.07.17', 34.50);

insert into product (name, type_id, expired_date, price)
values ('Гречка', 2, '2023.09.23', 99.99);

insert into product (name, type_id, expired_date, price)
values ('Рис', 2, '2023.08.15', 154.39);

insert into product (name, type_id, expired_date, price)
values ('Сыр плавленный', 3, '2022.07.13', 120.35);

insert into product (name, type_id, expired_date, price)
values ('Сыр моцарелла', 3, '2022.07.15', 499.89);

insert into product (name, type_id, expired_date, price)
values ('Молоко', 4, '2022.07.10', 54.45);

insert into product (name, type_id, expired_date, price)
values ('Молоко топленое', 4, '2022.07.17', 65.56);

insert into product (name, type_id, expired_date, price)
values ('Огурцы', 5, '2022.08.15', 215.50);

insert into product (name, type_id, expired_date, price)
values ('Помидоры', 5, '2022.07.27', 156.99);

insert into product (name, type_id, expired_date, price)
values ('Перец', 5, '2022.06.27', 78.99);

select product.name Наименование , type.name Тип
from product
join type
on product.type_id = type.id
where type.name like 'СЫР';

select name Наименование
from product 
where product.name like '%мороженое%';

select name Наименование, expired_date as "Срок годности"
from product 
where expired_date < NOW();

select max(price) as "Максимальная цена"
from product;

select type.name Тип, count(*) Количество
from type
join product
on type.id = product.type_id
group by type.name;

select product.name Наименование
from product
join type
on product.type_id = type.id
where type.name like 'СЫР' or type.name like 'МОЛОКО';

select type.name Тип, count(*) Количество
from type
join product
on type.id = product.type_id
group by type.name
having count(*) < 10;

select type.name Тип, product.name Наименование, 
product.expired_date as "Срок годности", product.price Цена 
from product
join type
on product.type_id = type.id;