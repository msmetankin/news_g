package dunice.news.registration.data.dto.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAuthDTO {
    private ResponseUserDTO data;
    @Builder.Default
    private int statusCode = 0;
    @Builder.Default
    private boolean success = true;
}
