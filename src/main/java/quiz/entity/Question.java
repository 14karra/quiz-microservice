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

    @JsonIgnore
    @ManyToOne
    @JoinTable(
            name = "QUIZ_TO_QUESTION",
            joinColumns = @JoinColumn(name = "QUESTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "QUIZ_ID")
    )
    private Quiz quiz;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QUESTION { ")
                .append("ID=").append(id)
                .append(" }");
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}

