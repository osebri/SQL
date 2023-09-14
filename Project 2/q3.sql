CREATE TABLE records(
id int unsigned NOT NULL,
date_time DATETIME NOT NULL,
amount FLOAT(11) UNSIGNED NOT NULL,
transact_type INT(1) UNSIGNED NOT NULL,
account_num INT UNSIGNED NOT NULL,
PRIMARY KEY (id),
FOREIGN KEY (transact_type)
	REFERENCES types(transact_type),
FOREIGN KEY (account_num)
	REFERENCES accounti(account_num) );