## 기술 스택
- java 17, springboot, mustache, gradle, jpa


## 실행 방법
- java 17 설치
- 커맨드창/콘솔에서 java -jar anchoreerChat.jar
- 또는 이클립스 혹은 intellij 등 툴에서 프로젝트를 받은 다음 run
- 브라우저에서 http://localhost:8080/ 접속

## URL 정보
- 루트/채팅방 목록 URL =  http://localhost:8080/
- H2DB 콘솔 URL = http://localhost:8080/h2-console
- H2DB 콘솔 접속은 기본세팅되어 있어 하단의 Connect 버튼을 누르면 접속된다.

## 개발 과정 중 필요한 가정
- 사용자는 키보드와 마우스로 유효한 액션을 취한다.
- 브라우저별 혹은 탭별로 별개의 사용자/디바이스로 가정한다.