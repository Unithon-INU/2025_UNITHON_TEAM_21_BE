package UNITON.demo.chatting.repository;

import UNITON.demo.login.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findById(Long id);
    Optional<Organization> findByName(String name);
    Optional<Organization> findByContactEmail(String senderEmail);
}
