create table students(
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Андрей Андреев');
insert into students (name) values ('Сергей Сергеев');
insert into students (name) values ('Николай Николаев');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Михаил Лермонтов');
insert into authors (name) values ('Антон Чехов');
insert into authors (name) values ('Федор Достоевский');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Мцыри', 3);
insert into books (name, author_id) values ('Бородино', 3);
insert into books (name, author_id) values ('Вишневый сад', 4);
insert into books (name, author_id) values ('Дядя Ваня', 4);
insert into books (name, author_id) values ('Игрок', 5);
insert into books (name, author_id) values ('Идиот', 5);
insert into books (name, author_id) values ('Преступление и наказание', 5);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 5);
insert into orders (active, book_id) values (false, 2);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (4, 3);
insert into orders (book_id, student_id) values (5, 4);
insert into orders (book_id, student_id) values (6, 2);
insert into orders (book_id, student_id) values (7, 5);
insert into orders (book_id, student_id) values (8, 3);
insert into orders (active, book_id) values (false, 9);
insert into orders (active, book_id) values (false, 10);
insert into orders (book_id, student_id) values (11, 2);
insert into orders (book_id, student_id) values (12, 3);
insert into orders (book_id, student_id) values (4, 4);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (6, 2);
insert into orders (book_id, student_id) values (7, 3);
insert into orders (book_id, student_id) values (8, 5);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (7, 1);
insert into orders (book_id, student_id) values (8, 3);
insert into orders (book_id, student_id) values (5, 2);

--Определить, кто из студентов взял несколько книг Вий 

create view duplicate as
    select students.name Имя_студента,
    count(books.name) Кол_дубликатов,
    authors.name Имя_автора,
    books.name Название_книги
    from students
    join orders
    on students.id = orders.student_id
    join books
    on books.id = orders.book_id
    join authors
    on authors.id = books.author_id
    group by (students.name, authors.name, books.name)
    having count(books.name) > 1
    order by students.name;

select * from duplicate
where Название_книги = 'Вий';




