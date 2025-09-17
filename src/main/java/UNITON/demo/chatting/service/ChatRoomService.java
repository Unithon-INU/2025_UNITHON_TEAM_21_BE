package UNITON.demo.chatting.service;

import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.repository.ChatRoomRepository;
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
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final OrganizationRepository organizationRepository;

    public ChatRoom getOrCreateChatRoom(UserEntity user, Long organizationId) {
        if (organizationId == null || organizationId <= 0) {
            throw new IllegalArgumentException("기관 ID가 유효하지 않습니다.");
        }

        // 🔍 기관 조회
        Organization organization = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new IllegalArgumentException("기관이 존재하지 않습니다."));

        // ✅ 기존 채팅방 조회 or 새로 생성
        return chatRoomRepository.findByUserAndOrganization(user, organization)
                .orElseGet(() -> {
                    ChatRoom room = new ChatRoom();
                    room.setUser(user);
                    room.setOrganization(organization);
                    return chatRoomRepository.save(room);
                });
    }
    /**
     * 내가 참여 중인 모든 채팅방 조회
     */
    public List<ChatRoom> getAllChatRoomsForUser(UserEntity user) {
        return chatRoomRepository.findAllByUserInvolved(user.getId());
    }
    public List<ChatRoom> getAllChatRoomsForOrganization(Organization organization) {
        return chatRoomRepository.findAllByOrganizationId(organization.getId());
    }
    /**
     * 특정 채팅방에 내가 접근 가능한지 확인
     */

}
