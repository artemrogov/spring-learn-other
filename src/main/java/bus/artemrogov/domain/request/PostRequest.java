package bus.artemrogov.domain.request;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
