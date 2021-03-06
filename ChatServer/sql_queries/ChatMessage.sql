CREATE TABLE IF NOT EXISTS "ChatMessage"(
  id SERIAL PRIMARY KEY,
  created_at DATE DEFAULT NULL,
  content TEXT NOT NULL,
  from_user_id INT REFERENCES "ChatUser"(id) ON DELETE CASCADE ON UPDATE CASCADE,
  destination_group_id INT REFERENCES "ChatGroup"(id) ON DELETE CASCADE ON UPDATE CASCADE
)