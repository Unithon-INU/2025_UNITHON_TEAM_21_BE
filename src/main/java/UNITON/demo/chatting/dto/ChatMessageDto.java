package UNITON.demo.chatting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageDto {
    private Long chatRoomId;
    private Long targetUserId;           // 유저 대상일 경우
    private Long targetOrganizationId;   // 기관 대상일 경우
    private String senderEmail;
    private String content;
    private boolean fromUser;
    private LocalDateTime sentAt;
}
