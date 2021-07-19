CREATE TABLE POSSIBLE_ANSWER
(
    question_id bigint NOT NULL,
    answer_id   bigint NOT NULL,

    PRIMARY KEY (question_id, answer_id),
    CONSTRAINT possible_answer_fk_1
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id)
            ON DELETE CASCADE,
    CONSTRAINT possible_answer_fk_2
        FOREIGN KEY (answer_id)
            REFERENCES ANSWER (id)
            ON DELETE CASCADE
);
