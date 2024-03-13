DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO public;

CREATE TABLE users (
	id serial PRIMARY KEY,
	name VARCHAR NOT NULL,
	email VARCHAR UNIQUE NOT NULL,
	password VARCHAR, 
	image_path VARCHAR,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE roles (
	id SERIAL PRIMARY KEY,
	name VARCHAR UNIQUE NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE role_user (
	id SERIAL PRIMARY KEY,
	user_id  NUMERIC,
	role_id NUMERIC
);

CREATE TABLE artists (
	id SERIAL PRIMARY KEY,
	name VARCHAR NOT NULL,
	alias VARCHAR,
	image_path VARCHAR,
	description TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE songs (
	id SERIAL PRIMARY KEY,
	title VARCHAR NOT NULL,
	year NUMERIC NOT NULL,
	image_path VARCHAR,
	audio_path VARCHAR,
	artist_id NUMERIC NOT NULL,
	album_id NUMERIC NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE albums (
	id SERIAL PRIMARY KEY,
	title VARCHAR NOT NULL,
	image_path VARCHAR,
	year NUMERIC NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

CREATE TABLE artist_song (
	artist_id NUMERIC NOT NULL,
	song_id NUMERIC NOT NULL,
	is_featured BOOLEAN NOT NULL
);

CREATE TABLE album_song (
	album_id NUMERIC NOT NULL,
	song_id NUMERIC NOT NULL
);

CREATE TABLE playlist (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	is_public BOOLEAN NOT NULL,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);
