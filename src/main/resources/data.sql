--insert into user_details (id, username, password, role) values (1, 'user', '{noop}user', 'ROLE_USER');	--No password encryption
--insert into user_details (id, username, password, role) values (2, 'admin', '{noop}admin', 'ROLE_ADMIN');

--insert into user_details (id, username, password, role) values (1, 'user', '{bcrypt}$2a$09$CAeE6Jxg9BsTVwQyCC7/PuIK9jdIXAX8T5kjRG.LjMO.kbk9DsZsW', 'ROLE_USER');	--bcrypt encypted pwd
--insert into user_details (id, username, password, role) values (2, 'admin', '{bcrypt}$2a$09$e7K7RncqPOwPRwN02eqTJOgVvcjsDAjh/gkNyllx6SpJX9DjeWJIe', 'ROLE_ADMIN');