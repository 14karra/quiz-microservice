package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
