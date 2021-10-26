package dunice.news.registration.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthDTO {
    private ResponseUserDTO data;
    private int statusCode;
    private boolean success;
}
