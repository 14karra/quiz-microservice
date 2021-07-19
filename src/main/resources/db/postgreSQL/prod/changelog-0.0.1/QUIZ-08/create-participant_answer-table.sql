CREATE TABLE PARTICIPANT_ANSWER
(
    participant_id bigint                   NOT NULL,
    question_id    bigint                   NOT NULL,
    answer_id      bigint                   NOT NULL,
    date_time      timestamp with time zone NOT NULL,

    PRIMARY KEY (participant_id, question_id, answer_id),
    CONSTRAINT participant_answer_fk_1
        FOREIGN KEY (participant_id)
            REFERENCES PARTICIPANT (id)
            ON DELETE CASCADE,
    CONSTRAINT participant_answer_fk_2
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id)
            ON DELETE CASCADE,
    CONSTRAINT participant_answer_fk_3
        FOREIGN KEY (answer_id)
            REFERENCES ANSWER (id)
            ON DELETE CASCADE
);
