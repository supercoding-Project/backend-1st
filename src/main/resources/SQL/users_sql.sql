create table users(
	user_id int primary key auto_increment,
    email varchar(50) not null,
    password varchar(255) not null,
    username varchar(20) not null,
    role varchar(20) not null,
    created_at datetime not null
);
