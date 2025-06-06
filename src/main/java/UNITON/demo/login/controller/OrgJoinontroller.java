package UNITON.demo.login.controller;

import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.login.dto.OrgJoinRequestDto;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.OrganizationAdmin;
import UNITON.demo.login.repository.OrganizationAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class OrgJoinontroller {
    private final OrganizationAdminRepository orgAdminRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public ResponseEntity<?> registerOrgAdmin(@RequestBody OrgJoinRequestDto dto) {

        // 연관된 기관 존재 확인
        Organization organization = organizationRepository.findByName(dto.getOrganizationname())
                .orElseThrow(() -> new RuntimeException("기관 정보를 찾을 수 없습니다."));

        // 관리자 생성
        OrganizationAdmin admin = new OrganizationAdmin();
        admin.setEmail(dto.getEmail());
        admin.setPassword(passwordEncoder.encode(dto.getPassword()));
        admin.setAdminname(dto.getAdminname());
        admin.setOrganization(organization);

        orgAdminRepository.save(admin);

        return ResponseEntity.ok(Map.of("message", "기관 관리자 등록 완료"));
    }
}
