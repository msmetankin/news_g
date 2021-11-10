package dunice.news.auth;

import dunice.news.common.CustomException;
import dunice.news.common.entity.UserEntity;
import dunice.news.common.repository.UserRepository;
import dunice.news.registration.configuration.jwt.JwtProvider;
import dunice.news.registration.data.dto.response.ResponseAuthDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dunice.news.common.Errors.*;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public Optional<UserEntity> findByLoginAndPassword(String login, String password) {
        Optional<UserEntity> userEntity = usersRepository.findByEmail(login);
        if (userEntity.isPresent()) {
            if (!passwordEncoder.matches(password, userEntity.get().getPassword())) {
                throw new CustomException(PASSWORD_NOT_VALID);
            } else {
                return userEntity;
            }
        } else throw new CustomException(USER_NOT_FOUND);
    }
   public  ResponseAuthDTO userAns(Optional<UserEntity> user) {
        UserEntity userEntity = user.orElseThrow(() -> new CustomException(UNKNOWN));
      String token = jwtProvider.generateToken(userEntity.getId().toString());
       ResponseUserDTO responseUserDTO = new ResponseUserDTO();
       responseUserDTO.setName(userEntity.getName());
       responseUserDTO.setId(userEntity.getId());
       responseUserDTO.setToken(token);
       responseUserDTO.setAvatar(userEntity.getAvatar());
       responseUserDTO.setEmail(userEntity.getEmail());
       responseUserDTO.setRole(userEntity.getRoleEntity().getName());


       return ResponseAuthDTO.builder()
              .data(responseUserDTO)
              .build();
 }
}