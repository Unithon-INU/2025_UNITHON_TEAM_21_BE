package UNITON.demo.volunteer.component;

import UNITON.demo.volunteer.dto.VolunteerDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VolunteerOpenApiClient {
    private final RestTemplate restTemplate; // ✅ 변경됨
    @Value("${openapi.1365.secret}")
    private String serviceKey;

    @PostConstruct
    public void checkKey() {
        System.out.println("✅ [DEBUG] serviceKey = " + serviceKey);
    }
    private static final String BASE = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService";

    public String searchByKeyword(String schCateGu,String keyword) {
        return get(BASE + "/getVltrSearchWordList", Map.of(
                "schCateGu",schCateGu,
                "keyword", keyword
        ));
    }
    public String getDetail(String progrmRegistNo) {
        return get(BASE + "/getVltrPartcptnItem", Map.of(
                "progrmRegistNo", progrmRegistNo));
    }

    public String getAllVolunteers(int pageNo, int numOfRows) {
        return get(BASE + "/getVltrSearchWordList", Map.of(
                "pageNo", String.valueOf(pageNo),
                "numOfRows", String.valueOf(numOfRows)
        ));
    }
    public String filterByCategoryList(String upperClCode, int pageNo, int numOfRows) {
        return get(BASE + "/getVltrSearchWordList", Map.of(
                "upperClCode",upperClCode,
                "pageNo", String.valueOf(pageNo),
                "numOfRows", String.valueOf(numOfRows)
        ));
    }
    /*public String findByPeriod(String start, String end, int pageNo) {
        return get(BASE + "/getVltrPeriodSrvcList", Map.of(
                "progrmBgnde", start,
                "progrmEndde", end,
                "pageNo", String.valueOf(pageNo)
        ));
    }

    public String findByArea(String sido, String gugun, int pageNo) {
        return get(BASE + "/getVltrAreaList", Map.of(
                "schSido", sido,
                "schSign1", gugun,
                "pageNo", String.valueOf(pageNo)
        ));
    }

    public String findByCategory(String category, int pageNo) {
        return get(BASE + "/getVltrCategoryList", Map.of(
                "upperClCode", category,
                "pageNo", String.valueOf(pageNo)
        ));
    }*/



    private String get(String baseUrl, Map<String, String> params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("serviceKey", serviceKey);

        params.forEach((key, value) -> {
            String encoded = URLEncoder.encode(value, StandardCharsets.UTF_8);
            builder.queryParam(key, encoded);
        });

        String url = builder.build(true).toUriString();
        System.out.println("🌐 요청 URL = " + url);

        // 2. 헤더 설정: XML 응답 요청
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_XML)); // 👈 핵심

        HttpEntity<?> entity = new HttpEntity<>(headers);

        // 3. 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        String xml = response.getBody();
        System.out.println("📦 응답 XML = \n" + xml);

        return xml;

    }


}



