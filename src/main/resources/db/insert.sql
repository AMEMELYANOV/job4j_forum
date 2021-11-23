insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$S76VzFIaAyjOVHe7LauAe.xnAG6jtL//4ZLUhjzTPBNKuQmfNrT6W',
(select id from authorities where authority = 'ROLE_ADMIN'));

insert into comments (text, user_id, post_id) values ('2', 1, 1);
insert into comments (text, user_id, post_id) values ('3', 1, 1);
insert into comments (text, user_id, post_id) values ('4', 1, 2);
insert into comments (text, user_id, post_id) values ('5', 1, 2);