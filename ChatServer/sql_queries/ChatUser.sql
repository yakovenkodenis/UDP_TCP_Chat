CREATE TABLE IF NOT EXISTS "ChatUser"(
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(30) default NULL,
	last_name VARCHAR(30) default NULL,
	email VARCHAR(40) default NULL,
	password VARCHAR(512) default NULL,
	created_at DATE default NULL,
	updated_at DATE default NULL,
	education_level VARCHAR(40) default NULL,
	is_admin BOOLEAN default FALSE
)