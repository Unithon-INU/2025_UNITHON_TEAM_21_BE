package UNITON.demo.login.repository;

import UNITON.demo.login.entity.OrgRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface OrgRefreshTokenRepository extends JpaRepository<OrgRefreshToken, Long> {

    Optional<OrgRefreshToken> findByToken(String token);

    Optional<OrgRefreshToken> findByAdminId(Long adminId);

    int deleteByToken(String token);
}