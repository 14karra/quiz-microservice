package quiz.entity;

import quiz.exception.InvalidPrimaryKeyFormatException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Entity
@Table(name = "QUESTION_TO_QUIZ")
public class QuestionToQuiz {

    @EmbeddedId
    private CompositeKey compositeKey;

    public QuestionToQuiz() {
    }

    public QuestionToQuiz(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Embeddable
    public static class CompositeKey implements Serializable {

        @Column(name = "QUESTION_ID", unique = true)
        private Long questionId;

        @Column(name = "QUIZ_ID")
        private Long quizId;

        @PreRemove
        @PrePersist
        public void isStructurallyValid() {
            if (questionId == null || quizId == null || questionId <= 0 || quizId <= 0) {
                throw new InvalidPrimaryKeyFormatException(this);
            }
        }

        public CompositeKey() {
        }

        public CompositeKey(Long questionId, Long quizId) {
            this.questionId = questionId;
            this.quizId = quizId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeKey)) return false;
            CompositeKey that = (CompositeKey) o;
            return getQuestionId().equals(that.getQuestionId()) &&
                    getQuizId().equals(that.getQuizId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getQuestionId(), getQuizId());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("COMPOSITE_KEY { ")
                    .append("QUESTION_ID=").append(questionId)
                    .append(", QUIZ_ID=").append(quizId)
                    .append(" }");
            return sb.toString();
        }

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public Long getQuizId() {
            return quizId;
        }

        public void setQuizId(Long quizId) {
            this.quizId = quizId;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QUESTION_TO_QUIZ { ")
                .append("COMPOSITE_KEY=").append(compositeKey)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final QuestionToQuiz buff;

        public Builder() {
            buff = new QuestionToQuiz();
        }

        public Builder(QuestionToQuiz buff) {
            this.buff = buff;
        }

        public Builder compositeKey(CompositeKey compositeKey) {
            buff.setCompositeKey(compositeKey);
            return this;
        }

        public QuestionToQuiz build() {
            QuestionToQuiz questionToQuiz = new QuestionToQuiz();
            questionToQuiz.setCompositeKey(buff.getCompositeKey());
            return questionToQuiz;
        }
    }

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }
}
