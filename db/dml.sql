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

-- support

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
