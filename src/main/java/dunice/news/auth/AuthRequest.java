package dunice.news.auth;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static dunice.news.common.ErrorMessages.EMAIL_SIZE_NOT_VALID;
import static dunice.news.common.ErrorMessages.PASSWORD_NOT_VALID;

@Data
public class AuthRequest {
    @NotBlank(message = EMAIL_SIZE_NOT_VALID)
    private String login;
    @NotBlank(message = PASSWORD_NOT_VALID)
    private String password;
}