package UNITON.demo.volunteer.dto;

import lombok.Data;

@Data
public class VolunteerDetailDto {
    private String progrmRegistNo;
    private String progrmSj;
    private String actPlace;
    private String postAdres;
    private String progrmCn;       // 상세 내용
    private String noticeEndde;
    private String actBeginTm;
    private String actEndTm;
    private String email;
    private String telno;
    private String mnnstNm;        // 기관명
}
