package dunice.news.common.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name  = "ID")
    private Integer id;
    @Column(name = "username")
    @NotBlank(message = "Text is mandatory")
    private String username;
    @Column(name = "password")
    @NotBlank(message = "Text is mandatory")
    private String password;
    @Column(name = "email")
    @NotBlank(message = "Text is mandatory")
    private String email;
    @Column(name = "avatar")
    private String avatar;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;



}