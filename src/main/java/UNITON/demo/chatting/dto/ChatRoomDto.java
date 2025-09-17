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

        // ✅ 기관 기반 채팅방만 처리
        dto.setTargetName(room.getOrganization().getName());
        dto.setOrganization(true);

        return dto;
    }

    public static ChatRoomDto fromForOrganization(ChatRoom room) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.setId(room.getId());
        dto.setLastMessage(room.getLastMessage());
        dto.setUpdatedAt(room.getUpdatedAt());

        // 기관 기준 → 상대는 유저
        if (room.getUser() != null) {
            dto.setTargetName(room.getUser().getNickname());
        } else {
            dto.setTargetName("알 수 없음");
        }

        dto.setOrganization(false); // 기관 입장에서 상대는 유저

        return dto;
    }
}
