CREATE TABLE QUESTION
(
    id    bigint     NOT NULL,
    label varchar    NOT NULL,
    type  varchar(3) NOT NULL,

    CONSTRAINT question_pk PRIMARY KEY (id),
    CONSTRAINT valid_type_cc CHECK (type IN ('OAQ', 'SAQ', 'MAQ'))
);
