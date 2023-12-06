INSERT INTO tb_user (email, password, name) VALUES ('fer@email.com', '$2a$10$XUf0WzsgyRVc0Yx.b3uzAuhIrk2kefwG5b5kzwj14oKk9ggeYQY1u', 'gilberto');
INSERT INTO tb_user (email, password, name) VALUES ('geo@email.com', '$2a$10$XUf0WzsgyRVc0Yx.b3uzAuhIrk2kefwG5b5kzwj14oKk9ggeYQY1u', 'geovanna');

INSERT INTO tb_role (authority) VALUES ('ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO tb_course (name) VALUES ('flebotomia');

INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0323', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0423', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0523', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0623', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0723', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0823', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0124', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0224', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0324', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0424', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0524', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('CTF0624', true, 1);
INSERT INTO tb_team (name, completed, course_id, start_date, quantity_months, first_month_payment, price_registration, price_month_payments) VALUES ('CTF0724', false, 1, '2023-07-01', 3, '2023-07-15', 150, 170);
INSERT INTO tb_team (name, completed, course_id, start_date, quantity_months, first_month_payment, price_registration, price_month_payments) VALUES ('CTF0824', false, 1, '2023-07-01', 3, '2023-07-15', 150, 170);

INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Odair Guimarães Pereira', '81992208527', '1990-01-01', '102.940.284-12');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Maria Fabielle Soares Da Silva', '81991100809', '1990-02-01', '125.962.574-59');

INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Maria Fernanda da Silva Oliveira', '81998440931', '1990-03-01', '115.897.914-28');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Luzia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-88');

INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Ana de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-10');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Bruna de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-11');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Carla de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-12');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Denise de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-13');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Eloisa de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-14');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Flávia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-15');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Gerlaine de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-16');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Hortência de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-17');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Iasmim de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-18');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Julia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-19');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Letícia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-20');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Mônica de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-21');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Natália de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-22');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Osmar de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-23');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Paula de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-24');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Quiteria de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-25');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Rafaela de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-26');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Silvânia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-27');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Tânia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-28');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Uganabara de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-29');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Vânia de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-30');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Katarine de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-31');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Zelma de Melo Valenca', '81998452127', '1990-05-01', '093.041.524-32');


INSERT INTO tb_team_student (student_id, team_id) VALUES (1, 13);
INSERT INTO tb_team_student (student_id, team_id) VALUES (2, 13);

INSERT INTO tb_team_student (student_id, team_id) VALUES (3, 14);
INSERT INTO tb_team_student (student_id, team_id) VALUES (4, 14);


INSERT INTO tb_registration (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (150, 0, 75, false, '2023-07-13', '2023-07-17', 1, 13);
INSERT INTO tb_registration (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (150, 0, 0, false, '2023-07-13', '2023-07-17', 2, 13);
INSERT INTO tb_registration (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (150, 0, 130, false, '2023-07-13', '2023-07-17', 3, 14);
INSERT INTO tb_registration (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (150, 0, 0, false, '2023-07-13', '2023-07-17', 4, 14);


INSERT INTO tb_payment_voucher (student_id, team_id, registration_id, month_payment_id, price, discount, received, generation_date) VALUES (4, 14, 4, null, 150, 0, 75,  TIMESTAMP WITH TIME ZONE '2023-11-05T15:00:00Z');

INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 0, 170, true, '2023-08-01', '2023-08-05', 1, 13);
INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 20, 150, true, '2023-08-01', '2023-08-05', 2, 13);
INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 0, 0, false, '2023-08-01', '2023-08-05', 3, 14);
INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 20, 170, true, '2023-08-01', '2023-08-05', 4, 14);
INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 20, 130, true, '2023-09-01', '2023-09-05', 4, 14);
INSERT INTO tb_month_payment (price, discount, received, paid, due_date, payday, student_id, team_id) VALUES (170, 0, 0, false, '2023-10-01', '2023-10-05', 4, 14);


INSERT INTO tb_degree (code, generation_date, student_id, team_id) VALUES ('ctf0323-01f3d06f-54d9-4472-bd49-44a3c25ac663-20230709-181050', '2023-07-07 15:24:45', 1, 13);


INSERT INTO tb_expense (emission, cost, course_id, description) VALUES ('2023-07-09', 385.45 , 1, 'Material para aulas práticas');
INSERT INTO tb_expense (emission, cost, course_id, description) VALUES ('2023-11-29', 25.00 , 1, 'Resma de papel');
INSERT INTO tb_expense (emission, cost, course_id, description) VALUES ('2023-11-29', 125.00 , 1, 'Abraçadeira');