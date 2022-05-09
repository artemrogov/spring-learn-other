package bus.artemrogov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;


    @Column(name = "content",columnDefinition = "TEXT")
    private String content;


    @Column(name = "description",length = 500)
    private String description;


    @Column(name = "slug",unique = true)
    private String slug;


    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.DETACH
    },
        fetch = FetchType.LAZY
    )
    @JoinTable(name = "tags_posts",
            joinColumns        = @JoinColumn(name = "post_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",  referencedColumnName = "id")
    )
    @JsonIgnoreProperties(value = "posts")
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();


    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    },
            fetch = FetchType.LAZY,
            mappedBy = "post"
    )
    @JsonIgnoreProperties(value = "post")
    @JsonManagedReference()
    private List<Comment> comments = new ArrayList<>();

}
