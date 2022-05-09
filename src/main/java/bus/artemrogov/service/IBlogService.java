package bus.artemrogov.service;

import bus.artemrogov.entity.Comment;
import bus.artemrogov.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface IBlogService {

    Page<Post>       getPostsList(Pageable pageable);
    Post             getPostById(Long id);
    Post             getPostBySlug(String slug);

    Boolean          destroyPost(Long id);
    Collection<Long> destroyPostByIdsAll(Long[] ids);

    Post             createPost(Post post);
    Post             updatePost(Long id, Post post);

    Boolean          detachTags(Long[] ids);
    Boolean          attachTags(Long[] ids);

    Page<Comment>    getCommentsByPostId(Long postId, Pageable pageable);

    Comment          createCommentByIdPost(Long postId,String body);

}
