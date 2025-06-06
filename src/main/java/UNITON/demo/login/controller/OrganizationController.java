package UNITON.demo.login.controller;

import UNITON.demo.chatting.dto.OrganizationDto;
import UNITON.demo.chatting.dto.UserDto;
import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.login.entity.Organization;
import UNITON.demo.login.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/org")
public class OrganizationController {
    private final OrganizationRepository organizationRepository;

    @GetMapping("/all")
    public ResponseEntity<List<OrganizationDto>> getAllOrgs(@RequestParam(required = false) String excludeEmail) {
        List<Organization> orgs = organizationRepository.findAll();

        List<OrganizationDto> result = orgs.stream()
                .filter(org -> excludeEmail == null || !org.getContactEmail().equals(excludeEmail))
                .map(OrganizationDto::fromEntity)
                .toList();

        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationById(@PathVariable Long id) {
        return organizationRepository.findById(id)
                .map(OrganizationDto::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
