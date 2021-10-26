package dunice.news.commond.entity;

import lombok.*;
import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
@Table(name = "t_user")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name  = "ID")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;


    public UserEntity() {

    }
}