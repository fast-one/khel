INSERT INTO ROLE (id, active_flag, name, description)
VALUES (1, TRUE, 'ADMIN', 'This role is for admins');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (2, TRUE, 'PARTICIPANT', 'This role is for participants');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (3, TRUE, 'ORGANIZER', 'This role is for organizers');
INSERT INTO ROLE (id, active_flag, name, description)
VALUES (4, TRUE, 'SUPPORT', 'This role is for support');

-- admin
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (1, 'DELETE_USER', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (1, 'USER_REGISTER', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (1, 'USER_EDIT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (1, 'UPDATE_USER_PASSWORD', TRUE);

-- support
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (4, 'USER_REGISTER', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (4, 'USER_EDIT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (4, 'UPDATE_USER_PASSWORD', TRUE);

-- participants
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'USER_REGISTER', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'USER_EDIT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'UPDATE_USER_PASSWORD', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'PARTICIPANT_SCHEDULE_EVENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'PARTICIPANT_CANCEL_SCHEDULE_EVENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'PARTICIPANT_UPDATE_PROFILE', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'PARTICIPANT_PAYMENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (2, 'PARTICIPANT_REQUEST_REFUND', TRUE);

-- Organizers
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'USER_REGISTER', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'USER_EDIT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'UPDATE_USER_PASSWORD', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'ADD_GEO_LOCATION', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'EDIT_GEO_LOCATION', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'ADD_EVENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'EDIT_EVENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'DELETE_EVENT', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'ADD_SCHEDULE', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'EDIT_SCHEDULE', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'VIEW_SCHEDULE', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'DELETE_SCHEDULE', TRUE);
INSERT INTO role_permission (role_id, permission, active_flag)
VALUES (3, 'REQUEST_PAYMENT', TRUE);

-- insert admin
-- password: aata
INSERT INTO app_user (id, first_name, last_name, password, user_name, email, mobile, active_flag) VALUES
  (9, 'Admin', 'Khel', '$2a$10$76YDYNHTZ0wesQEeDXbF8ei0awuh/5SaZ.waM25Q4hS7.dodE1Pq.', 'admin', 'admin@khel.com',
   '7702222194', TRUE);
INSERT INTO user_role (active_flag, user_id, role_id) VALUES (TRUE, 9, 1);

