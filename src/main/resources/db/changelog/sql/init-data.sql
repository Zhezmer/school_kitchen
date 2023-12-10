insert into school_group(name) values ('Daniel'), ('Anzor');

insert into product(name) values ('milk'), ('nutella'), ('joint');

INSERT INTO role(role_name) VALUES ('ROLE_USER');
INSERT INTO role(role_name) VALUES ('ROLE_ADMIN');

INSERT INTO users(role_id, username, password, first_name, last_name, email, personal_id, phone_number)
VALUES ((select id from role where role_name = 'ROLE_ADMIN'), 'mentael', '$2a$10$kifGdQNysdZJbSVHSnPFNOwWJtXsSz5Eys.oG.Ra8hE7WWBzGeNBW', 'админ', 'админ', 'admin@mail.ru', '33651', '05488');