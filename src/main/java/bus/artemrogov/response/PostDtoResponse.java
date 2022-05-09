package bus.artemrogov.response;


import bus.artemrogov.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoResponse {
    private Long   id;
    private String title;
    private String description;
    private String content;
    private Collection<Tag> tags = new ArrayList<>();
}
