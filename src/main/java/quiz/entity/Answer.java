package quiz.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "ANSWER_ID_SEQ", sequenceName = "ANSWER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ANSWER_ID_SEQ")
    private Long id;

    @NotNull
    @Column(name = "TEXT")
    private String text;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ANSWER { ")
                .append("ID=").append(id)
                .append(", TEXT=").append(text)
                .append(" }");
        return sb.toString();
    }

    public static class Builder {

        private final Answer buf;

        public Builder() {
            buf = new Answer();
        }

        public Builder text(String text) {
            buf.setText(text);
            return this;
        }

        public Answer build() {
            Answer question = new Answer();
            question.setText(buf.getText());
            return question;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
