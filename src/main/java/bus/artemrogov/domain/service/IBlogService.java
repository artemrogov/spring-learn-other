package bus.artemrogov.domain.service;

import bus.artemrogov.domain.entity.Comment;
import bus.artemrogov.domain.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Collection;

public interface IBlogService {

    Page<Post>       getPostsList(Pageable pageable);
    Post             getPostById(Long id);
    Post             getPostBySlug(String slug);

    Boolean          destroyPost(Long id);
    Collection<Long> destroyPostByIdsAll(Long[] ids);

    Post             createPost(Post post);
    Post             updatePost(Long id, Post post);

    Collection<Long>   syncTags(Long idPost,Long[] ids);

    Page<Comment>    getCommentsByPostId(Long postId, Pageable pageable);

    Comment          createCommentByIdPost(Long postId,String body);


    Comment           updateCommentById(Long id,String body);

}
