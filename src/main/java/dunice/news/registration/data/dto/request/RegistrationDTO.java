package dunice.news.registration.data.dto.request;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "Text is mandatory")
    private String avatar;
    @NotBlank(message = "Text is mandatory")
    private String email;
    @NotBlank(message = "Text is mandatory")
    private String name;
    @NotBlank(message = "Text is mandatory")
    private String password;
}