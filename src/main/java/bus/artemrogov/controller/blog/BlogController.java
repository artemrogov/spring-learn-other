package bus.artemrogov.controller.blog;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api-blog/v1")
public class BlogController {

    @GetMapping(value = "/categories-posts")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String categories(){
        return "categories posts";
    }
}
