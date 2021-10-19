create table users(
	id bigint(12) auto_increment primary key,
	name varchar(30) not null
);

create table addresses(
	id bigint(12) auto_increment primary key,
	user_id bigint(12) not null,
	address varchar(70) not null,
	foreign key (user_id) references users(id)
);