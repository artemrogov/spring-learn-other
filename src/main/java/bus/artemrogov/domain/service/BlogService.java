package bus.artemrogov.domain.service;


import bus.artemrogov.domain.entity.Comment;
import bus.artemrogov.domain.entity.Post;
import bus.artemrogov.domain.repository.CommentRepository;
import bus.artemrogov.domain.repository.PostRepository;
import bus.artemrogov.domain.repository.TagRepository;
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


    @Autowired
    private TagRepository tagRepository;


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

            if(post.getTags() == null || post.getTags().isEmpty()){
                post.setTags(postEdit.getTags());
            }

            return this.postRepository.save(post);

        }).orElseThrow(IllegalArgumentException::new);
    }


    public Collection<Long> syncTags(Long idPost, Long[] ids){

        return this.tagRepository.findAllById(Arrays.asList(ids)).stream().map(tag -> {

            boolean exists = this.postRepository.existsById(idPost);

            if (exists){

                Post postSaved = postRepository.findById(idPost).orElseThrow();

                postSaved.setTags(List.of(tag));
                tag.setPosts(List.of(postSaved));
                postRepository.save(postSaved);
                return tag.getId();
            }

            return null;

        }).filter(Objects::nonNull).collect(Collectors.toList());
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

    public Comment updateCommentById(Long id,String body){
        return this.commentRepository.findById(id).map(comment -> {
             comment.setId(id);
             comment.setContent(body);
             return this.commentRepository.save(comment);
        }).orElseThrow(IllegalArgumentException::new);
    }

}
