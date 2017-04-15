-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-04-13 23:09:12.767
-- tables
-- Table: HOSPITALS
CREATE TABLE HOSPITALS (
   hospital_id int NOT NULL,
   hospital_user_id varchar(200) NULL,
   hospital_name varchar(200) NOT NULL,
   hospital_type int NOT NULL,
   establishement_date date NULL,
   total_score bigint NOT NULL,
   total_vote_number int NOT NULL,
   overall_score double(10,5) NOT NULL,
   is_active bool NOT NULL,
   website varchar(100) NULL,
   phone_number varchar(13) NULL,
   mail varchar(50) NULL,
   created_at timestamp NOT NULL,
   updated_at timestamp NOT NULL,
   CONSTRAINT HOSPITALS_pk PRIMARY KEY (hospital_id),
   FULLTEXT KEY `hospital_name` (`hospital_name`)
)ENGINE=MyISAM, CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'hospital_type = 0 | devlet/state
hospital_type = 1 | özel/private
isActive = true | hastane kendi sayfasını yönetiyor.
isActive = false | hastanenin sistem tarafından bilgilarinin gösterildiği bir sayfası var.';
-- End of file.

