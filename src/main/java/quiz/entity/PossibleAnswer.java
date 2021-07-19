package quiz.entity;

import quiz.exception.InvalidPrimaryKeyFormatException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Stream;


@Entity
@Table(name = "POSSIBLE_ANSWER")
public class PossibleAnswer {

    @EmbeddedId
    private CompositeKey compositeKey;

    public PossibleAnswer() {
    }

    public PossibleAnswer(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    @Embeddable
    public static class CompositeKey implements Serializable {

        @Column(name = "QUESTION_ID", unique = true)
        private Long questionId;

        @Column(name = "ANSWER_ID")
        private Long answerId;

        @PreRemove
        @PrePersist
        public void isStructurallyValid() {
            if (Stream.of(questionId, answerId)
                    .anyMatch(value -> Objects.isNull(value) || value <= 0)) {
                throw new InvalidPrimaryKeyFormatException(this);
            }
        }

        public CompositeKey() {
        }

        public CompositeKey(Long questionId, Long answerId) {
            this.questionId = questionId;
            this.answerId = answerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeKey)) return false;
            CompositeKey that = (CompositeKey) o;
            return getQuestionId().equals(that.getQuestionId()) &&
                    getAnswerId().equals(that.getAnswerId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getQuestionId(), getAnswerId());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("COMPOSITE_KEY { ")
                    .append("QUESTION_ID=").append(questionId)
                    .append(", ANSWER_ID=").append(answerId)
                    .append(" }");
            return sb.toString();
        }

        public Long getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Long questionId) {
            this.questionId = questionId;
        }

        public Long getAnswerId() {
            return answerId;
        }

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("POSSIBLE_ANSWER { ")
                .append("COMPOSITE_KEY=").append(compositeKey)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final PossibleAnswer buff;

        public Builder() {
            buff = new PossibleAnswer();
        }

        public Builder(PossibleAnswer buff) {
            this.buff = buff;
        }

        public Builder compositeKey(CompositeKey compositeKey) {
            buff.setCompositeKey(compositeKey);
            return this;
        }

        public PossibleAnswer build() {
            PossibleAnswer questionToQuiz = new PossibleAnswer();
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
