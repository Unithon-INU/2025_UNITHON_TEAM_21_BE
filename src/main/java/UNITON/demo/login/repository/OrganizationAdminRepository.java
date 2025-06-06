package UNITON.demo.login.repository;

import UNITON.demo.login.entity.OrganizationAdmin;
import UNITON.demo.login.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationAdminRepository extends JpaRepository<OrganizationAdmin,Long> {
    // 이메일로 기관 관리자 조회 (email 필드를 로그인 식별자로 쓴다면)
    Optional<OrganizationAdmin> findByEmail(String email);

}
