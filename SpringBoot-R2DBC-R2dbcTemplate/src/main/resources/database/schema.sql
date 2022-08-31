DROP TABLE IF EXISTS users;
CREATE TABLE users (
	user_id serial PRIMARY KEY,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	created_on TIMESTAMP NOT NULL
);

DROP TABLE IF EXISTS branch;
CREATE TABLE branch (
	branch_id serial PRIMARY KEY,
	bank_code VARCHAR ( 50 )  NOT NULL,
	branch_code VARCHAR ( 50 ) NOT NULL,
    branch_name VARCHAR ( 250 ),
	created_on TIMESTAMP NOT NULL
);