-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-10 22:10:25.525

-- tables
-- Table: CITIES
CREATE TABLE CITIES (
    city_id int NOT NULL,
    state_id int NOT NULL,
    city_name varchar(100) NOT NULL,
    CONSTRAINT CITIES_pk PRIMARY KEY (city_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'İlçeler';

-- Table: COUNTRIES
CREATE TABLE COUNTRIES (
    country_id int NOT NULL,
    binary_code varchar(2) NOT NULL,
    triple_code varchar(3) NOT NULL,
    country_name varchar(100) NOT NULL,
    phone_code int NOT NULL,
    CONSTRAINT COUNTRIES_pk PRIMARY KEY (country_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Ülkeler';

-- Table: NEIGHBORHOODS
CREATE TABLE NEIGHBORHOODS (
    neighborhood_id int NOT NULL,
    town_id int NOT NULL,
    neighborhood_name varchar(50) NOT NULL,
    zip_code int NOT NULL,
    CONSTRAINT NEIGHBORHOODS_pk PRIMARY KEY (neighborhood_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Mahalleler';

-- Table: STATES
CREATE TABLE STATES (
    state_id int NOT NULL,
    country_id int NOT NULL,
    state_name varchar(30) NOT NULL,
    state_code varchar(5) NOT NULL,
    phone_code int NOT NULL,
    CONSTRAINT STATES_pk PRIMARY KEY (state_id,country_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'İller';

-- Table: TOWNS
CREATE TABLE TOWNS (
    town_id int NOT NULL,
    city_id int NOT NULL,
    town_name varchar(100) NOT NULL,
    CONSTRAINT TOWNS_pk PRIMARY KEY (town_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Beldeler';

-- End of file.

