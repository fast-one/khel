INSERT INTO ROLE (id, active_flag, name, description)
VALUES (1, TRUE, 'ADMIN', 'This role is for admins');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (2, TRUE, 'PLAYER', 'This role is for players');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (3, TRUE, 'ORGANIZER', 'This role is for organizers');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (4, TRUE, 'SUPPORT', 'This role is for support');


INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (1, 'ADD_USER', TRUE);
-- password: aata
INSERT INTO app_user (id, first_name, last_name, password, user_name, email, mobile, active_flag) VALUES
  (9, 'Admin', 'Khel', '$2a$10$76YDYNHTZ0wesQEeDXbF8ei0awuh/5SaZ.waM25Q4hS7.dodE1Pq.', 'khel', 'admin@khel.com',
   '7702222194', TRUE);

INSERT INTO user_role (active_flag, user_id, role_id) VALUES (TRUE, 9, 1);
