-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2017-01-26 16:39:48.35

-- tables
-- Table: ALBUMS
CREATE TABLE ALBUMS (
    album_id int NOT NULL AUTO_INCREMENT,
    album_name varchar(100) NOT NULL,
    created_time date NOT NULL,
    updated_time date NOT NULL,
    description varchar(300) NOT NULL,
    location_path varchar(200) NOT NULL,
    owner_id varchar(30) NOT NULL,
    subject_id int NULL,
    CONSTRAINT ALBUMS_pk PRIMARY KEY (album_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: ALBUMS_HAVE_PHOTOS
CREATE TABLE ALBUMS_HAVE_PHOTOS (
    album_id integer NOT NULL,
    photo_id integer NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: ALBUMS_HAVE_VOTES
CREATE TABLE ALBUMS_HAVE_VOTES (
    user_id varchar(30) NOT NULL,
    album_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: BLOCKED_USERS
CREATE TABLE BLOCKED_USERS (
    user_id varchar(30) NOT NULL,
    blocked_user_id varchar(30) NOT NULL,
    block_date date NOT NULL,
    block_reason_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcının(user_id),hangi kullanıcıları (blocked_user_id) engellediğinin tutulduğu tablodur.';

-- Table: BLOCK_REASON
CREATE TABLE BLOCK_REASON (
    block_reason_id int NOT NULL,
    description varchar(100) NOT NULL,
    CONSTRAINT BLOCK_REASON_pk PRIMARY KEY (block_reason_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcıların başka kullanıcıları neden engellediğinin kriterlerinin tutulduğu tablodur.';

-- Table: BLOOD_TYPES
CREATE TABLE BLOOD_TYPES (
    blood_type_id int NOT NULL,
    blood_type varchar(10) NOT NULL,
    rh_factor varchar(5) NOT NULL,
    CONSTRAINT BLOOD_TYPES_pk PRIMARY KEY (blood_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: BODY_SYSTEMS
CREATE TABLE BODY_SYSTEMS (
    body_system_id int NOT NULL AUTO_INCREMENT,
    system_name varchar(100) NOT NULL,
    CONSTRAINT BODY_SYSTEMS_pk PRIMARY KEY (body_system_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: BRANCHS
CREATE TABLE BRANCHS (
    branch_id int NOT NULL AUTO_INCREMENT,
    branch_name varchar(200) NOT NULL,
    clinic_id int NOT NULL,
    CONSTRAINT BRANCHS_pk PRIMARY KEY (branch_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: BRANCHS_HAVE_DISEASES
CREATE TABLE BRANCHS_HAVE_DISEASES (
    branch_id int NOT NULL,
    disease_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: CITIES
CREATE TABLE CITIES (
    city_id int NOT NULL,
    city_name varchar(150) NOT NULL,
    province_id int NOT NULL,
    CONSTRAINT CITIES_pk PRIMARY KEY (city_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: CLINICS
CREATE TABLE CLINICS (
    clinic_id int NOT NULL AUTO_INCREMENT,
    clinic_name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    CONSTRAINT CLINICS_pk PRIMARY KEY (clinic_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: CLINICS_HAVE_DISEASE
CREATE TABLE CLINICS_HAVE_DISEASE (
    disease_id int NOT NULL,
    clinic_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: COUNTRIES
CREATE TABLE COUNTRIES (
    country_id int NOT NULL,
    country_name varchar(300) NOT NULL,
    country_code varchar(5) NOT NULL,
    country_phone_code int NOT NULL,
    has_states bool NOT NULL,
    CONSTRAINT COUNTRIES_pk PRIMARY KEY (country_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISEASES
CREATE TABLE DISEASES (
    disease_id int NOT NULL AUTO_INCREMENT,
    disease_name varchar(255) NOT NULL,
    disease_latin_name varchar(200) NULL,
    disease_reason_id int NOT NULL,
    incubation_period_in_days int NULL,
    is_chronic bool NOT NULL,
    gender_factor varchar(10) NOT NULL,
    rate_of_appearance double(10,5) NULL,
    body_system_id integer NOT NULL,
    organ_id integer NOT NULL,
    CONSTRAINT DISEASES_pk PRIMARY KEY (disease_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISEASE_HAVE_DRUGS
CREATE TABLE DISEASE_HAVE_DRUGS (
    disease_id int NOT NULL,
    drug_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISEASE_HAVE_THREATMENT
CREATE TABLE DISEASE_HAVE_THREATMENT (
    disease_id int NOT NULL,
    treatment_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISEASE_LEVEL
CREATE TABLE DISEASE_LEVEL (
    disease_level_id int NOT NULL,
    disease_level_name varchar(100) NOT NULL,
    CONSTRAINT DISEASE_LEVEL_pk PRIMARY KEY (disease_level_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISEASE_PRIOR_REASON
CREATE TABLE DISEASE_PRIOR_REASON (
    disease_reason_id int NOT NULL,
    reason_name varchar(100) NOT NULL,
    CONSTRAINT DISEASE_PRIOR_REASON_pk PRIMARY KEY (disease_reason_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DISTRICT
CREATE TABLE DISTRICT (
    distict_id int NOT NULL,
    district_name varchar(300) NOT NULL,
    province_id int NOT NULL,
    postal_code_id int NOT NULL,
    CONSTRAINT DISTRICT_pk PRIMARY KEY (distict_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DOCTORS
CREATE TABLE DOCTORS (
    user_id varchar(30) NOT NULL,
    user_last_name varchar(150) NOT NULL,
    gender varchar(20) NOT NULL,
    branch_id int NOT NULL,
    doctor_type_id int NOT NULL,
    birthday date NOT NULL,
    blood_type_id int NOT NULL,
    CONSTRAINT DOCTORS_pk PRIMARY KEY (user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DOCTOR_TYPES
CREATE TABLE DOCTOR_TYPES (
    doctor_type_id int NOT NULL,
    doctor_type varchar(100) NOT NULL,
    CONSTRAINT DOCTOR_TYPES_pk PRIMARY KEY (doctor_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

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
    CONSTRAINT DRUGS_pk PRIMARY KEY (drug_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Prescripton tipleri sayı değerleri alacak ve hangi değerin neye karşılık geldiğini veritabanı sözlüğüne yaz.';

-- Table: DRUG_COMPANIES
CREATE TABLE DRUG_COMPANIES (
    company_id int NOT NULL AUTO_INCREMENT,
    company_name varchar(200) NOT NULL,
    CONSTRAINT DRUG_COMPANIES_pk PRIMARY KEY (company_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DRUG_DOSE_AMOUNT_HISTORY
CREATE TABLE DRUG_DOSE_AMOUNT_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    drug_id int NOT NULL,
    drug_dose_in_miligrams int NOT NULL,
    drug_dose_start_date date NOT NULL,
    drug_dose_finish_date date NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DRUG_USAGE_GAP_PERIODS
CREATE TABLE DRUG_USAGE_GAP_PERIODS (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    drug_id int NOT NULL,
    drug_usage_gap_start_date date NOT NULL,
    drug_usage_gap_finish_date date NOT NULL,
    CONSTRAINT DRUG_USAGE_GAP_PERIODS_pk PRIMARY KEY (drug_usage_gap_finish_date)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: DRUG_USAGE_STATE_HISTORY
CREATE TABLE DRUG_USAGE_STATE_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    drug_id int NOT NULL,
    drug_state_id int NOT NULL,
    drug_state_start_date date NOT NULL,
    drug_state_finish_date date NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENTS
CREATE TABLE EVENTS (
    event_id int NOT NULL AUTO_INCREMENT,
    event_type_id int NOT NULL,
    subject_id int NOT NULL,
    event_name varchar(200) NOT NULL,
    event_description varchar(300) NOT NULL,
    event_date date NOT NULL,
    event_create_time timestamp NOT NULL,
    participation_count int NOT NULL,
    owner_id varchar(30) NOT NULL,
    event_photo_id integer NOT NULL,
    event_general_rate double(10,5) NOT NULL,
    CONSTRAINT EVENTS_pk PRIMARY KEY (event_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENTS_HAVE_POSTS
CREATE TABLE EVENTS_HAVE_POSTS (
    event_id int NOT NULL,
    post_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENTS_HAVE_USERS
CREATE TABLE EVENTS_HAVE_USERS (
    event_id int NOT NULL,
    user_id varchar(30) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENTS_RATES
CREATE TABLE EVENTS_RATES (
    event_id int NOT NULL,
    user_id varchar(30) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENT_ADRESS
CREATE TABLE EVENT_ADRESS (
    event_id int NOT NULL,
    country_id int NOT NULL,
    state_id int NOT NULL,
    province_id int NOT NULL,
    city_id int NOT NULL,
    distict_id int NOT NULL,
    street_adress varchar(250) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENT_REQUESTS
CREATE TABLE EVENT_REQUESTS (
    sender_user_id varchar(30) NOT NULL,
    receiver_user_id varchar(30) NOT NULL,
    event_id int NOT NULL,
    request_time timestamp NOT NULL,
    ip varchar(30) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: EVENT_TYPE
CREATE TABLE EVENT_TYPE (
    event_type_id int NOT NULL,
    event_type_name varchar(50) NULL,
    CONSTRAINT EVENT_TYPE_pk PRIMARY KEY (event_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: FORM_OF_DRUGS
CREATE TABLE FORM_OF_DRUGS (
    form_id int NOT NULL,
    form_name varchar(50) NOT NULL,
    form_description varchar(200) NOT NULL,
    CONSTRAINT FORM_OF_DRUGS_pk PRIMARY KEY (form_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GENERAL_TYPE_GROUPS
CREATE TABLE GENERAL_TYPE_GROUPS (
    general_type_id varchar(2) NOT NULL,
    general_type_name varchar(50) NOT NULL,
    CONSTRAINT GENERAL_TYPE_GROUPS_pk PRIMARY KEY (general_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUPS
CREATE TABLE GROUPS (
    group_id int NOT NULL,
    subject_id int NOT NULL,
    group_name varchar(255) NOT NULL,
    group_type_id int NOT NULL,
    created_time date NOT NULL,
    admin_id varchar(30) NOT NULL,
    description varchar(300) NOT NULL,
    group_photo_id int NOT NULL,
    is_private bool NOT NULL,
    CONSTRAINT GROUPS_pk PRIMARY KEY (group_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUPS_HAS_USERS
CREATE TABLE GROUPS_HAS_USERS (
    group_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    CONSTRAINT GROUPS_HAS_USERS_pk PRIMARY KEY (group_id,user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUPS_POSTS
CREATE TABLE GROUPS_POSTS (
    group_id int NOT NULL,
    post_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUPS_RATES
CREATE TABLE GROUPS_RATES (
    group_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    user_comment varchar(300) NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUPS_REQUEST
CREATE TABLE GROUPS_REQUEST (
    sender_user_id varchar(30) NOT NULL,
    receiver_user_id varchar(30) NOT NULL,
    group_id int NOT NULL,
    ip varchar(30) NOT NULL,
    request_time timestamp NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: GROUP_TYPES
CREATE TABLE GROUP_TYPES (
    type_id int NOT NULL,
    type_name varchar(30) NOT NULL,
    CONSTRAINT GROUP_TYPES_pk PRIMARY KEY (type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITALS
CREATE TABLE HOSPITALS (
    hospital_id int NOT NULL,
    hospital_code int NOT NULL,
    hospital_type_id int NOT NULL,
    street_adress varchar(200) NOT NULL,
    establishement_date date NOT NULL,
    total_score bigint NOT NULL,
    total_vote int NOT NULL,
    overall_score double(10,5) NOT NULL,
    isActive bool NOT NULL,
    website varchar(100) NOT NULL,
    phone_number varchar(20) NOT NULL,
    CONSTRAINT HOSPITALS_pk PRIMARY KEY (hospital_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITALS_HAVE_CLINICS
CREATE TABLE HOSPITALS_HAVE_CLINICS (
    hospital_id int NOT NULL,
    clinic_id int NOT NULL,
    total_vote int NOT NULL,
    vote_count bigint NOT NULL,
    overall_score double(10,5) NOT NULL,
    comment varchar(300) NOT NULL,
    CONSTRAINT HOSPITALS_HAVE_CLINICS_pk PRIMARY KEY (hospital_id,clinic_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITALS_HAVE_DOCTORS
CREATE TABLE HOSPITALS_HAVE_DOCTORS (
    hospital_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    clinic_id int NOT NULL,
    hiring_date date NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITAL_ADRESS
CREATE TABLE HOSPITAL_ADRESS (
    hospital_id int NOT NULL,
    country_id int NOT NULL,
    state_id int NOT NULL,
    province_id int NOT NULL,
    city_id int NOT NULL,
    distict_id int NOT NULL,
    street_adress varchar(250) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITAL_TYPE
CREATE TABLE HOSPITAL_TYPE (
    hospital_type_id integer NOT NULL,
    hospital_type varchar(100) NOT NULL,
    CONSTRAINT HOSPITAL_TYPE_pk PRIMARY KEY (hospital_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: HOSPITAL_USERS
CREATE TABLE HOSPITAL_USERS (
    user_id varchar(30) NOT NULL,
    hospital_id int NOT NULL,
    CONSTRAINT HOSPITAL_USERS_pk PRIMARY KEY (user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: INSTITUTES_FOCUS_AREA
CREATE TABLE INSTITUTES_FOCUS_AREA (
    focus_area_id int NOT NULL,
    focus_area int NOT NULL,
    subject_id int NOT NULL,
    CONSTRAINT INSTITUTES_FOCUS_AREA_pk PRIMARY KEY (focus_area_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: INSTITUTE_TYPES
CREATE TABLE INSTITUTE_TYPES (
    institute_type_id int NOT NULL,
    institute_type_name varchar(50) NOT NULL,
    CONSTRAINT INSTITUTE_TYPES_pk PRIMARY KEY (institute_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: INSTITUTE_USERS
CREATE TABLE INSTITUTE_USERS (
    user_id varchar(30) NOT NULL,
    institute_type_id int NOT NULL,
    focus_area_id int NOT NULL,
    establishment_date date NOT NULL,
    website varchar(100) NOT NULL,
    CONSTRAINT INSTITUTE_USERS_pk PRIMARY KEY (user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: LANGUAGES
CREATE TABLE LANGUAGES (
    language_id int NOT NULL,
    language_name varchar(50) NOT NULL,
    CONSTRAINT LANGUAGES_pk PRIMARY KEY (language_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: MESSAGES
CREATE TABLE MESSAGES (
    conversation_id int NOT NULL AUTO_INCREMENT,
    sender_user_id varchar(30) NOT NULL,
    receiver_user_id varchar(30) NOT NULL,
    ip varchar(30) NOT NULL,
    time timestamp NOT NULL,
    status int NOT NULL,
    CONSTRAINT MESSAGES_pk PRIMARY KEY (conversation_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: MESSAGES_HAVE_PHOTOS
CREATE TABLE MESSAGES_HAVE_PHOTOS (
    conversation_reply_id int NOT NULL,
    photo_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: MESSAGES_REPLY
CREATE TABLE MESSAGES_REPLY (
    conversation_reply_id int NOT NULL AUTO_INCREMENT,
    reply_text varchar(300) NOT NULL,
    user_id varchar(30) NOT NULL,
    ip varchar(30) NOT NULL,
    time timestamp NOT NULL,
    status int NOT NULL,
    conversation_id int NOT NULL,
    CONSTRAINT MESSAGES_REPLY_pk PRIMARY KEY (conversation_reply_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: ORGANS
CREATE TABLE ORGANS (
    organ_id int NOT NULL AUTO_INCREMENT,
    organ_name varchar(200) NOT NULL,
    body_system_id int NOT NULL,
    CONSTRAINT ORGANS_pk PRIMARY KEY (organ_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: PATIENTS
CREATE TABLE PATIENTS (
    user_id varchar(30) NOT NULL,
    user_last_name varchar(150) NOT NULL,
    gender varchar(20) NOT NULL,
    blood_type_id int NOT NULL,
    birthday date NOT NULL,
    CONSTRAINT PATIENTS_pk PRIMARY KEY (user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: PHOTOS
CREATE TABLE PHOTOS (
    photo_id int NOT NULL AUTO_INCREMENT,
    description varchar(400) NOT NULL,
    created_time date NOT NULL,
    owner_id varchar(30) NOT NULL,
    location_path varchar(200) NOT NULL,
    subject_id int NULL,
    CONSTRAINT PHOTOS_pk PRIMARY KEY (photo_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: PHOTOS_HAVE_VOTES
CREATE TABLE PHOTOS_HAVE_VOTES (
    photo_id int NOT NULL,
    user_id varchar(30) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: POSTAL_CODE
CREATE TABLE POSTAL_CODE (
    postal_code_id int NOT NULL,
    code int NOT NULL,
    CONSTRAINT POSTAL_CODE_pk PRIMARY KEY (postal_code_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: POST_COMMENTS
CREATE TABLE POST_COMMENTS (
    comment_id int NOT NULL,
    post_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    text varchar(500) NOT NULL,
    comment_time timestamp NOT NULL,
    user_ip varchar(30) NOT NULL,
    CONSTRAINT POST_COMMENTS_pk PRIMARY KEY (comment_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcı paylaşımlarına yapılan yorumların tutulduğu tablodur.';

-- Table: POST_COMMENTS_HAVE_PHOTOS
CREATE TABLE POST_COMMENTS_HAVE_PHOTOS (
    comment_id int NOT NULL,
    photo_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: POST_VOTES
CREATE TABLE POST_VOTES (
    vote_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    post_id int NOT NULL,
    CONSTRAINT POST_VOTES_pk PRIMARY KEY (vote_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcı paylaşımalarının beğenmelerinin tutultuğu tablodur.';

-- Table: PRESCRIPTION_TYPE
CREATE TABLE PRESCRIPTION_TYPE (
    prescription_type_id int NOT NULL,
    prescription_type varchar(100) NOT NULL,
    CONSTRAINT PRESCRIPTION_TYPE_pk PRIMARY KEY (prescription_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: PROVINCES
CREATE TABLE PROVINCES (
    province_id int NOT NULL,
    state_id int NOT NULL,
    province_name varchar(200) NOT NULL,
    CONSTRAINT PROVINCES_pk PRIMARY KEY (province_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: RELATIONSHIPS
CREATE TABLE RELATIONSHIPS (
    active_user_id varchar(30) NOT NULL,
    passive_user_id varchar(30) NOT NULL,
    status_id int NOT NULL,
    date timestamp NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Esas olarak kullanıcıların arkadaşlarınıN tutulduğu tablodur.
Status_Id = 1 first user ve second user arkadaş
active_user_id: işlemi yapan kişinin_id(arkadaşlık isteği gönderen kişinin id''''si)';

-- Table: STATE
CREATE TABLE STATE (
    state_id int NOT NULL,
    state_name varchar(20) NOT NULL,
    CONSTRAINT STATE_pk PRIMARY KEY (state_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'States | 1:başlangıç  2:devam  3:bırakıldı 4:tekrar başlangıç 5:bitiş';

-- Table: STATES
CREATE TABLE STATES (
    state_id int NOT NULL,
    state_name varchar(300) NOT NULL,
    state_code varchar(5) NOT NULL,
    country_id int NOT NULL,
    CONSTRAINT STATES_pk PRIMARY KEY (state_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: STATUS
CREATE TABLE STATUS (
    status_id int NOT NULL,
    status_description varchar(15) NOT NULL,
    CONSTRAINT STATUS_pk PRIMARY KEY (status_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcıların birbirleriyle ilgili durumlarının tanımlandığı tablodur - Arkadaşlık isteği için:
- Pending: İstek gönderildi
- Accepted: Kabul edildi
- Cancelled ...

vb';

-- Table: TREATMENTS
CREATE TABLE TREATMENTS (
    treatment_id int NOT NULL AUTO_INCREMENT,
    treatment_name varchar(150) NOT NULL,
    age_range varchar(10) NULL,
    avarage_period_in_days int NULL,
    body_system_id int NOT NULL,
    organ_id int NOT NULL,
    avarage_succes_rate double(10,5) NULL,
    CONSTRAINT TREATMENTS_pk PRIMARY KEY (treatment_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: TREATMENTS_HAVE_DRUGS
CREATE TABLE TREATMENTS_HAVE_DRUGS (
    drug_id int NOT NULL,
    treatment_id integer NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: TREATMENT_GAP_PERIODS
CREATE TABLE TREATMENT_GAP_PERIODS (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    gap_start_date date NOT NULL,
    gap_finish_date date NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: TREATMENT_STATE_HISTORY
CREATE TABLE TREATMENT_STATE_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    treatment_state_id int NOT NULL,
    treatment_state_start_date date NOT NULL,
    treatment_state_finish_date date NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: TYPES_OF_DRUGS
CREATE TABLE TYPES_OF_DRUGS (
    type_id varchar(5) NOT NULL,
    type_name varchar(100) NOT NULL,
    general_type_id varchar(2) NOT NULL,
    CONSTRAINT TYPES_OF_DRUGS_pk PRIMARY KEY (type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USERS
CREATE TABLE USERS (
    user_id varchar(30) NOT NULL,
    user_name varchar(250) NOT NULL,
    user_type_id int NOT NULL,
    mail varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    mail_activation bool NOT NULL,
    created_time date NOT NULL,
    phone_number varchar(11) NULL,
    about_me varchar(200) NULL,
    friend_count int NOT NULL,
    is_official_user bool NOT NULL,
    relationship_status_id integer NOT NULL CHECK (user_type_id!=4 && user_type_id!=3),
    CONSTRAINT USERS_pk PRIMARY KEY (user_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USERS_HAVE_HOSPITAL_CLINICS
CREATE TABLE USERS_HAVE_HOSPITAL_CLINICS (
    user_id varchar(30) NOT NULL,
    hospital_id int NOT NULL,
    clinic_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USERS_HAVE_LANGUAGES
CREATE TABLE USERS_HAVE_LANGUAGES (
    language_id int NOT NULL,
    user_id varchar(30) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USERS_HOSPITAL_RATES
CREATE TABLE USERS_HOSPITAL_RATES (
    user_id varchar(30) NOT NULL,
    user_rate int NOT NULL,
    hospital_id int NOT NULL,
    clinic_id int NOT NULL,
    user_comment varchar(300) NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullacıların hastanelerin kniklerine olan oylamalarının tutulduğu tablodur.';

-- Table: USERS_MARKED_POSTS
CREATE TABLE USERS_MARKED_POSTS (
    user_id varchar(30) NOT NULL,
    post_id int NOT NULL,
    importance_level int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcıların önemli bularak işaretledikleri paylaşımların tutulduğu tablodur.';

-- Table: USER_ADRESS
CREATE TABLE USER_ADRESS (
    user_id varchar(30) NOT NULL,
    country_id int NOT NULL,
    state_id int NOT NULL,
    province_id int NOT NULL,
    city_id int NOT NULL,
    distict_id int NOT NULL,
    street_adress varchar(250) NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USER_DRUG_USAGE_HISTORY
CREATE TABLE USER_DRUG_USAGE_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    drug_id int NOT NULL,
    drug_usage_start_date date NOT NULL,
    drug_usage_sys_reg_date date NOT NULL,
    drug_usage_finish_date date NULL,
    drug_effect_on_disease int NOT NULL,
    CONSTRAINT USER_DRUG_USAGE_HISTORY_pk PRIMARY KEY (user_id,disease_id,treatment_id,drug_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USER_HEALTH_HISTORY
CREATE TABLE USER_HEALTH_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    disease_diagnosis_date date NOT NULL,
    disease_system_reg_date date NOT NULL,
    disease_finish_date date NULL,
    disease_level_id int NOT NULL,
    disease_state_id int NOT NULL,
    CONSTRAINT USER_HEALTH_HISTORY_pk PRIMARY KEY (user_id,disease_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USER_POSTS
CREATE TABLE USER_POSTS (
    post_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    user_ip varchar(30) NOT NULL,
    created_time timestamp NOT NULL,
    vote_up_number int NOT NULL,
    subject_id int NULL,
    CONSTRAINT USER_POSTS_pk PRIMARY KEY (post_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcılara ait paylaşımların tutulduğu tablodur.';

-- Table: USER_POSTS_HAVE_PHOTOS
CREATE TABLE USER_POSTS_HAVE_PHOTOS (
    post_id int NOT NULL,
    photo_id int NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USER_PRIVACY_OPTIONS
CREATE TABLE USER_PRIVACY_OPTIONS (
    user_id varchar(30) NOT NULL,
    profile_privacy_state bool NOT NULL,
    friends_request_state bool NOT NULL,
    location_privacy_state bool NOT NULL,
    message_privacy_state bool NOT NULL
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcı gizlilik bilgileri tutulduğu tablodur.';

-- Table: USER_RELATIONSHIP_STATUS
CREATE TABLE USER_RELATIONSHIP_STATUS (
    relationship_status_id int NOT NULL,
    relationship_status varchar(30) NOT NULL,
    CONSTRAINT USER_RELATIONSHIP_STATUS_pk PRIMARY KEY (relationship_status_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Kullanıcıların ilişki durumlarının (evli,bekar vb) tanımlandığı tablodur.';

-- Table: USER_TREATMENT_HISTORY
CREATE TABLE USER_TREATMENT_HISTORY (
    user_id varchar(30) NOT NULL,
    disease_id int NOT NULL,
    treatment_id int NOT NULL,
    treatment_start_date date NOT NULL,
    treatment_sys_reg_date date NOT NULL,
    treatment_finish_date date NOT NULL,
    treatment_effect_on_disease int NULL,
    CONSTRAINT USER_TREATMENT_HISTORY_pk PRIMARY KEY (user_id,disease_id,treatment_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci;

-- Table: USER_TYPES
CREATE TABLE USER_TYPES (
    user_type_id int NOT NULL,
    user_type_name varchar(20) NOT NULL,
    CONSTRAINT USER_TYPES_pk PRIMARY KEY (user_type_id)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_unicode_ci COMMENT 'Hasta, doktor, hastane vb kullanıcı tiplerinin tutulduğu tablodur.';

-- foreign keys
-- Reference: ALBUMS_HAVE_PHOTOS_ALBUMS (table: ALBUMS_HAVE_PHOTOS)
ALTER TABLE ALBUMS_HAVE_PHOTOS ADD CONSTRAINT ALBUMS_HAVE_PHOTOS_ALBUMS FOREIGN KEY ALBUMS_HAVE_PHOTOS_ALBUMS (album_id)
    REFERENCES ALBUMS (album_id);

-- Reference: ALBUMS_HAVE_PHOTOS_PHOTOS (table: ALBUMS_HAVE_PHOTOS)
ALTER TABLE ALBUMS_HAVE_PHOTOS ADD CONSTRAINT ALBUMS_HAVE_PHOTOS_PHOTOS FOREIGN KEY ALBUMS_HAVE_PHOTOS_PHOTOS (photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: ALBUMS_HAVE_VOTES_ALBUMS (table: ALBUMS_HAVE_VOTES)
ALTER TABLE ALBUMS_HAVE_VOTES ADD CONSTRAINT ALBUMS_HAVE_VOTES_ALBUMS FOREIGN KEY ALBUMS_HAVE_VOTES_ALBUMS (album_id)
    REFERENCES ALBUMS (album_id);

-- Reference: ALBUMS_HAVE_VOTES_USERS (table: ALBUMS_HAVE_VOTES)
ALTER TABLE ALBUMS_HAVE_VOTES ADD CONSTRAINT ALBUMS_HAVE_VOTES_USERS FOREIGN KEY ALBUMS_HAVE_VOTES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: ALBUMS_USERS (table: ALBUMS)
ALTER TABLE ALBUMS ADD CONSTRAINT ALBUMS_USERS FOREIGN KEY ALBUMS_USERS (owner_id)
    REFERENCES USERS (user_id);

-- Reference: BLOCKED_USERS_BLOCKED_USER_ID (table: BLOCKED_USERS)
ALTER TABLE BLOCKED_USERS ADD CONSTRAINT BLOCKED_USERS_BLOCKED_USER_ID FOREIGN KEY BLOCKED_USERS_BLOCKED_USER_ID (blocked_user_id)
    REFERENCES USERS (user_id);

-- Reference: BLOCKED_USERS_BLOCK_REASON (table: BLOCKED_USERS)
ALTER TABLE BLOCKED_USERS ADD CONSTRAINT BLOCKED_USERS_BLOCK_REASON FOREIGN KEY BLOCKED_USERS_BLOCK_REASON (block_reason_id)
    REFERENCES BLOCK_REASON (block_reason_id);

-- Reference: BLOCKED_USERS_USER_ID (table: BLOCKED_USERS)
ALTER TABLE BLOCKED_USERS ADD CONSTRAINT BLOCKED_USERS_USER_ID FOREIGN KEY BLOCKED_USERS_USER_ID (user_id)
    REFERENCES USERS (user_id);

-- Reference: BRANCHS_CLINICS (table: BRANCHS)
ALTER TABLE BRANCHS ADD CONSTRAINT BRANCHS_CLINICS FOREIGN KEY BRANCHS_CLINICS (clinic_id)
    REFERENCES CLINICS (clinic_id);

-- Reference: BRANCHS_HAVE_DISEASES_BRANCHS (table: BRANCHS_HAVE_DISEASES)
ALTER TABLE BRANCHS_HAVE_DISEASES ADD CONSTRAINT BRANCHS_HAVE_DISEASES_BRANCHS FOREIGN KEY BRANCHS_HAVE_DISEASES_BRANCHS (branch_id)
    REFERENCES BRANCHS (branch_id);

-- Reference: BRANCHS_HAVE_DISEASES_DISEASES (table: BRANCHS_HAVE_DISEASES)
ALTER TABLE BRANCHS_HAVE_DISEASES ADD CONSTRAINT BRANCHS_HAVE_DISEASES_DISEASES FOREIGN KEY BRANCHS_HAVE_DISEASES_DISEASES (disease_id)
    REFERENCES DISEASES (disease_id);

-- Reference: CITIES_PROVINCES (table: CITIES)
ALTER TABLE CITIES ADD CONSTRAINT CITIES_PROVINCES FOREIGN KEY CITIES_PROVINCES (province_id)
    REFERENCES PROVINCES (province_id);

-- Reference: CITIES_STATES (table: PROVINCES)
ALTER TABLE PROVINCES ADD CONSTRAINT CITIES_STATES FOREIGN KEY CITIES_STATES (state_id)
    REFERENCES STATES (state_id);

-- Reference: CONVERSATION_REPLY_USERS (table: MESSAGES_REPLY)
ALTER TABLE MESSAGES_REPLY ADD CONSTRAINT CONVERSATION_REPLY_USERS FOREIGN KEY CONVERSATION_REPLY_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: CONVERSATION_USERS_FIRST_USER (table: MESSAGES)
ALTER TABLE MESSAGES ADD CONSTRAINT CONVERSATION_USERS_FIRST_USER FOREIGN KEY CONVERSATION_USERS_FIRST_USER (sender_user_id)
    REFERENCES USERS (user_id);

-- Reference: CONVERSATION_USERS_SECOND_USER (table: MESSAGES)
ALTER TABLE MESSAGES ADD CONSTRAINT CONVERSATION_USERS_SECOND_USER FOREIGN KEY CONVERSATION_USERS_SECOND_USER (receiver_user_id)
    REFERENCES USERS (user_id);

-- Reference: CONV_REPLY_CONV_ID (table: MESSAGES_REPLY)
ALTER TABLE MESSAGES_REPLY ADD CONSTRAINT CONV_REPLY_CONV_ID FOREIGN KEY CONV_REPLY_CONV_ID (conversation_id)
    REFERENCES MESSAGES (conversation_id);

-- Reference: DISEASES_BODY_SYSTEMS (table: DISEASES)
ALTER TABLE DISEASES ADD CONSTRAINT DISEASES_BODY_SYSTEMS FOREIGN KEY DISEASES_BODY_SYSTEMS (body_system_id)
    REFERENCES BODY_SYSTEMS (body_system_id);

-- Reference: DISEASES_DISEASE_PRIOR_REASON (table: DISEASES)
ALTER TABLE DISEASES ADD CONSTRAINT DISEASES_DISEASE_PRIOR_REASON FOREIGN KEY DISEASES_DISEASE_PRIOR_REASON (disease_reason_id)
    REFERENCES DISEASE_PRIOR_REASON (disease_reason_id);

-- Reference: DISEASES_HAVE_CLINIC_CLINICS (table: CLINICS_HAVE_DISEASE)
ALTER TABLE CLINICS_HAVE_DISEASE ADD CONSTRAINT DISEASES_HAVE_CLINIC_CLINICS FOREIGN KEY DISEASES_HAVE_CLINIC_CLINICS (clinic_id)
    REFERENCES CLINICS (clinic_id);

-- Reference: DISEASES_HAVE_CLINIC_DISEASES (table: CLINICS_HAVE_DISEASE)
ALTER TABLE CLINICS_HAVE_DISEASE ADD CONSTRAINT DISEASES_HAVE_CLINIC_DISEASES FOREIGN KEY DISEASES_HAVE_CLINIC_DISEASES (disease_id)
    REFERENCES DISEASES (disease_id);

-- Reference: DISEASES_ORGANS (table: DISEASES)
ALTER TABLE DISEASES ADD CONSTRAINT DISEASES_ORGANS FOREIGN KEY DISEASES_ORGANS (organ_id)
    REFERENCES ORGANS (organ_id);

-- Reference: DISEASE_HAVE_DRUGS_DISEASES (table: DISEASE_HAVE_DRUGS)
ALTER TABLE DISEASE_HAVE_DRUGS ADD CONSTRAINT DISEASE_HAVE_DRUGS_DISEASES FOREIGN KEY DISEASE_HAVE_DRUGS_DISEASES (disease_id)
    REFERENCES DISEASES (disease_id);

-- Reference: DISEASE_HAVE_DRUGS_DRUGS (table: DISEASE_HAVE_DRUGS)
ALTER TABLE DISEASE_HAVE_DRUGS ADD CONSTRAINT DISEASE_HAVE_DRUGS_DRUGS FOREIGN KEY DISEASE_HAVE_DRUGS_DRUGS (drug_id)
    REFERENCES DRUGS (drug_id);

-- Reference: DISEASE_HAVE_THREATMENT_DISEASES (table: DISEASE_HAVE_THREATMENT)
ALTER TABLE DISEASE_HAVE_THREATMENT ADD CONSTRAINT DISEASE_HAVE_THREATMENT_DISEASES FOREIGN KEY DISEASE_HAVE_THREATMENT_DISEASES (disease_id)
    REFERENCES DISEASES (disease_id);

-- Reference: DISEASE_HAVE_THREATMENT_TREATMENTS (table: DISEASE_HAVE_THREATMENT)
ALTER TABLE DISEASE_HAVE_THREATMENT ADD CONSTRAINT DISEASE_HAVE_THREATMENT_TREATMENTS FOREIGN KEY DISEASE_HAVE_THREATMENT_TREATMENTS (treatment_id)
    REFERENCES TREATMENTS (treatment_id);

-- Reference: DISTRICT_POSTAL_CODE (table: DISTRICT)
ALTER TABLE DISTRICT ADD CONSTRAINT DISTRICT_POSTAL_CODE FOREIGN KEY DISTRICT_POSTAL_CODE (postal_code_id)
    REFERENCES POSTAL_CODE (postal_code_id);

-- Reference: DISTRICT_PROVINCES (table: DISTRICT)
ALTER TABLE DISTRICT ADD CONSTRAINT DISTRICT_PROVINCES FOREIGN KEY DISTRICT_PROVINCES (province_id)
    REFERENCES CITIES (city_id);

-- Reference: DOCTORS_BLOOD_TYPES (table: DOCTORS)
ALTER TABLE DOCTORS ADD CONSTRAINT DOCTORS_BLOOD_TYPES FOREIGN KEY DOCTORS_BLOOD_TYPES (blood_type_id)
    REFERENCES BLOOD_TYPES (blood_type_id);

-- Reference: DOCTORS_BRANCHS (table: DOCTORS)
ALTER TABLE DOCTORS ADD CONSTRAINT DOCTORS_BRANCHS FOREIGN KEY DOCTORS_BRANCHS (branch_id)
    REFERENCES BRANCHS (branch_id);

-- Reference: DOCTORS_DOCTOR_TYPES (table: DOCTORS)
ALTER TABLE DOCTORS ADD CONSTRAINT DOCTORS_DOCTOR_TYPES FOREIGN KEY DOCTORS_DOCTOR_TYPES (doctor_type_id)
    REFERENCES DOCTOR_TYPES (doctor_type_id);

-- Reference: DOCTORS_HAVE_HOSPITAL_DOCTORS (table: HOSPITALS_HAVE_DOCTORS)
ALTER TABLE HOSPITALS_HAVE_DOCTORS ADD CONSTRAINT DOCTORS_HAVE_HOSPITAL_DOCTORS FOREIGN KEY DOCTORS_HAVE_HOSPITAL_DOCTORS (user_id)
    REFERENCES DOCTORS (user_id);

-- Reference: DOCTORS_HAVE_HOSPITAL_HOSPITALS (table: HOSPITALS_HAVE_DOCTORS)
ALTER TABLE HOSPITALS_HAVE_DOCTORS ADD CONSTRAINT DOCTORS_HAVE_HOSPITAL_HOSPITALS FOREIGN KEY DOCTORS_HAVE_HOSPITAL_HOSPITALS (hospital_id)
    REFERENCES HOSPITALS (hospital_id);

-- Reference: DOCTORS_USERS (table: DOCTORS)
ALTER TABLE DOCTORS ADD CONSTRAINT DOCTORS_USERS FOREIGN KEY DOCTORS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: DRUGS_DRUG_COMPANIES (table: DRUGS)
ALTER TABLE DRUGS ADD CONSTRAINT DRUGS_DRUG_COMPANIES FOREIGN KEY DRUGS_DRUG_COMPANIES (company_id)
    REFERENCES DRUG_COMPANIES (company_id);

-- Reference: DRUGS_FORM_OF_DRUGS (table: DRUGS)
ALTER TABLE DRUGS ADD CONSTRAINT DRUGS_FORM_OF_DRUGS FOREIGN KEY DRUGS_FORM_OF_DRUGS (form_id)
    REFERENCES FORM_OF_DRUGS (form_id);

-- Reference: DRUGS_PRESCRIPTION_TYPE (table: DRUGS)
ALTER TABLE DRUGS ADD CONSTRAINT DRUGS_PRESCRIPTION_TYPE FOREIGN KEY DRUGS_PRESCRIPTION_TYPE (prescription_type_id)
    REFERENCES PRESCRIPTION_TYPE (prescription_type_id);

-- Reference: DRUGS_TYPES_OF_DRUGS (table: DRUGS)
ALTER TABLE DRUGS ADD CONSTRAINT DRUGS_TYPES_OF_DRUGS FOREIGN KEY DRUGS_TYPES_OF_DRUGS (type_id)
    REFERENCES TYPES_OF_DRUGS (type_id);

-- Reference: DRUG_DOSE_AMOUNT_HISTORY_USER_DRUG_USAGE_HISTORY (table: DRUG_DOSE_AMOUNT_HISTORY)
ALTER TABLE DRUG_DOSE_AMOUNT_HISTORY ADD CONSTRAINT DRUG_DOSE_AMOUNT_HISTORY_USER_DRUG_USAGE_HISTORY FOREIGN KEY DRUG_DOSE_AMOUNT_HISTORY_USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id)
    REFERENCES USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id);

-- Reference: DRUG_USAGE_GAP_PERIODS_USER_DRUG_USAGE_HISTORY (table: DRUG_USAGE_GAP_PERIODS)
ALTER TABLE DRUG_USAGE_GAP_PERIODS ADD CONSTRAINT DRUG_USAGE_GAP_PERIODS_USER_DRUG_USAGE_HISTORY FOREIGN KEY DRUG_USAGE_GAP_PERIODS_USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id)
    REFERENCES USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id);

-- Reference: DRUG_USAGE_STATE_HISTORY_STATE (table: DRUG_USAGE_STATE_HISTORY)
ALTER TABLE DRUG_USAGE_STATE_HISTORY ADD CONSTRAINT DRUG_USAGE_STATE_HISTORY_STATE FOREIGN KEY DRUG_USAGE_STATE_HISTORY_STATE (drug_state_id)
    REFERENCES STATE (state_id);

-- Reference: DRUG_USAGE_STATE_HISTORY_USER_DRUG_USAGE_HISTORY (table: DRUG_USAGE_STATE_HISTORY)
ALTER TABLE DRUG_USAGE_STATE_HISTORY ADD CONSTRAINT DRUG_USAGE_STATE_HISTORY_USER_DRUG_USAGE_HISTORY FOREIGN KEY DRUG_USAGE_STATE_HISTORY_USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id)
    REFERENCES USER_DRUG_USAGE_HISTORY (user_id,disease_id,treatment_id,drug_id);

-- Reference: EDUCATION (table: GROUPS_HAS_USERS)
ALTER TABLE GROUPS_HAS_USERS ADD CONSTRAINT EDUCATION FOREIGN KEY EDUCATION (user_id)
    REFERENCES USERS (user_id);

-- Reference: EVENTS_EVENT_TYPE (table: EVENTS)
ALTER TABLE EVENTS ADD CONSTRAINT EVENTS_EVENT_TYPE FOREIGN KEY EVENTS_EVENT_TYPE (event_type_id)
    REFERENCES EVENT_TYPE (event_type_id);

-- Reference: EVENTS_HAVE_USERS_EVENTS (table: EVENTS_HAVE_USERS)
ALTER TABLE EVENTS_HAVE_USERS ADD CONSTRAINT EVENTS_HAVE_USERS_EVENTS FOREIGN KEY EVENTS_HAVE_USERS_EVENTS (event_id)
    REFERENCES EVENTS (event_id);

-- Reference: EVENTS_HAVE_USERS_USERS (table: EVENTS_HAVE_USERS)
ALTER TABLE EVENTS_HAVE_USERS ADD CONSTRAINT EVENTS_HAVE_USERS_USERS FOREIGN KEY EVENTS_HAVE_USERS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: EVENTS_PHOTOS (table: EVENTS)
ALTER TABLE EVENTS ADD CONSTRAINT EVENTS_PHOTOS FOREIGN KEY EVENTS_PHOTOS (event_photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: EVENTS_POST_EVENTS (table: EVENTS_HAVE_POSTS)
ALTER TABLE EVENTS_HAVE_POSTS ADD CONSTRAINT EVENTS_POST_EVENTS FOREIGN KEY EVENTS_POST_EVENTS (event_id)
    REFERENCES EVENTS (event_id);

-- Reference: EVENTS_POST_USER_POSTS (table: EVENTS_HAVE_POSTS)
ALTER TABLE EVENTS_HAVE_POSTS ADD CONSTRAINT EVENTS_POST_USER_POSTS FOREIGN KEY EVENTS_POST_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: EVENTS_RATES_USERS (table: EVENTS_RATES)
ALTER TABLE EVENTS_RATES ADD CONSTRAINT EVENTS_RATES_USERS FOREIGN KEY EVENTS_RATES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: EVENTS_RATE_EVENTS (table: EVENTS_RATES)
ALTER TABLE EVENTS_RATES ADD CONSTRAINT EVENTS_RATE_EVENTS FOREIGN KEY EVENTS_RATE_EVENTS (event_id)
    REFERENCES EVENTS (event_id);

-- Reference: EVENTS_USERS (table: EVENTS)
ALTER TABLE EVENTS ADD CONSTRAINT EVENTS_USERS FOREIGN KEY EVENTS_USERS (owner_id)
    REFERENCES USERS (user_id);

-- Reference: EVENT_ADRESS_CITIES (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_CITIES FOREIGN KEY EVENT_ADRESS_CITIES (city_id)
    REFERENCES CITIES (city_id);

-- Reference: EVENT_ADRESS_COUNTRIES (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_COUNTRIES FOREIGN KEY EVENT_ADRESS_COUNTRIES (country_id)
    REFERENCES COUNTRIES (country_id);

-- Reference: EVENT_ADRESS_DISTRICT (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_DISTRICT FOREIGN KEY EVENT_ADRESS_DISTRICT (distict_id)
    REFERENCES DISTRICT (distict_id);

-- Reference: EVENT_ADRESS_EVENTS (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_EVENTS FOREIGN KEY EVENT_ADRESS_EVENTS (event_id)
    REFERENCES EVENTS (event_id);

-- Reference: EVENT_ADRESS_PROVINCES (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_PROVINCES FOREIGN KEY EVENT_ADRESS_PROVINCES (province_id)
    REFERENCES PROVINCES (province_id);

-- Reference: EVENT_ADRESS_STATES (table: EVENT_ADRESS)
ALTER TABLE EVENT_ADRESS ADD CONSTRAINT EVENT_ADRESS_STATES FOREIGN KEY EVENT_ADRESS_STATES (state_id)
    REFERENCES STATES (state_id);

-- Reference: EVENT_REQUESTS_EVENTS (table: EVENT_REQUESTS)
ALTER TABLE EVENT_REQUESTS ADD CONSTRAINT EVENT_REQUESTS_EVENTS FOREIGN KEY EVENT_REQUESTS_EVENTS (event_id)
    REFERENCES EVENTS (event_id);

-- Reference: EVENT_REQUESTS_RECEIVER_USERS (table: EVENT_REQUESTS)
ALTER TABLE EVENT_REQUESTS ADD CONSTRAINT EVENT_REQUESTS_RECEIVER_USERS FOREIGN KEY EVENT_REQUESTS_RECEIVER_USERS (receiver_user_id)
    REFERENCES USERS (user_id);

-- Reference: EVENT_REQUESTS_SENDER_USERS (table: EVENT_REQUESTS)
ALTER TABLE EVENT_REQUESTS ADD CONSTRAINT EVENT_REQUESTS_SENDER_USERS FOREIGN KEY EVENT_REQUESTS_SENDER_USERS (sender_user_id)
    REFERENCES USERS (user_id);

-- Reference: GROUPS_GROUP_TYPES (table: GROUPS)
ALTER TABLE GROUPS ADD CONSTRAINT GROUPS_GROUP_TYPES FOREIGN KEY GROUPS_GROUP_TYPES (group_type_id)
    REFERENCES GROUP_TYPES (type_id);

-- Reference: GROUPS_HAS_USERS_GROUPS (table: GROUPS_HAS_USERS)
ALTER TABLE GROUPS_HAS_USERS ADD CONSTRAINT GROUPS_HAS_USERS_GROUPS FOREIGN KEY GROUPS_HAS_USERS_GROUPS (group_id)
    REFERENCES GROUPS (group_id);

-- Reference: GROUPS_PHOTOS (table: GROUPS)
ALTER TABLE GROUPS ADD CONSTRAINT GROUPS_PHOTOS FOREIGN KEY GROUPS_PHOTOS (group_photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: GROUPS_POSTS_GROUPS (table: GROUPS_POSTS)
ALTER TABLE GROUPS_POSTS ADD CONSTRAINT GROUPS_POSTS_GROUPS FOREIGN KEY GROUPS_POSTS_GROUPS (group_id)
    REFERENCES GROUPS (group_id);

-- Reference: GROUPS_POSTS_USER_POSTS (table: GROUPS_POSTS)
ALTER TABLE GROUPS_POSTS ADD CONSTRAINT GROUPS_POSTS_USER_POSTS FOREIGN KEY GROUPS_POSTS_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: GROUPS_RATES_GROUPS (table: GROUPS_RATES)
ALTER TABLE GROUPS_RATES ADD CONSTRAINT GROUPS_RATES_GROUPS FOREIGN KEY GROUPS_RATES_GROUPS (group_id)
    REFERENCES GROUPS (group_id);

-- Reference: GROUPS_RATES_USERS (table: GROUPS_RATES)
ALTER TABLE GROUPS_RATES ADD CONSTRAINT GROUPS_RATES_USERS FOREIGN KEY GROUPS_RATES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: GROUPS_REQUEST_GROUPS (table: GROUPS_REQUEST)
ALTER TABLE GROUPS_REQUEST ADD CONSTRAINT GROUPS_REQUEST_GROUPS FOREIGN KEY GROUPS_REQUEST_GROUPS (group_id)
    REFERENCES GROUPS (group_id);

-- Reference: GROUPS_REQUEST_RECEIVER_USER (table: GROUPS_REQUEST)
ALTER TABLE GROUPS_REQUEST ADD CONSTRAINT GROUPS_REQUEST_RECEIVER_USER FOREIGN KEY GROUPS_REQUEST_RECEIVER_USER (receiver_user_id)
    REFERENCES USERS (user_id);

-- Reference: GROUPS_REQUEST_SENDER_USER (table: GROUPS_REQUEST)
ALTER TABLE GROUPS_REQUEST ADD CONSTRAINT GROUPS_REQUEST_SENDER_USER FOREIGN KEY GROUPS_REQUEST_SENDER_USER (sender_user_id)
    REFERENCES USERS (user_id);

-- Reference: GROUPS_USERS (table: GROUPS)
ALTER TABLE GROUPS ADD CONSTRAINT GROUPS_USERS FOREIGN KEY GROUPS_USERS (admin_id)
    REFERENCES USERS (user_id);

-- Reference: HOSPITALS_HAVE_CLINICS (table: HOSPITALS_HAVE_CLINICS)
ALTER TABLE HOSPITALS_HAVE_CLINICS ADD CONSTRAINT HOSPITALS_HAVE_CLINICS FOREIGN KEY HOSPITALS_HAVE_CLINICS (hospital_id)
    REFERENCES HOSPITALS (hospital_id);

-- Reference: HOSPITALS_HAVE_CLINICS_CLINICS (table: HOSPITALS_HAVE_CLINICS)
ALTER TABLE HOSPITALS_HAVE_CLINICS ADD CONSTRAINT HOSPITALS_HAVE_CLINICS_CLINICS FOREIGN KEY HOSPITALS_HAVE_CLINICS_CLINICS (clinic_id)
    REFERENCES CLINICS (clinic_id);

-- Reference: HOSPITALS_HAVE_DOCTORS_CLINICS (table: HOSPITALS_HAVE_DOCTORS)
ALTER TABLE HOSPITALS_HAVE_DOCTORS ADD CONSTRAINT HOSPITALS_HAVE_DOCTORS_CLINICS FOREIGN KEY HOSPITALS_HAVE_DOCTORS_CLINICS (clinic_id)
    REFERENCES CLINICS (clinic_id);

-- Reference: HOSPITALS_HOSPITAL_TYPE (table: HOSPITALS)
ALTER TABLE HOSPITALS ADD CONSTRAINT HOSPITALS_HOSPITAL_TYPE FOREIGN KEY HOSPITALS_HOSPITAL_TYPE (hospital_type_id)
    REFERENCES HOSPITAL_TYPE (hospital_type_id);

-- Reference: HOSPITAL_ADRESS_CITIES (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_CITIES FOREIGN KEY HOSPITAL_ADRESS_CITIES (city_id)
    REFERENCES CITIES (city_id);

-- Reference: HOSPITAL_ADRESS_COUNTRIES (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_COUNTRIES FOREIGN KEY HOSPITAL_ADRESS_COUNTRIES (country_id)
    REFERENCES COUNTRIES (country_id);

-- Reference: HOSPITAL_ADRESS_DISTRICT (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_DISTRICT FOREIGN KEY HOSPITAL_ADRESS_DISTRICT (distict_id)
    REFERENCES DISTRICT (distict_id);

-- Reference: HOSPITAL_ADRESS_HOSPITALS (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_HOSPITALS FOREIGN KEY HOSPITAL_ADRESS_HOSPITALS (hospital_id)
    REFERENCES HOSPITALS (hospital_id);

-- Reference: HOSPITAL_ADRESS_PROVINCES (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_PROVINCES FOREIGN KEY HOSPITAL_ADRESS_PROVINCES (province_id)
    REFERENCES PROVINCES (province_id);

-- Reference: HOSPITAL_ADRESS_STATES (table: HOSPITAL_ADRESS)
ALTER TABLE HOSPITAL_ADRESS ADD CONSTRAINT HOSPITAL_ADRESS_STATES FOREIGN KEY HOSPITAL_ADRESS_STATES (state_id)
    REFERENCES STATES (state_id);

-- Reference: HOSPITAL_CLINIC_RANKING_USERS (table: USERS_HOSPITAL_RATES)
ALTER TABLE USERS_HOSPITAL_RATES ADD CONSTRAINT HOSPITAL_CLINIC_RANKING_USERS FOREIGN KEY HOSPITAL_CLINIC_RANKING_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: HOSPITAL_USERS_HOSPITALS (table: HOSPITAL_USERS)
ALTER TABLE HOSPITAL_USERS ADD CONSTRAINT HOSPITAL_USERS_HOSPITALS FOREIGN KEY HOSPITAL_USERS_HOSPITALS (hospital_id)
    REFERENCES HOSPITALS (hospital_id);

-- Reference: HOSPITAL_USERS_PERSONAL_USERS (table: HOSPITAL_USERS)
ALTER TABLE HOSPITAL_USERS ADD CONSTRAINT HOSPITAL_USERS_PERSONAL_USERS FOREIGN KEY HOSPITAL_USERS_PERSONAL_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: INSTITUTE_USERS_INSTITUTE_TYPES (table: INSTITUTE_USERS)
ALTER TABLE INSTITUTE_USERS ADD CONSTRAINT INSTITUTE_USERS_INSTITUTE_TYPES FOREIGN KEY INSTITUTE_USERS_INSTITUTE_TYPES (institute_type_id)
    REFERENCES INSTITUTE_TYPES (institute_type_id);

-- Reference: INSTITUTE_USERS_INSTITUTIONS_FOCUS_AREA (table: INSTITUTE_USERS)
ALTER TABLE INSTITUTE_USERS ADD CONSTRAINT INSTITUTE_USERS_INSTITUTIONS_FOCUS_AREA FOREIGN KEY INSTITUTE_USERS_INSTITUTIONS_FOCUS_AREA (focus_area_id)
    REFERENCES INSTITUTES_FOCUS_AREA (focus_area_id);

-- Reference: INSTITUTE_USERS_PERSONAL_USERS (table: INSTITUTE_USERS)
ALTER TABLE INSTITUTE_USERS ADD CONSTRAINT INSTITUTE_USERS_PERSONAL_USERS FOREIGN KEY INSTITUTE_USERS_PERSONAL_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: MESSAGES_HAVE_PHOTOS_MESSAGES_REPLY (table: MESSAGES_HAVE_PHOTOS)
ALTER TABLE MESSAGES_HAVE_PHOTOS ADD CONSTRAINT MESSAGES_HAVE_PHOTOS_MESSAGES_REPLY FOREIGN KEY MESSAGES_HAVE_PHOTOS_MESSAGES_REPLY (conversation_reply_id)
    REFERENCES MESSAGES_REPLY (conversation_reply_id);

-- Reference: MESSAGES_HAVE_PHOTOS_PHOTOS (table: MESSAGES_HAVE_PHOTOS)
ALTER TABLE MESSAGES_HAVE_PHOTOS ADD CONSTRAINT MESSAGES_HAVE_PHOTOS_PHOTOS FOREIGN KEY MESSAGES_HAVE_PHOTOS_PHOTOS (photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: ORGANS_BODY_SYSTEMS (table: ORGANS)
ALTER TABLE ORGANS ADD CONSTRAINT ORGANS_BODY_SYSTEMS FOREIGN KEY ORGANS_BODY_SYSTEMS (body_system_id)
    REFERENCES BODY_SYSTEMS (body_system_id);

-- Reference: PATIENTS_BLOOD_TYPES (table: PATIENTS)
ALTER TABLE PATIENTS ADD CONSTRAINT PATIENTS_BLOOD_TYPES FOREIGN KEY PATIENTS_BLOOD_TYPES (blood_type_id)
    REFERENCES BLOOD_TYPES (blood_type_id);

-- Reference: PATIENTS_USERS (table: PATIENTS)
ALTER TABLE PATIENTS ADD CONSTRAINT PATIENTS_USERS FOREIGN KEY PATIENTS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: PHOTOS_HAVE_VOTES_PHOTOS (table: PHOTOS_HAVE_VOTES)
ALTER TABLE PHOTOS_HAVE_VOTES ADD CONSTRAINT PHOTOS_HAVE_VOTES_PHOTOS FOREIGN KEY PHOTOS_HAVE_VOTES_PHOTOS (photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: PHOTOS_HAVE_VOTES_USERS (table: PHOTOS_HAVE_VOTES)
ALTER TABLE PHOTOS_HAVE_VOTES ADD CONSTRAINT PHOTOS_HAVE_VOTES_USERS FOREIGN KEY PHOTOS_HAVE_VOTES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: PHOTOS_USERS (table: PHOTOS)
ALTER TABLE PHOTOS ADD CONSTRAINT PHOTOS_USERS FOREIGN KEY PHOTOS_USERS (owner_id)
    REFERENCES USERS (user_id);

-- Reference: POST_COMMENTS_HAVE_PHOTOS_PHOTOS (table: POST_COMMENTS_HAVE_PHOTOS)
ALTER TABLE POST_COMMENTS_HAVE_PHOTOS ADD CONSTRAINT POST_COMMENTS_HAVE_PHOTOS_PHOTOS FOREIGN KEY POST_COMMENTS_HAVE_PHOTOS_PHOTOS (photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: POST_COMMENTS_HAVE_PHOTOS_POST_COMMENTS (table: POST_COMMENTS_HAVE_PHOTOS)
ALTER TABLE POST_COMMENTS_HAVE_PHOTOS ADD CONSTRAINT POST_COMMENTS_HAVE_PHOTOS_POST_COMMENTS FOREIGN KEY POST_COMMENTS_HAVE_PHOTOS_POST_COMMENTS (comment_id)
    REFERENCES POST_COMMENTS (comment_id);

-- Reference: POST_COMMENTS_USERS (table: POST_COMMENTS)
ALTER TABLE POST_COMMENTS ADD CONSTRAINT POST_COMMENTS_USERS FOREIGN KEY POST_COMMENTS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: POST_COMMENTS_USER_POSTS (table: POST_COMMENTS)
ALTER TABLE POST_COMMENTS ADD CONSTRAINT POST_COMMENTS_USER_POSTS FOREIGN KEY POST_COMMENTS_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: POST_VOTES_USERS (table: POST_VOTES)
ALTER TABLE POST_VOTES ADD CONSTRAINT POST_VOTES_USERS FOREIGN KEY POST_VOTES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: POST_VOTES_USER_POSTS (table: POST_VOTES)
ALTER TABLE POST_VOTES ADD CONSTRAINT POST_VOTES_USER_POSTS FOREIGN KEY POST_VOTES_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: RELATIONSHIPS_STATUS (table: RELATIONSHIPS)
ALTER TABLE RELATIONSHIPS ADD CONSTRAINT RELATIONSHIPS_STATUS FOREIGN KEY RELATIONSHIPS_STATUS (status_id)
    REFERENCES STATUS (status_id);

-- Reference: RELATIONSHIPS_USERS_ACTIVE (table: RELATIONSHIPS)
ALTER TABLE RELATIONSHIPS ADD CONSTRAINT RELATIONSHIPS_USERS_ACTIVE FOREIGN KEY RELATIONSHIPS_USERS_ACTIVE (passive_user_id)
    REFERENCES USERS (user_id);

-- Reference: RELATIONSHIPS_USERS_SECOND (table: RELATIONSHIPS)
ALTER TABLE RELATIONSHIPS ADD CONSTRAINT RELATIONSHIPS_USERS_SECOND FOREIGN KEY RELATIONSHIPS_USERS_SECOND (active_user_id)
    REFERENCES USERS (user_id);

-- Reference: STATES_COUNTRIES (table: STATES)
ALTER TABLE STATES ADD CONSTRAINT STATES_COUNTRIES FOREIGN KEY STATES_COUNTRIES (country_id)
    REFERENCES COUNTRIES (country_id);

-- Reference: THREATMENTS_HAVE_DRUGS (table: TREATMENTS_HAVE_DRUGS)
ALTER TABLE TREATMENTS_HAVE_DRUGS ADD CONSTRAINT THREATMENTS_HAVE_DRUGS FOREIGN KEY THREATMENTS_HAVE_DRUGS (drug_id)
    REFERENCES DRUGS (drug_id);

-- Reference: TREATMENTS_HAVE_DRUGS_TREATMENT (table: TREATMENTS_HAVE_DRUGS)
ALTER TABLE TREATMENTS_HAVE_DRUGS ADD CONSTRAINT TREATMENTS_HAVE_DRUGS_TREATMENT FOREIGN KEY TREATMENTS_HAVE_DRUGS_TREATMENT (treatment_id)
    REFERENCES TREATMENTS (treatment_id);

-- Reference: TREATMENT_BODY_SYSTEMS (table: TREATMENTS)
ALTER TABLE TREATMENTS ADD CONSTRAINT TREATMENT_BODY_SYSTEMS FOREIGN KEY TREATMENT_BODY_SYSTEMS (body_system_id)
    REFERENCES BODY_SYSTEMS (body_system_id);

-- Reference: TREATMENT_GAP_PERIODS_USER_TREATMENT_HISTORY (table: TREATMENT_GAP_PERIODS)
ALTER TABLE TREATMENT_GAP_PERIODS ADD CONSTRAINT TREATMENT_GAP_PERIODS_USER_TREATMENT_HISTORY FOREIGN KEY TREATMENT_GAP_PERIODS_USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id)
    REFERENCES USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id);

-- Reference: TREATMENT_ORGANS (table: TREATMENTS)
ALTER TABLE TREATMENTS ADD CONSTRAINT TREATMENT_ORGANS FOREIGN KEY TREATMENT_ORGANS (organ_id)
    REFERENCES ORGANS (organ_id);

-- Reference: TREATMENT_STATE_HISTORY_STATE (table: TREATMENT_STATE_HISTORY)
ALTER TABLE TREATMENT_STATE_HISTORY ADD CONSTRAINT TREATMENT_STATE_HISTORY_STATE FOREIGN KEY TREATMENT_STATE_HISTORY_STATE (treatment_state_id)
    REFERENCES STATE (state_id);

-- Reference: TREATMENT_STATE_HISTORY_USER_TREATMENT_HISTORY (table: TREATMENT_STATE_HISTORY)
ALTER TABLE TREATMENT_STATE_HISTORY ADD CONSTRAINT TREATMENT_STATE_HISTORY_USER_TREATMENT_HISTORY FOREIGN KEY TREATMENT_STATE_HISTORY_USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id)
    REFERENCES USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id);

-- Reference: TYPES_OF_DRUGS_GENERAL_TYPE_GROUPS (table: TYPES_OF_DRUGS)
ALTER TABLE TYPES_OF_DRUGS ADD CONSTRAINT TYPES_OF_DRUGS_GENERAL_TYPE_GROUPS FOREIGN KEY TYPES_OF_DRUGS_GENERAL_TYPE_GROUPS (general_type_id)
    REFERENCES GENERAL_TYPE_GROUPS (general_type_id);

-- Reference: USERS_ADRESS_CITIES (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_ADRESS_CITIES FOREIGN KEY USERS_ADRESS_CITIES (city_id)
    REFERENCES CITIES (city_id);

-- Reference: USERS_ADRESS_COUNTRIES (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_ADRESS_COUNTRIES FOREIGN KEY USERS_ADRESS_COUNTRIES (country_id)
    REFERENCES COUNTRIES (country_id);

-- Reference: USERS_ADRESS_DISTRICT (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_ADRESS_DISTRICT FOREIGN KEY USERS_ADRESS_DISTRICT (distict_id)
    REFERENCES DISTRICT (distict_id);

-- Reference: USERS_ADRESS_PROVINCES (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_ADRESS_PROVINCES FOREIGN KEY USERS_ADRESS_PROVINCES (province_id)
    REFERENCES PROVINCES (province_id);

-- Reference: USERS_ADRESS_STATES (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_ADRESS_STATES FOREIGN KEY USERS_ADRESS_STATES (state_id)
    REFERENCES STATES (state_id);

-- Reference: USERS_HAVE_HOSPITALS_HOSPITALS_HAVE_CLINICS (table: USERS_HAVE_HOSPITAL_CLINICS)
ALTER TABLE USERS_HAVE_HOSPITAL_CLINICS ADD CONSTRAINT USERS_HAVE_HOSPITALS_HOSPITALS_HAVE_CLINICS FOREIGN KEY USERS_HAVE_HOSPITALS_HOSPITALS_HAVE_CLINICS (hospital_id,clinic_id)
    REFERENCES HOSPITALS_HAVE_CLINICS (hospital_id,clinic_id);

-- Reference: USERS_HAVE_HOSPITALS_USERS (table: USERS_HAVE_HOSPITAL_CLINICS)
ALTER TABLE USERS_HAVE_HOSPITAL_CLINICS ADD CONSTRAINT USERS_HAVE_HOSPITALS_USERS FOREIGN KEY USERS_HAVE_HOSPITALS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USERS_HAVE_LANGUAGES_LANGUAGES (table: USERS_HAVE_LANGUAGES)
ALTER TABLE USERS_HAVE_LANGUAGES ADD CONSTRAINT USERS_HAVE_LANGUAGES_LANGUAGES FOREIGN KEY USERS_HAVE_LANGUAGES_LANGUAGES (language_id)
    REFERENCES LANGUAGES (language_id);

-- Reference: USERS_HAVE_LANGUAGES_USERS (table: USERS_HAVE_LANGUAGES)
ALTER TABLE USERS_HAVE_LANGUAGES ADD CONSTRAINT USERS_HAVE_LANGUAGES_USERS FOREIGN KEY USERS_HAVE_LANGUAGES_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USERS_HOSPITAL_RATES_CLINICS (table: USERS_HOSPITAL_RATES)
ALTER TABLE USERS_HOSPITAL_RATES ADD CONSTRAINT USERS_HOSPITAL_RATES_CLINICS FOREIGN KEY USERS_HOSPITAL_RATES_CLINICS (clinic_id)
    REFERENCES CLINICS (clinic_id);

-- Reference: USERS_HOSPITAL_RATES_HOSPITALS (table: USERS_HOSPITAL_RATES)
ALTER TABLE USERS_HOSPITAL_RATES ADD CONSTRAINT USERS_HOSPITAL_RATES_HOSPITALS FOREIGN KEY USERS_HOSPITAL_RATES_HOSPITALS (hospital_id)
    REFERENCES HOSPITALS (hospital_id);

-- Reference: USERS_LOCATION_INFORMATIONS_USERS (table: USER_ADRESS)
ALTER TABLE USER_ADRESS ADD CONSTRAINT USERS_LOCATION_INFORMATIONS_USERS FOREIGN KEY USERS_LOCATION_INFORMATIONS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USERS_MARKED_POSTS_USERS (table: USERS_MARKED_POSTS)
ALTER TABLE USERS_MARKED_POSTS ADD CONSTRAINT USERS_MARKED_POSTS_USERS FOREIGN KEY USERS_MARKED_POSTS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USERS_MARKED_POSTS_USER_POSTS (table: USERS_MARKED_POSTS)
ALTER TABLE USERS_MARKED_POSTS ADD CONSTRAINT USERS_MARKED_POSTS_USER_POSTS FOREIGN KEY USERS_MARKED_POSTS_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: USERS_USER_RELATIONSHIP_STATUS (table: USERS)
ALTER TABLE USERS ADD CONSTRAINT USERS_USER_RELATIONSHIP_STATUS FOREIGN KEY USERS_USER_RELATIONSHIP_STATUS (relationship_status_id)
    REFERENCES USER_RELATIONSHIP_STATUS (relationship_status_id);

-- Reference: USERS_USER_TYPE_ID (table: USERS)
ALTER TABLE USERS ADD CONSTRAINT USERS_USER_TYPE_ID FOREIGN KEY USERS_USER_TYPE_ID (user_type_id)
    REFERENCES USER_TYPES (user_type_id);

-- Reference: USER_DRUG_USAGE_HISTORY_DRUGS (table: USER_DRUG_USAGE_HISTORY)
ALTER TABLE USER_DRUG_USAGE_HISTORY ADD CONSTRAINT USER_DRUG_USAGE_HISTORY_DRUGS FOREIGN KEY USER_DRUG_USAGE_HISTORY_DRUGS (drug_id)
    REFERENCES DRUGS (drug_id);

-- Reference: USER_DRUG_USAGE_HISTORY_USER_TREATMENT_HISTORY (table: USER_DRUG_USAGE_HISTORY)
ALTER TABLE USER_DRUG_USAGE_HISTORY ADD CONSTRAINT USER_DRUG_USAGE_HISTORY_USER_TREATMENT_HISTORY FOREIGN KEY USER_DRUG_USAGE_HISTORY_USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id)
    REFERENCES USER_TREATMENT_HISTORY (user_id,disease_id,treatment_id);

-- Reference: USER_HEALTH_HISTORY_DISEASES (table: USER_HEALTH_HISTORY)
ALTER TABLE USER_HEALTH_HISTORY ADD CONSTRAINT USER_HEALTH_HISTORY_DISEASES FOREIGN KEY USER_HEALTH_HISTORY_DISEASES (disease_id)
    REFERENCES DISEASES (disease_id);

-- Reference: USER_HEALTH_HISTORY_DISEASE_LEVEL (table: USER_HEALTH_HISTORY)
ALTER TABLE USER_HEALTH_HISTORY ADD CONSTRAINT USER_HEALTH_HISTORY_DISEASE_LEVEL FOREIGN KEY USER_HEALTH_HISTORY_DISEASE_LEVEL (disease_level_id)
    REFERENCES DISEASE_LEVEL (disease_level_id);

-- Reference: USER_HEALTH_HISTORY_STATE (table: USER_HEALTH_HISTORY)
ALTER TABLE USER_HEALTH_HISTORY ADD CONSTRAINT USER_HEALTH_HISTORY_STATE FOREIGN KEY USER_HEALTH_HISTORY_STATE (disease_state_id)
    REFERENCES STATE (state_id);

-- Reference: USER_HEALTH_HISTORY_USERS (table: USER_HEALTH_HISTORY)
ALTER TABLE USER_HEALTH_HISTORY ADD CONSTRAINT USER_HEALTH_HISTORY_USERS FOREIGN KEY USER_HEALTH_HISTORY_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USER_POSTS_HAVE_PHOTOS_PHOTOS (table: USER_POSTS_HAVE_PHOTOS)
ALTER TABLE USER_POSTS_HAVE_PHOTOS ADD CONSTRAINT USER_POSTS_HAVE_PHOTOS_PHOTOS FOREIGN KEY USER_POSTS_HAVE_PHOTOS_PHOTOS (photo_id)
    REFERENCES PHOTOS (photo_id);

-- Reference: USER_POSTS_HAVE_PHOTOS_USER_POSTS (table: USER_POSTS_HAVE_PHOTOS)
ALTER TABLE USER_POSTS_HAVE_PHOTOS ADD CONSTRAINT USER_POSTS_HAVE_PHOTOS_USER_POSTS FOREIGN KEY USER_POSTS_HAVE_PHOTOS_USER_POSTS (post_id)
    REFERENCES USER_POSTS (post_id);

-- Reference: USER_POSTS_USERS (table: USER_POSTS)
ALTER TABLE USER_POSTS ADD CONSTRAINT USER_POSTS_USERS FOREIGN KEY USER_POSTS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USER_PRIVACY_OPTIONS_USERS (table: USER_PRIVACY_OPTIONS)
ALTER TABLE USER_PRIVACY_OPTIONS ADD CONSTRAINT USER_PRIVACY_OPTIONS_USERS FOREIGN KEY USER_PRIVACY_OPTIONS_USERS (user_id)
    REFERENCES USERS (user_id);

-- Reference: USER_TREATMENT_HISTORY_TREATMENTS (table: USER_TREATMENT_HISTORY)
ALTER TABLE USER_TREATMENT_HISTORY ADD CONSTRAINT USER_TREATMENT_HISTORY_TREATMENTS FOREIGN KEY USER_TREATMENT_HISTORY_TREATMENTS (treatment_id)
    REFERENCES TREATMENTS (treatment_id);

-- Reference: USER_TREATMENT_HISTORY_USER_HEALTH_HISTORY (table: USER_TREATMENT_HISTORY)
ALTER TABLE USER_TREATMENT_HISTORY ADD CONSTRAINT USER_TREATMENT_HISTORY_USER_HEALTH_HISTORY FOREIGN KEY USER_TREATMENT_HISTORY_USER_HEALTH_HISTORY (user_id,disease_id)
    REFERENCES USER_HEALTH_HISTORY (user_id,disease_id);

-- End of file.

