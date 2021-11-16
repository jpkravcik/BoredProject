drop table if exists activityid_userid;
drop table if exists user_activity;
drop table if exists activity_users;
drop table if exists rating;
drop table if exists booking;
drop table if exists hotel_reservation;
drop table if exists hotel;
DROP table if exists review;
DROP table if exists incategory;
DROP table if exists category;
DROP table if exists users_authority;
DROP table if exists  authority;
DROP table if exists Activity;
DROP table if exists userdata;
DROP table if exists city;

create table city(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	unique(name)
);

CREATE TABLE activity(
	id serial PRIMARY KEY,
	name VARCHAR(150),
	description VARCHAR(250),
	city_id INT,
	cost DECIMAL(10,3),
	unique (name),
	
	foreign key(city_id) references city(id) ON DELETE CASCADE
);



create table userdata(
	id serial PRIMARY KEY,
	firstname varchar(50),
	lastname varchar(50),
	email VARCHAR(255),
	password varchar(255),
	unique(email)

);



create table category(
	id serial PRIMARY KEY,
	name VARCHAR(150),
	unique(name)
	
);

create table incategory(

	activity_name VARCHAR(150),
	category_name VARCHAR(150),
	
	primary key(activity_name,category_name),
	
	foreign key(activity_name) references activity(name) ON DELETE CASCADE,
	foreign key(category_name) references category(name) ON DELETE CASCADE
);

create table review(
	id serial PRIMARY KEY,
	user_id INT,
	activity_id INT,
	review VARCHAR(255),
	
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);





create table hotel(
	id serial PRIMARY KEY,
	name VARCHAR(255),
	cost DECIMAL(10,3),
	city_id INT,
	foreign key(city_id) references city(id) ON DELETE CASCADE
);


create table hotel_reservation(
	id serial PRIMARY KEY,
		hotel_id INT,
		user_id INT,
		number_of_nights INTEGER,
		start_date DATE,
		end_date   DATE,
		foreign key(hotel_id) references hotel(id) ON DELETE CASCADE,
		foreign key(user_id) references userdata(id) ON DELETE CASCADE
);



create table booking(
	id serial PRIMARY KEY,
	user_email VARCHAR(255),
	activity_id INT,
	activity_name VARCHAR(255),
	cost DECIMAL(10,3),
	foreign key(activity_id) references activity(id) ON DELETE CASCADE,
	foreign key(user_email) references userdata(email) ON DELETE CASCADE
	
	
);


create table rating(
	id serial PRIMARY KEY,
	user_id INT,
	activity_id INT,
	rating INTEGER,
	
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);




create table user_activity(
	id serial PRIMARY KEY,
	user_id INT,
	activity_id INT,
	foreign key(user_id) references userdata(id) ON DELETE CASCADE,
	foreign key(activity_id) references activity(id) ON DELETE CASCADE
);


