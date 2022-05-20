package bus.artemrogov;

import bus.artemrogov.domain.repository.PostRepository;
import bus.artemrogov.domain.repository.TagRepository;
import bus.artemrogov.domain.service.IBlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Profile(value = "db_data")
public class RelationTests {


    @Autowired
    private PostRepository postRepository;


    @Autowired
    private TagRepository tagRepository;


    @Autowired
    private IBlogService blogService;


    @Test
    public void testPostTagsRelation(){

       /* Post post1 = new Post();

        post1.setTitle("Первая статья или заметка!");

        post1.setSlug("First-article01");

        post1.setDescription("Первая статья краткое описание!");
        post1.setContent("<p>Hello world!</p>");

       Post post2 = new Post();
       post2.setTitle("Вторая статья");
       post2.setSlug("second-article");
       post2.setDescription("Текст второй статьи");
       post2.setContent("<p><strong class='old-style'>Большое содержание</strong> для второй статьи</p>");

       this.blogService.createPost(post1);
       this.blogService.createPost(post2);*/


      ///  this.blogService.updatePost(3L,postCreated);


       // Long[] ids = {3L,5L};

        ///Long[] ids = {4L,3L,6L,7L};

        ///this.blogService.destroyPostByIdsAll(ids);



        /*Post post1 = new Post();
        post1.setTitle("Первая статья или заметка!");
        post1.setSlug("First-article01");
        post1.setDescription("Первая статья краткое описание!");
        post1.setContent("<p>Hello world!</p>");

        Comment comment1 = new Comment();
        comment1.setContent("Test comment");
        this.blogService.createPost(post1);

         this.blogService.createCommentByIdPost(post1.getId(), "Test comment 2");
         this.blogService.createCommentByIdPost(post1.getId(), "Test comment 3");
         this.blogService.createCommentByIdPost(post1.getId(), "Test comment 4");
         this.blogService.createCommentByIdPost(post1.getId(), "Test comment 5");

        Pageable pageable = PageRequest.of(0,3, Sort.Direction.DESC,"content","id");
        Page<Comment> comments = this.blogService.getCommentsByPostId(8L,pageable);
        Assert.assertEquals(3,comments.getSize());*/

    }

    @Test
    public void testDeleteTagRelation(){

    }
}
