package dunice.news.registration.controller;

import dunice.news.common.CustomException;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.common.entity.UserEntity;
import dunice.news.registration.data.dto.request.RegistrationDTO;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import dunice.news.registration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Optional;

import static dunice.news.common.Errors.UNKNOWN;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<ResponseAuthDTO> registerUser(@Valid @RequestBody  RegistrationDTO registrationDTO) {
         Optional<ResponseUserDTO> opt = authService.registerUser(registrationDTO);
         return ResponseEntity.ok().body(authService.userToken(opt));
    }



}
