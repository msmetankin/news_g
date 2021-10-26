package dunice.news.commond.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "t_role")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;
    @Column(name = "name")
    @NotBlank(message = "Text is mandatory")
    String name;

}