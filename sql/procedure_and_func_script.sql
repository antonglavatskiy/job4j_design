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

-- Процедура заполнения данными
create or replace procedure insert_data
(ins_name varchar, ins_producer varchar, 
ins_count int, ins_price int)
    language 'plpgsql'
    as $$
        begin
        insert into products(name, producer, count, price) 
        values (ins_name, ins_producer, ins_count, ins_price);
        end
$$;

-- Процедура обновления данных
create or replace procedure update_data
(upd_count int, tax float, upd_id int)
    language 'plpgsql'
    as $$
    begin
        if upd_count > 0 then
			update products set count = count - upd_count
			where id = upd_id;
        end if;
        if tax > 0 then
			update products set price = price + price * tax;
        end if;
    end;
$$;

--Хранимая функция добавления записи
create or replace function f_insert_data
(ins_name varchar, ins_producer varchar, ins_count int, ins_price int)
returns void
language 'plpgsql'
as $$
    begin
        insert into products (name, producer, count, price)
        values (ins_name, ins_producer, ins_count, ins_price);
    end;
$$;

--Хранимая функция обновления записи
create or replace function f_update_data(upd_count int, tax float, upd_id int)
returns int
language 'plpgsql'
as $$
    declare
        result int;
    begin
        if upd_count > 0 then
            update products set count = count - upd_count
            where id = upd_id;
            select into result count 
            from products where id = upd_id;
        end if;
        if tax > 0 then
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

--Процедура удаления записей по производителю и количеству меньше 10 
create or replace procedure delete_data(del_producer varchar)
    language 'plpgsql'
    as $$
        begin
        delete from products 
        where producer = del_producer and count < 10;
        end
$$;

--Функция удаления записей по имени и цене больше 1000
create or replace function f_delete_data(del_name varchar, del_price int)
returns void
language 'plpgsql'
as $$
    begin
        delete from products where name like del_name;
        if del_price >= 1000 then
            delete from products where price >= del_price;
        end if;
    end;
$$;
