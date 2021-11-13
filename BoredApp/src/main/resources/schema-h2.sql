drop table if exists activityid_userid;
drop table if exists user_activity;
drop table if exists rating;
drop table if exists booking;
drop table if exists hotel_reservation;
drop table if exists hotel;
DROP table if exists review;
DROP table if exists incategory;
DROP table if exists category;
DROP table if exists Activity;
DROP table if exists userdata;
DROP table if exists city;

create table city(
	id long AUTO_INCREMENT,
	name VARCHAR(255),
	unique(name)
);

CREATE TABLE activity(
	id long AUTO_INCREMENT,
	name VARCHAR(150),
	description VARCHAR(250),
	city_id BIGINT UNSIGNED,
	cost DOUBLE,
	primary key(id),
	unique (name),
	
	foreign key(city_id) references city(id) ON DELETE CASCADE
);



create table userdata(
	id long AUTO_INCREMENT,
	firstname varchar(50),
	lastname varchar(50),
	email VARCHAR(255),
	password varchar(255),
	unique(email),
	
	primary key(id)
	
);



create table category(
	id long AUTO_INCREMENT,
	name VARCHAR(150),
	unique(name),
	primary key(id)
	
);

create table incategory(

	activity_name VARCHAR(150),
	category_name VARCHAR(150),
	
	primary key(activity_name,category_name),
	
	foreign key(activity_name) references activity(name) ON DELETE CASCADE,
	foreign key(category_name) references category(name) ON DELETE CASCADE
);

create table review(
	id long AUTO_INCREMENT,
	user_id BIGINT UNSIGNED,
	activity_id BIGINT UNSIGNED,
	review VARCHAR(255),
	
	primary key(id),
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);





create table hotel(
	id long AUTO_INCREMENT,
	name VARCHAR(255),
	cost DOUBLE,
	city_id BIGINT UNSIGNED,
	primary key(id),
	foreign key(city_id) references city(id) ON DELETE CASCADE
);


create table hotel_reservation(
		id long AUTO_INCREMENT,
		hotel_id BIGINT UNSIGNED,
		user_id BIGINT UNSIGNED,
		number_of_nights INTEGER,
		start_date DATE,
		end_date   DATE,
		primary key(id),
		foreign key(hotel_id) references hotel(id) ON DELETE CASCADE,
		foreign key(user_id) references userdata(id) ON DELETE CASCADE
);



create table booking(
	id long AUTO_INCREMENT,
	user_email VARCHAR(255),
	activity_id BIGINT UNSIGNED,
	activity_name VARCHAR(255),
	day DATE,
	cost DOUBLE,
	
	primary key(id),
	foreign key(activity_id) references activity(id) ON DELETE CASCADE,
	foreign key(user_email) references userdata(email) ON DELETE CASCADE
	
	
);


create table rating(
	id long AUTO_INCREMENT,
	user_id BIGINT UNSIGNED,
	activity_id BIGINT UNSIGNED,
	rating INTEGER,
	
	primary key(id),
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);





create table user_activity(
	id long AUTO_INCREMENT,
	user_id BIGINT UNSIGNED,
	activity_id BIGINT UNSIGNED,
	primary key(id),
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);


