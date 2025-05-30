package UNITON.demo.volunteer.service;

import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;

import java.util.List;

public interface VolunteerService {

    List<VolunteerDto> searchByKeyword(String schCateGu,String keyword);
    List<VolunteerDto> getVolunteerList(int pageNo,int numOfRows);
    List<VolunteerDto> filterByCategoryList(String upperClCode, int pageNo, int numOfRows);
    VolunteerDetailDto getDetail(String progrmRegistNo);
    /*List<VolunteerDto> findByPeriod(String start, String end, int pageNo);
    List<VolunteerDto> findByArea(String sido, String gugun, int pageNo);
    List<VolunteerDto> findByCategory(String category, int pageNo);

    void toggleBookmark(String progrmRegistNo, String username);
    List<VolunteerDto> getBookmarks(String username);*/
}
