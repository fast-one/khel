DROP SEQUENCE IF EXISTS HIBERNATE_SEQUENCE;
DROP SEQUENCE IF EXISTS APP_SEQ;
-- system tables
DROP TABLE IF EXISTS APP_AUDIT CASCADE;
DROP TABLE IF EXISTS ROLE CASCADE;
DROP TABLE IF EXISTS ROLE_AUD CASCADE;
DROP TABLE IF EXISTS APP_USER CASCADE;
DROP TABLE IF EXISTS APP_USER_AUD CASCADE;
DROP TABLE IF EXISTS USER_ROLE CASCADE;
DROP TABLE IF EXISTS USER_ROLE_AUD CASCADE;
DROP TABLE IF EXISTS ROLE_PERMISSION CASCADE;
DROP TABLE IF EXISTS ROLE_PERMISSION_AUD CASCADE;
-- app tables
DROP TABLE IF EXISTS GEO_LOCATION CASCADE;
DROP TABLE IF EXISTS GEO_LOCATION_AUD CASCADE;
DROP TABLE IF EXISTS SPORTS_EVENT CASCADE;
DROP TABLE IF EXISTS SPORTS_EVENT_AUD CASCADE;
DROP TABLE IF EXISTS EVENT_SCHEDULE CASCADE;
DROP TABLE IF EXISTS EVENT_SCHEDULE_AUD CASCADE;
DROP TABLE IF EXISTS EVENT_PARTICIPANT CASCADE;
DROP TABLE IF EXISTS EVENT_PARTICIPANT_AUD CASCADE;

