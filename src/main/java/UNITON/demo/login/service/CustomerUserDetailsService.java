package UNITON.demo.login.service;

import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> userData=userRepository.findByEmail(email);
        log.info("로그인 요청 email: " + email);
        if (userData == null) {
            throw new UsernameNotFoundException("유저를 찾을 수 없습니다.");
        }
        return new CustomerUserDetails(userData.get());
    }
}
