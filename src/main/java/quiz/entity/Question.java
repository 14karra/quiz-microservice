package quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "QUESTION_ID_SEQ", sequenceName = "QUESTION_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUESTION_ID_SEQ")
    private Long id;

    @NotNull
    @Column(name = "LABEL")
    private String label;

    @NotNull
    @Column(name = "TYPE")
    @Convert(converter = QuestionType.QuestionTypeConverter.class)
    private QuestionType type;

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "QUESTION_TO_QUIZ",
            joinColumns = @JoinColumn(name = "QUESTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUIZ_ID")
    )
    private Quiz quiz;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QUESTION { ")
                .append("ID=").append(id)
                .append(", LABEL=").append(label)
                .append(", TYPE=").append(type)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final Question buf;

        public Builder(Question buf) {
            this.buf = buf;
        }

        public Builder label(String label) {
            buf.setLabel(label);
            return this;
        }

        public Builder type(QuestionType type) {
            buf.setType(type);
            return this;
        }

        public Question build() {
            Question question = new Question();
            question.setLabel(buf.getLabel());
            question.setType(buf.getType());
            return question;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

