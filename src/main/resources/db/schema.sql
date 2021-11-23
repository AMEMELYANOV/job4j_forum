create table authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

create table users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now(),
  user_id int references users(id)
);

create table comments (
    id serial primary key,
    text text not null,
    created timestamp  without time zone not null default now(),
    user_id int references users(id),
    post_id int references posts(id)
);