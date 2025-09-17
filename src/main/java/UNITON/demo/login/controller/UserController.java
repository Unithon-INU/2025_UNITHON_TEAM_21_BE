package UNITON.demo.login.controller;

import UNITON.demo.chatting.dto.UserDto;
import UNITON.demo.chatting.dto.UserEmailDto;
import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.dto.NicknameUpdateRequest;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import UNITON.demo.login.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    // 🔹 이메일 목록 전체 조회 (내 이메일 제외 가능)
    @GetMapping("/all-emails")
    public ResponseEntity<List<UserEmailDto>> getAllUserEmails(
            @RequestParam(required = false) String excludeEmail
    ) {
        List<UserEntity> users = userRepository.findAll();

        List<UserEmailDto> list = users.stream()
                .filter(user -> excludeEmail == null || !user.getEmail().equals(excludeEmail))
                .map(user -> new UserEmailDto(user.getId(), user.getEmail(), user.getNickname()))
                .toList();

        return ResponseEntity.ok(list);
    }



        @GetMapping("/all")
        public ResponseEntity<List<UserDto>> getAllUsers(
                @RequestParam(required = false) String excludeEmail
        ) {
            List<UserEntity> users = userRepository.findAll();

            List<UserDto> result = users.stream()
                    .filter(user -> excludeEmail == null || !user.getEmail().equals(excludeEmail))
                    .map(UserDto::fromEntity)
                    .toList();

            return ResponseEntity.ok(result);
        }

        @PutMapping("/nickname")
        public ResponseEntity<String> updateNickname(
                @RequestBody NicknameUpdateRequest request,
                @AuthenticationPrincipal CustomerUserDetails userDetails) {

            userService.updateNickname(userDetails.getUserId(), request.getNickname());
            return ResponseEntity.ok("닉네임이 성공적으로 수정되었습니다.");
    }
}


