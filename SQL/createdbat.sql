CREATE USER feedbackadmin WITH password '12345';
CREATE DATABASE feedback OWNER feedbackadmin ENCODING 'UTF8';
GRANT ALL PRIVILEGES ON DATABASE feedback TO feedbackadmin;

\connect feedback;

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO feedbackadmin;

SET client_encoding TO 'UTF8';

create table recipients(
    RECIPIENT_ID SERIAL,
    RECIPIENT_FULL_NAME VARCHAR,
    RECIPIENT_EMAIL VARCHAR,
    PRIMARY KEY(RECIPIENT_ID)
);

INSERT INTO Recipients (RECIPIENT_FULL_NAME,RECIPIENT_EMAIL) values('Иванов Иван Иванович', 'ceo@company.com');
INSERT INTO Recipients (RECIPIENT_FULL_NAME,RECIPIENT_EMAIL) values('Петров Пётр Петров', 'reclamation@company.com');
INSERT INTO Recipients (RECIPIENT_FULL_NAME,RECIPIENT_EMAIL) values('Осинцева Инна Игоревна', 'hr@company.com');
INSERT INTO Recipients (RECIPIENT_FULL_NAME,RECIPIENT_EMAIL) values('Петрова Юлия Геннадьевна', 'adverts@company.com');