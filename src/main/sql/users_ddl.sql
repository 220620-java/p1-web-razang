CREATE TABLE bank.users (
	userid serial4 NOT NULL,
	username varchar NOT NULL,
	birthdate date NULL,
	email varchar NOT NULL,
	phone varchar(15) NULL,
	"password" varchar NOT NULL,
	salt bytea NULL,
	"timestamp" timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
	CONSTRAINT users_pk PRIMARY KEY (userid),
	CONSTRAINT users_un UNIQUE (username,email)
);