# 토비의 스프링 부트 - 이해와 원리

학습 시작일: 2023.07.11  
학습 종료일:

인프런 강의 중에 토비님의 강의를 들으면서 학습한 코드를 정리한 저장소입니다.

기존 강의에서는 Spring Boot 2.7과 jdk 11로 했지만 학습 코드는 Spring Boot 3.1.1과 jdk 17로 적용했습니다.

### 강의에서 나온 프로그램 추천

- [sdkman](https://sdkman.io/): jdk 및 여러 sdk들의 버전을 쉽게 변경할 수 있도록 해준다.
- [Wrap](https://www.warp.dev/): AI가 내장된 터미널(다양한 기능 있음)
- [httpie](https://httpie.io/cli): 터미널에서 간단한 api 응답 요청이 가능하다.
  ```
  http -v ":8080/hello?name=Spring
  ```

Code Style은 `intellij-java-google-style.xml`로 적용했습니다.  
관련 [링크](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)입니다.

### 강의 클론 코딩 중 발생한 이슈

버전이 달라서 발생한 문제

- spring boot 3.1.1을 적용하면서 `@RequestMapping`이 있어도 스프링 컨트롤러로 인식하지 않는 현상이 있다.  
  `@RestController`, `@Controller` 애노테이션이 있어야 스프링 컨트롤러로 인식한다.
    - 스프링 boot 3.0부터는 클래스 레벨의 핸들러 감지 대상이 `@Controller`만 포함 됨
    - [Github Issue](https://github.com/spring-projects/spring-framework/issues/22154)
    - [변경 사항](https://github.com/spring-projects/spring-framework/commit/3600644ed1776dce35c4a42d74799a90b90e359e)

- API를 통한 테스트 중 port가 안맞는 문제
  ``` bash
  org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://localhost:8080/hello": Connection refused
  ... 
  ```
  와 같은 에러 발생하여 테스트 클래스 상단에 `@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)` 어노테이션
  추가    
  -> [관련링크](https://stackoverflow.com/questions/73828590/i-o-error-on-get-in-testresttemplate-getforentity-method)

- jetty를 config로 지정할 때 아래와 같은 에러가 발생한 경우
  ``` bash
  ...
  at tobyspring.helloboot.HelloBootApplication.main(HelloBootApplication.java:15) ~[classes/:na]
  Caused by: java.lang.NoClassDefFoundError: jakarta/servlet/http/HttpSessionContext
  at org.eclipse.jetty.servlet.ServletContextHandler.newSessionHandler(ServletContextHandler.java:339) ~[je
  ...
  ```
  spring-boot-starter-jetty 의존성 관련된 문제로 판단된다. Spring Boot 3에서는 Jakarta Servlet 6.0.0이고,
  spring-boot-starter-jetty는 Jakarta Servlet 5.0.0이라 발생하는
  문제로 [판단](https://stackoverflow.com/questions/74946784/java-lang-classnotfoundexception-jakarta-servlet-http-httpsessioncontext-with-s)
  일단 해결하기 위해서 `build.gradle`에 종속성 하나를 더 추가해줬다.
  ``` bash
  implementation group: 'org.eclipse.jetty', name: 'jetty-servlet', version: '11.0.15'
  ```

- 버전이 안맞는
  문제 [링크](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#jetty

### 추가 팁

- gradle 라이브러리 디펜더시 확인
  ``` bash
  ./gradlew dependencies --configuration compileClasspath
  ```
- [스프링 부트 스타터 공식 문서](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters)
