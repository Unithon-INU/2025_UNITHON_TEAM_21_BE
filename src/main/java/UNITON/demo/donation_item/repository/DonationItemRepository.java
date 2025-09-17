package UNITON.demo.donation_item.repository;

import UNITON.demo.donation.entity.Donation;
import UNITON.demo.donation_item.entity.DonationItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationItemRepository extends JpaRepository<DonationItem, Long> {
    List<DonationItem> findByOrganizationId(Long id);
}
