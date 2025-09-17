package UNITON.demo.chatting.controller;

import UNITON.demo.chatting.dto.ChatRoomDto;
import UNITON.demo.chatting.dto.MessageDto;
import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.chatting.service.ChatRoomService;
import UNITON.demo.chatting.service.MessageService;
import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chatroom")
@RequiredArgsConstructor
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    @GetMapping
    public List<ChatRoomDto> getMyChatRooms(@AuthenticationPrincipal CustomerUserDetails customUserDetails) {
        UserEntity user = customUserDetails.getUserEntity(); // ✅ 안전하게 UserEntity 추출
        return chatRoomService.getAllChatRoomsForUser(user).stream()
                .map(room -> ChatRoomDto.from(room, user))
                .toList();
    }

    @GetMapping("/org")
    public List<ChatRoomDto> getChatRoomsForOrganization(@RequestParam Long organizationId) {
        Organization org = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 기관이 존재하지 않습니다."));

        return chatRoomService.getAllChatRoomsForOrganization(org).stream()
                .map(ChatRoomDto::fromForOrganization) // ✅ 기관 전용 변환 사용
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
    public ResponseEntity<Map<String, Long>> getOrCreateChatRoom(
            @RequestParam Long userId,
            @AuthenticationPrincipal CustomerUserDetails customUserDetails,
            @RequestParam Long organizationId
    ) {
        if (!customUserDetails.getUserId().equals(userId)) {
            throw new SecurityException("인증된 사용자와 요청된 userId가 일치하지 않습니다.");
        }

        // ✅ 여기서 UserEntity 꺼내서 넘기기
        UserEntity user = customUserDetails.getUserEntity();
        ChatRoom room = chatRoomService.getOrCreateChatRoom(user, organizationId);

        return ResponseEntity.ok(Map.of("chatRoomId", room.getId()));
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
