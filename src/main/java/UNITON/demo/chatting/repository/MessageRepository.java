package UNITON.demo.chatting.repository;

import UNITON.demo.chatting.entity.ChatRoom;
import UNITON.demo.chatting.entity.Message;
import UNITON.demo.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoomOrderBySentAtAsc(ChatRoom chatRoom);

    long countByChatRoomAndIsReadFalseAndSenderUserNot(ChatRoom chatRoom, UserEntity user);
}
