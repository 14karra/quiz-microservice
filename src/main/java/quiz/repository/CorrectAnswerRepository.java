package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.CorrectAnswer;

import java.util.Optional;

@Repository
public interface CorrectAnswerRepository extends JpaRepository<CorrectAnswer, CorrectAnswer.CompositeKey> {
    Optional<CorrectAnswer> findByCompositeKey_QuestionId(Long compositeKey_questionId);
}
