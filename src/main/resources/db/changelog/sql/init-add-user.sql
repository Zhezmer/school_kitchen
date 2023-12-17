INSERT INTO users(role_id, username, password, first_name, last_name, email, personal_id, phone_number)
VALUES ((select id from role where role_name = 'ROLE_ADMIN'), 'root', '$2a$12$BOaa1hUZ9SPxq5yQ9MyY2Oxv560BUnaauv08bzcmRepJLdL.pnBJm', 'דניק', 'דניק', 'admin@mail.ru', '000000', '0548807508');

UPDATE users
SET first_name = 'גלעד', last_name = 'מנטל', email = 'giladmental@mevoot-eron.com', personal_id = '1111111', phone_number = '0547968710'
WHERE username = 'mentael';