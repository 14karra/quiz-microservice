package quiz.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "QUIZ")
public class Quiz {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "QUIZ_ID_SEQ", sequenceName = "QUIZ_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUIZ_ID_SEQ")
    private Long id;

    @NotNull
    @Length(max = 100)
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "CREATION_DATE")
    @DateTimeFormat
    private OffsetDateTime creationDate;

    @Column(name = "START_DATE")
    @DateTimeFormat
    private OffsetDateTime startDate;

    @Column(name = "END_DATE")
    @DateTimeFormat
    private OffsetDateTime endDate;

    @NotNull
    @Column(name = "STATE")
    @Convert(converter = QuizState.QuizStateConverter.class)
    private QuizState state;

    @OrderBy("ID ASC")
    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER)
    private Set<Question> questions;

    @PrePersist
    private void prePersist() {
        state = QuizState.getInitialState();
        creationDate = OffsetDateTime.now();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QUIZ { ")
                .append("ID=").append(id)
                .append(", NAME=").append(name)
                .append(", DESCRIPTION=").append(description)
                .append(", CREATION_DATE=").append(creationDate)
                .append(", START_DATE=").append(startDate)
                .append(", END_DATE=").append(endDate)
                .append(", STATE=").append(state)
                .append(", QUESTIONS=").append(questions)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final Quiz buf;

        public Builder() {
            buf = new Quiz();
        }

        public Builder name(String name) {
            buf.name = name;
            return this;
        }

        public Builder description(String description) {
            buf.description = description;
            return this;
        }

        public Builder startDate(OffsetDateTime startDate) {
            buf.startDate = startDate;
            return this;
        }

        public Builder endDate(OffsetDateTime endDate) {
            buf.endDate = endDate;
            return this;
        }

        public Builder state(QuizState state) {
            buf.state = state;
            return this;
        }

        public Builder questions(Set<Question> questions) {
            buf.questions = questions;
            return this;
        }

        public Quiz build() {
            Quiz quiz = new Quiz();
            quiz.name = buf.name;
            quiz.description = buf.description;
            quiz.startDate = buf.startDate;
            quiz.endDate = buf.endDate;
            quiz.state = buf.state;
            quiz.questions = buf.questions;
            return quiz;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public QuizState getState() {
        return state;
    }

    public void setState(QuizState state) {
        this.state = state;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
