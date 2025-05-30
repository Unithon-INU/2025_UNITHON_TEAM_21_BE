package UNITON.demo.login.service;

import UNITON.demo.login.dto.JoinByEmailDto;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.entity.UserRole;
import UNITON.demo.login.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public void joinByEmail(JoinByEmailDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        UserEntity user =createBasicUser(dto.getEmail(), dto.getNickname());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
    }

    private UserEntity createBasicUser(String email, String nickname) {
        UserEntity user = new UserEntity();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setRole(UserRole.USER);
        return user;
    }
}
