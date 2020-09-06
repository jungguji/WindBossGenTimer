# WindBossGenTimer

바람의나라 연 보스 별 젠 타이머

* * *

<br />
<br />

## 목차

- [WindBossGenTimer](#windbossgentimer)
  - [목차](#목차)
  - [목표 기능](#목표-기능)
    - [메인](#메인)
    - [채널 클릭 시](#채널-클릭-시)
  - [예상 화면](#예상-화면)
    - [메인](#메인-1)
  - [DB 구조](#db-구조)
  - [적용 기술](#적용-기술)

* * *

<br />
<br />

## 목표 기능

<br />

### 메인

1. 네이게이션 바에 home 버튼과 로그인 버튼 존재
   1. home 클릭 시 main 하면으로 이동
   2. 로그인은 oauth 2.0 방식으로 소셜 로그인 가능(google, naver 등)
2. content area엔 DB에 저장된 던전 list를 네모 모양으로 나열해서 보여줌
   1. 로그인 시 해당 유저가 저장한 던전만 보여줌
   2. '+' 버튼을 클릭해서 던전 추가 가능
3. 던전 박스를 클릭하면 바람연 채널 선택 화면처럼 채널을 list로 보여줌

### 채널 클릭 시

1. 채널 클릭 시 그 채널과 던전에 해당하는 보스의 남은 젠 시간을 보여줌
   1. ex) 힘쎈 전갈 - 02 : 35
   2. 비 로그인시 db에 저장된 값들 중 최빈값을 보여줌
   3. 로그인시 해당 유저가 저장한 젠 타임 값을 보여주고 아닌 경우 1과 마찬가지로 최빈값을 보여줌
2. 로그인 한 경우 해당 보스 row를 더블클릭하면 해당 보스를 죽인 시간을 업데이트 할 수 있음
   1. 죽인 시간을 업데이트하면 그 시간에 맞춰 다시 젠 타임을 계산해서 보여줌
   2. 죽인 시간에 저장되지 않은 채널은 빈 값을 보여줌

* * *
<br />
<br />

## 예상 화면

<br />

### 메인

![메인화면](backend/src/main/resources/static/images/mainview.PNG)

* * *
<br />
<br />

## DB 구조

![DB구조](backend/src/main/resources/db/WindBossGenTimer_20200903.PNG)

* * *

## 적용 기술

- Java 8
- Junit 5
- Spring boot
- Hibernate
- Vue.js
- MaraiDB
- H2 DB
- Travis CI
- Codacy
- AWS