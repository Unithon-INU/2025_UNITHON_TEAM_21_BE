package UNITON.demo.login.controller;

import UNITON.demo.login.dto.OrganizationCheckResponseDto;
import UNITON.demo.login.service.OrganizationAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/org-admin")
public class OrganizationAdminController {
    private final OrganizationAdminService organizationAdminService;

    @GetMapping("/check")
    public ResponseEntity<OrganizationCheckResponseDto> checkMembership(@RequestParam Long id) {
        OrganizationCheckResponseDto responseDto = organizationAdminService.isRegistered(id);
        return ResponseEntity.ok(responseDto);
    }
}
