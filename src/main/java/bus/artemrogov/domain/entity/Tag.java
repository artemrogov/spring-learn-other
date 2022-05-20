package bus.artemrogov.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tags")
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag_name",nullable = false)
    private String name;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;


    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "tags")
    @JsonBackReference
    List<Post> posts = new ArrayList<>();
}
