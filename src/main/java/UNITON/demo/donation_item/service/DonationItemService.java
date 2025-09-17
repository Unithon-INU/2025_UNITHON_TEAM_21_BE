package UNITON.demo.donation_item.service;

import UNITON.demo.chatting.repository.OrganizationRepository;
import UNITON.demo.donation_item.dto.DonationItemDto;
import UNITON.demo.donation_item.entity.DonationItem;
import UNITON.demo.donation_item.repository.DonationItemRepository;
import UNITON.demo.login.entity.Organization;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonationItemService {
    private final DonationItemRepository donationItemRepository;
    private final OrganizationRepository organizationRepository;

    public Long create(Long organizationId, DonationItemDto dto) {
        Organization org = organizationRepository.findById(organizationId)
                .orElseThrow(() -> new IllegalArgumentException("기관이 존재하지 않습니다."));

        DonationItem item = DonationItem.builder()
                .id(dto.getId())
                .itemName(dto.getItemName())
                .requiredQuantity(dto.getRequiredQuantity())
                .currentQuantity(dto.getCurrentQuantity())
                .organization(org)
                .build();

        DonationItem saved = donationItemRepository.save(item);
        return saved.getId();  // item_id 반환
    }

    public void update(Long itemId, DonationItemDto dto) {
        DonationItem item = donationItemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("물품이 존재하지 않습니다."));

        item.setId(itemId);
        item.setItemName(dto.getItemName());
        item.setRequiredQuantity(dto.getRequiredQuantity());
        item.setCurrentQuantity(dto.getCurrentQuantity());
        donationItemRepository.save(item);
    }

    public void delete(Long itemId) {
        donationItemRepository.deleteById(itemId);
    }



    public List<DonationItemDto> getItemsByOrganization(Long orgId) {
        List<DonationItem> items = donationItemRepository.findByOrganizationId(orgId);
        return items.stream()
                .map(item -> DonationItemDto.builder()
                        .id(item.getId())
                        .itemName(item.getItemName())
                        .requiredQuantity(item.getRequiredQuantity())
                        .currentQuantity(item.getCurrentQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}
