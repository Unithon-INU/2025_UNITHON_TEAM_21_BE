package UNITON.demo.donation.repository;

import UNITON.demo.donation.entity.Donation;
import UNITON.demo.donation.entity.DonationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation,Long> {

    // 유저 ID로 기부 내역 조회
    List<Donation> findByUserId(Long userid);

    // 기관 ID로 기부 내역 조회
    List<Donation> findByOrganizationId(Long organizationId);

    // 상태별 기부 내역 (예: 기관이 확인 대기중인 내역)
    List<Donation> findByOrganizationIdAndStatus(Long organizationId, DonationStatus status);


    @Query("SELECT SUM(d.amount) FROM Donation d WHERE d.user.id = :userId and d.status=:status")
    Integer getTotalDonationsByUserId(@Param("userId") Long userId,@Param("status") DonationStatus status);

}
