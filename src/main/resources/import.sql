INSERT INTO tb_user (email, password) VALUES ('fer@email.com', '$2a$10$XUf0WzsgyRVc0Yx.b3uzAuhIrk2kefwG5b5kzwj14oKk9ggeYQY1u')

INSERT INTO tb_role (authority) VALUES ('ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);

INSERT INTO tb_course (name) VALUES ('flebotomia');

INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0323', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0423', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0523', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0623', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0723', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0823', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0124', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0224', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0324', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0424', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0524', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0624', true, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0724', false, 1);
INSERT INTO tb_team (name, completed, course_id) VALUES ('ctf0824', false, 1);

INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Odair Guimarães Pereira', '81992208527', '1900-01-01', '102.940.284-12');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Maria Fabielle Soares Da Silva', '81991100809', '1900-02-01', '125.962.574-59');

INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Maria Fernanda da Silva Oliveira', '81998440931', '1900-03-01', '115.897.914-28');
INSERT INTO tb_student (name, phone, date_birth, cpf) VALUES ('Luzia de Melo Valenca', '81998452127', '1900-05-01', '093.041.524-88');


INSERT INTO tb_team_student (student_id, team_id) VALUES (1, 13);
INSERT INTO tb_team_student (student_id, team_id) VALUES (2, 13);

INSERT INTO tb_team_student (student_id, team_id) VALUES (3, 14);
INSERT INTO tb_team_student (student_id, team_id) VALUES (4, 14);


INSERT INTO tb_registration (price, discount, received, paid, payday, student_id, team_id) VALUES (150, 0, 75, false, '2023-07-13', 1, 1);
INSERT INTO tb_registration (price, discount, received, paid, payday, student_id, team_id) VALUES (150, 0, 0, false, '2023-07-13', 2, 1);
INSERT INTO tb_registration (price, discount, received, paid, payday, student_id, team_id) VALUES (150, 0, 130, false, '2023-07-13', 3, 2);
INSERT INTO tb_registration (price, discount, received, paid, payday, student_id, team_id) VALUES (150, 0, 0, false, '2023-07-13', 4, 2);

INSERT INTO tb_month_payment (price, discount, paid, payday, student_id, team_id) VALUES (170, 0, false, '2023-08-01', 1, 1);
INSERT INTO tb_month_payment (price, discount, paid, payday, student_id, team_id) VALUES (170, 20, false, '2023-08-01', 2, 1);
INSERT INTO tb_month_payment (price, discount, paid, payday, student_id, team_id) VALUES (170, 0, false, '2023-08-01', 3, 2);
INSERT INTO tb_month_payment (price, discount, paid, payday, student_id, team_id) VALUES (170, 20, false, '2023-08-01', 4, 2);


INSERT INTO tb_degree (code, generation_date, student_id, team_id) VALUES ('ctf0323-01f3d06f-54d9-4472-bd49-44a3c25ac663-20230709-181050', '2023-07-07 15:24:45', 1, 1);