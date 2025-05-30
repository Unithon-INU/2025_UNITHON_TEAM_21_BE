package UNITON.demo.chatting.dto;

import UNITON.demo.chatting.entity.Message;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private String content;
    private boolean fromUser;
    private boolean isRead;
    private LocalDateTime sentAt;

    public static MessageDto from(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setSentAt(message.getSentAt());
        dto.setRead(message.isRead());
        dto.setFromUser(message.getSenderUser() != null);  // senderUser면 유저가 보낸 것
        return dto;
    }
}
