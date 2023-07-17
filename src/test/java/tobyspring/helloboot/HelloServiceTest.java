package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class HelloServiceTest {

  @Test
  void simple_hello_service() {
    // Given
    SimpleHelloService helloService = new SimpleHelloService();
    // When
    String response = helloService.sayHello("Test");
    // Then
    assertThat(response).isEqualTo("Hello Test");
  }

}
