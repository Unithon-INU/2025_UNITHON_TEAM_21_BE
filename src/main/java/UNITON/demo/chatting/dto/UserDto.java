package UNITON.demo.chatting.dto;

import UNITON.demo.login.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String role;
    private String provider;
    private String providerId;
    private LocalDateTime createdAt;

    public static UserDto fromEntity(UserEntity user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getNickname(),
                user.getRole().name(),
                user.getProvider(),
                user.getProviderId(),
                user.getCreatedAt()
        );
    }
}