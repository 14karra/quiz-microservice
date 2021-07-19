package quiz.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PARTICIPANT")
public class Participant {

    @Id
    @NotNull
    @Column(name = "ID")
    @SequenceGenerator(name = "PARTICIPANT_ID_SEQ", sequenceName = "PARTICIPANT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTICIPANT_ID_SEQ")
    private Long id;

    @Override
    public String toString() {
        return "PARTICIPANT { " + "ID=" + id + " }";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
