DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;


CREATE TABLE users (
	id	serial PRIMARY KEY,
	name VARCHAR NOT NULL,
	email VARCHAR UNIQUE NOT NULL,
	password VARCHAR NOT NULL,
  image_path VARCHAR,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR UNIQUE NOT NULL
);

CREATE TABLE role_user (
	user_id  NUMERIC,
	role_id NUMERIC
);

--CREATE TABLE artists (
--	id SERIAL PRIMARY KEY,
--	name VARCHAR UNIQUE NOT NULL,
--	image_path VARCHAR,
--	description TEXT
--);

--CREATE TABLE songs {
--	id SERIAL PRIMARY KEY,
--}

--CREATE TABLE albums {
--	id SERIAL PRIMARY KEY,
--}

