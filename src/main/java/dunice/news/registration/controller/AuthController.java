package dunice.news.registration.controller;

import dunice.news.common.CustomException;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.common.entity.UserEntity;
import dunice.news.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static dunice.news.common.Errors.USER_WITH_THIS_EMAIL_ALREADY_EXIST;


@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    private Object Null;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity u = new UserEntity();
        u.setPassword(registrationRequest.getPassword());
        u.setUsername(registrationRequest.getName());
        u.setEmail(registrationRequest.getEmail());
        u.setAvatar(registrationRequest.getAvatar());
        if (userService.findByLogin(u.getUsername())== Null) userService.saveUser(u);
        else {
            throw new CustomException(USER_WITH_THIS_EMAIL_ALREADY_EXIST);
        }
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getUsername());
        return new AuthResponse(token);
    }
}
