package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
