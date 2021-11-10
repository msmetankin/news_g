package dunice.news.registration.configuration;



import dunice.news.common.entity.UserEntity;
import dunice.news.registration.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  AuthService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userService.findByLogin(username));
    }
}
