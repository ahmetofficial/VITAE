-- Developer: Ahmet Kaymak
-- Date: 01.02.2017
-- Description: Veritabanı DRUG MODULE ile ilgili create script'i oluşturuldu.

-- tables
-- Table: DRUGS
CREATE TABLE DRUGS (
    drug_id int NOT NULL AUTO_INCREMENT,
    commercial_name varchar(100) NOT NULL,
    chemical_name varchar(200) NULL,
    type_id varchar(5) NOT NULL,
    prescription_type_id int NOT NULL,
    form_id int NOT NULL,
    company_id int NOT NULL,
    general_descriptions varchar(300) NULL,
    is_active bool NOT NULL,
    createdAt timestamp NOT NULL,
    updatedAt timestamp NOT NULL,
    CONSTRAINT DRUGS_pk PRIMARY KEY (drug_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Prescripton tipleri sayı değerleri alacak ve hangi değerin neye karşılık geldiğini veritabanı sözlüğüne yaz.';

-- Table: DRUG_COMPANIES
CREATE TABLE DRUG_COMPANIES (
    company_id int NOT NULL AUTO_INCREMENT,
    company_name varchar(200) NOT NULL,
    createdAt timestamp NOT NULL,
    updatedAt timestamp NOT NULL,
    CONSTRAINT DRUG_COMPANIES_pk PRIMARY KEY (company_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: FORM_OF_DRUGS
CREATE TABLE FORM_OF_DRUGS (
    form_id int NOT NULL,
    form_name varchar(50) NOT NULL,
    form_description varchar(200) NOT NULL,
    CONSTRAINT FORM_OF_DRUGS_pk PRIMARY KEY (form_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GENERAL_DRUG_TYPE_GROUPS
CREATE TABLE GENERAL_DRUG_TYPE_GROUPS (
    general_type_id varchar(2) NOT NULL,
    general_type_name varchar(50) NOT NULL,
    CONSTRAINT GENERAL_DRUG_TYPE_GROUPS_pk PRIMARY KEY (general_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: PRESCRIPTION_TYPE
CREATE TABLE PRESCRIPTION_TYPE (
    prescription_type_id int NOT NULL,
    prescription_type varchar(100) NOT NULL,
    CONSTRAINT PRESCRIPTION_TYPE_pk PRIMARY KEY (prescription_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: TYPES_OF_DRUGS
CREATE TABLE TYPES_OF_DRUGS (
    type_id varchar(5) NOT NULL,
    type_name varchar(100) NOT NULL,
    general_type_id varchar(2) NOT NULL,
    CONSTRAINT TYPES_OF_DRUGS_pk PRIMARY KEY (type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- End of file.

