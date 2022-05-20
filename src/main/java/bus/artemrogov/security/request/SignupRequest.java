package bus.artemrogov.security.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    private String username;
    private String password;
    private String name;
    private String firstName;
    private String lastName;
    private Set<String> role = new HashSet<>();
}
