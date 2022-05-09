package bus.artemrogov.response;


import bus.artemrogov.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDtoResponse {

    private Long   id;

    private String content;

    @JsonIgnore
    private Long post_id;

    public static CommentDtoResponse buildToDto(Comment commentEntity){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commentEntity,CommentDtoResponse.class);
    }

    public static Comment buildToEntity(CommentDtoResponse commentDtoResponse){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(commentDtoResponse,Comment.class);
    }
}
