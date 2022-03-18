package dunice.news.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AuthResponse {
    private String avatar;
    private String email;
    private Integer id;
    private String name;
    private String role;
    private String token;

}