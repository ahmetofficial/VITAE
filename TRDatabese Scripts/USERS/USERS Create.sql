-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-14 13:48:05.84
-- tables
-- Table: USERS
CREATE TABLE USERS (
   user_id varchar(30) NOT NULL,
   user_name varchar(300) NOT NULL,
   user_type_id int NOT NULL,
   mail varchar(50) NOT NULL,
   password varchar(50) NOT NULL,
   mail_activation bool NOT NULL,
   phone_number varchar(11) NULL,
   about_me varchar(200) NULL,
   friend_count int NOT NULL,
   is_official_user bool NOT NULL,
   profile_picture_id varchar(36) NOT NULL,
   created_at timestamp NOT NULL,
   updated_at timestamp NOT NULL,
   CONSTRAINT USERS_pk PRIMARY KEY (user_id),
   FULLTEXT KEY `user_id` (`user_id`),
   FULLTEXT KEY `user_name` (`user_name`),
   FULLTEXT KEY `mail` (`mail`)
) ENGINE MyISAM CHARACTER SET utf8 COLLATE utf8_unicode_ci;
-- End of file.