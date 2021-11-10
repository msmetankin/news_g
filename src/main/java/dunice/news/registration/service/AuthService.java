package dunice.news.registration.service;

import dunice.news.common.CustomException;
import dunice.news.common.entity.UserEntity;
import dunice.news.common.repository.RoleRepository;
import dunice.news.common.repository.UserRepository;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.registration.data.dto.request.RegistrationDTO;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dunice.news.common.Errors.*;
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository usersRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Optional<ResponseUserDTO> registerUser(RegistrationDTO registrationData) {

        if (!usersRepository.findByEmail(registrationData.getEmail()).isEmpty())
            throw new CustomException(USER_WITH_THIS_EMAIL_ALREADY_EXIST);

        UserEntity user = new UserEntity();
        user.setAvatar(registrationData.getAvatar());
        user.setEmail(registrationData.getEmail());
        user.setUsername(registrationData.getName());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setRoleEntity(roleRepository.findByName(registrationData.getRole())
                .orElseThrow(() -> new CustomException(USER_ROLE_NOT_NULL)));

        user = usersRepository.save(user);

        return Optional.of(ResponseUserDTO.fromUserEntity(user));

    }

    public UserEntity findByLogin(String login) {
        return usersRepository.findByUsername(login);
    }

    public  ResponseAuthDTO userToken(Optional<ResponseUserDTO> opt) {
        ResponseUserDTO responseUserDTO = opt.orElseThrow(() -> new CustomException(UNKNOWN));
        responseUserDTO.setToken(jwtProvider.generateToken(responseUserDTO.getId().toString()));
        return ResponseAuthDTO.builder()
                .data(responseUserDTO)
                .build();
    }
}