package dunice.news.commond.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
@AllArgsConstructor
@Data
@Entity
@Table(name = "t_user")
public class UserEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name  = "ID")
    private Long id;
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
    @Column(name = "role")
    @NotBlank(message = "Text is mandatory")
    private String role;





}