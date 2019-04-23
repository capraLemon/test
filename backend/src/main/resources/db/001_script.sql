CREATE TABLE todo (
  id serial primary key,
  completed boolean,
  created_at timestamp,
  title varchar(200)
);
