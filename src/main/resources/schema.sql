DROP TABLE IF EXISTS tbl_users;

CREATE TABLE IF NOT EXISTS tbl_users (
id VARCHAR(30) PRIMARY KEY,
name VARCHAR(50),
email varchar(50),
password varchar(50),
created DATE,
last_login DATE,
is_active BOOLEAN);

DROP TABLE IF EXISTS tbl_phones;

CREATE TABLE IF NOT EXISTS tbl_phones (
id VARCHAR(30) PRIMARY KEY,
user_id VARCHAR(30),
number BIGINT,
city_code INT,
contry_code VARCHAR(2));