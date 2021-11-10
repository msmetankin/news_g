package dunice.news.common.entity;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Data
@Entity
@Table(name = "roles")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    int id;
    @Column(name = "name")
    @NotBlank(message = "Text is mandatory")
    String name;
}