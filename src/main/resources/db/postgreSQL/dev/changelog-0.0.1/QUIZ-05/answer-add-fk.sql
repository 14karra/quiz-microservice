ALTER TABLE ANSWER
    ADD CONSTRAINT answer_question_fk
        FOREIGN KEY (question_id)
            REFERENCES QUESTION (id);