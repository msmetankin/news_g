package dunice.news.registration.controller;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String avatar;


}
