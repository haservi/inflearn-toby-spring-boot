# 토비의 스프링 부트 - 이해와 원리

학습 시작일: 2023.07.11  
학습 종료일:

인프런 강의 중에 토비님의 강의를 들으면서 학습한 코드를 정리한 저장소입니다.

기존 강의에서는 Spring Boot 2.7과 jdk 11로 했지만 학습 코드는 Spring Boot 3.1.1과 jdk 17로 적용했습니다.

강의에서 나온 mac에서 쓸만한 프로그램

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
-spring boot 3.1.1을 적용하면서 `@RequestMapping`이 있어도 스프링 컨트롤러로 인식하지 않는 현상이 있다.  
`@RestController`, `@Controller` 애노테이션이 있어야 스프링 컨트롤러로 인식한다.
  - 스프링 boot 3.0부터는 클래스 레벨의 핸들러 감지 대상이 `@Controller`만 포함 됨
  - [Github Issue](https://github.com/spring-projects/spring-framework/issues/22154)
  - [변경 사항](https://github.com/spring-projects/spring-framework/commit/3600644ed1776dce35c4a42d74799a90b90e359e)
