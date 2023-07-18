package tobyspring.helloboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {

}

class HelloServiceTest {

  @FastUnitTest
  void simple_hello_service() {
    // Given
    SimpleHelloService helloService = new SimpleHelloService();
    // When
    String response = helloService.sayHello("Test");
    // Then
    assertThat(response).isEqualTo("Hello Test");
  }

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
