package UNITON.demo.login.service;

import UNITON.demo.login.entity.OrgRefreshToken;
import UNITON.demo.login.entity.OrganizationAdmin;
import UNITON.demo.login.repository.OrgRefreshTokenRepository;
import UNITON.demo.login.repository.OrganizationAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrgRefreshTokenService {

    private final OrgRefreshTokenRepository orgRefreshTokenRepository;
    private final OrganizationAdminRepository organizationAdminRepository;

    @Value("${app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    // 1. 생성 또는 갱신
    public OrgRefreshToken createOrUpdateRefreshToken(Long adminId) {
        Optional<OrgRefreshToken> existingToken = orgRefreshTokenRepository.findByAdminId(adminId);
        OrgRefreshToken refreshToken;

        if (existingToken.isPresent()) {
            verifyExpiration(existingToken.get());
            refreshToken = existingToken.get();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        } else {
            refreshToken = new OrgRefreshToken();
            OrganizationAdmin admin = organizationAdminRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("기관 관리자를 찾을 수 없습니다."));
            refreshToken.setAdmin(admin);
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        }

        return orgRefreshTokenRepository.save(refreshToken);
    }

    // 2. 토큰 문자열로 조회
    public Optional<OrgRefreshToken> findByToken(String token) {
        return orgRefreshTokenRepository.findByToken(token);
    }

    // 3. 만료 확인
    public void verifyExpiration(OrgRefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            orgRefreshTokenRepository.delete(token);
            throw new RuntimeException("기관 리프레시 토큰이 만료되었습니다. 다시 로그인해주세요.");
        }
    }

    // 4. 토큰 삭제
    public int deleteByToken(String token) {
        return orgRefreshTokenRepository.deleteByToken(token);
    }
}
