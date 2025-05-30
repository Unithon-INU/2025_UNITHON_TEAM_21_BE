package UNITON.demo.chatting.dto;

import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.login.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomDto {
    private Long id;
    private String lastMessage;
    private String targetName;
    private boolean isOrganization;
    private LocalDateTime updatedAt;

    public static ChatRoomDto from(ChatRoom room, UserEntity me) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.setId(room.getId());
        dto.setLastMessage(room.getLastMessage());
        dto.setUpdatedAt(room.getUpdatedAt());

        if (room.getOrganization() != null) {
            dto.setTargetName(room.getOrganization().getName());
            dto.setOrganization(true);
        } else {
            UserEntity target = room.getUser().equals(me) ? room.getOtherUser() : room.getUser();
            dto.setTargetName(target.getNickname());
            dto.setOrganization(false);
        }

        return dto;
    }
}
