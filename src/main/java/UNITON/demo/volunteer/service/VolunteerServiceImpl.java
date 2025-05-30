package UNITON.demo.volunteer.service;

import UNITON.demo.login.repository.UserRepository;
import UNITON.demo.volunteer.component.VolunteerOpenApiClient;
import UNITON.demo.volunteer.component.VolunteerXmlParser;
import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService{
    private final VolunteerOpenApiClient openApiClient;
    private final VolunteerXmlParser xmlParser;
    private final UserRepository userRepository;
    /*private final BookmarkRepository bookmarkRepository;*/

    @Override
    public List<VolunteerDto> searchByKeyword(String schCateGu,String keyword) {
        String xml = openApiClient.searchByKeyword(schCateGu,keyword);
        System.out.println(xml);
        return xmlParser.parseVolunteerList(xml);
    }

    @Override
    public List<VolunteerDto> getVolunteerList(int pageNo, int numOfRows) {
        String xml = openApiClient.getAllVolunteers(pageNo, numOfRows);
        return xmlParser.parseVolunteerList(xml);
    }

    @Override
    public List<VolunteerDto> filterByCategoryList(String upperClCode, int pageNo, int numOfRows) {
        String xml = openApiClient.filterByCategoryList(upperClCode,pageNo, numOfRows);
        return xmlParser.parseVolunteerList(xml);
    }


    @Override
    public VolunteerDetailDto getDetail(String progrmRegistNo) {
        String xml = openApiClient.getDetail(progrmRegistNo);
        return xmlParser.parseVolunteerDetail(xml);
    }


/*@Override
    public List<VolunteerDto> findByPeriod(String start, String end, int pageNo) {
        return List.of();
    }

    @Override
    public List<VolunteerDto> findByArea(String sido, String gugun, int pageNo) {
        return List.of();
    }

    @Override
    public List<VolunteerDto> findByCategory(String category, int pageNo) {
        return List.of();
    }


    @Override
    public List<VolunteerDto> getBookmarks(String username) {
        return List.of();
    }*/
}
