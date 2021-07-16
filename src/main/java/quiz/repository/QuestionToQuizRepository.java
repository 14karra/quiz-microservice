package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.QuestionToQuiz;

@Repository
public interface QuestionToQuizRepository extends JpaRepository<QuestionToQuiz, QuestionToQuiz.CompositeKey> {

    boolean existsQuestionToQuizByCompositeKey_QuestionId(Long questionId);
}
