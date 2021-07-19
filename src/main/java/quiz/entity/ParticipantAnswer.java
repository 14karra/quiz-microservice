package quiz.entity;

import org.springframework.format.annotation.DateTimeFormat;
import quiz.exception.InvalidPrimaryKeyFormatException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.stream.Stream;

@Entity
@Table(name = "PARTICIPANT_ANSWER")
public class ParticipantAnswer {

    @EmbeddedId
    private CompositeKey compositeKey;

    @NotNull
    @Column(name = "DATE_TIME")
    @DateTimeFormat
    private OffsetDateTime dateTime;

    public ParticipantAnswer() {
    }

    public ParticipantAnswer(CompositeKey compositeKey, @NotNull OffsetDateTime dateTime) {
        this.compositeKey = compositeKey;
        this.dateTime = dateTime;
    }

    @PreUpdate
    @PrePersist
    private void prePersist() {
        dateTime = OffsetDateTime.now();
    }

    @Embeddable
    public static class CompositeKey implements Serializable {

        @Column(name = "PARTICIPANT_ID")
        private Long participantId;

        @Column(name = "QUESTION_ID")
        private Long questionId;

        @Column(name = "ANSWER_ID")
        private Long answerId;

        @PreRemove
        @PrePersist
        public void isStructurallyValid() {
            if (Stream.of(participantId, questionId, answerId)
                    .anyMatch(value -> Objects.isNull(value) || value <= 0)) {
                throw new InvalidPrimaryKeyFormatException(this);
            }
        }

        public CompositeKey() {
        }

        public CompositeKey(Long participantId, Long questionId, Long answerId) {
            this.participantId = participantId;
            this.questionId = questionId;
            this.answerId = answerId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CompositeKey)) return false;
            CompositeKey that = (CompositeKey) o;
            return getParticipantId().equals(that.getParticipantId()) &&
                    getQuestionId().equals(that.getQuestionId()) &&
                    getAnswerId().equals(that.getAnswerId());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getParticipantId(), getQuestionId(), getAnswerId());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("COMPOSITE_KEY { ")
                    .append("PARTICIPANT_ID=").append(participantId)
                    .append(", QUESTION_ID=").append(questionId)
                    .append(", ANSWER_ID=").append(answerId)
                    .append(" }");
            return sb.toString();
        }

        public Long getParticipantId() {
            return participantId;
        }

        public void setParticipantId(Long participantId) {
            this.participantId = participantId;
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
        sb.append("PARTICIPANT_ANSWER { ")
                .append("COMPOSITE_KEY=").append(compositeKey)
                .append(", DATE_TIME=").append(dateTime)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final ParticipantAnswer buf;

        public Builder(ParticipantAnswer buf) {
            this.buf = buf;
        }

        public Builder compositeKey(CompositeKey compositeKey) {
            buf.setCompositeKey(compositeKey);
            return this;
        }

        public ParticipantAnswer build() {
            ParticipantAnswer participantAnswer = new ParticipantAnswer();
            participantAnswer.setCompositeKey(buf.getCompositeKey());
            return participantAnswer;
        }
    }

    public CompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(CompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
