package UNITON.demo.volunteer.component;

import UNITON.demo.volunteer.dto.VolunteerDetailDto;
import UNITON.demo.volunteer.dto.VolunteerDto;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    /*public VolunteerDetailDto parseVolunteerDetail(String xml) {
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(xml)));
            Element e = (Element) doc.getElementsByTagName("item").item(0);
            VolunteerDetailDto dto = new VolunteerDetailDto();
            dto.setProgrmRegistNo(get(e, "progrmRegistNo"));
            dto.setProgrmSj(get(e, "progrmSj"));
            dto.setPostAdres(get(e, "postAdres"));
            dto.setActPlace(get(e, "actPlace"));
            dto.setNoticeEndde(get(e, "noticeEndde"));
            dto.setProgrmCn(get(e, "progrmCn"));
            dto.setEmail(get(e, "email"));
            dto.setTelno(get(e, "telno"));
            dto.setMnnstNm(get(e, "mnnstNm"));
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("상세 XML 파싱 실패", e);
        }
    }*/

    private String get(Element e, String tag) {
        NodeList n = e.getElementsByTagName(tag);
        return (n.getLength() > 0) ? n.item(0).getTextContent() : null;
    }
}
