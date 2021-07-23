package quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quiz.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
