CREATE TABLE CORRECT_ANSWER
(
    question_id bigint NOT NULL,
    answer_id   bigint NOT NULL,
    primary key (question_id, answer_id),
    CONSTRAINT correct_answer_fk_1
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id)
            ON DELETE CASCADE,
    CONSTRAINT correct_answer_fk_2
        FOREIGN KEY (answer_id)
            REFERENCES ANSWER (id)
            ON DELETE CASCADE
);
