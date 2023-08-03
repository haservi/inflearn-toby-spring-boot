package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {


  @Test
  void simple_hello_service() {
    // Given
    SimpleHelloService helloService = new SimpleHelloService(helloRepositoryStub);
    // When
    String response = helloService.sayHello("Test");
    // Then
    assertThat(response).isEqualTo("Hello Test");
  }

  private static HelloRepository helloRepositoryStub = new HelloRepository() {
    @Override
    public Hello findHello(String name) {
      return null;
    }

    @Override
    public void increaseCount(String name) {

    }
  };

  @Test
  void hello_decorator() {
    // Given
    HelloDecorator decorator = new HelloDecorator(name -> name);
    // When
    String response = decorator.sayHello("Test");
    // Then
    Assertions.assertThat(response).isEqualTo("*Test*");
  }

}
