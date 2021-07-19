ALTER TABLE ANSWER
    DROP CONSTRAINT answer_question_fk;

DROP INDEX answer_question_i;

ALTER TABLE ANSWER
    DROP COLUMN question_id;