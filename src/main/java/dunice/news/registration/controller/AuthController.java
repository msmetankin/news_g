package dunice.news.registration.controller;

import dunice.news.common.CustomException;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.common.entity.UserEntity;
import dunice.news.registration.data.dto.request.RegistrationDTO;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import dunice.news.registration.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.Optional;

import static dunice.news.common.Errors.UNKNOWN;


@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/register")
    public ResponseEntity<ResponseAuthDTO> registerUser(@Valid @RequestBody  RegistrationDTO registrationDTO) {
         Optional<ResponseUserDTO> opt = authService.registerUser(registrationDTO);

        ResponseUserDTO responseUserDTO = opt.orElseThrow(() -> new CustomException(UNKNOWN));
        responseUserDTO.setToken(jwtProvider.generateToken(responseUserDTO.getId().toString()));
        ResponseAuthDTO responseAuthDTO = ResponseAuthDTO.builder()
                .data(responseUserDTO)
                .build();
        return ResponseEntity.ok().body(responseAuthDTO);
    }



}
