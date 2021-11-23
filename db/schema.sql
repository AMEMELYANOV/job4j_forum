create table if exist authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);

create table if exist users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

create table if exist posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now(),
  user_id int references users(id)
);

create table if exist comments (
    id serial primary key,
    text text not null,
    created timestamp  without time zone not null default now(),
    user_id int references users(id),
    post_id int references posts(id)
);

insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$S76VzFIaAyjOVHe7LauAe.xnAG6jtL//4ZLUhjzTPBNKuQmfNrT6W',
(select id from authorities where authority = 'ROLE_ADMIN'));

insert into comments (text, user_id, post_id) values ('2', 1, 5);
insert into comments (text, user_id, post_id) values ('3', 1, 5);
insert into comments (text, user_id, post_id) values ('4', 1, 5);
insert into comments (text, user_id, post_id) values ('5', 1, 5);