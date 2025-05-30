package UNITON.demo.chatting.controller;

import UNITON.demo.chatting.dto.ChatRoomDto;
import UNITON.demo.chatting.dto.MessageDto;
import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.service.ChatRoomService;
import UNITON.demo.chatting.service.MessageService;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final UserRepository userRepository;

    @GetMapping
    public List<ChatRoomDto> getMyChatRooms(@AuthenticationPrincipal UserEntity user) {
        return chatRoomService.getAllChatRoomsForUser(user).stream()
                .map(room -> ChatRoomDto.from(room, user))
                .toList();
    }
    @GetMapping("/debug")
    public List<ChatRoomDto> debugChatRooms(@RequestParam Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저 없음"));
        return chatRoomService.getAllChatRoomsForUser(user).stream()
                .map(room -> ChatRoomDto.from(room, user))
                .toList();
    }

    @GetMapping("/get-or-create")
    public ResponseEntity<Long> getOrCreateChatRoom(
            @RequestParam String senderEmail,
            @RequestParam Long targetUserId,
            @RequestParam(required = false) Long organizationId
    ) {
        // ✅ 이메일로 유저 조회
        UserEntity user = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        ChatRoom room = chatRoomService.getOrCreateChatRoom(user, targetUserId, organizationId);
        return ResponseEntity.ok(room.getId());
    }

    @GetMapping("/{chatRoomId}/messages")
        public List<MessageDto> getMessages(@PathVariable Long chatRoomId, @AuthenticationPrincipal UserEntity user) {
            return messageService.getMessages(chatRoomId, user).stream()
                    .map(MessageDto::from)
                .toList();
    }

    @PutMapping("/{chatRoomId}/read")
    public void markAsRead(@PathVariable Long chatRoomId, @AuthenticationPrincipal UserEntity user) {
        messageService.markMessagesAsRead(chatRoomId, user);
    }
}
