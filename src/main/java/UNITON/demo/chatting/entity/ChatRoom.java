package UNITON.demo.chatting.entity;

import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "organization_id")  // 유저 ↔ 기관용
    private Organization organization;

    private String lastMessage;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @PrePersist
    public void created() {
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void updated() {
        this.updatedAt = LocalDateTime.now();
    }
}