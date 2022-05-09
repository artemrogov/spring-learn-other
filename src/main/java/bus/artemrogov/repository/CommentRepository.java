package bus.artemrogov.repository;

import bus.artemrogov.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(value = "select * from comments c where c.post_id=:postId",nativeQuery = true)
    Page<Comment> queryCommentsByPostId(@Param("postId") Long postId, Pageable pageable);
}
