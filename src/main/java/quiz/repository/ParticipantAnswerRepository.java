package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.ParticipantAnswer;

import java.util.Optional;

@Repository
public interface ParticipantAnswerRepository extends JpaRepository<ParticipantAnswer, ParticipantAnswer.CompositeKey> {

    Optional<ParticipantAnswer> findByCompositeKey_ParticipantIdAndCompositeKey_QuestionId(Long compositeKey_participantId,
                                                                                           Long compositeKey_questionId);
}
