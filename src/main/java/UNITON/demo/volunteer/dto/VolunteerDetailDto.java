package UNITON.demo.volunteer.dto;

import lombok.Data;

@Data
public class VolunteerDetailDto {
    private String actBeginTm;         // 활동 시작 시간
    private String actEndTm;           // 활동 종료 시간
    private String actPlace;           // 활동 장소
    private String actWkdy;            // 활동 요일 (ex: 1111111)
    private String adultPosblAt;       // 성인 가능 여부 (Y/N)
    private String appTotal;           // 신청 인원 수
    private String email;              // 이메일
    private String familyPosblAt;      // 가족 참여 가능 여부 (Y/N)
    private String fxnum;              // 팩스 번호
    private String grpPosblAt;         // 단체 가능 여부 (Y/N)
    private String gugunCd;            // 구군 코드
    private String mnnstNm;            // 주관 기관명
    private String nanmmbyNm;          // 나눔 참여자 명
    private String nanmmbyNmAdmn;      // 담당자 명
    private String noticeBgnde;        // 공고 시작일 (yyyyMMdd)
    private String noticeEndde;        // 공고 종료일 (yyyyMMdd)
    private String pbsvntPosblAt;      // 사전 봉사자 여부 (Y/N)
    private String postAdres;          // 주소
    private String progrmBgnde;        // 프로그램 시작일
    private String progrmEndde;        // 프로그램 종료일
    private String progrmCn;           // 프로그램 내용 (HTML 인코딩 있음)
    private String progrmRegistNo;     // 프로그램 등록 번호
    private String progrmSj;           // 프로그램 제목
    private String progrmSttusSe;      // 프로그램 상태 코드
    private String rcritNmpr;          // 모집 인원
    private String sidoCd;             // 시도 코드
    private String srvcClCode;         // 서비스 분류
    private String telno;              // 전화번호
    private String yngbgsPosblAt;      // 청소년 가능 여부 (Y/N)
    private String url; //신청 url
}
