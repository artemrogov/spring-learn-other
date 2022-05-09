package bus.artemrogov;

import bus.artemrogov.entity.Post;
import bus.artemrogov.entity.Tag;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PostBuilder {

    private String title;
    private String content;
    private String description;

    private Collection<String> tags;
    private Collection<Tag> tagResult;

    public static PostBuilder postBuilder(){
        return new PostBuilder();
    }

    public PostBuilder withTitle(String title){
        this.title = title;
        return this;
    }

    public PostBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public PostBuilder withContent(String content){
        this.content = content;
        return this;
    }

    public PostBuilder withTags(String[] tagNames){
        if (tagNames == null){
            return this;
        }
        this.tags = List.of(tagNames);
        return this;
    }

    public Post build(){

        Post post = new Post();
        post.setTitle(this.title);
        post.setDescription(this.description);
        post.setContent(this.content);

        if(this.tags!=null){
            this.tagResult = this.tags.stream().map(this::createTag).collect(Collectors.toList());
            post.setTags((List<Tag>) tagResult);
        }

        return post;
    }

    private Tag createTag(String tagName){
        Tag tag = new Tag();
        tag.setName(tagName);
        return tag;
    }
}
