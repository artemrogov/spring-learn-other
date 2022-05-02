package bus.artemrogov.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "first_name",length = 255)
    @NotNull(message = "first_name должно быть заполнено")
    @Size(min = 2,message = "Min value 2 symbols!")
    private String firstName;

    @Column(name = "last_name",length = 255)
    @NotNull(message = "lastName должно быть заполнено!")
    @Size(min = 2,message = "Min value 2 symbols")
    private String lastName;
}
