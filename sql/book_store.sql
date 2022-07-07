create table book(
    id serial primary key,
    title varchar(255),
    author varchar(255),
    quantity int,
    price money
);
insert into book (title, author, quantity, price) values ('Java. Полное руководство', 'Герберд Шилдт', 13, 5200.50);
select * from book;
update book set quantity = 7, price = 85.35;
select * from book;
delete from book;
select * from book;