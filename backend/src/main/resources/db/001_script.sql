CREATE TABLE todo (
  id serial primary key,
  completed boolean,
  created_at timestamp,
  title varchar(200)
);

CREATE TABLE role (
    role_id serial PRIMARY KEY,
    role_name VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL
);

CREATE TABLE account (
    account_id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    login VARCHAR(100) UNIQUE,
    password VARCHAR(100) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    last_login_date TIMESTAMP,
    is_active boolean NOT NULL
);

CREATE TABLE account_role (
    account_id INTEGER REFERENCES account(account_id),
    role_id INTEGER REFERENCES role(role_id),
    PRIMARY KEY(account_id, role_id)
);


CREATE TABLE contest (
  contest_id serial primary key,
  name varchar(255) not null,
  contest_start timestamp not null,
  contest_end timestamp not null,
  is_active boolean not null,
  duration_for_person_in_days integer not null
);

CREATE TABLE participation (
    participation_id SERIAL PRIMARY KEY,
    account_id INTEGER NOT NULL REFERENCES account(account_id),
    contest_id INTEGER NOT NULL REFERENCES contest(contest_id),
    participation_start TIMESTAMP,
    UNIQUE(account_id, contest_id)
);

CREATE TABLE task (
 task_id serial PRIMARY KEY,
 contest_id integer REFERENCES contest(contest_id),
 task_text text NOT NULL,
 creation_date timestamp NOT NULL,
 time_limit integer NOT NULL,
 memory_limit integer NOT NULL,
 tries_limit integer NOT NULL,
 title varchar(100) NOT NULL,
 is_chat BOOLEAN NOT NULL
);

CREATE TABLE test (
test_id serial PRIMARY KEY,
task_id integer REFERENCES task(task_id),
test_input text NOT NULL,
expected_output text NOT NULL,
is_smoke boolean NOT NULL,
title varchar(100),
author integer REFERENCES account(account_id)
);

CREATE TABLE solution (
solution_id serial PRIMARY KEY,
task_id integer REFERENCES task(task_id) NOT NULL,
solution_text text NOT NULL,
language_id varchar(50) NOT NULL,
status_id varchar(75) NOT NULL,
creation_date timestamp NOT NULL,
account_id INTEGER REFERENCES account(account_id) NOT NULL
);

CREATE TABLE message (
message_id SERIAL PRIMARY KEY,
message_text VARCHAR(500) NOT NULL,
sender_id INTEGER REFERENCES account(account_id),
receiver_id INTEGER REFERENCES account(account_id),
task_id INTEGER REFERENCES task(task_id),
creation_date TIMESTAMP NOT NULL,
is_watched BOOLEAN NOT NULL
);

CREATE TABLE browser_code (
browser_code_id SERIAL PRIMARY KEY,
account_id INTEGER NOT NULL REFERENCES account(account_id),
task_id INTEGER NOT NULL REFERENCES task(task_id),
solution_text text,
language_id INTEGER
);

CREATE TABLE task_description (
task_description_id serial PRIMARY KEY,
task_id integer REFERENCES task(task_id),
sample_input text,
sample_output text,
notes text
);
CREATE TABLE test_batch (
test_batch_id serial PRIMARY KEY,
account_id INTEGER REFERENCES account(account_id)
);

CREATE TABLE browser_test_check (
browser_test_id serial PRIMARY KEY,
test_batch_id integer REFERENCES test_batch(test_batch_id),
test_id integer REFERENCES test(test_id),
status_id varchar(75) NOT NULL,
test_output text
);
