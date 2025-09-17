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


    @Query("""
    SELECT c FROM ChatRoom c
    WHERE c.user = :user AND c.organization = :organization
        """)
    Optional<ChatRoom> findByUserAndOrganization(@Param("user") UserEntity user, @Param("organization") Organization organization);

    // 버전 2: JPQL 기반 (원하는 이름 사용 가능)
    @Query("SELECT c FROM ChatRoom c WHERE c.user.id = :userId")
    List<ChatRoom> findAllByUserInvolved(@Param("userId") Long userId);

    @Query("SELECT c FROM ChatRoom c WHERE c.organization.id = :orgId")
    List<ChatRoom> findAllByOrganizationId(@Param("orgId") Long organizationId);


}
