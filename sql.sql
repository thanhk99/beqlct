Drop database if exists qlct;
create database qlct;
use qlct;
create table users(
	id int auto_increment primary key,
    account char(20) unique,
    password char(20),
    phone char(15),
	username char(40)
);
insert into users(account,password,phone,username) values
('user1', 'pass1', '1234567890', 'User  One'),
('user2', 'pass2', '0987654321', 'User  Two'),
('user3', 'pass3', '1122334455', 'User  Three'),
('user4', 'pass4', '2233445566', 'User  Four'),
('user5', 'pass5', '3344556677', 'User  Five'),
('user6', 'pass6', '4455667788', 'User  Six'),
('user7', 'pass7', '5566778899', 'User  Seven'),
('user8', 'pass8', '6677889900', 'User  Eight'),
('user9', 'pass9', '7788990011', 'User  Nine'),
('user10', 'pass10', '8899001122', 'User  Ten');

create table wallet(
	id int auto_increment primary key,
    id_user int ,
    name_wallet char(20),
    currency  char(10),
    balance float ,
    foreign key (id_user) references users(id)
);
create table transaction(
	id int auto_increment primary key,
    id_user int ,
    id_wallet int,
    foreign key (id_wallet) references wallet(id),
	foreign key (id_user) references users(id),
    type char(20),
    amount float,
    spent char(20),
    time char(30)
);