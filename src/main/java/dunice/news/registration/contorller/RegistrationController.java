package dunice.news.registration.contorller;

import dunice.news.registration.Entity.User;

import dunice.news.registration.response.ResponceHandler;
import dunice.news.registration.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/v1")
public class RegistrationController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/user")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }
    //check mistake
    @GetMapping(value ="/user/{id}")
    public ResponseEntity<?>
    //check mistake
    @PostMapping("/user")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())){
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }
        if (!userService.saveUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            final boolean deleted = userService.deleteUser(id);
            return ResponceHandler.generateResponse("Successfully deleted selected data!", HttpStatus.OK, deleted);
        }
        catch (Exception e){
            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

}

