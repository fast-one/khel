CREATE SEQUENCE HIBERNATE_SEQUENCE MINVALUE 0 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 100 CACHE 20;

CREATE SEQUENCE APP_SEQ MINVALUE 0 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 100 CACHE 20;

CREATE TABLE APP_AUDIT (
  VERSION_NUMBER NUMERIC      NOT NULL PRIMARY KEY,
  EVENT_TYPE     VARCHAR(250) NOT NULL,
  VERSION_TIME   NUMERIC      NOT NULL,
  USER_ID        NUMERIC      NOT NULL
);

CREATE TABLE ROLE
(
  ID          NUMERIC      NOT NULL PRIMARY KEY,
  ACTIVE_FLAG BOOLEAN      NOT NULL,
  NAME        VARCHAR(200) NOT NULL UNIQUE,
  DESCRIPTION VARCHAR(1000)
);

CREATE TABLE ROLE_AUD
(
  ID               NUMERIC      NOT NULL,
  ACTIVE_FLAG      BOOLEAN      NOT NULL,
  NAME             VARCHAR(200) NOT NULL UNIQUE,
  DESCRIPTION      VARCHAR(1000),
  VERSION_NUMBER   NUMERIC      NOT NULL,
  TRANSACTION_TYPE NUMERIC      NOT NULL,
  CONSTRAINT ROLE_AUD_PK PRIMARY KEY (ID, VERSION_NUMBER)
);

CREATE TABLE APP_USER
(
  ID          NUMERIC      NOT NULL PRIMARY KEY,
  FIRST_NAME  VARCHAR(250) NOT NULL,
  LAST_NAME   VARCHAR(250) NOT NULL,
  PASSWORD    VARCHAR(200) NOT NULL,
  USER_NAME   VARCHAR(200) NOT NULL UNIQUE,
  EMAIL       VARCHAR(200) NOT NULL UNIQUE,
  MOBILE      VARCHAR(12)  NOT NULL UNIQUE,
  ACTIVE_FLAG BOOLEAN      NOT NULL
);

CREATE TABLE APP_USER_AUD
(
  ID               NUMERIC      NOT NULL,
  FIRST_NAME       VARCHAR(250) NOT NULL,
  LAST_NAME        VARCHAR(250) NOT NULL,
  PASSWORD         VARCHAR(200) NOT NULL,
  USER_NAME        VARCHAR(200) NOT NULL,
  EMAIL            VARCHAR(200) NOT NULL,
  MOBILE           VARCHAR(12)  NOT NULL,
  ACTIVE_FLAG      BOOLEAN      NOT NULL,
  VERSION_NUMBER   NUMERIC      NOT NULL,
  TRANSACTION_TYPE NUMERIC      NOT NULL,
  CONSTRAINT APP_USER_AUD_PK PRIMARY KEY (ID, VERSION_NUMBER)
);

CREATE TABLE USER_ROLE
(
  USER_ID     NUMERIC              NOT NULL REFERENCES APP_USER (ID),
  ROLE_ID     NUMERIC              NOT NULL REFERENCES ROLE (ID),
  ACTIVE_FLAG BOOLEAN DEFAULT TRUE NOT NULL,
  CONSTRAINT USER_ROLE_PK PRIMARY KEY (USER_ID, ROLE_ID, ACTIVE_FLAG)
);

CREATE TABLE USER_ROLE_AUD
(
  USER_ID          NUMERIC              NOT NULL REFERENCES APP_USER (ID),
  ROLE_ID          NUMERIC              NOT NULL REFERENCES ROLE (ID),
  ACTIVE_FLAG      BOOLEAN DEFAULT TRUE NOT NULL,
  VERSION_NUMBER   NUMERIC              NOT NULL,
  TRANSACTION_TYPE NUMERIC              NOT NULL,
  CONSTRAINT USER_ROLE_M_PK PRIMARY KEY (USER_ID, ROLE_ID, VERSION_NUMBER)
);

CREATE TABLE ROLE_PERMISSION
(
  ACTIVE_FLAG BOOLEAN DEFAULT TRUE NOT NULL,
  ROLE_ID     NUMERIC              NOT NULL,
  PERMISSION  VARCHAR(200)         NOT NULL,
  CONSTRAINT ROLE_PERMISSION_PK PRIMARY KEY (ROLE_ID, PERMISSION, ACTIVE_FLAG)
);

CREATE TABLE ROLE_PERMISSION_AUD
(
  ACTIVE_FLAG      BOOLEAN DEFAULT TRUE NOT NULL,
  ROLE_ID          NUMERIC              NOT NULL,
  PERMISSION       VARCHAR(200)         NOT NULL,
  VERSION_NUMBER   NUMERIC              NOT NULL,
  TRANSACTION_TYPE NUMERIC              NOT NULL,
  CONSTRAINT ROLE_PERMISSION_AUD_PK PRIMARY KEY (ROLE_ID, PERMISSION, VERSION_NUMBER)
);

-- APP Based Tables
CREATE TABLE GEO_LOCATION
(
  ID          NUMERIC      NOT NULL PRIMARY KEY,
  NAME        VARCHAR(250) NOT NULL,
  LOCATION    GEOGRAPHY(POINT, 4326),
  ACTIVE_FLAG BOOLEAN      NOT NULL
);

CREATE TABLE GEO_LOCATION_AUD
(
  ID               NUMERIC      NOT NULL,
  NAME             VARCHAR(250) NOT NULL,
  LOCATION         GEOGRAPHY(POINT, 4326),
  ACTIVE_FLAG      BOOLEAN      NOT NULL,
  VERSION_NUMBER   NUMERIC      NOT NULL,
  TRANSACTION_TYPE NUMERIC      NOT NULL,
  CONSTRAINT GEO_LOCATION_AUD_PK PRIMARY KEY (ID, VERSION_NUMBER)
);

CREATE TABLE SPORTS_EVENT
(
  ID               NUMERIC      NOT NULL PRIMARY KEY,
  NAME             VARCHAR(250) NOT NULL,
  TYPE             VARCHAR(250) NOT NULL,
  CATEGORY         VARCHAR(250) NOT NULL,
  MIN_PARTICIPANTS NUMERIC      NOT NULL,
  MAX_PARTICIPANTS NUMERIC      NOT NULL,
  DETAILS          TEXT,
  ORGANIZER_ID     NUMERIC      NOT NULL REFERENCES APP_USER (ID),
  ACTIVE_FLAG      BOOLEAN      NOT NULL
);

CREATE TABLE SPORTS_EVENT_AUD
(
  ID               NUMERIC      NOT NULL,
  NAME             VARCHAR(250) NOT NULL,
  TYPE             VARCHAR(250) NOT NULL,
  CATEGORY         VARCHAR(250) NOT NULL,
  MIN_PARTICIPANTS NUMERIC      NOT NULL,
  MAX_PARTICIPANTS NUMERIC      NOT NULL,
  DETAILS          TEXT,
  ORGANIZER_ID     NUMERIC      NOT NULL REFERENCES APP_USER (ID),
  ACTIVE_FLAG      BOOLEAN      NOT NULL,
  VERSION_NUMBER   NUMERIC      NOT NULL,
  TRANSACTION_TYPE NUMERIC      NOT NULL,
  CONSTRAINT SPORTS_EVENT_AUD_PK PRIMARY KEY (ID, VERSION_NUMBER)
);


CREATE TABLE EVENT_SCHEDULE
(
  ID              NUMERIC      NOT NULL PRIMARY KEY,
  NAME            VARCHAR(250) NOT NULL,
  IS_PUBLIC       BOOLEAN      NOT NULL DEFAULT TRUE,
  DETAILS         TEXT,
  START_TIME      TIMESTAMP    NOT NULL,
  DURATION        NUMERIC,
  REPEAT          BOOLEAN      NOT NULL,
  RECURRENCE_ID   NUMERIC,
  SPORTS_EVENT_ID NUMERIC      NOT NULL REFERENCES SPORTS_EVENT (ID),
  LOCATION_ID     NUMERIC      NOT NULL REFERENCES GEO_LOCATION (ID),
  ORGANIZER_ID    NUMERIC      NOT NULL REFERENCES APP_USER (ID),
  ACTIVE_FLAG     BOOLEAN      NOT NULL
);

CREATE TABLE EVENT_SCHEDULE_AUD
(
  ID               NUMERIC      NOT NULL,
  NAME             VARCHAR(250) NOT NULL,
  IS_PUBLIC        BOOLEAN      NOT NULL DEFAULT TRUE,
  DETAILS          TEXT,
  START_TIME       TIMESTAMP    NOT NULL,
  DURATION         NUMERIC,
  REPEAT           BOOLEAN      NOT NULL,
  RECURRENCE_ID    NUMERIC,
  SPORTS_EVENT_ID  NUMERIC      NOT NULL REFERENCES SPORTS_EVENT (ID),
  LOCATION_ID      NUMERIC      NOT NULL REFERENCES GEO_LOCATION (ID),
  ORGANIZER_ID     NUMERIC      NOT NULL REFERENCES APP_USER (ID),
  ACTIVE_FLAG      BOOLEAN      NOT NULL,
  VERSION_NUMBER   NUMERIC      NOT NULL,
  TRANSACTION_TYPE NUMERIC      NOT NULL,
  CONSTRAINT EVENT_SCHEDULE_AUD_PK PRIMARY KEY (ID, VERSION_NUMBER)
);

CREATE TABLE EVENT_PARTICIPANT
(
  ID                NUMERIC NOT NULL PRIMARY KEY,
  PARTICIPANT_ID    NUMERIC NOT NULL REFERENCES APP_USER (ID),
  EVENT_SCHEDULE_ID NUMERIC NOT NULL REFERENCES EVENT_SCHEDULE (ID),
  ACTIVE_FLAG       BOOLEAN NOT NULL
);

CREATE TABLE EVENT_PARTICIPANT_AUD
(
  ID                NUMERIC NOT NULL,
  PARTICIPANT_ID    NUMERIC NOT NULL REFERENCES APP_USER (ID),
  EVENT_SCHEDULE_ID NUMERIC NOT NULL REFERENCES EVENT_SCHEDULE (ID),
  ACTIVE_FLAG       BOOLEAN NOT NULL,
  VERSION_NUMBER    NUMERIC NOT NULL,
  TRANSACTION_TYPE  NUMERIC NOT NULL,
  CONSTRAINT EVENT_PARTICIPANT_PK PRIMARY KEY (ID, VERSION_NUMBER)
);
