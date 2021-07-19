package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quiz.entity.Answer;

import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT * FROM ANSWER AS answer " +
                    "INNER JOIN PARTICIPANT_ANSWER AS participantAnswer " +
                    "ON answer.id = participantAnswer.answer_id " +
                    "WHERE (participantAnswer.question_id = ?1 AND participantAnswer.participant_id = ?2)")
    Set<Answer> findParticipantAnswers(Long questionId, Long participantId);
}
