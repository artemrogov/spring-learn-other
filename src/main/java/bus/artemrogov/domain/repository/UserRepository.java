package bus.artemrogov.domain.repository;

import bus.artemrogov.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
      Optional<User> findByUsername(String name);
      Boolean existsByUsername(String username);
}
