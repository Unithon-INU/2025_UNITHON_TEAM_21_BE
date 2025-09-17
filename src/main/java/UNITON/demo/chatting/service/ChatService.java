package UNITON.demo.chatting.service;

/*import UNITON.demo.chatting.dto.ChatMessageDto;
import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.entity.Message;
import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    /**
     * WebSocket 메시지 수신 → 저장 + 전송 DTO 반환
     */
    /*public ChatMessageDto handleIncomingMessage(ChatMessageDto chatMessageDto, String senderEmail) {

        System.out.println("💬 handleIncomingMessage 호출됨!");


        UserEntity senderUser = userRepository.findByEmail(senderEmail).orElse(null);
        Organization senderOrg = organizationRepository.findByContactEmail(senderEmail).orElse(null);
        boolean fromUser = senderUser != null;

        if (senderUser == null && senderOrg == null) {
            throw new IllegalArgumentException("인증된 사용자 또는 기관이 아닙니다.");
        }

        ChatRoom chatRoom = chatRoomService.getOrCreateChatRoom(
                senderUser,
                chatMessageDto.getTargetUserId(),
                chatMessageDto.getTargetOrganizationId()
        );

        Message savedMessage = messageService.saveMessage(
                chatRoom.getId(),
                chatMessageDto.getContent(),
                senderEmail,
                fromUser
        );

        return ChatMessageDto.builder()
                .chatRoomId(chatRoom.getId())
                .content(savedMessage.getContent())
                .fromUser(fromUser)
                .sentAt(savedMessage.getSentAt())
                .build();
    }
}*/
