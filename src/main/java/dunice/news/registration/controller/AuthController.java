package dunice.news.registration.controller;

import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.commond.entity.UserEntity;
import dunice.news.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getName());
        u.setEmail(registrationRequest.getEmail());
        u.setAvatar(registrationRequest.getAvatar());
        userService.saveUser(u);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }
}
