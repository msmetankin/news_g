package dunice.news.registration.service;

import dunice.news.common.CustomException;
import dunice.news.common.entity.UserEntity;
import dunice.news.common.repository.RoleRepository;
import dunice.news.common.repository.UserRepository;
import dunice.news.registration.data.dto.request.RegistrationDTO;
import dunice.news.registration.data.dto.response.ResponseUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static dunice.news.common.Errors.*;

@Service
public class AuthService {

    private UserRepository usersRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository usersRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public  Optional<ResponseUserDTO> registerUser(RegistrationDTO registrationData) {

        if(!usersRepository.findByEmail(registrationData.getEmail()).isEmpty())
            throw new CustomException(USER_WITH_THIS_EMAIL_ALREADY_EXIST);

        UserEntity user = new UserEntity();
        user.setAvatar(registrationData.getAvatar());
        user.setEmail(registrationData.getEmail());
        user.setUsername(registrationData.getName());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setRoleEntity(roleRepository.findByName(registrationData.getRole())
                .orElseThrow(() -> new CustomException(USER_ROLE_NOT_NULL)));

        user = usersRepository.save(user);

        System.out.println(EMAIL_SIZE_NOT_VALID.getMessage());

        return Optional.of(ResponseUserDTO.fromUserEntity(user));
    }
    public UserEntity findByLogin(String login) {
        return usersRepository.findByUsername(login);
    }
    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }


}
