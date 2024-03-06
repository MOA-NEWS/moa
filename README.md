# MOA




## Version1

### ✨ 프로젝트 설명
1. 성능 비교 분석을 위한 게시판
2. 시큐리티를 적용한 회원관리

### 📌 목표
- JPA와 프로시저의 이해
- 프로시저 성능 분석


### Report
[산출물(Stored_Procedure&JPA, 개발환경, ERD)]  
<br>
<img src="https://github.com/MOA-NEWS/moa/assets/98940420/02f73055-55c4-4e82-abfd-cb4d71703561" width="50%" height="50%">  
> 왼쪽 부터 JPA /	Spring Data JPA	/ Stored procedure 순서, Scouter 모니터링 결과(게시판 1000개 호출)<br>
> 미세하게 스토어드 프로시저가 안정감있다  

<br><br>

#### 분석 요약
데이터베이스와 한 번 연결하고 작업을 여러 번 반복하는 작업과
연결된 후 자주 사용하지 않지만 복잡한 데이터 처리에 적합하다
MySQL의 특성도 알아야 한다. 타 데이터베이스에 비해 연산에 대한 최적화가 좋지 않고
MySQL은 프로시저를 컴파일 하고 재사용하지 않는다
다른 RDBMS는 최초 컴파일 후에 보관하고 프로시저 호출시 재사용 한다



## Version2(진행중)

### ✨ 프로젝트 설명
1. 모아타운과 관련한 정보(뉴스, 진행현황)를 주기적으로 수집

### 📌 목표
- 파일처리
- Python으로 데이터 수집하여 DB에 저장
- Version1에서 활용하지 못한 Scouter(APM) 사용
- 부하테스트로 쿼리 성능 개선














[산출물(Stored_Procedure&JPA, 개발환경, ERD)]: https://github.com/MOA-NEWS/moa/tree/master/Version1_report
