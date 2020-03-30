# 커뮤니티 사이트

## 목차
- [프로젝트 목표](#프로젝트-목표)
- [프로젝트 구조](#프로젝트-구조)
- [API 명세](#api-명세)
- [구현](#구현)


# 프로젝트 목표
- MAS(Micro Service Architecture) 이해
- Scale Out 가능한 구조의 설계 및 구현
- Spring Security 이해
- OAuth2를 이용한 token 기반 인증 설계 및 구현
- Spring Data JPA를 이용한 ORM 방식 구현
- H2데이터 베이스를 이용한 개발 및 Oracle로의 전환


# 프로젝트 구조
<img src="/resource/project-structure.png" />

##### Gateway
- Spring Security OAuth2인증
- Ribbon 로드 밸런싱
- Hystrix circuit breaker
- Zuul

##### Micro Service
- REST API
- 회원(Member) 및 게시물(TimeLine) API
- Rabbit을 이용한 이벤트 처리
- RestTemplate을 이용한 DDD(Domain Driven Design)

##### Service-Registry
- Eureka를 통한 Service관리

##### UI server
- [Client Repository](https://github.com/open-msa-service/open-msa-web-client)로 이동


# API 명세
- [GateWay](https://github.com/open-msa-service/open-msa-web-server/tree/master/msa_gateway)
- [Member API](https://github.com/open-msa-service/open-msa-web-server/tree/master/msa_member)
- [TimeLine API](https://github.com/open-msa-service/open-msa-web-server/tree/master/msa_timeline)


# 구현
#### 시연영상
[![시연영상](http://img.youtube.com/vi/xfzPemVeXJs/0.jpg)](http://www.youtube.com/watch?v=xfzPemVeXJs "시연영상")


#### 이벤트 설계
<img src="/resource/register.png" />
<img src="/resource/modify.png" />


#### REST Client
<img src="/resource/timeline.png" />
