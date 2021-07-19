package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import quiz.entity.Quiz;
import quiz.entity.QuizState;

import java.util.List;
import java.util.Set;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findQuizByState(QuizState state);

    @Query(nativeQuery = true,
            value = "SELECT DISTINCT * FROM QUIZ AS quiz " +
                    "INNER JOIN QUESTION_TO_QUIZ AS questionToQuiz " +
                    "ON questionToQuiz.quiz_id = quiz.id " +
                    "INNER JOIN QUESTION AS question " +
                    "ON questionToQuiz.question_id = question.id " +
                    "INNER JOIN PARTICIPANT_ANSWER AS participantAnswer " +
                    "ON question.id = participantAnswer.question_id " +
                    "INNER JOIN PARTICIPANT AS participant " +
                    "ON participantAnswer.participant_id = participant.id " +
                    "WHERE participant.id = ?1")
    Set<Quiz> getQuizzesPassedByParticipant(Long participantId);
}
