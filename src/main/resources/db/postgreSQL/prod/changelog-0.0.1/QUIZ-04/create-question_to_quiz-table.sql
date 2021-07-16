CREATE TABLE QUESTION_TO_QUIZ
(
    question_id bigint NOT NULL UNIQUE,
    quiz_id     bigint NOT NULL,

    PRIMARY KEY (question_id, quiz_id),
    CONSTRAINT quiz_question_fk_2
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id)
            ON DELETE CASCADE,
    CONSTRAINT quiz_question_fk_1
        FOREIGN KEY (quiz_id)
            REFERENCES QUIZ (id)
            ON DELETE CASCADE
);