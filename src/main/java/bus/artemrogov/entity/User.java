package bus.artemrogov.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;


    @Column(name = "name")
    private String name;

    @Column(name = "first_name",length = 255)
    @NotNull(message = "first_name должно быть заполнено")
    @Size(min = 2,message = "Min value 2 symbols!")
    private String firstName;

    @Column(name = "last_name",length = 255)
    @NotNull(message = "lastName должно быть заполнено!")
    @Size(min = 2,message = "Min value 2 symbols")
    private String lastName;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.REFRESH,
                CascadeType.MERGE
            }
     )
    @JoinTable(name = "user_roles",
            joinColumns        = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
