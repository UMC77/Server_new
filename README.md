# 니빵내빵 Server

# 프로젝트 소개
UMC 2기 니빵내빵 프로젝트의 Server 팀입니다.

📌니빵내빵은 전국 모든 빵순이와 빵돌이들이 원하는 시간, 원하는 장소에서 빵을 행복하게 먹을 수 있는 그날까지 전국 빵집의 실시간 데이터를 업데이트하고, 부가적인 서비스를 제공해드리는 서비스 입니다.

### 주요기능

🍞 빵의 종류(식사 빵/비건/크루아상/케이크/파이 등) 선택 기능(빵 종류 모아보기) 
 : 특정 빵을 원하는 경우, 기존의 배달 앱(배민, 쿠팡이츠)은 쉽게 찾을 수 없다는 단점을 보완한 기능
 

🍞 빵집 위치 기능(원하는 빵집을 찜할 수 있는 기능) 
: 사용자의 위치에서 빵집까지의 거리가 얼마나 걸리는지 파악하는 기능

🍞 빵집 알림 설정 및 예약 기능
: 어떤 빵이 몇 시에 나오는지 확인 및 알림 설정 가능 + 예약 후, 방문 시간에 맞춰 픽업할 수 있는 기능

🍞 빵집 리뷰 기능 
: 다른 소비자들이 볼 수 있도록 정보를 공유하는 기능



### 팀원 소개

설다경 (dakyommii)

윤지인 (JuliaYun1129)

이주은 (dlwndms0812)

# 기술 스택

<img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=C%2B%2B&logoColor=white"/></a>

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=C%2B%2B&logoColor=white"/></a>

<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=flat-square&logo=C%2B%2B&logoColor=white"/></a>

<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=C%2B%2B&logoColor=white"/></a>

# 구현 기능

### 웹 (판매자 공간)
- 웹 사용자 회원가입, 로그인
- 가게 정보 등록, 수정
- 가게 정보 조회
- 메뉴 카테고리 추가, 메뉴 등록
- 전체 메뉴, 수정할 메뉴 조회
- 카테고리 조회, 메뉴 개수 조회
- 메뉴 검색
- 메뉴, 메뉴 판매여부, 카테고리명 수정
- 메뉴 삭제
- 예약 조회, 상세 예약 조회
- 리뷰 조회, 답변 상태별 리뷰 조회
- 리뷰 답변 등록 및 수정
- 리뷰 답변 삭제

### 앱 (주문자 공간)
- 앱 사용자 회원가입, 로그인
- 앱 사용자 비밀번호, 닉네임, 프로필 변경
- 예약, 예약 조회
- 리뷰 입력, 리뷰 조회
- 찜, 찜 조회
- 검색

# UML

![umc_bread.png](https://github.com/UMC77/Server_new/blob/main/umc_bread.png)

# API 명세서
[API 명세서 바로가기](https://docs.google.com/spreadsheets/d/1ukqpz-uhL1GAEvm_u8lrGBq-Kcody0zct7Dn8fWv8M0/edit?usp=sharing)

# 배운 점 및 아쉬운 점

- API 명세서를 작성하여 개발자들끼리 공유한 것은 정말 효과적이었음. 오류 처리에 관한 Base Status인 BaseResponseStatus도 공유 문서를 통해 정리했으면 좋았을 것 같은 아쉬움이 남음
