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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    public ChatRoom getOrCreateChatRoom(UserEntity user, Long targetUserId, Long organizationId) {
        if (targetUserId != null && targetUserId > 0) {
            UserEntity otherUser = userRepository.findById(targetUserId)
                    .orElseThrow(() -> new IllegalArgumentException("상대 유저가 존재하지 않습니다."));
            // ✅ 항상 작은 ID가 먼저 오도록 정렬
            UserEntity user1 = user.getId() < otherUser.getId() ? user : otherUser;
            UserEntity user2 = user.getId() < otherUser.getId() ? otherUser : user;

            return chatRoomRepository.findChatRoomBetweenUsers(user, otherUser)
                    .orElseGet(() -> {
                        ChatRoom room = new ChatRoom();
                        room.setUser(user);
                        room.setOtherUser(otherUser);
                        return chatRoomRepository.save(room);
                    });

        } else if (organizationId != null && organizationId > 0) {
            Organization org = organizationRepository.findById(organizationId)
                    .orElseThrow(() -> new IllegalArgumentException("기관이 존재하지 않습니다."));

            return chatRoomRepository.findByUserAndOrganization(user, org)
                    .orElseGet(() -> {
                        ChatRoom room = new ChatRoom();
                        room.setUser(user);
                        room.setOrganization(org);
                        return chatRoomRepository.save(room);
                    });
        }

        throw new IllegalArgumentException("대상 ID가 비어있습니다.");
    }
    /**
     * 내가 참여 중인 모든 채팅방 조회
     */
    public List<ChatRoom> getAllChatRoomsForUser(UserEntity user) {
        return chatRoomRepository.findAllByUserInvolved(user);
    }

    /**
     * 특정 채팅방에 내가 접근 가능한지 확인
     */
    public ChatRoom validateAccess(Long roomId, UserEntity currentUser) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("채팅방이 존재하지 않습니다."));

        boolean isAccessible =
                room.getUser().equals(currentUser) ||
                        (room.getOtherUser() != null && room.getOtherUser().equals(currentUser));
// 추가: 유저 ↔ 기관일 때도 유저는 접근 가능해야 함
        if (room.getOrganization() != null && room.getUser().equals(currentUser)) {
            isAccessible = true;
        }

        if (!isAccessible) {
            throw new AccessDeniedException("해당 채팅방에 접근할 수 없습니다.");
        }

        return room;
    }
}
