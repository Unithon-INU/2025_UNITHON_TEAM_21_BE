package UNITON.demo.login.repository;

import UNITON.demo.login.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    // 이메일로 유저 찾기 (로그인, 중복체크 등에서 사용)
    Optional<UserEntity> findByEmail(String email);

    // 닉네임으로 유저 찾기 (선택적으로 사용)
    Optional<UserEntity> findByNickname(String nickname);

    // 이메일 중복 여부 확인
    boolean existsByEmail(String email);
}
