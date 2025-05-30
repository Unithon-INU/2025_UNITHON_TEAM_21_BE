package UNITON.demo.login.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;             // 기관 이름
    private String region;           // 지역명
    private String contactEmail;
    private String phoneNumber;      // 기관 연락처
    private String profileImageUrl;  // 썸네일용
}
