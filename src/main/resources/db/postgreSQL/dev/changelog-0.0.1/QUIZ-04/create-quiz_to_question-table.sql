CREATE TABLE QUIZ_TO_QUESTION
(
    quiz_id     bigint NOT NULL,
    question_id bigint NOT NULL UNIQUE,
    primary key (quiz_id, question_id),
    CONSTRAINT quiz_question_fk_1
        FOREIGN KEY (quiz_id)
            REFERENCES QUIZ (id)
            ON DELETE CASCADE,
    CONSTRAINT quiz_question_fk_2
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id)
            ON DELETE CASCADE
);
