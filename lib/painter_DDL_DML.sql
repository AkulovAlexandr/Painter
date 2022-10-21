SET autocommit = 0;
SET foreign_key_checks = 0;
START TRANSACTION;

#DataBase creation
create database if not exists painter;
use painter;

#Tables creation
drop table if exists university;
create table university
(
    id      bigint auto_increment
        primary key,
    title   varchar(150)  not null,
    city    varchar(150)  not null,
    avg_age int default 0 not null
);
drop table if exists student;
create table student
(
    id            bigint auto_increment primary key,
    name          varchar(50),
    surname       varchar(50),
    university_id bigint,
    age           int default 17 not null,
    foreign key (university_id) references university (id)
);

#User creation
CREATE USER 'painter_dev'@'localhost' IDENTIFIED BY 'd1f5g3r4';
CREATE USER 'painter_dev'@'%' IDENTIFIED BY 'd1f5g3r4';
GRANT SELECT ON painter.student TO 'painter_dev'@'localhost';
GRANT SELECT ON painter.university TO 'painter_dev'@'localhost';
GRANT SELECT ON painter.student TO 'painter_dev'@'%';
GRANT SELECT ON painter.university TO 'painter_dev'@'%';
flush privileges;

#Triggers creation 
delimiter //
create trigger avg_age_university_on_insert
after INSERT
on student
for each row
BEGIN
   UPDATE university u 
   SET u.avg_age = (
   SELECT AVG(s.age) 
   FROM student s 
   WHERE s.university_id = NEW.university_id
   ) 
   WHERE id = NEW.university_id;
END //

create trigger avg_age_university_on_update
after UPDATE
on student
for each row
BEGIN
   UPDATE university u 
   SET u.avg_age = (
   SELECT AVG(s.age) 
   FROM student s 
   WHERE s.university_id = NEW.university_id
   ) 
   WHERE id = NEW.university_id;
END //

create trigger avg_age_university_on_delete
after DELETE
on student
for each row
BEGIN
   UPDATE university u 
   SET u.avg_age = (
   SELECT AVG(s.age) 
   FROM student s 
   WHERE s.university_id = OLD.university_id
   ) 
   WHERE id = OLD.university_id;
END //
delimiter ;

#Values insertion 
insert into painter.university (title, city)
values  ('Белорусский государственный университет', 'Минск'),
        ('Белорусский национальный технический университет', 'Минск'),
        ('Francisk Skorina Gomel State University', 'Гомель'),
        ('Гродзенскі дзяржаўны медыцынскі універсітэт', 'Гродно'),
        ('Polessky State University', 'Пинск'),
        ('IT-Академия ШАГ', 'Бобруйск');  
insert into painter.student (name, surname, university_id, age)
values  ('Александр', 'Акулов', 6, 28),
        ('Иван', 'Иванов', 2, 18),
        ('Петр', 'Петров', 2, 17),
        ('Дмитрий', 'Иванов', 5, 21),
        ('Иван', 'Петров', 4, 22),
        ('Сергей', 'Петров', 3, 25),
        ('Александр', 'Петров', 1, 24);
          
COMMIT;
SET autocommit = 1;
SET foreign_key_checks = 1;        
