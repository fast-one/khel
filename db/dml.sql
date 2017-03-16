INSERT INTO ROLE (id, active_flag, name, description)
VALUES (1, TRUE, 'ADMIN', 'This role is for admins');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (2, TRUE, 'PLAYER', 'This role is for players');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (3, TRUE, 'ORGANIZER', 'This role is for organizers');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (4, TRUE, 'SUPPORT', 'This role is for support');


INSERT INTO role_permission ( role_id, permission, active_flag)
VALUES (1, 'ADD_USER', TRUE);
INSERT INTO role_permission ( role_id, permission, active_flag)
VALUES (1, 'ADD_USER', TRUE);
