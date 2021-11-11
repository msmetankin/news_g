package dunice.news.users;

import dunice.news.common.CustomException;
import dunice.news.common.dto.response.ResponseAuthDTO;
import dunice.news.common.dto.response.ResponseUserDTO;
import dunice.news.common.entity.UserEntity;
import dunice.news.common.repository.RoleRepository;
import dunice.news.common.repository.UserRepository;
import dunice.news.registration.configuration.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dunice.news.common.Errors.UNKNOWN;
import static dunice.news.common.Errors.USER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository usersRepository;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    public UserEntity findByLogin(String login) {
        Optional<UserEntity> userFromDb = usersRepository.findByEmail(login);
        UserEntity userEntity = userFromDb.orElseThrow(() -> new CustomException(UNKNOWN));
        return userEntity;
    }

    public void deleteUser(Long id) {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
        }
        else throw new CustomException(USER_NOT_FOUND);
    }
    public UserEntity findById(Integer userId) {
        Optional<UserEntity> userFromDb = usersRepository.findById(userId);
        UserEntity userEntity = userFromDb.orElseThrow(() -> new CustomException(UNKNOWN));
        return userEntity;
    }
    public ResponseAuthDTO getUser(UserEntity userEntity) {
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
