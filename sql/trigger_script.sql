create table products(
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count int default 0,
    price int
);

insert into products(name, producer, count, price)
values ('milk', 'MilkFactory', 15, 75);
insert into products(name, producer, count, price)
values ('cheese', 'MilkFactory', 7, 235);
insert into products(name, producer, count, price)
values ('apple', 'SweetGarden', 37, 170);

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

-- 1. Триггер насчитывает налог
create or replace function tax_up()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- после вставки statement уровень
create trigger tax_up_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax_up();

-- 2. Триггер вычитает налог
create or replace function tax_down()
    returns trigger as
$$
    BEGIN
        update products
        set price = price - price * 0.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

-- перед вставкой row уровень
create trigger discount_trigger
    before insert
    on products
    for each row
    execute procedure tax_down();

-- 3. Триггер history_of_price
create or replace function insert_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values (new.name, new.price, now());
        return new;
    END;
$$
LANGUAGE 'plpgsql';


create trigger insert_history
    before update on products
    for each row
    execute procedure insert_history();




