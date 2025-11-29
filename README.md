# 기봉사 – 기부와 봉사를 더 가깝게
# ![기봉사 판넬](https://github.com/user-attachments/assets/9d2e48de-7a73-4ee8-8b90-3f552e5cce3c)

> UNITHON 2025 인천대 21팀으로 진행한 팀 프로젝트입니다.

기봉사는 **기부와 봉사활동의 접근성을 쉽고 간편하게** 만드는 것을 목표로 한 모바일 앱입니다.  
사용자는 주변 지역아동센터를 대상으로

- 물품 기부
- 금액 기부 
- 봉사활동 조회

를 한 곳에서 편하게 이용할 수 있습니다.

기존 정부 공식 봉사앱이 가진 복잡한 UI/UX를 개선하고, 
“기부금이 실제로 어떻게 처리되는지 믿을 수 있을까?”라는 의문을  
블록체인 기반 기부 기록과 기부 인증서로 해결하는 것이 핵심 방향입니다.


##  주요 기능


### 1) 봉사활동 조회

- 카카오을 사용해 주변 봉사활동 위치를 시각적으로 표시
- 기존 텍스트 위주의 목록보다 직관적인 UI 제공
- 봉사 종류, 날짜, 지역 등으로 필터링하여 검색 가능

### 2) 물품 기부

- 지역아동센터가 필요한 물품 목록을 등록 / 수정 / 삭제
- 사용자는 원하는 센터를 선택하고 물품을 기부 신청
- 센터 관리자가 기부 신청을 승인/거절할 수 있는 관리 화면 제공

### 3) 금액 기부 

- 사용자가 선택한 지역아동센터에 금액을 기부 신청
- 센터 관리자가 기부를 최종 승인하는 시점에, 기부 내역(센터, 기부자, 금액, 시각 등)을 블록체인에 기록
- 사용자는 자신의 기부 내역에 연결된 블록체인 트랜잭션을 통해 기부가 실제로 확정·기록되었다는 사실을 검증할 수 있음
- 이 정보를 기반으로 기부 증명서를 제공하여 기부의 투명성과 신뢰성을 강화

## 기술스택

![KakaoTalk_20250627_232726161](https://github.com/user-attachments/assets/783925b3-ea79-4b04-8ca0-1379f2444b9e)


## 나의 역할 (Backend)

- 지속 가능한 사회문제를 주제로 한 기부·봉사 매칭 플랫폼 ‘기봉사’의 백엔드 및 블록체인 파트 담당
- Spring Boot + Spring Security 기반 백엔드 아키텍처 설계
- JWT + 카카오 OAuth2 로그인 인증 플로우 구현
- 기부·봉사 도메인을 중심으로 한 핵심 엔티티(DB 스키마) 설계 및 모델링(MySQL)
- 기부·봉사 요청 생성, 승인, 매칭, 조회를 위한 REST API 설계 및 구현
- Solidity 스마트 컨트랙트를 이용해 금액 기부 내역을 블록체인에 기록하는 로직 구현
- 블록체인 트랜잭션 정보를 활용한 기부 인증(증명) 기능 기획 및 백엔드 연동


## 시연영상

## 봉사활동

가벼운 UI/UX로 사용자가 봉사활동을 편하게 찾아 볼 수 있습니다

![KakaoTalk_20250626_212548201-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/d8062ba2-a916-4651-a2aa-fc29e8a8190e)

![KakaoTalk_20250626_212548201-ezgif com-video-to-gif-converter (1)](https://github.com/user-attachments/assets/735d7859-f890-41d4-b4ae-3374ee8d146e)


## 물품기부

지역아동센터가 기부물품 목록을 추가, 수정, 삭제가 가능합니다

![KakaoTalk_20250626_212605353-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/3ed2ab58-781c-4e0e-a750-f32618433b44)

사용자가 지역아동센터에 뭂품을 기부할 수 있습니다

![KakaoTalk_20250626_212605353-ezgif com-video-to-gif-converter (1)](https://github.com/user-attachments/assets/8ea8dded-d721-4347-9a76-e8a17135274c)




## 금액기부

사용자가 지역아동센터에 블록체인을 기반으로 전달 할 수 있습니다

![KakaoTalk_20250626_212706793-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/8021acef-4fb3-4be9-891c-d644e8d61df2)

지역아동센터가 사용자의 기부금을 승인할 수 있습니다

![KakaoTalk_20250626_212706793-ezgif com-video-to-gif-converter (1)](https://github.com/user-attachments/assets/c44c2cbe-ec98-4a08-85fd-dc396fd6c44c)

# 🏃Members

|                                                                        FE                                                                        |                                                                         FE                                                                          |                                                                         FE                                                                         |                                                                          BE                                                                           |
| :----------------------------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------: |
| <a href="https://github.com/optshj"><img src="https://avatars.githubusercontent.com/u/105402944?v=4" alt="profile" width="140" height="140"></a> | <a href="https://github.com/jhlee-inu"><img src="https://avatars.githubusercontent.com/u/197494172?v=4" alt="profile" width="140" height="140"></a> | <a href="https://github.com/gyuri224"><img src="https://avatars.githubusercontent.com/u/176257557?v=4" alt="profile" width="140" height="140"></a> | <a href="https://github.com/RYUJEONGHUN"><img src="https://avatars.githubusercontent.com/u/192004734?v=4" alt="profile" width="140" height="140"></a> |
|                                                       [이성빈](https://github.com/optshj)                                                        |                                                       [이정환](https://github.com/jhlee-inu)                                                        |                                                       [김규리](https://github.com/gyuri224)                                                        |                                                       [류정훈](https://github.com/RYUJEONGHUN) 

