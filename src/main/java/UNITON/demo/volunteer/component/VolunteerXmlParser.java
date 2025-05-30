package UNITON.demo.volunteer.component;

import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// 6. XML Parser: VolunteerXmlParser
@Component
public class VolunteerXmlParser {
    public List<VolunteerDto> parseVolunteerList(String xml) {
        List<VolunteerDto> result = new ArrayList<>();
        System.out.println("items size = " + result.size());
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            InputSource inputSource = new InputSource(byteStream);
            inputSource.setEncoding("UTF-8");

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(inputSource);
            NodeList nodes = doc.getElementsByTagName("item");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element e = (Element) nodes.item(i);
                VolunteerDto dto = new VolunteerDto();
                dto.setProgrmRegistNo(get(e, "progrmRegistNo"));
                dto.setProgrmSj(get(e, "progrmSj"));
                dto.setActPlace(get(e, "actPlace"));
                dto.setNoticeEndde(get(e, "noticeEndde"));
                result.add(dto);
            }
        } catch (Exception e) {
            throw new RuntimeException("XML 파싱 실패", e);
        }
        return result;
    }

    public VolunteerDetailDto parseVolunteerDetail(String xml) {
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
            InputSource inputSource = new InputSource(byteStream);
            inputSource.setEncoding("UTF-8");

            Document doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(inputSource);

            NodeList nodes = doc.getElementsByTagName("item");
            if (nodes.getLength() == 0) return null;

            Element e = (Element) nodes.item(0);  // 단일 item

            VolunteerDetailDto dto = new VolunteerDetailDto();
            dto.setActBeginTm(get(e, "actBeginTm"));
            dto.setActEndTm(get(e, "actEndTm"));
            dto.setActPlace(get(e, "actPlace"));
            dto.setActWkdy(get(e, "actWkdy"));
            dto.setAdultPosblAt(get(e, "adultPosblAt"));
            dto.setAppTotal(get(e, "appTotal"));
            dto.setEmail(get(e, "email"));
            dto.setFamilyPosblAt(get(e, "familyPosblAt"));
            dto.setFxnum(get(e, "fxnum"));
            dto.setGrpPosblAt(get(e, "grpPosblAt"));
            dto.setGugunCd(get(e, "gugunCd"));
            dto.setMnnstNm(get(e, "mnnstNm"));
            dto.setNanmmbyNm(get(e, "nanmmbyNm"));
            dto.setNanmmbyNmAdmn(get(e, "nanmmbyNmAdmn"));
            dto.setNoticeBgnde(get(e, "noticeBgnde"));
            dto.setNoticeEndde(get(e, "noticeEndde"));
            dto.setPbsvntPosblAt(get(e, "pbsvntPosblAt"));
            dto.setPostAdres(get(e, "postAdres"));
            dto.setProgrmBgnde(get(e, "progrmBgnde"));
            dto.setProgrmEndde(get(e, "progrmEndde"));
            dto.setProgrmCn(get(e, "progrmCn"));
            dto.setProgrmRegistNo(get(e, "progrmRegistNo"));
            dto.setProgrmSj(get(e, "progrmSj"));
            dto.setProgrmSttusSe(get(e, "progrmSttusSe"));
            dto.setRcritNmpr(get(e, "rcritNmpr"));
            dto.setSidoCd(get(e, "sidoCd"));
            dto.setSrvcClCode(get(e, "srvcClCode"));
            dto.setTelno(get(e, "telno"));
            dto.setYngbgsPosblAt(get(e, "yngbgsPosblAt"));
            dto.setUrl("https://1365.go.kr/vols/P9210/partcptn/timeCptn.do?type=show&progrmRegistNo=" + get(e, "progrmRegistNo"));
            return dto;

        } catch (Exception ex) {
            throw new RuntimeException("XML 파싱 실패", ex);
        }
    }


    private String get(Element e, String tag) {
        NodeList n = e.getElementsByTagName(tag);
        return (n.getLength() > 0) ? n.item(0).getTextContent() : null;

    }
}
