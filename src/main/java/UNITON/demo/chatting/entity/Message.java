package UNITON.demo.chatting.entity;

import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;

    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    private UserEntity senderUser;

    @ManyToOne
    @JoinColumn(name = "sender_org_id")
    private Organization senderOrganization;

    @Column(length = 1000,nullable = false)
    private String content;

    private boolean isRead = false;
    private LocalDateTime sentAt;

    @PrePersist
    public void sentTime() {
        this.sentAt = LocalDateTime.now();
    }

    public boolean isFromUser() {
        return senderUser != null;
    }
}

