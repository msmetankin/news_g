package dunice.news.registration.service;

import dunice.news.commond.entity.RoleEntity;
import dunice.news.commond.entity.UserEntity;
import dunice.news.commond.repository.RoleRepository;
import dunice.news.commond.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository usersRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository usersRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return usersRepository.save(userEntity);
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
