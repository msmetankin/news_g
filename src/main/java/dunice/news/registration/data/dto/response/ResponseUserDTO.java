package dunice.news.registration.data.dto.response;

import dunice.news.common.entity.UserEntity;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {
    private String avatar;
    private String email;
    private Integer id;
    private String name;
    private String role;
    private String token;

    public static ResponseUserDTO fromUserEntity(UserEntity entity) {
        return ResponseUserDTO.builder()
                .avatar(entity.getAvatar())
                .email(entity.getEmail())
                .id(entity.getId())
                .name(entity.getName())
                .role(entity.getRoleEntity().getName())
                .build();
    }
}
