package dunice.news.auth;

import dunice.news.common.entity.UserEntity;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @PostMapping("/login")
    public ResponseEntity<ResponseAuthDTO> auth(@RequestBody AuthRequest request) {
        Optional<UserEntity> userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        return ResponseEntity.ok().body(userService.userAns(userEntity));
    }
}
