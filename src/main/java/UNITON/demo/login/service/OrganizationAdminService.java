package UNITON.demo.login.service;


import UNITON.demo.login.dto.OrganizationCheckResponseDto;
import UNITON.demo.login.repository.OrganizationAdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationAdminService {
    private final OrganizationAdminRepository organizationAdminRepository;

    public OrganizationCheckResponseDto isRegistered(Long organizationId) {
        boolean exists = organizationAdminRepository.existsByOrganizationId(organizationId);
        return new OrganizationCheckResponseDto(organizationId, exists);
    }
}
