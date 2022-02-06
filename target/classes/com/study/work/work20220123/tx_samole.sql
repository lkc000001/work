create table if not exists book(
	bid integer not null auto_increment,
	bname varchar(20) not null,
	price integer,
	ct timestamp default current_timestamp,
	primary key (bid)
);

create table if not exists stock(
	sid integer not null auto_increment,
	bid integer not null,
	amount integer,
	primary key (sid),
	foreign key (bid) references book(bid)
);

create table if not exists wallet(
	wid integer not null auto_increment,
	wname varchar(20) not null,
	money integer default 0,
	primary key (wid)
);

create table if not exists transactionlog(
	logid integer not null auto_increment,
	wid integer,
	bid integer,
	bname varchar(20),
	qty integer,
	amount integer,
	buydate datetime,
	primary key (logid)
);
