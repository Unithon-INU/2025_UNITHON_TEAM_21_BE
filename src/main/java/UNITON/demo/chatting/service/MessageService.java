package UNITON.demo.chatting.service;

import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.entity.Message;
import UNITON.demo.chatting.repository.ChatRoomRepository;
import UNITON.demo.chatting.repository.MessageRepository;
import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    /**
     * 메시지 저장 (유저 or 기관 발신자)
     */
    public Message saveMessage(Long chatRoomId, String content, String senderEmail, boolean fromUser) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        Message message = new Message();
        message.setChatRoom(chatRoom);
        message.setContent(content);
        message.setRead(false);

        if (fromUser) {
            UserEntity sender = userRepository.findByEmail(senderEmail)
                    .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            message.setSenderUser(sender);
        } else {
            Organization senderOrg = organizationRepository.findByContactEmail(senderEmail)
                    .orElseThrow(() -> new IllegalArgumentException("기관이 존재하지 않습니다."));
            message.setSenderOrganization(senderOrg);
        }

        // 채팅방 최신 상태 업데이트
        chatRoom.setLastMessage(content);
        chatRoom.setUpdatedAt(LocalDateTime.now());
        chatRoomRepository.save(chatRoom);

        return messageRepository.save(message);
    }

    /**
     * 채팅방 메시지 리스트 조회 (정렬 포함)
     */
    public List<Message> getMessages(Long chatRoomId, UserEntity currentUser) {
        ChatRoom room = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        // 접근 권한 확인
        //if (!room.getUser().equals(currentUser) &&
        //        (room.getOtherUser() == null || !room.getOtherUser().equals(currentUser))) {
        //    throw new AccessDeniedException("채팅방 접근 불가");
       // }

        return messageRepository.findByChatRoomOrderBySentAtAsc(room);
    }

    /**
     * 안 읽은 메시지 읽음 처리
     */
    public void markMessagesAsRead(Long chatRoomId, UserEntity currentUser) {
        ChatRoom room = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        List<Message> messages = messageRepository.findByChatRoomOrderBySentAtAsc(room);

        for (Message msg : messages) {
            // 내가 보낸 메시지는 읽음 처리 대상 아님
            boolean isMyMessage = (msg.isFromUser() && msg.getSenderUser().equals(currentUser))
                    || (!msg.isFromUser() && room.getUser().equals(currentUser));
            if (!isMyMessage) {
                msg.setRead(true);
            }
        }

        messageRepository.saveAll(messages);
    }
}
