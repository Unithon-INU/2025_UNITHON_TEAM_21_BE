package UNITON.demo.chatting.repository;

import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    // 유저 ↔ 유저
    Optional<ChatRoom> findByUserAndOtherUser(UserEntity user, UserEntity otherUser);

    // 유저 ↔ 기관
    Optional<ChatRoom> findByUserAndOrganization(UserEntity user, Organization organization);

    Optional<ChatRoom> findByUserIdAndOrganizationId(Long userId, Long orgId);

    Optional<ChatRoom> findByUserIdAndOtherUserId(Long userId, Long otherUserId);
    // 로그인 유저가 포함된 모든 채팅방 (목록 조회용)
    @Query("SELECT c FROM ChatRoom c WHERE c.user = :user OR c.otherUser = :user")
    List<ChatRoom> findAllByUserInvolved(@Param("user") UserEntity user);

    @Query("""
    SELECT c FROM ChatRoom c
    WHERE (c.user = :user1 AND c.otherUser = :user2)
       OR (c.user = :user2 AND c.otherUser = :user1)
       """)
    Optional<ChatRoom> findChatRoomBetweenUsers(@Param("user1") UserEntity user1, @Param("user2") UserEntity user2);
}
