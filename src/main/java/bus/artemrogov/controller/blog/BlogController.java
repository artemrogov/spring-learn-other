package bus.artemrogov.controller.blog;

import bus.artemrogov.PostBuilder;
import bus.artemrogov.entity.Comment;
import bus.artemrogov.entity.Post;
import bus.artemrogov.entity.Tag;
import bus.artemrogov.repository.TagRepository;
import bus.artemrogov.request.CommentRequest;
import bus.artemrogov.request.PostRequest;
import bus.artemrogov.request.TagRequest;
import bus.artemrogov.request.TagRequestIds;
import bus.artemrogov.response.CommentDtoResponse;
import bus.artemrogov.response.PostDtoResponse;
import bus.artemrogov.response.TagResponse;
import bus.artemrogov.service.IBlogService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api-blog/v1")
public class BlogController {

    @Autowired
    private IBlogService blogService;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private TagRepository tagRepository;


    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Page<PostDtoResponse> posts(
            @RequestParam(value = "limit",  defaultValue  =  "10")  Integer   limit,
            @RequestParam(value = "page",   defaultValue  =  "0")   Integer   page
    ){
        Pageable pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"id");

        Page<Post> posts = this.blogService.getPostsList(pageable);

        return posts.map(PostDtoResponse::buildToDto);
    }


    @GetMapping(value = "/comments/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Page<CommentDtoResponse> getCommentsPost(
            @PathVariable(value = "post_id")                     Long     postId,
            @RequestParam(value = "limit",defaultValue  = "10")  Integer  limit,
            @RequestParam(value = "page",defaultValue   = "0")   Integer  page

    ){
        Pageable pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"id");
        Page<Comment> comments  = this.blogService.getCommentsByPostId(postId,pageable);
        return comments.map(CommentDtoResponse::buildToDto);
    }



    @PutMapping(value = "/sync/tag/{post_id}")
    public ResponseEntity<?> syncTag(
            @PathVariable(value = "post_id") Long postId,
            @Valid @RequestBody TagRequestIds tagRequestIds
    ){
        return ResponseEntity.ok(this.blogService.syncTags(postId,tagRequestIds.getIds()));
    }

    @PostMapping(value = "/create/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody TagResponse createTag(
            @Valid @RequestBody TagRequest tagRequest
    ){

        Tag tagEntity = new Tag();
        tagEntity.setName(tagRequest.getName());
        tagEntity.setDescription(tagRequest.getDescription());

        Tag tagSaved = this.tagRepository.save(tagEntity);
        return TagResponse.tagResponses(tagSaved);
    }


    @PostMapping(value = "/posts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody PostDtoResponse createPost(
            @Valid @RequestBody PostRequest postRequest
    ){
        PostBuilder postBuilder = PostBuilder.postBuilder();

        Post postResult = postBuilder
                .withTitle(postRequest.getTitle())
                .withDescription(postRequest.getDescription())
                .withContent(postRequest.getContent())
                .withTags(postRequest.getTags())
                .build();

        Post postSaved = this.blogService.createPost(postResult);

        return PostDtoResponse.buildToDto(postSaved);
    }

    @PutMapping(value = "/posts/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody PostDtoResponse putPost(
            @PathVariable(value = "post_id") Long postId,
            @Valid @RequestBody PostRequest postRequest
    ){

        PostBuilder postBuilder = PostBuilder.postBuilder();

        Post postResult = postBuilder
                .withTitle(postRequest.getTitle())
                .withDescription(postRequest.getDescription())
                .withContent(postRequest.getContent())
                .withTags(postRequest.getTags())
                .build();

        Post postSaved = this.blogService.updatePost(postId,postResult);

        return PostDtoResponse.buildToDto(postSaved);
    }

    @PostMapping(value = "/comments/{post_id}/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody CommentDtoResponse createComment(
            @PathVariable(value = "post_id") Long postId,
            @Valid
            @RequestBody CommentRequest commentDtoResponse
    ){
        return CommentDtoResponse.buildToDto(
                this.blogService.createCommentByIdPost(postId,commentDtoResponse.getContent())
        );
    }

    @PutMapping(value = "/comments/{comment_id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody CommentDtoResponse putComment(
            @PathVariable(value = "comment_id") Long commentId,
            @Valid
            @RequestBody CommentRequest commentRequest
    ){
        return CommentDtoResponse.buildToDto(
                this.blogService.updateCommentById(commentId,commentRequest.getContent())
        );
    }

}
