CREATE TABLE bank.accounts (
	accountnumber bigserial NOT NULL,
	accounttype varchar NOT NULL,
	balance float8 NOT NULL,
	"timestamp" timestamptz NULL DEFAULT CURRENT_TIMESTAMP,
	userid int4 NULL,
	CONSTRAINT accounts_pk PRIMARY KEY (accountnumber),
	CONSTRAINT accounts_un UNIQUE (accountnumber),
	CONSTRAINT accounts_fk FOREIGN KEY (userid) REFERENCES bank.users(userid) ON DELETE SET NULL ON UPDATE CASCADE
);