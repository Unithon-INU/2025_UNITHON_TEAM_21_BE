package UNITON.demo.donation.entity;

import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private UserEntity user;

    @ManyToOne(optional = false)
    private Organization organization;

    private int amount;

    @Enumerated(EnumType.STRING)
    private DonationStatus status; // PENDING, CONFIRMED, REJECTED

    private LocalDateTime donatedAt;

    @PrePersist
    protected void onCreate() {
        this.donatedAt = LocalDateTime.now();
    }
}
