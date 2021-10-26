//package dunice.news.user.service;
//
//import dunice.news.commond.repository.RoleRepository;
//import dunice.news.commond.repository.UserRepository;
//import dunice.news.commond.Entity.RoleEntity;
//import dunice.news.commond.Entity.UserEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//
//
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    RoleRepository roleRepository;
//    @Autowired
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity user = userRepository.findByUsername(username);
//
//        if (user == null) {
//
//            throw new UsernameNotFoundException("User not found");// TODO make enum
//        }
//
//        return user;
//    }
//
//    public UserEntity findUserById(Long userId) {
//        Optional<UserEntity> userFromDb = userRepository.findById(userId);
//
//        return userFromDb.orElse(new UserEntity());
//    }
//
//    public boolean saveUser(UserEntity user) {
//        UserEntity userFromDB = userRepository.findByUsername(user.getUsername());
//        if (userFromDB != null) {
//
//            return false;
//
//        }
//
//        user.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//        return true;
//    }
//
//    public boolean deleteUser(Long userId) {
//        if (userRepository.findById(userId).isPresent()) {
//            userRepository.deleteById(userId);// TODO exist by id
//            // TODO make exeption
//            return true;
//        }
//        return false;
//    }
//
//}