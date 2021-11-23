package dunice.news.common.dto.request;

import dunice.news.common.ErrorMessages;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static dunice.news.common.Constants.maxi;
import static dunice.news.common.Constants.mini;

@Getter
@Setter
public class UpdateUserDTO {
    @NotBlank(message = ErrorMessages.USER_AVATAR_NOT_NULL)
    private String avatar;

    @NotBlank(message = ErrorMessages.USER_EMAIL_NOT_NULL)
    @Size(min = mini, max = maxi, message = ErrorMessages.EMAIL_SIZE_NOT_VALID)
    @Email(message = ErrorMessages.USER_EMAIL_NOT_VALID)
    private String email;

    @Size(min = mini,max = maxi, message = ErrorMessages.USERNAME_SIZE_NOT_VALID)
    @NotBlank(message = ErrorMessages.USER_NAME_HAS_TO_BE_PRESENT)
    private String name;

    @Size(min = mini, max = maxi, message = ErrorMessages.ROLE_SIZE_NOT_VALID)
    @NotBlank(message = ErrorMessages.USER_ROLE_NOT_NULL)
    private String role;
}
