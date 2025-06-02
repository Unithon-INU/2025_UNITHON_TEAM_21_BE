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

    private String name;            // 기관 이름
    private String address;          //
    private String contactEmail;
    private String accountnumber;   // 계좌번호
    private String phoneNumber;

    private int donationGoalAmount;     // 🎯 목표 금액
    private int totalReceivedAmount = 0; // 💰 누적 수령 금액
}
