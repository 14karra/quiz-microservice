CREATE TABLE QUIZ
(
    id            bigint                   NOT NULL,
    name          varchar(100)             NOT NULL,
    description   varchar                  NOT NULL,
    creation_date timestamp with time zone NOT NULL,
    start_date    timestamp with time zone,
    end_date      timestamp with time zone,
    state         varchar(1)               NOT NULL,

    CONSTRAINT valid_state_cc CHECK (state IN ('C', 'A', 'E')),
    CONSTRAINT quiz_pk PRIMARY KEY (id)
);
