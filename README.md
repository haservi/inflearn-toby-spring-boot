# 토비의 스프링 부트 - 이해와 원리

학습 시작일: 2023.07.11  
학습 종료일: 2023.08.08

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
  -> [관련 링크](https://stackoverflow.com/questions/73828590/i-o-error-on-get-in-testresttemplate-getforentity-method)

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

  조금 더 찾아보니 스프링부트에 [이슈](https://github.com/spring-projects/spring-boot/issues/33044)로
  있으며, `build.gradle`에서
  ``` bash
  ext['jakarta-servlet.version'] = '5.0.0' 
  ```
  를 명시적으로 써주면 된다고하여 수정했다.


- 버전이 안맞는
  문제 [링크](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#jetty)

- Hikari-cp 설정이 잘안되는 현상
  ```bash
  implementation('com.zaxxer:HikariCP:4.0.3') // 이렇게 하니 잘됨..
  ```

### 환경 설정을 통해 env 우선 순위를 정할 수 있다.

![image](./images/image01.png)

위와 같이 설정한 경우 기존 `application.properties`보다 더 높은 우선순위를 지정할 수 있다.  
VM Option > Environment variables > application.properties 순으로 우선순위가 정해진다.

### 기존 스프링 자동 구성 확인

설정의 add VM option 에서  `-Ddebug`를 정의하면 프로젝트를 실행하면 무언가 나온다.


### 추가 팁

- gradle 라이브러리 디펜더시 확인
  ``` bash
  ./gradlew dependencies --configuration compileClasspath
  ```
- [스프링 부트 스타터 공식 문서](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#using.build-systems.starters)
- 코드에서 `Comaparator.java` 인터페이스를 보면 좋다고 함
- 스프링 부트에서 운영환경의 모니터링, 관리 방법도 있다고 함
