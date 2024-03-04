Database initializer

CREATE TABLE user_details(
	user_id INT IDENTITY(1,1),
	name VARCHAR(60) NOT NULL,
	password VARCHAR(255) ,
	position VARCHAR(60) NOT NULL,
	is_active bit,
	CONSTRAINT user_pk PRIMARY KEY(user_id),
);

