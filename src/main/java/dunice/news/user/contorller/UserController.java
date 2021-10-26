//package dunice.news.user.contorller;
//
//import dunice.news.user.response.ResponceHandler;
//import dunice.news.commond.Entity.UserEntity;
//import dunice.news.user.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/v1")
//public class UserController {
//
//    @Autowired
//    private UserDetailsServiceImpl userService;
//
//    @GetMapping("/user")
//    public String registration(Model model) {
//        model.addAttribute("userForm", new UserEntity());
//
//        return "registration";
//    }
//    @GetMapping("/user/{id}")
//    public String userById(@PathVariable(name = "id") Long id,Model model) {
//        model.addAttribute("userForm", userService.findUserById(id));
//        return "userById";
//    }
//    @PostMapping("/user")
//    public String addUser(@ModelAttribute("userForm") @Valid UserEntity userForm, BindingResult bindingResult, Model model) {
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//        if (!userService.saveUser(userForm)){
//            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
//            return "registration";
//        }
//
//        return "redirect:/";
//    }
//    @DeleteMapping(value = "/user/{id}")
//    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
//        try {
//            final boolean deleted = userService.deleteUser(id);
//            return ResponceHandler.generateResponse("Successfully deleted selected data!", HttpStatus.OK, deleted);
//        }
//        catch (Exception e){
//            return ResponceHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
//        }
//    }
//
//}
//
