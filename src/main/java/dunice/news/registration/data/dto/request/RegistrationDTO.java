package dunice.news.registration.data.dto.request;
import dunice.news.common.ErrorMessages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = ErrorMessages.USER_AVATAR_NOT_NULL)
    private String avatar;

    @NotBlank(message = ErrorMessages.USER_EMAIL_NOT_NULL)
    @Size(min = 5, max = 200, message = ErrorMessages.EMAIL_SIZE_NOT_VALID)
    @Email(message = ErrorMessages.USER_EMAIL_NOT_VALID)
    private String email;

    @Size(min = 1, max = 100, message = ErrorMessages.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ErrorMessages.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

    @NotBlank(message = ErrorMessages.PASSWORD_NOT_NULL)
    @Size(min = 1, max = 200, message = ErrorMessages.PASSWORD_NOT_VALID)
    private String password;

    @Size(min = 1, max = 100, message = ErrorMessages.ROLE_SIZE_NOT_VALID)
    @NotBlank(message = ErrorMessages.USER_ROLE_NOT_NULL)
    private String role;

}