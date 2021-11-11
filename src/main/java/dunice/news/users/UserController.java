package dunice.news.users;


import dunice.news.common.dto.response.ResponseAuthDTO;
import dunice.news.registration.configuration.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping(value = "/v1/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService usersService;
    private final JwtProvider jwtProvider;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseAuthDTO> getUser(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok().body(usersService.getUser(usersService.findById(id)));
    }
    @DeleteMapping
    public ResponseEntity.BodyBuilder deleteUser(@RequestHeader (name = "Authorization") String token){
        Long id = Long.parseLong(jwtProvider.getIdFromToken(token));
        usersService.deleteUser(id);
        return ResponseEntity.ok();
    }
}
