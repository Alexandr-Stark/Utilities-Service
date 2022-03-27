CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE account_roles
(
    id        BIGINT NOT NULL,
    role_name VARCHAR(255),
    CONSTRAINT pk_account_roles PRIMARY KEY (id)
);

CREATE TABLE bills
(
    bill_id                 BIGINT NOT NULL,
    residence_id            BIGINT,
    personal_account_number VARCHAR(255),
    company_id              BIGINT,
    CONSTRAINT pk_bills PRIMARY KEY (bill_id)
);

CREATE TABLE payment_cards
(
    id            BIGINT NOT NULL,
    user_id       BIGINT,
    card_nickname VARCHAR(255),
    card_type     VARCHAR(255),
    card_number   VARCHAR(255),
    cvc           VARCHAR(255),
    month         INTEGER,
    year          INTEGER,
    CONSTRAINT pk_payment_cards PRIMARY KEY (id)
);

CREATE TABLE residences
(
    residence_id  BIGINT NOT NULL,
    user_id       BIGINT,
    owner_name    VARCHAR(255),
    owner_surname VARCHAR(255),
    country       VARCHAR(255),
    city          VARCHAR(255),
    street        VARCHAR(255),
    house         VARCHAR(255),
    corps         VARCHAR(255),
    flat          VARCHAR(255),
    CONSTRAINT pk_residences PRIMARY KEY (residence_id)
);

CREATE TABLE users
(
    user_id     BIGINT NOT NULL,
    name        VARCHAR(255),
    surname     VARCHAR(255),
    middle_name VARCHAR(255),
    email       VARCHAR(255),
    password    VARCHAR(255),
    phone       VARCHAR(255),
    role_id     BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (user_id)
);

CREATE TABLE utility_companies
(
    id           BIGINT NOT NULL,
    company_name VARCHAR(255),
    iban         VARCHAR(255),
    usreou       VARCHAR(255),
    CONSTRAINT pk_utility_companies PRIMARY KEY (id)
);

CREATE TABLE utility_meters
(
    utility_meters_id      BIGINT NOT NULL,
    bill_id                BIGINT,
    preliminary_indicators BIGINT,
    current_indicators     BIGINT,
    indicators_date        TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_utility_meters PRIMARY KEY (utility_meters_id)
);

ALTER TABLE bills
    ADD CONSTRAINT FK_BILLS_ON_COMPANY FOREIGN KEY (company_id) REFERENCES utility_companies (id);

ALTER TABLE bills
    ADD CONSTRAINT FK_BILLS_ON_RESIDENCE FOREIGN KEY (residence_id) REFERENCES residences (residence_id);

ALTER TABLE payment_cards
    ADD CONSTRAINT FK_PAYMENT_CARDS_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE residences
    ADD CONSTRAINT FK_RESIDENCES_ON_USER FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES account_roles (id);

ALTER TABLE utility_meters
    ADD CONSTRAINT FK_UTILITY_METERS_ON_BILL FOREIGN KEY (bill_id) REFERENCES bills (bill_id);