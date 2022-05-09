package bus.artemrogov.request;


import bus.artemrogov.entity.Tag;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String content;

    @NotNull
    private String description;

    private String[] tags;
}
