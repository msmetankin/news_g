package dunice.news.users;


import dunice.news.common.dto.request.UpdateUserDTO;
import dunice.news.common.dto.response.ResponseAuthDTO;
import dunice.news.common.dto.response.ResponseUserDTO;
import dunice.news.registration.configuration.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
        Integer id = Integer.parseInt(jwtProvider.getIdFromToken(token));
        usersService.deleteUser(id);
        return ResponseEntity.ok();
    }
    @GetMapping
    public ResponseEntity<ResponseAuthDTO> getUser(@RequestHeader (name = "Authorization") String token){
        Integer id = Integer.parseInt(jwtProvider.getIdFromToken(token));
        return ResponseEntity.ok().body(usersService.getUser(usersService.findById(id)));
    }
    @PutMapping
    public ResponseEntity<ResponseAuthDTO> putUser(@RequestHeader(name="Authorization") String token, @Valid @RequestBody UpdateUserDTO user){
        Integer id = Integer.parseInt(jwtProvider.getIdFromToken(token));
        return ResponseEntity.ok().body(usersService.updateUser(id,user));

    }
}
