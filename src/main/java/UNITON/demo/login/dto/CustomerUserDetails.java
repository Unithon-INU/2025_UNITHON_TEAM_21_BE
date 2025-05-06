package UNITON.demo.login.dto;

import UNITON.demo.login.entity.UserEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetails {
    private final UserEntity userEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // "ROLE_USER" 형식으로 Spring Security가 인식
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole()));
    }

    @Override
    public String getPassword() {
        // 일반 로그인 사용자: 암호화된 비밀번호
        // OAuth 로그인 사용자: null or ""
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        // 로그인 식별자로 email 사용
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;  // 계정 만료 체크 안 함
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;  // 잠금 처리 안 함
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;  // 비밀번호 만료 체크 안 함
    }

    @Override
    public boolean isEnabled() {
        return true;  // 계정 활성화 상태
    }

    // 유저 추가 정보 getter
    public String getNickname() {
        return userEntity.getNickname();
    }

    public Integer getUserId() {
        return userEntity.getId();
    }

    public String getProvider() {
        return userEntity.getProvider();
    }

    public String getProviderId() {
        return userEntity.getProviderId();
    }
}
