CREATE SEQUENCE  HIBERNATE_SEQUENCE  MINVALUE 0 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;
CREATE SEQUENCE  ERE_SEQ  MINVALUE 0 MAXVALUE 99999999999999 INCREMENT BY 1 START WITH 0 CACHE 20 NOORDER  NOCYCLE  NOPARTITION ;

CREATE TABLE ere_audit (
  version_number NUMBER NOT NULL PRIMARY KEY,
  event_type NUMBER DEFAULT NULL,
  version_time NUMBER DEFAULT NULL,
  user_id NUMBER DEFAULT NULL
);

CREATE TABLE address (
  address_id NUMBER NOT NULL PRIMARY KEY,
  type varchar(45),
  street varchar(45),
  city varchar(45),
  state varchar(45),
  postal_code varchar(45),
  active_flag char(1)
);
CREATE TABLE address_m (
  address_id NUMBER NOT NULL,
  type varchar(45),
  street varchar(45),
  city varchar(45),
  state varchar(45),
  postal_code varchar(45),
  active_flag char(1),
  version_number NUMBER NOT NULL  PRIMARY KEY,
  transaction_type NUMBER DEFAULT NULL
);

CREATE TABLE person (
  person_id NUMBER NOT NULL PRIMARY KEY,
  active_flag char(1) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL
);

CREATE TABLE person_m (
  person_id NUMBER NOT NULL,
  active_flag char(1) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  version_number NUMBER NOT NULL  PRIMARY KEY,
  transaction_type NUMBER DEFAULT NULL
);


CREATE TABLE person_address (
  address_id NUMBER NOT NULL,
  person_id NUMBER NOT NULL,
  active_flag char(1) DEFAULT 'Y',
  constraint person_address_pk primary key (address_id,person_id)
);

CREATE TABLE person_address_m (
  address_id NUMBER NOT NULL,
  person_id NUMBER NOT NULL,
  active_flag char(1) DEFAULT 'Y',
  version_number NUMBER NOT NULL  PRIMARY KEY,
  transaction_type NUMBER DEFAULT NULL
);


CREATE TABLE person_phone_number (
  person_phone_number_id NUMBER NOT NULL PRIMARY KEY,
  active_flag char(1) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  number_type NUMBER DEFAULT NULL,
  person_id NUMBER NOT NULL
);


CREATE TABLE person_phone_number_m (
  person_phone_number_id NUMBER NOT NULL,
  active_flag char(1) DEFAULT NULL,
  phone_number varchar(255) DEFAULT NULL,
  number_type NUMBER DEFAULT NULL,
  person_id NUMBER NOT NULL,
  version_number NUMBER NOT NULL  PRIMARY KEY,
  transaction_type NUMBER DEFAULT NULL
);


CREATE
  TABLE CUSTOM_EVENT
  (
    ID            NUMBER NOT NULL PRIMARY KEY,
    EVENT_VERSION NUMBER NOT NULL,
    EVENT_TYPE    NUMBER NOT NULL,
    EVENT_TIME    TIMESTAMP(6) NOT NULL
  ) ;
CREATE
  TABLE CUSTOM_EVENT_M
  (
    ID             NUMBER NOT NULL,
    EVENT_TYPE     NUMBER NOT NULL,
    EVENT_TIME     TIMESTAMP(6) NOT NULL,
    VERSION_NUMBER   NUMBER NOT NULL PRIMARY KEY,
    TRANSACTION_TYPE NUMBER DEFAULT NULL
  );