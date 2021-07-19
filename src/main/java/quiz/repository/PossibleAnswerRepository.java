package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.PossibleAnswer;

@Repository
public interface PossibleAnswerRepository extends JpaRepository<PossibleAnswer, Long> {
}
