package UNITON.demo.volunteer.controller;

import UNITON.demo.login.dto.CustomerUserDetails;
import UNITON.demo.login.entity.UserEntity;
import UNITON.demo.login.repository.UserRepository;
import UNITON.demo.volunteer.dto.VolunteerBookmarkDto;
import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;
import UNITON.demo.volunteer.entity.VolunteerBookmark;
import UNITON.demo.volunteer.repository.VolunteerBookmarkRepository;
import UNITON.demo.volunteer.service.VolunteerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/volunteer")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;
    private final UserRepository userRepository;
    private final VolunteerBookmarkRepository volunteerBookmarkRepository;

    @GetMapping("/list")
    public ResponseEntity<List<VolunteerDto>> getAll(@RequestParam(defaultValue = "1") int pageNo,
                                     @RequestParam(defaultValue = "10") int numOfRows) {
        List<VolunteerDto> list = volunteerService.getVolunteerList(pageNo, numOfRows);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf("application/json;charset=UTF-8")) // ✅ 핵심 설정
                .body(list);
    }

    @GetMapping("/search")
    public List<VolunteerDto> search(@RequestParam String schCateGu,@RequestParam String keyword) {
        return volunteerService.searchByKeyword(schCateGu, keyword);
    }

    @GetMapping("/filter")
    public List<VolunteerDto> filter(@RequestParam String upperClCode,
                                     @RequestParam(defaultValue = "1") int pageNo,
                                     @RequestParam(defaultValue = "10") int numOfRows) {
        return volunteerService.filterByCategoryList(upperClCode, pageNo, numOfRows);
    }
    @GetMapping("/detail")
    public VolunteerDetailDto detail(@RequestParam String progrmRegistNo) {
        return volunteerService.getDetail(progrmRegistNo);
    }

    @Transactional
    @PostMapping("/bookmark")
    public ResponseEntity<String> toggleBookmark(@AuthenticationPrincipal CustomerUserDetails userDetails,
                                                 @RequestBody VolunteerBookmarkDto dto) {

        UserEntity user = userDetails.getUserEntity();

        // 북마크 존재 여부 확인
        if (volunteerBookmarkRepository.existsByUserAndProgrmRegistNo(user, dto.getProgrmRegistNo())) {
            // 존재하면 삭제
            volunteerBookmarkRepository.deleteByUserAndProgrmRegistNo(user, dto.getProgrmRegistNo());
            return ResponseEntity.ok("북마크 해제됨");
        }

        // 존재하지 않으면 북마크 추가
        VolunteerBookmark newBookmark = new VolunteerBookmark();
        newBookmark.setUser(user);
        newBookmark.setProgrmRegistNo(dto.getProgrmRegistNo());
        newBookmark.setProgrmSj(dto.getProgrmSj());
        newBookmark.setActPlace(dto.getActPlace());
        newBookmark.setNoticeEndde(dto.getNoticeEndde());

        try {
            volunteerBookmarkRepository.save(newBookmark);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 북마크 되어 있음");
        }

        return ResponseEntity.ok("북마크 추가됨");
    }


    /*@GetMapping("/period")
    public List<VolunteerDto> findByPeriod(@RequestParam String startDate,
                                           @RequestParam String endDate,
                                           @RequestParam(defaultValue = "1") int pageNo) {
        return volunteerService.findByPeriod(startDate, endDate, pageNo);
    }

    @GetMapping("/area")
    public List<VolunteerDto> findByArea(@RequestParam String sido,
                                         @RequestParam String gugun,
                                         @RequestParam(defaultValue = "1") int pageNo) {
        return volunteerService.findByArea(sido, gugun, pageNo);
    }

    @GetMapping("/category")
    public List<VolunteerDto> findByCategory(@RequestParam String category,
                                             @RequestParam(defaultValue = "1") int pageNo) {
        return volunteerService.findByCategory(category, pageNo);
    }

    @GetMapping("/{id}")
    public VolunteerDetailDto detail(@PathVariable String id) {
        return volunteerService.getDetail(id);
    }

    @PostMapping("/bookmark/{id}")
    public void toggleBookmark(@PathVariable String id,
                               @AuthenticationPrincipal UserDetails user) {
        volunteerService.toggleBookmark(id, user.getUsername());
    }

    @GetMapping("/bookmark")
    public List<VolunteerDto> getBookmarks(@AuthenticationPrincipal UserDetails user) {
        return volunteerService.getBookmarks(user.getUsername());
    }*/
}
