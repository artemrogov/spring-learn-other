package bus.artemrogov.controller.blog;


import bus.artemrogov.entity.Comment;
import bus.artemrogov.entity.Post;
import bus.artemrogov.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api-blog/v1")
public class BlogController {


    @Autowired
    private IBlogService blogService;


    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Page<Post> posts(
            @RequestParam(value = "limit",  defaultValue  =  "10")  Integer   limit,
            @RequestParam(value = "page",   defaultValue  =  "0")   Integer   page
    ){
        Pageable pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"id");
        return this.blogService.getPostsList(pageable);
    }


    @GetMapping(value = "/comments/{post_id}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Page<Comment> getCommentsPost(
            @PathVariable(value = "post_id")                     Long     postId,
            @RequestParam(value = "limit",defaultValue  = "10")  Integer  limit,
            @RequestParam(value = "page",defaultValue   = "0")   Integer  page

    ){
        Pageable pageable = PageRequest.of(page,limit, Sort.Direction.DESC,"id");
        return this.blogService.getCommentsByPostId(postId,pageable);
    }
}
