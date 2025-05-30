package UNITON.demo.volunteer.controller;

import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;
import UNITON.demo.volunteer.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
@RequiredArgsConstructor
public class VolunteerController {

    private final VolunteerService volunteerService;

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
