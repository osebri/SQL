
create table album
(  <define all columns and keys...>
);

-- This version works

create table album
(  album_id     int unsigned not null primary key,
   album_name   varchar(20) not null,
   artist_name  varchar(20) not null
);

-- This version looks identical but doesn't work!

create table album
(  album_id		int unsigned not null primary key,
   album_name	varchar(20) not null,
   artist_name	varchar(20) not null
);

show tables;
show columns in album;
drop table album;

create table track
(  
);

create table track
(  album_id       int unsigned not null,
   track_number   int unsigned not null,
   song_title     varchar(30) not null,
   length         varchar(7),
   primary key (album_id, track_number),
   foreign key (album_id) 
       references album (album_id)
);

-- The following won't work
-- The DBMS thinks I'm trying to create multiple PKs

create table track
(  album_id       int unsigned not null primary key,
   track_number   int unsigned not null primary key,
   song_title     varchar(30) not null,
   length         varchar(7),
   foreign key (album_id) 
       references album (album_id)
);

-- What happens if I have a syntax error?
-- I've omitted the brackets for the PK

create table track
(  album_id       int unsigned not null,
   track_number   int unsigned not null,
   song_title     varchar(30) not null,
   length         varchar(7),
   primary key album_id, track_number,
   foreign key (album_id) 
       references album (album_id)
);

-- Now insert some data...

insert into album values (100, 'Greatest Hits', 'Queen');
insert into album values (101, 'Greatest Hits', 'Boston');
insert into album values (102, 'Rumours', 'Fleetwood Mac');
insert into album values (103, 'Second Helping', 'Lynyrd Skynyrd');

insert into track values (100, 1, 'Killer Queen', '3:01');
insert into track values (100, 2, 'Bohemian Rhapsody', '5:55');
insert into track values (101, 3, 'More Than a Feeling', null);
insert into track values (101, 12, 'Rock and Roll Band', null);

insert into track values (102, 1, 'Second Hand News', '2:55');
insert into track values (102, 2, 'Dreams', '4:32');

-- Try some insertions with errors

-- Duplicate a primary key value
insert into album values (100, 'Slowhand', 'Eric Clapton');

-- Referential integrity error
insert into track values (999, 1, 'Happy Birthday', '1:00');

-- Negative value for unsigned column
insert into album values (-100, 'Slowhand', 'Eric Clapton');

delete from album where album_id = 0;

select * from album join track using (album_id);


-- Now the Receipt & Product example...

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

-- Find all albums that have no tracks
-- Or I could do this with an left join?
select album_id from album where album_id not in
(select distinct album_id from track natural join album);



