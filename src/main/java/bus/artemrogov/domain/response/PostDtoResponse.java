package bus.artemrogov.domain.response;

import bus.artemrogov.domain.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private Collection<TagResponse> tags = new ArrayList<>();


    public static PostDtoResponse buildToDto(Post postEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postEntity,PostDtoResponse.class);
    }


    public static Post buildToEntity(PostDtoResponse postDtoResponse){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(postDtoResponse,Post.class);
    }

}
