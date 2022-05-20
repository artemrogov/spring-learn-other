package bus.artemrogov.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false,columnDefinition = "TEXT")
    private String  content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnoreProperties(value = "comments")
    @JsonBackReference
    private Post post;
}
