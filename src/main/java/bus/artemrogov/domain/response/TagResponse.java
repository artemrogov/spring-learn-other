package bus.artemrogov.domain.response;

import bus.artemrogov.domain.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagResponse {

    private Long id;
    private String name;

    public static TagResponse tagResponses(Tag tag){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tag, TagResponse.class);
    }
}
