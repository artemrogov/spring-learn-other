package bus.artemrogov.service;


import bus.artemrogov.entity.Comment;
import bus.artemrogov.entity.Post;
import bus.artemrogov.repository.CommentRepository;
import bus.artemrogov.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private CommentRepository commentRepository;


    @Override
    public Page<Post> getPostsList(Pageable pageable) {
        return this.postRepository.findAll(pageable);
    }

    @Override
    public Post getPostById(Long id) {
        return this.postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Post getPostBySlug(String slug) {
        return this.postRepository.findBySlug(slug).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Boolean destroyPost(Long id) {

        boolean deleted = false;

        boolean existsPost = this.postRepository.existsById(id);

        if(existsPost){
            this.postRepository.deleteById(id);
            deleted = true;
        }

        return deleted;
    }

    @Override
    public Collection<Long> destroyPostByIdsAll(Long[] ids) {
        return this.postRepository.findAllById(Arrays.asList(ids)).stream().map(post -> {

            boolean exists = this.postRepository.existsById(post.getId());

            if (exists){
                Long postId = post.getId();
                this.postRepository.deleteById(postId);
                return postId;
            }

            return null;

        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


    @Override
    public Post createPost(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {

        return this.postRepository.findById(id).map(postEdit->{

            post.setId(id);

            if(post.getTitle() == null){
                post.setTitle(postEdit.getTitle());
            }

            if (post.getDescription() == null){
                post.setDescription(postEdit.getDescription());
            }

            if(post.getContent() == null){
                post.setContent(postEdit.getContent());
            }

            if(post.getSlug() == null){
                post.setSlug(postEdit.getSlug());
            }

            return this.postRepository.save(post);

        }).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Boolean detachTags(Long[] ids) {
        return null;
    }

    @Override
    public Boolean attachTags(Long[] ids) {
        return null;
    }

    @Override
    public Page<Comment> getCommentsByPostId(Long postId, Pageable pageable) {
        return this.commentRepository.queryCommentsByPostId(postId,pageable);
    }

    @Override
    public Comment createCommentByIdPost(Long postId,String body) {

        return this.postRepository.findById(postId).map(post->{
            post.setId(postId);
            Comment comment = new Comment();
            comment.setContent(body);
            comment.setPost(post);
            return this.commentRepository.save(comment);

        }).orElseThrow(IllegalArgumentException::new);
    }
}
