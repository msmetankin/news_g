package dunice.news.registration.controller;

import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.registration.data.dto.request.RegistrationDTO;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import dunice.news.registration.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class RegistrationController {

    private final RegistrationService authService;
    private final JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<ResponseAuthDTO> registerUser(@Valid @RequestBody  RegistrationDTO registrationDTO) {
         Optional<ResponseUserDTO> opt = authService.registerUser(registrationDTO);
         return ResponseEntity.ok().body(authService.userToken(opt));
    }



}
