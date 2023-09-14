-- CS1103 Open Entry Edition
-- Instructor Andrew McAllister

-- DDL for the Receipt / Product / Line Item example
-- Plus a few insert statements
-- Developed in Module 2

create table receipt
(  receipt_num   int unsigned not null primary key,
   date_time     datetime not null
);

insert into receipt values (101, '2021-08-27 14:10:05');
insert into receipt values (102, '2021-08-27 14:17:43');

create table product
(  product_id   int unsigned not null primary key,
   description  varchar(40) not null,
   unit_price   decimal(7,2) not null
);

insert into product values (234, 'Claw Hammer', 40.00);
insert into product values (605, '2x4x8 Spruce', 8.00);

create table line_item
(  receipt_num  int unsigned not null,
   line_num     int unsigned not null,
   product_id   int unsigned not null,
   quantity     int unsigned not null,
   primary key (receipt_num, line_num),
   foreign key (receipt_num) 
       references receipt (receipt_num),
   foreign key (product_id) 
       references product (product_id)
);

insert into line_item values(101, 1, 234,  1);
insert into line_item values(101, 2, 605, 10);
insert into line_item values(102, 1, 234,  1);



