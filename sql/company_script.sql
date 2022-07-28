CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Apple');
INSERT INTO company (id, name) VALUES (2, 'Microsoft');
INSERT INTO company (id, name) VALUES (3, 'Gazprom');
INSERT INTO company (id, name) VALUES (4, 'Lukoil');
INSERT INTO company (id, name) VALUES (5, 'Google');

INSERT INTO person (id, name, company_id) VALUES (1, 'Mark', 1);
INSERT INTO person (id, name, company_id) VALUES (2, 'Mike', 1);
INSERT INTO person (id, name, company_id) VALUES (3, 'Anna', 2);
INSERT INTO person (id, name, company_id) VALUES (4, 'Bill', 2);
INSERT INTO person (id, name, company_id) VALUES (5, 'Ivan', 3);
INSERT INTO person (id, name, company_id) VALUES (6, 'Oleg', 3);
INSERT INTO person (id, name, company_id) VALUES (7, 'Igor', 4);
INSERT INTO person (id, name, company_id) VALUES (8, 'John', 4);
INSERT INTO person (id, name, company_id) VALUES (9, 'Bob', 5);
INSERT INTO person (id, name, company_id) VALUES (10, 'Lucy', 5);
INSERT INTO person (id, name) VALUES (11, 'Stefan');
INSERT INTO person (id, name) VALUES (12, 'Alexey');
INSERT INTO person (id, name) VALUES (13, 'Martin');
INSERT INTO person (id, name, company_id) VALUES (14, 'Olga', 3);
INSERT INTO person (id, name, company_id) VALUES (15, 'Maria', 4);

--1
SELECT person.name AS Имя, company.name AS Компания
FROM person
JOIN company
ON person.company_id = company.id 
WHERE company.id != 5;

--2
SELECT company.name AS Название_компании,
COUNT(person.name) AS Количество_сотрудников
FROM company
JOIN person
ON company.id = person.company_id
GROUP BY company.name
HAVING COUNT(person.name) = (
    SELECT MAX(Количество)
    FROM
    (SELECT COUNT(person.name) AS Количество
        FROM company
        JOIN person
        ON company.id = person.company_id
        GROUP BY company.name
    ) AS Результат
);

