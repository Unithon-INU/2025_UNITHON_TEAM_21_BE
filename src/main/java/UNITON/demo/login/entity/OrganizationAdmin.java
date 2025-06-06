package UNITON.demo.login.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrganizationAdmin {

    public OrganizationAdmin() {
        this.role = UserRole.ORG_ADMIN;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(length = 100)
    private String password; // 일반 가입자만 사용

    @Column(nullable = false, unique = true)
    private String adminname;

    @Column(length = 100)
    private String organizationname; // 기관 이름

    @OneToOne
    private Organization organization; // 관리하는 기관 1:1

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;


    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
