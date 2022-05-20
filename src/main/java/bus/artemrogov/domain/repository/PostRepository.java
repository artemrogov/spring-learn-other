package bus.artemrogov.domain.repository;

import bus.artemrogov.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    Optional<Post> findBySlug(String slug);
}
