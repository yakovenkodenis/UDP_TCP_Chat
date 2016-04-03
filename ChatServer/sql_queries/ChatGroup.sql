CREATE TABLE IF NOT EXISTS "ChatGroup"(
  id SERIAL PRIMARY KEY,
  group_name VARCHAR(40) default NULL,
  created_at DATE default NULL
)