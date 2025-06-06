package UNITON.demo.volunteer.dto;

import lombok.Data;

@Data
public class VolunteerDto {
    private String progrmRegistNo;
    private String progrmSj;       // 제목
    private String actPlace;       // 장소
    private String noticeEndde;    // 마감일
}
