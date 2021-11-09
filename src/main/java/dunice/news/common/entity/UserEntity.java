package dunice.news.common.entity;


import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static dunice.news.common.ErrorMessages.*;


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
    @NotBlank(message = USER_NAME_HAS_TO_BE_PRESENT)
    private String username;
    @Column(name = "password")
    @NotBlank(message = PASSWORD_NOT_NULL)
    private String password;
    @Column(name = "email")
    @NotBlank(message = USER_EMAIL_NOT_NULL)
    private String email;
    @Column(name = "avatar")
    private String avatar;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;



}