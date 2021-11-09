package dunice.news.user;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
    }
//    @GetMapping(value = "/{id}")
//    public String registerUser((@PathVariable(name = "id") long id)) {
//        UserEntity u = new UserEntity();
//        u.getPassword();
//        u.setUsername(registrationRequest.getName());
//        u.setEmail(registrationRequest.getEmail());
//        u.setAvatar(registrationRequest.getAvatar());
//        if (userService.findByLogin(u.getUsername())== Null) userService.saveUser(u);
//        else {
//            throw new CustomException(USER_WITH_THIS_EMAIL_ALREADY_EXIST);
//        }
//        return "OK";
//

}
